package net.hypixel.skyblock.items.armor.mushroom;

import net.hypixel.skyblock.items.ModItemRarity;
import net.hypixel.skyblock.items.armor.FullSetInformation;
import net.hypixel.skyblock.items.armor.ModArmorMaterial;
import net.hypixel.skyblock.util.ColorCodes;
import net.minecraft.inventory.EquipmentSlotType;

/**
 * Holds {@link EquipmentSlotType} specific information for the
 * <a href="https://hypixel-skyblock.fandom.com/wiki/Mushroom_Armor">Mushroom
 * Armor Set</a>
 *
 * @author MrPineapple070
 * @version 09 August 2020
 * @since 09 August 2020
 */
public class MushroomSet implements FullSetInformation {
	public static final MushroomSet instance = new MushroomSet();

	public int boots_hp = 3;
	public int chestplate_hp = 2;
	public int helmet_hp = 4;
	public int leggings_hp = 1;

	public MushroomSet() {
	}

	@Override
	public double[] getBootsBuffs() throws IllegalAccessException {
		return new double[] { 0, 0, 0, this.boots_hp, 0, 0, 0 };
	}

	@Override
	public double[] getChestplateBuffs() throws IllegalAccessException {
		return new double[] { 0, 0, 0, this.chestplate_hp, 0, 0, 0 };
	}

	@Override
	public String getFullSetBonus() {
		return ColorCodes.gold + "Full Set Bonus: Night Affinity\n" + ColorCodes.gray
				+ "Grants the wearer permanent Night Vision.\n"
				+ "During the Night, the stats of the armor pieces are tripled.";
	}

	@Override
	public double[] getHelmetBuffs() throws IllegalAccessException {
		return new double[] { 0, 0, 0, this.helmet_hp, 0, 0, 0 };
	}

	@Override
	public double[] getLeggingsBuffs() throws IllegalAccessException {
		return new double[] { 0, 0, 0, this.leggings_hp, 0, 0, 0 };
	}

	@Override
	public ModArmorMaterial getMaterial() {
		return ModArmorMaterial.Mushroom;
	}

	@Override
	public ModItemRarity getRarity() {
		return ModItemRarity.Common;
	}
}