package net.hypixel.skyblock.items.armor.pack;

import java.util.List;

import net.hypixel.skyblock.items.armor.ModArmorItem;
import net.hypixel.skyblock.util.FormatingCodes;
import net.hypixel.skyblock.util.ItemProperties;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

/**
 * <a href=
 * "https://hypixel-skyblock.fandom.com/wiki/Armor_of_the_Pack#Helmet">Pack
 * Helmet</a>
 *
 * @author MrPineapple070
 * @version 18 December 2019
 * @since 11 October 2019
 */
public class PackHelmet extends ModArmorItem {

	public PackHelmet() {
		super(Pack.material, EquipmentSlotType.HEAD, ItemProperties.c1, Pack.rarity);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new StringTextComponent(
				FormatingCodes.red + "Health" + FormatingCodes.gray + ": " + Pack.helmet + "\n" + Pack.fsb));
	}

}
