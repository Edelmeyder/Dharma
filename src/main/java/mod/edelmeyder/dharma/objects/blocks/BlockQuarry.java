package mod.edelmeyder.dharma.objects.blocks;

import mod.edelmeyder.dharma.init.ModTileEntityTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

import java.util.stream.Stream;

public class BlockQuarry extends Block {

    private static final VoxelShape SHAPE = Stream.of(
            Block.makeCuboidShape(7, 0, 7, 9, 1, 9),
            Block.makeCuboidShape(6, 1, 6, 10, 2, 10),
            Block.makeCuboidShape(5, 2, 5, 11, 3, 11),
            Block.makeCuboidShape(4, 3, 4, 12, 4, 12),
            Block.makeCuboidShape(3, 4, 3, 13, 5, 13),
            Block.makeCuboidShape(2, 5, 2, 14, 6, 14),
            Block.makeCuboidShape(1, 6, 1, 15, 7, 15),
            Block.makeCuboidShape(0, 7, 0, 16, 11, 16),
            Block.makeCuboidShape(0, 0, 0, 1, 7, 1),
            Block.makeCuboidShape(0, 0, 15, 1, 7, 16),
            Block.makeCuboidShape(15, 0, 15, 16, 7, 16),
            Block.makeCuboidShape(15, 0, 0, 16, 7, 1),
            Block.makeCuboidShape(2, 11, 2, 7, 14, 7),
            Block.makeCuboidShape(3, 14, 5, 4, 16, 6),
            Block.makeCuboidShape(10, 11, 1, 13, 12, 15),
            Block.makeCuboidShape(11, 12, 2, 12, 13, 3),
            Block.makeCuboidShape(11, 12, 5, 12, 13, 6),
            Block.makeCuboidShape(11, 12, 10, 12, 13, 11),
            Block.makeCuboidShape(11, 12, 13, 12, 13, 14),
            Block.makeCuboidShape(1, 11, 9, 2, 12, 15),
            Block.makeCuboidShape(3, 11, 9, 4, 12, 15),
            Block.makeCuboidShape(5, 11, 9, 6, 12, 15),
            Block.makeCuboidShape(7, 11, 9, 8, 12, 15))
            .reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    public BlockQuarry(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return ModTileEntityTypes.QUARRY.get().create();
    }
}
