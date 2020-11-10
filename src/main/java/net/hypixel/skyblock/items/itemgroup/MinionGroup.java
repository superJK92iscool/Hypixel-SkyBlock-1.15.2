package net.hypixel.skyblock.items.itemgroup;

import net.hypixel.skyblock.init.blocks.MinionBlockInit;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class MinionGroup extends ItemGroup {
	public static final MinionGroup instance = new MinionGroup(ItemGroup.GROUPS.length, "Minions");

	private MinionGroup(int index, String label) {
		super(index, label);
	}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(MinionBlockInit.cobblestone_minion_1.get());
	}
}
