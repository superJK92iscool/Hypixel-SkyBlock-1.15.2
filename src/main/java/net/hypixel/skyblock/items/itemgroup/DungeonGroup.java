package net.hypixel.skyblock.items.itemgroup;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class DungeonGroup extends ItemGroup {
	public static final DungeonGroup instance = new DungeonGroup(ItemGroup.GROUPS.length, "Dungeon");

	public DungeonGroup(int index, String label) {
		super(index, label);
	}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(Items.DRAGON_EGG);
	}
}