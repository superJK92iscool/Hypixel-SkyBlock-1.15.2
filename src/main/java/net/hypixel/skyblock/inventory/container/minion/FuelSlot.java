package net.hypixel.skyblock.inventory.container.minion;

import net.hypixel.skyblock.items.minion.MinionFuelItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

/**
 * @author LaiHuy411846
 * @version 08 November 2020
 * @since 08 November 2020
 */
public class FuelSlot extends Slot {
	public FuelSlot(IInventory inventoryIn) {
		super(inventoryIn, 0, 84, 54);
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		return stack.getItem() instanceof MinionFuelItem;
	}
}