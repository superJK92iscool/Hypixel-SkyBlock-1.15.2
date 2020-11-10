package net.hypixel.skyblock.items.armor.miner;

import java.util.List;

import net.hypixel.skyblock.HypixelSkyBlockMod;
import net.hypixel.skyblock.items.ModItemRarity;
import net.hypixel.skyblock.items.armor.ModArmorItem;
import net.hypixel.skyblock.util.ItemProperties;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class MinerLeggings extends ModArmorItem {
	public MinerLeggings() {
		super(MinerSet.instance.getMaterial(), EquipmentSlotType.LEGS, ItemProperties.m1, ModItemRarity.Rare);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		try {
			tooltip.addAll(MinerSet.instance.getDescription(this.slot));
		} catch (final IllegalAccessException e) {
			HypixelSkyBlockMod.LOGGER.error(e.getMessage());
		}
	}
}