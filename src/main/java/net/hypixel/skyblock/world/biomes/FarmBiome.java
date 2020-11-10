package net.hypixel.skyblock.world.biomes;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

/**
 * @author MrPineapple070
 * @version 07 August 2020
 * @since 07 August 2020
 */
public class FarmBiome extends Biome {
	public FarmBiome() {
		super(new Builder().surfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_DIRT_GRAVEL_CONFIG)
				.precipitation(RainType.RAIN).category(Category.PLAINS).depth(0.125F).scale(0.05F).temperature(0.8F)
				.downfall(0.4F).waterColor(0x3F76E4).waterFogColor(0x50533).parent((String) null));
		this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.PIG, 0xa, 4, 4));
		this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.CHICKEN, 0xa, 4, 4));
		this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(EntityType.COW, 0xa, 4, 4));
	}
}