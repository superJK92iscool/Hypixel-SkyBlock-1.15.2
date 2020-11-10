package net.hypixel.skyblock.items.armor.lapis;

import java.util.List;

import net.hypixel.skyblock.items.ModItemRarity;
import net.hypixel.skyblock.items.armor.ModArmorItem;
import net.hypixel.skyblock.items.armor.ModArmorMaterial;
import net.hypixel.skyblock.util.ColorCodes;
import net.hypixel.skyblock.util.ItemProperties;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

/**
 * A class for all Lapis Armor pieces.
 *
 * @author MrPineapple070
 * @version 18 May 2020
 */
public class LapisSet extends ModArmorItem {
	public LapisSet(EquipmentSlotType slot) {
		super(ModArmorMaterial.Lapis, slot, ItemProperties.m1, ModItemRarity.Uncommon);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new StringTextComponent(
				ColorCodes.green + "+200% " + ColorCodes.gray + "Bonus Experience when mining ores.\n" + ColorCodes.gold
						+ "Full Set Bonus: Health\n" + ColorCodes.gray + "Increases the wearer's " + ColorCodes.dark_red
						+ "Health " + ColorCodes.gray + "by 60."));
	}
}
