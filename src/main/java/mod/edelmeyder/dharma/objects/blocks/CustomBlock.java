package mod.edelmeyder.dharma.objects.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.stream.Stream;

public class CustomBlock extends Block {
    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
    private static final VoxelShape SHAPE_N= Stream.of(
            Block.makeCuboidShape(12, 0, 3, 14, 4, 13),
            Block.makeCuboidShape(2, 0, 3, 4, 4, 13),
            Block.makeCuboidShape(4, 3, 3, 12, 4, 4),
            Block.makeCuboidShape(4, 2, 5, 12, 3, 6),
            Block.makeCuboidShape(4, 2, 9, 12, 3, 10),
            Block.makeCuboidShape(4, 1, 7, 12, 2, 8),
            Block.makeCuboidShape(4, 3, 7, 12, 4, 8),
            Block.makeCuboidShape(4, 3, 11, 12, 4, 12),
            Block.makeCuboidShape(4, 1, 11, 12, 2, 12),
            Block.makeCuboidShape(4, 1, 3, 12, 2, 4),
            Block.makeCuboidShape(4, 0, 5, 12, 1, 6),
            Block.makeCuboidShape(4, 0, 9, 12, 1, 10))
            .reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
    private static final VoxelShape SHAPE_E = Stream.of(
            Block.makeCuboidShape(3, 0, 12, 13, 4, 14),
            Block.makeCuboidShape(3, 0, 2, 13, 4, 4),
            Block.makeCuboidShape(12, 3, 4, 13, 4, 12),
            Block.makeCuboidShape(10, 2, 4, 11, 3, 12),
            Block.makeCuboidShape(6, 2, 4, 7, 3, 12),
            Block.makeCuboidShape(8, 1, 4, 9, 2, 12),
            Block.makeCuboidShape(8, 3, 4, 9, 4, 12),
            Block.makeCuboidShape(4, 3, 4, 5, 4, 12),
            Block.makeCuboidShape(4, 1, 4, 5, 2, 12),
            Block.makeCuboidShape(12, 1, 4, 13, 2, 12),
            Block.makeCuboidShape(10, 0, 4, 11, 1, 12),
            Block.makeCuboidShape(6, 0, 4, 7, 1, 12))
            .reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
    private static final VoxelShape SHAPE_S = Stream.of(
            Block.makeCuboidShape(2, 0, 2, 4, 4, 12),
            Block.makeCuboidShape(12, 0, 2, 14, 4, 12),
            Block.makeCuboidShape(4, 3, 12, 12, 4, 12),
            Block.makeCuboidShape(4, 2, 9, 12, 3, 12),
            Block.makeCuboidShape(4, 2, 5, 12, 3, 6),
            Block.makeCuboidShape(4, 1, 7, 12, 2, 8),
            Block.makeCuboidShape(4, 3, 7, 12, 4, 8),
            Block.makeCuboidShape(4, 3, 3, 12, 4, 4),
            Block.makeCuboidShape(4, 1, 3, 12, 2, 4),
            Block.makeCuboidShape(4, 1, 11, 12, 2, 12),
            Block.makeCuboidShape(4, 0, 9, 12, 1, 10),
            Block.makeCuboidShape(4, 0, 5, 12, 1, 6))
            .reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
    private static final VoxelShape SHAPE_W = Stream.of(
            Block.makeCuboidShape(3, 0, 2, 13, 4, 4),
            Block.makeCuboidShape(3, 0, 12, 13, 4, 14),
            Block.makeCuboidShape(3, 3, 4, 4, 4, 12),
            Block.makeCuboidShape(5, 2, 4, 6, 3, 12),
            Block.makeCuboidShape(9, 2, 4, 10, 3, 12),
            Block.makeCuboidShape(7, 1, 4, 8, 2, 12),
            Block.makeCuboidShape(7, 3, 4, 8, 4, 12),
            Block.makeCuboidShape(11, 3, 4, 12, 4, 12),
            Block.makeCuboidShape(11, 1, 4, 12, 2, 12),
            Block.makeCuboidShape(3, 1, 4, 4, 2, 12),
            Block.makeCuboidShape(5, 0, 4, 6, 1, 12),
            Block.makeCuboidShape(9, 0, 4, 10, 1, 12))
            .reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    public CustomBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.getStateContainer().getBaseState().with(FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (state.get(FACING)) {
            case SOUTH:
                return SHAPE_S;
            case WEST:
                return SHAPE_W;
            case EAST:
                return SHAPE_E;
            default:
                return SHAPE_N;
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote){
            ServerWorld serverWorld = (ServerWorld)worldIn;
            LightningBoltEntity entity = new LightningBoltEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), false);
            serverWorld.addLightningBolt(entity);
        }
        return ActionResultType.SUCCESS;
    }
}
