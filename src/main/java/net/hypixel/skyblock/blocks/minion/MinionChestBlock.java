package net.hypixel.skyblock.blocks.minion;

import java.util.Objects;

import javax.annotation.Nonnull;

import net.hypixel.skyblock.inventory.container.minion.MinionChestContainer.LargeMCC;
import net.hypixel.skyblock.inventory.container.minion.MinionChestContainer.MediumMCC;
import net.hypixel.skyblock.inventory.container.minion.MinionChestContainer.SmallMCC;
import net.hypixel.skyblock.tileentity.ModTileEntityTypes;
import net.hypixel.skyblock.tileentity.minion.AbstractMinionChestTileEntity;
import net.hypixel.skyblock.tileentity.minion.AbstractMinionChestTileEntity.SmallMCTE;
import net.minecraft.block.AbstractChestBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
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
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

/**
 * Serve as base for additional {@link AbstractMinionBlock} storage.<br>
 * <a href="https://hypixel-skyblock.fandom.com/wiki/Minion_Chests">Click
 * here</a> for a description.
 *
 * @author MrPineapple2020
 * @version 2 June 2020
 * @since 11 June 2019
 */
public abstract class MinionChestBlock extends AbstractChestBlock<AbstractMinionChestTileEntity> {
	/**
	 * Help with the logic handling of the different types of
	 * {@link MinionChestBlock}.
	 */
	public enum ChestType {
		/**
		 * Gives 3 extra slots.
		 */
		Small(3),
		/**
		 * Gives 9 extra slots.
		 */
		Medium(9),
		/**
		 * Gives 15 extra slots.
		 */
		Large(15),
		/**
		 * Gives 21 extra slots.
		 */
		XLarge(21),
		/**
		 * Gives 27 extra slots.
		 */
		XXLarge(27);

		/**
		 * The number of additional slots this will give
		 */
		public final int additional;

		private ChestType(int additional) {
			this.additional = additional;
		}
	}

	public static class SmallMCB extends MinionChestBlock {
		public SmallMCB(Properties properties) {
			super(properties, ChestType.Small);
		}
	}

	public static class MediumMCB extends MinionChestBlock {	
		public MediumMCB(Properties properties) {
			super(properties, ChestType.Medium);
		}
	}

	public static class LargeMCB extends MinionChestBlock {
		public LargeMCB(Properties properties) {
			super(properties, ChestType.Large);
		}
	}

	private static final ITextComponent container_name_s = new TranslationTextComponent("container.small_minion_chest");
	private static final ITextComponent container_name_m = new TranslationTextComponent("container.medium_minion_chest");
	private static final ITextComponent container_name_l = new TranslationTextComponent("container.large_minion_chest");
	//private static final ITextComponent container_name_xl = new TranslationTextComponent("container.x_large_minion_chest");
	//private static final ITextComponent container_name_xxl = new TranslationTextComponent("container.xx_large_minion_chest");

	/**
	 * {@link DirectionProperty}
	 */
	@Nonnull
	public static final DirectionProperty facing = HorizontalBlock.HORIZONTAL_FACING;

	/**
	 * {@link VoxelShape}
	 */
	@Nonnull
	protected static final VoxelShape shape = Block.makeCuboidShape(1, 0, 1, 15, 14, 15);

	/**
	 * The {@link ChestType} of {@code this}
	 */
	@Nonnull
	public final ChestType type;

	protected MinionChestBlock(Properties properties, @Nonnull ChestType type) {
		super(properties, () -> {
			switch (type) {
			case Small:
				return ModTileEntityTypes.small_mcte.get();
			case Medium:
				return ModTileEntityTypes.medium_mcte.get();
			case Large:
				return ModTileEntityTypes.large_mcte.get();
			default:
				throw new IllegalStateException("Illegal ChestType " + type.name());
			}
		});
		this.type = Objects.requireNonNull(type, "ChestType cannot be null.");
		this.setDefaultState(this.stateContainer.getBaseState().with(facing, Direction.NORTH));
	}
	
	@Override
	public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
		return false;
	}
	
	@Override
	public ICallbackWrapper<? extends ChestTileEntity> func_225536_a_(BlockState p_225536_1_, World p_225536_2_,
			BlockPos p_225536_3_, boolean p_225536_4_) {
		return TileEntityMerger.ICallback::func_225537_b_;
	}

	@Override
	@Deprecated
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn) {
		switch (this.type) {
		case Small:
			return ModTileEntityTypes.small_mcte.get().create();
		case Medium:
			return ModTileEntityTypes.medium_mcte.get().create();
		case Large:
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
	public boolean hasTileEntity(BlockState state) {
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
		SimpleNamedContainerProvider container;
		switch (this.type) {
		case Small:
			container = new SimpleNamedContainerProvider((id, inventory, playerIn) -> {
				return new SmallMCC(id, inventory);
			}, container_name_s);
			break;
		case Medium:
			container = new SimpleNamedContainerProvider((id, inventory, playerIn) -> {
				return new MediumMCC(id, inventory);
			}, container_name_m);
			break;
		case Large:
			container = new SimpleNamedContainerProvider((id, inventory, playerIn) -> {
				return new LargeMCC(id, inventory);
			}, container_name_l);
			break;
		default:
			throw new IllegalStateException("Illegal ChestType " + this.type.name());
		}

		TileEntity te = worldIn.getTileEntity(pos);
		if (te instanceof SmallMCTE) {
			BlockPos blockpos = pos.up();
			if (worldIn.getBlockState(blockpos).isNormalCube(worldIn, blockpos))
				return ActionResultType.PASS;
			else if (worldIn.isRemote)
				return ActionResultType.SUCCESS;
			else {
				player.openContainer(container);
				return ActionResultType.CONSUME;
			}
		} if (worldIn.isRemote)
			return ActionResultType.SUCCESS;
		return ActionResultType.PASS;
	}

	@Deprecated
	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.with(facing, rot.rotate(state.get(facing)));
	}
}