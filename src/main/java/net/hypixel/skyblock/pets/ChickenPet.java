package net.hypixel.skyblock.pets;

import net.hypixel.skyblock.entity.player.ModServerPlayerEntity;
import net.hypixel.skyblock.items.Collection;
import net.hypixel.skyblock.items.ModItemRarity;

/**
 * @author MrPineapple070
 * @version 12 October 2020
 * @since 12 October 2020
 */
public class ChickenPet extends Pet {
	protected ChickenPet() {
		super(ModItemRarity.Common, Collection.Farming, PetType.Chicken);
	}

	@Override
	public void effect(ModServerPlayerEntity player) {
	}

	@Override
	public double[] getBuffs() {
		return null;
	}
}