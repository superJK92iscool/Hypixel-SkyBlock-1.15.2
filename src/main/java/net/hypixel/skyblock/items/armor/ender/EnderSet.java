package net.hypixel.skyblock.items.armor.ender;

import net.hypixel.skyblock.items.ModItemRarity;
import net.hypixel.skyblock.items.armor.FullSetInformation;
import net.hypixel.skyblock.items.armor.ModArmorMaterial;
import net.minecraft.inventory.EquipmentSlotType;

/**
 * Holds {@link EquipmentSlotType} specific information for the
 * <a href="https://hypixel-skyblock.fandom.com/wiki/Ender_Armor">Ender
 * Armor</a>.
 *
 * @author MrPineapple070
 * @version 18 December 2019
 * @since 02 August 2019
 */
public class EnderSet implements FullSetInformation {
	public static int boots_def = 25;

	private static int boots_hp = 3;
	public static int chestplate_def = 60;
	private static int chestplate_hp = 6;
	public static int helmet_def = 35;

	private static int helmet_hp = 4;
	public static final EnderSet instance = new EnderSet();
	public static int leggings_def = 50;
	private static int leggings_hp = 5;

	public EnderSet() {
	}

	@Override
	public double[] getBootsBuffs() {
		return new double[] { 0, 0, 0, boots_hp, 0, 0, 0 };
	}

	@Override
	public double[] getChestplateBuffs() {
		return new double[] { 0, 0, 0, chestplate_hp, 0, 0, 0 };
	}

	@Override
	public String getFullSetBonus() {
		return "All stats of this armor are doubled while in The End.";
	}

	@Override
	public double[] getHelmetBuffs() throws IllegalAccessException {
		return new double[] { 0, 0, 0, helmet_hp, 0, 0, 0 };
	}

	@Override
	public double[] getLeggingsBuffs() {
		return new double[] { 0, 0, 0, leggings_hp, 0, 0, 0 };
	}

	@Override
	public ModArmorMaterial getMaterial() {
		return ModArmorMaterial.Ender;
	}

	@Override
	public ModItemRarity getRarity() {
		return ModItemRarity.Epic;
	}
}