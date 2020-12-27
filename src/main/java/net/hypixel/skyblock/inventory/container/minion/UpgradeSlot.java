package net.hypixel.skyblock.inventory.container.minion;

import net.hypixel.skyblock.items.minion.UpgradeItem;
import net.hypixel.skyblock.tags.ModItemTags;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

/**
 * A {@link Slot} for {@link AbstractMinionContainer} that holds
 * {@link UpgradeItem}
 *
 * @author MrPineapple070
 * @version 08 November 2020
 * @since 08 November 2020
 */
public class UpgradeSlot extends Slot {
	public UpgradeSlot(IInventory inventoryIn, int index, int yPosition) {
		super(inventoryIn, index, 84, yPosition);
	}

	@Override
	public int getItemStackLimit(ItemStack stack) {
		return 1;
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		return stack.getItem().isIn(ModItemTags.upgrade);
	}
}