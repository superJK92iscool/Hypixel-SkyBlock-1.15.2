package net.hypixel.skyblock.items.armor.protector_dragon;

import net.hypixel.skyblock.items.ModItemRarity;
import net.hypixel.skyblock.items.armor.FullSetInformation;
import net.hypixel.skyblock.items.armor.ModArmorMaterial;
import net.hypixel.skyblock.util.ColorCodes;
import net.minecraft.inventory.EquipmentSlotType;

/**
 * Holds {@link EquipmentSlotType} specific information for the <a href=
 * "https://hypixel-skyblock.fandom.com/wiki/Protector_Dragon_Armor">Protector
 * Dragon Armor Set</a>.
 *
 * @author MrPineapple070
 * @version 01 July 2020
 * @since 02 August 2019
 */
public class ProtectorDragonSet implements FullSetInformation {
	public static final ProtectorDragonSet instance = new ProtectorDragonSet();

	public double boots_hp = 12;
	public double chestplate_hp = 24;
	public double helmet_hp = 14;
	public double leggings_hp = 20;

	public ProtectorDragonSet() {
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
		return ColorCodes.gold + "Full Set Bonus: Protective Blood\n" + ColorCodes.gray
				+ "Increases the Defense bonus of each armor piece by 1% for each percent of missing Health.";
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
		return ModArmorMaterial.Protector_Dragon;
	}

	@Override
	public ModItemRarity getRarity() {
		return ModItemRarity.Legendary;
	}
}