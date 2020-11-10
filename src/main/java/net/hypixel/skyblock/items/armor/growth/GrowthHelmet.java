package net.hypixel.skyblock.items.armor.growth;

import java.util.List;

import net.hypixel.skyblock.items.armor.ModArmorItem;
import net.hypixel.skyblock.util.ColorCodes;
import net.hypixel.skyblock.util.ItemProperties;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

/**
 * <a href=
 * "https://hypixel-skyblock.fandom.com/wiki/Armor_of_Growth#Helmet">Growth
 * Helmet</a>
 *
 * @author MrPineapple070
 * @version 18 December 2019
 * @since 11 June 2019
 */
public class GrowthHelmet extends ModArmorItem {
	public GrowthHelmet() {
		super(Growth.material, EquipmentSlotType.HEAD, ItemProperties.fg1, Growth.rarity);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new StringTextComponent(ColorCodes.red + "Health" + ColorCodes.gray + ": " + Growth.helmet));
	}

}
