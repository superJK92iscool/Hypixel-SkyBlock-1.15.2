package net.hypixel.skyblock.pets;

import net.hypixel.skyblock.entity.player.ModServerPlayerEntity;
import net.hypixel.skyblock.items.Collection;
import net.hypixel.skyblock.items.ModItemRarity;

public class BatPet extends Pet {
	public BatPet() {
		super(ModItemRarity.Common, Collection.Mining, PetType.Bat);
	}

	@Override
	public void effect(ModServerPlayerEntity player) {
	}
}