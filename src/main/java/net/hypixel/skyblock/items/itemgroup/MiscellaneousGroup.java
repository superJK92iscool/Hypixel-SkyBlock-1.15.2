package net.hypixel.skyblock.items.itemgroup;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class MiscellaneousGroup extends ItemGroup {
	public static final MiscellaneousGroup instance = new MiscellaneousGroup(ItemGroup.GROUPS.length, "Miscellaneous");

	public MiscellaneousGroup(int index, String label) {
		super(index, label);
	}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(Items.BEACON);
	}
}