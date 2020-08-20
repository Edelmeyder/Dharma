package mod.edelmeyder.dharma.objects.tileEntities;

import mod.edelmeyder.dharma.init.ModTileEntityTypes;
import mod.edelmeyder.dharma.util.helpers.NBTHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;

public class QuarryTileEntity extends TileEntity implements ITickableTileEntity {

    public int x, y, z, tick;
    boolean initialize = false;

    public QuarryTileEntity(final TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public QuarryTileEntity(){
        this(ModTileEntityTypes.QUARRY.get());
    }


    @Override
    public void tick() {
        if (!initialize) init();
        tick++;
        if (tick == 40) {
            tick = 0;
            if (y > 2) execute();
        }
    }

    private void init() {
        initialize = true;
        x = this.getPos().getX() - 1;
        y = this.getPos().getY() - 1;
        z = this.getPos().getZ() - 1;
        tick = 0;
    }

    private void execute(){
        int index = 0;
        Block[] blocksRemoved = new Block[9];
        for(int x = 0; x < 3; x++) {
            for (int z = 0; z < 3; z++) {
                BlockPos posToBreak = new BlockPos(this.x + x, this.y, this.z + z);
                blocksRemoved[index] = this.world.getBlockState(posToBreak).getBlock();
                destroyBlock(posToBreak, true, null);
                index++;
            }
        }
        this.y--;
    }

    private boolean destroyBlock(BlockPos pos, boolean dropBlock, @Nullable Entity entity) {
        BlockState state = world.getBlockState(pos);
        if (state.isAir(world, pos)) return false;
        else {
            IFluidState iFluidState = world.getFluidState(pos);
            world.playEvent(2001, pos, Block.getStateId(state));
            if (dropBlock) {
                TileEntity tileEntity = state.hasTileEntity() ? world.getTileEntity(pos) : null;
                Block.spawnDrops(state, world, this.pos.add(0, 1.5, 0), tileEntity, entity, ItemStack.EMPTY);
            }
            return world.setBlockState(pos, iFluidState.getBlockState(), 3);
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("initValues", NBTHelper.toNBT(this));
        return super.write(compound);
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        CompoundNBT initValues = compound.getCompound("initValues");
        if (initValues != null) {
            this.x = initValues.getInt("x");
            this.y = initValues.getInt("y");
            this.z = initValues.getInt("z");
            this.tick = 0;
            this.initialize = true;
            return;
        }
        init();
    }
}
