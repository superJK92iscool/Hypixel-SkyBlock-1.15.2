package net.hypixel.skyblock.blocks.minion;

import javax.annotation.Nonnull;

import net.hypixel.skyblock.tileentity.ModTileEntityTypes;
import net.hypixel.skyblock.tileentity.minion.AbstractMinionChestTileEntity;
import net.minecraft.block.AbstractChestBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMerger;
import net.minecraft.tileentity.TileEntityMerger.ICallbackWrapper;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

/**
 * Serve as base for additional {@link AbstractMinionBlock} storage.<br>
 * <a href="https://hypixel-skyblock.fandom.com/wiki/Minion_Chests">Click here
 * for a description.</a>
 *
 * @author MrPineapple2020
 * @version 2 June 2020
 */
public abstract class MinionChestBlock extends AbstractChestBlock<AbstractMinionChestTileEntity> {
	public static class SmallMCB extends MinionChestBlock {
		public SmallMCB(Properties properties) {
			super(properties, ChestType.SMALL);
		}
	}
	
	public static class MediumMCB extends MinionChestBlock {
		public MediumMCB(Properties properties) {
			super(properties, ChestType.MEDIUM);
		}
	}
	
	public static class LargeMCB extends MinionChestBlock {
		public LargeMCB(Properties properties) {
			super(properties, ChestType.LARGE);
		}
	}

	/**
	 * Help with the logic handling of the different types of {@link MinionChestBlock}.
	 */
	public enum ChestType {
		/**
		 * Gives 3 extra slots.
		 */
		SMALL(3),
		/**
		 * Gives 9 extra slots.
		 */
		MEDIUM(9),
		/**
		 * Gives 15 extra slots.
		 */
		LARGE(15),
		/**
		 * Gives 21 extra slots.
		 */
		XLARGE(21),
		/**
		 * Gives 27 extra slots.
		 */
		XXLARGE(27);

		/**
		 * The number of additional slots this will give
		 */
		public final int additional;

		private ChestType(int additional) {
			this.additional = additional;
		}
	}

	/**
	 * {@link DirectionProperty}
	 */
	public static final DirectionProperty facing = HorizontalBlock.HORIZONTAL_FACING;

	/**
	 * {@link VoxelShape}
	 */
	protected static final VoxelShape shape = Block.makeCuboidShape(1, 0, 1, 15, 14, 15);

	/**
	 * The {@link ChestType} of {@code this}
	 */
	public final ChestType type;

	protected MinionChestBlock(Properties properties, @Nonnull ChestType type) {
		super(properties, () -> {
			switch (type) {
			case SMALL:
				return ModTileEntityTypes.small_mcte.get();
			case MEDIUM:
				return ModTileEntityTypes.medium_mcte.get();
			case LARGE:
				return ModTileEntityTypes.large_mcte.get();
			default:
				throw new IllegalStateException("Illegal ChestType " + type.name());
			}
		});
		this.setDefaultState(this.stateContainer.getBaseState().with(facing, Direction.NORTH));
		this.type = type;
	}

	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn) {
		switch (this.type) {
		case SMALL:
			return ModTileEntityTypes.small_mcte.get().create();
		case MEDIUM:
			return ModTileEntityTypes.medium_mcte.get().create();
		case LARGE:
			return ModTileEntityTypes.large_mcte.get().create();
		default:
			throw new IllegalStateException("Illegal ChestType " + this.type.name());
		} 
	}

	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(facing);
	}

	@Override
	public ICallbackWrapper<? extends ChestTileEntity> func_225536_a_(BlockState blockState, World worldIn,
			BlockPos pos, boolean p_225536_4_) {
		return TileEntityMerger.ICallback::func_225537_b_;
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return shape;
	}

	/**
	 * @return {@link ChestType#additional}
	 */
	public int getSize() {
		return this.type.additional;
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(facing, context.getPlacementHorizontalFacing().getOpposite());
	}

	@Override
	public boolean hasTileEntity() {
		return true;
	}

	@Deprecated
	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.toRotation(state.get(facing)));
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult hit) {
		if (worldIn.isRemote) return ActionResultType.FAIL;
		final TileEntity te = worldIn.getTileEntity(pos);
		if (te instanceof AbstractMinionChestTileEntity) {
			NetworkHooks.openGui((ServerPlayerEntity) player, (AbstractMinionChestTileEntity) te, pos);
			return ActionResultType.SUCCESS;
		} return ActionResultType.FAIL;
	}

	@Deprecated
	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.with(facing, rot.rotate(state.get(facing)));
	}
}
