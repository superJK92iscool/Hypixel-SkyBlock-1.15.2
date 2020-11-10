package net.hypixel.skyblock.items.armor.pack;

import net.hypixel.skyblock.items.ModItemRarity;
import net.hypixel.skyblock.items.armor.ModArmorMaterial;
import net.hypixel.skyblock.util.ColorCodes;
import net.minecraft.inventory.EquipmentSlotType;

/**
 * Holds {@link EquipmentSlotType} specific information for the
 * <a href="https://hypixel-skyblock.fandom.com/wiki/Armor_of_the_Pack">Pack
 * Armor</a>
 *
 * @author MrPineapple070
 * @version 18 December 2019
 * @since 11 October 2019
 */
public class Pack {
	public static final int boots = 25;
	public static final int chestplate = 35;

	public static final String fsb = ColorCodes.gold + "Full Set Bonus: Armor of the Pack\n" + ColorCodes.gray
			+ "Gain +35 " + ColorCodes.dark_red + "Strength" + ColorCodes.gray + "and +80 " + ColorCodes.green
			+ "Defense" + ColorCodes.gray + "for each Armor of the Pack wearers within 30 blocks. Max of 3 players.";
	public static final int helmet = 29;
	public static final int leggings = 30;
	public static final ModArmorMaterial material = ModArmorMaterial.Pack;

	public static final ModItemRarity rarity = ModItemRarity.Epic;
}
