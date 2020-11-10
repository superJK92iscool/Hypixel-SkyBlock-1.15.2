package net.hypixel.skyblock.inventory.container.minion;

import javax.annotation.Nonnull;

import net.hypixel.skyblock.tileentity.minion.AbstractMinionTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;

/**
 * A base for all {@link Container} for all Minions.
 * <a href="https://hypixel-skyblock.fandom.com/wiki/Minions">Click here for a
 * description of Minions.</a>
 *
 * @author MrPineapple070
 * @version 3 July 2020
 */
public abstract class AbstractMinionContainer extends Container {
	/**
	 * The {@link IWorldPosCallable} of this.
	 */
	@Nonnull
	protected final IWorldPosCallable canInteractWithCallable;

	/**
	 * The {@link IInventory} of this.
	 */
	@Nonnull
	protected final IInventory inventory;

	/**
	 * The {@link AbstractMinionTileEntity} of this,
	 */
	@Nonnull
	protected final AbstractMinionTileEntity minion;

	/**
	 * Construct {@code this}
	 *
	 * @param type       the type.
	 * @param windowId   the unique window id.
	 * @param pInvIn     the player's inventory.
	 * @param tileEntity the {@link TileEntity} for {@code this}.
	 */
	protected AbstractMinionContainer(ContainerType<? extends AbstractMinionContainer> type, int windowId,
			PlayerInventory pInvIn, AbstractMinionTileEntity tileEntity) {
		super(type, windowId);
		this.minion = tileEntity;
		this.inventory = new Inventory(this.minion.minionContents.toArray(new ItemStack[this.minion.getSizeInventory()]));
		this.canInteractWithCallable = IWorldPosCallable.of(this.minion.getWorld(), this.minion.getPos());
		
		this.addSlot(new FuelSlot(this.minion));
		this.addSlot(new SellerSlot(this.minion));
		this.addSlot(new UpgradeSlot(this.minion, 2, 90));
		this.addSlot(new UpgradeSlot(this.minion, 3, 108));

		// Main Inventory
		int num = this.minion.getSizeInventory();
		for (int row = 0; row < 3; ++row)
			for (int col = 0; col < 5; ++col)
				if (num == 4)
					break;
				else {
					this.addSlot(new MinionSlot(this.minion, row, col));
					num--;
				}

		// Player Inventory
		for (int row = 0; row < 3; ++row)
			for (int column = 0; column < 9; ++column)
				this.addSlot(new Slot(pInvIn, 9 + row * 9 + column, 48 + column * 18, 138 + row * 18));

		// Player HotBar
		for (int column = 0; column < 9; ++column)
			this.addSlot(new Slot(pInvIn, column, 48 + column * 18, 196));
	}

	@Override
	public abstract boolean canInteractWith(PlayerEntity playerIn);

	@Override
	public void onContainerClosed(PlayerEntity playerIn) {
		super.onContainerClosed(playerIn);
		this.inventory.closeInventory(playerIn);
	}

	@Override
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		final Slot slot = this.inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) {
			final ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if (index < this.minion.getSizeInventory()) {
				if (!this.mergeItemStack(itemstack1, this.minion.getSizeInventory(), this.inventorySlots.size(), true))
					return ItemStack.EMPTY;
			} else if (!this.mergeItemStack(itemstack1, 0, this.minion.getSizeInventory(), false))
				return ItemStack.EMPTY;

			if (itemstack1.isEmpty())
				slot.putStack(ItemStack.EMPTY);
			else
				slot.onSlotChanged();
		}
		return itemstack;
	}
}