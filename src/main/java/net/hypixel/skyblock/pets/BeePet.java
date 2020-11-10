package net.hypixel.skyblock.pets;

import java.util.List;

import net.hypixel.skyblock.entity.player.ModServerPlayerEntity;
import net.hypixel.skyblock.items.Collection;
import net.minecraft.entity.player.ServerPlayerEntity;

/**
 * @author MrPineapple070
 * @version 17 August 2020
 * @since 17 August 2020
 */
public class BeePet extends Pet {
	public BeePet() {
		super(Pet.getRandomRarityLow(), Collection.Farming, PetType.Bee);
	}

	@Override
	public void effect(ModServerPlayerEntity player) {
		final List<ServerPlayerEntity> players = player.getServerWorld().getPlayers();
		for (final ServerPlayerEntity p : players) {
			if (p instanceof ModServerPlayerEntity) {
				final ModServerPlayerEntity mp = (ModServerPlayerEntity) p;
				if (player == mp)
					continue;
				if (player.getActivePet().isIdentical(mp.getActivePet()))
					continue;
			}
			continue;
		}
	}
}