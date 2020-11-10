package net.hypixel.skyblock.inventory.container.minion;

import javax.annotation.Nonnull;

import net.hypixel.skyblock.init.blocks.BlockInit;
import net.hypixel.skyblock.inventory.container.ModContainerTypes;
import net.hypixel.skyblock.tileentity.minion.AbstractMinionChestTileEntity;
import net.hypixel.skyblock.tileentity.minion.AbstractMinionChestTileEntity.LargeMCTE;
import net.hypixel.skyblock.tileentity.minion.AbstractMinionChestTileEntity.MediumMCTE;
import net.hypixel.skyblock.tileentity.minion.AbstractMinionChestTileEntity.SmallMCTE;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IWorldPosCallable;

/**
 * Serve as a basis for all {@code MinionChestContainer}.<br>
 * <a href="https://hypixel-skyblock.fandom.com/wiki/Minion_Chests">Click here
 * for a discription of Minion Chests.</a>
 *
 * @author MrPineapple070
 * @version 12 June 2020
 */
public abstract class MinionChestContainer extends Container {
	public static class LargeMCC extends MinionChestContainer {
		public LargeMCC(int windowId, PlayerInventory pInvIn, AbstractMinionChestTileEntity tileEntity) {
			super(ModContainerTypes.large_mcc.get(), windowId, pInvIn, tileEntity);
		}

		public LargeMCC(int windowId, PlayerInventory pInvIn, PacketBuffer data) {
			this(windowId, pInvIn, new LargeMCTE());
		}
	}

	public static class MediumMCC extends MinionChestContainer {
		public MediumMCC(int windowId, PlayerInventory pInvIn, AbstractMinionChestTileEntity tileEntity) {
			super(ModContainerTypes.medium_mcc.get(), windowId, pInvIn, tileEntity);
		}

		public MediumMCC(int windowId, PlayerInventory pInvIn, PacketBuffer data) {
			this(windowId, pInvIn, new MediumMCTE());
		}
	}

	public static class SmallMCC extends MinionChestContainer {
		public SmallMCC(int windowId, PlayerInventory pInvIn, AbstractMinionChestTileEntity tileEntity) {
			super(ModContainerTypes.small_mcc.get(), windowId, pInvIn, tileEntity);
		}

		public SmallMCC(int windowId, PlayerInventory pInvIn, PacketBuffer data) {
			this(windowId, pInvIn, new SmallMCTE());
		}
	}

	/**
	 * The {@link IWorldPosCallable} for this.
	 */
	@Nonnull
	protected IWorldPosCallable canInteractWithCallable;

	/**
	 * The {@link AbstractMinionChestTileEntity} for this.
	 */
	@Nonnull
	public AbstractMinionChestTileEntity tileEntity;

	protected MinionChestContainer(ContainerType<? extends MinionChestContainer> typeIn, int windowId,
			PlayerInventory pInvIn, AbstractMinionChestTileEntity tileEntity) {
		super(typeIn, windowId);
		this.tileEntity = tileEntity;
		this.canInteractWithCallable = IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos());

		// Main Inventory
		int rowMin, rowMax, colMin, colMax;
		switch (this.tileEntity.getChestType()) {
		case SMALL:
			rowMin = 1;
			rowMax = 2;
			colMin = 1;
			colMax = 4;
			break;
		case MEDIUM:
			rowMin = 0;
			rowMax = 3;
			colMin = 1;
			colMax = 4;
			break;
		case LARGE:
			rowMin = 0;
			rowMax = 3;
			colMin = 0;
			colMax = 5;
			break;
		default:
			throw new IllegalStateException(
					"Illegal AbstractMinionChestTileEntity.Type " + this.tileEntity.getChestType().name());
		}

		for (int row = rowMin; row < rowMax; ++row)
			for (int col = colMin; col < colMax; ++col) {
				this.addSlot(new MinionChestSlot(this.tileEntity, row, col, rowMax));
			}

		// Player Inventory
		for (int row = 0; row < 3; ++row)
			for (int col = 0; col < 9; ++col)
				this.addSlot(new Slot(pInvIn, 9 + 9 * row + col, 48 + 18 * col, 129 + 18 * row));

		// Player HotBar
		for (int col = 0; col < 9; ++col)
			this.addSlot(new Slot(pInvIn, col, 48 + 18 * col, 187));
	}

	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		Block block;
		switch (this.tileEntity.getChestType()) {
		case SMALL:
		default:
			block = BlockInit.small_mcb.get();
		case MEDIUM:
			block = BlockInit.medium_mcb.get();
		case LARGE:
			block = BlockInit.large_mcb.get();
		}
		return isWithinUsableDistance(this.canInteractWithCallable, playerIn, block);
	}
}
