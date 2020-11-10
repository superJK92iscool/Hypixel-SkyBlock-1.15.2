package net.hypixel.skyblock.items.armor;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;

import net.hypixel.skyblock.items.ModItemRarity;
import net.hypixel.skyblock.util.ColorCodes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

/**
 * Serve as a base for {@link EquipmentSlotType} specific information for all
 * {@link ModArmorItem}
 *
 * @author MrPineapple070
 * @version 07 July 2020
 * @since 11 June 2019
 */
public interface FullSetInformation {
	/**
	 * A {@link ImmutableList} of color codes for each buff.
	 */
	ImmutableList<String> color = ImmutableList.copyOf(new String[] { ColorCodes.dark_red, ColorCodes.blue,
			ColorCodes.dark_blue, ColorCodes.red, ColorCodes.white, ColorCodes.aqua, ColorCodes.green });

	/**
	 * An {@link ImmutableList} of buff names.
	 */
	ImmutableList<String> name = ImmutableList.copyOf(new String[] { "Strength", "Crit Chance", "Crit Damage", "Health",
			"Speed", "Intelligence", "True Defense" });

	/**
	 * Create a buff array.
	 *
	 * @param str     Strength buff.
	 * @param cr_chn  Crit Chance buff.
	 * @param cr_dmg  Crit Damage buff.
	 * @param hp      Health buff.
	 * @param spd     Speed buff.
	 * @param intel   Intelligence buff.
	 * @param tru_def True Defense buff.
	 * @return an array of parameters.
	 */
	static double[] createBuffArray(double str, double cr_chn, double cr_dmg, double hp, double spd, double intel,
			double tru_def) {
		return new double[] { str, cr_chn, cr_dmg, hp, spd, intel, tru_def };
	}

	/**
	 * The order of this array is Strength, Crit Chance, Crit Damage, Health, Speed,
	 * Intelligence, True Defense.
	 *
	 * @return an array of all buffs for {@link EquipmentSlotType#FEET}.
	 * @throws IllegalAccessException if the Boots do not exist.
	 */
	double[] getBootsBuffs() throws IllegalAccessException;

	/**
	 * The order of this array is Strength, Crit Chance, Crit Damage, Health, Speed,
	 * Intelligence, True Defense.
	 *
	 * @return an array of all buffs for {@link EquipmentSlotType#CHEST}.
	 * @throws IllegalAccessException if the Chestplate does not exist.
	 */
	double[] getChestplateBuffs() throws IllegalAccessException;

	/**
	 * Add a description to the Armor Pieces of each set.
	 *
	 * @param slotIn {@link EquipmentSlotType} to add the description to.
	 * @return the description of the item.
	 * @throws IllegalAccessException if {@code slotIn} is not a valid
	 *                                {@code EquipmentSlotType}
	 */
	default List<ITextComponent> getDescription(EquipmentSlotType slotIn) throws IllegalAccessException {
		final List<ITextComponent> description = new ArrayList<>();
		double[] buffs;

		switch (slotIn) {
		case HEAD:
			buffs = this.getHelmetBuffs();
			break;
		case CHEST:
			buffs = this.getChestplateBuffs();
			break;
		case LEGS:
			buffs = this.getLeggingsBuffs();
			break;
		case FEET:
			buffs = this.getBootsBuffs();
			break;
		default:
			throw new IllegalAccessException("Illegal Slot: " + slotIn);
		}

		for (int i = 0; i < buffs.length; i++)
			if (buffs[i] == 0)
				continue;
			else
				description.add(new StringTextComponent(
						color.get(i) + FullSetInformation.name.get(i) + ColorCodes.gray + ": " + buffs[i]));
		description.add(new StringTextComponent(this.getFullSetBonus()));
		return description;
	}

	/**
	 * @return the description for the full set bonus of this set.
	 */
	String getFullSetBonus();

	/**
	 * The order of this array is Strength, Crit Chance, Crit Damage, Health, Speed,
	 * Intelligence, True Defense.
	 *
	 * @return an array of all buffs for {@link EquipmentSlotType#HEAD}.
	 * @throws IllegalAccessException if the Helmet does not exist.
	 */
	double[] getHelmetBuffs() throws IllegalAccessException;

	/**
	 * The order of this array is Strength, Crit Chance, Crit Damage, Health, Speed,
	 * Intelligence, True Defense.
	 *
	 * @return an array of all buffs for {@link EquipmentSlotType#LEGS}.
	 * @throws IllegalAccessException if the Leggings do not exist.
	 */
	double[] getLeggingsBuffs() throws IllegalAccessException;

	/**
	 * @return {@link ModArmorMaterial} for this set.
	 */
	ModArmorMaterial getMaterial();

	/**
	 * @return {@link ModItemRarity} for this.
	 */
	ModItemRarity getRarity();
}