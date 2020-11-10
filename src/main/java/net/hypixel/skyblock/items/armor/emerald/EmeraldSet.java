package net.hypixel.skyblock.items.armor.emerald;

import net.hypixel.skyblock.items.ModItemRarity;
import net.hypixel.skyblock.items.armor.FullSetInformation;
import net.hypixel.skyblock.items.armor.ModArmorMaterial;
import net.hypixel.skyblock.util.ColorCodes;

public class EmeraldSet implements FullSetInformation {
	private static int boots = 0;

	private static int chestplate = 0;
	private static int helmet = 0;
	public static final EmeraldSet instance = new EmeraldSet();
	private static int leggings = 0;

	public EmeraldSet() {
	}

	@Override
	public double[] getBootsBuffs() {
		return new double[] { 0, 0, 0, boots, 0, 0, 0 };
	}

	@Override
	public double[] getChestplateBuffs() {
		return new double[] { 0, 0, 0, chestplate, 0, 0, 0 };
	}

	@Override
	public String getFullSetBonus() {
		return ColorCodes.gold + "Full Set Bonus: Tank\n" + ColorCodes.gray + "Increases the wearer's max "
				+ ColorCodes.red + "Health" + ColorCodes.gray + " and " + ColorCodes.green + "Defense" + ColorCodes.gray
				+ " by 1 for every 3000 Emeralds in your collection. Max 350 each.";
	}

	@Override
	public double[] getHelmetBuffs() throws IllegalAccessException {
		return new double[] { 0, 0, 0, helmet, 0, 0, 0 };
	}

	@Override
	public double[] getLeggingsBuffs() {
		return new double[] { 0, 0, 0, leggings, 0, 0, 0 };
	}

	@Override
	public ModArmorMaterial getMaterial() {
		return ModArmorMaterial.Emerald;
	}

	@Override
	public ModItemRarity getRarity() {
		return ModItemRarity.Epic;
	}
}