package net.hypixel.skyblock.init;

import net.hypixel.skyblock.HypixelSkyBlockMod;
import net.hypixel.skyblock.world.biomes.FarmBiome;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @author MrPineapple070
 * @version 11 September 2020
 * @since 11 September 2020
 */
public class BiomeInit {
	public static final DeferredRegister<Biome> biome = new DeferredRegister<>(ForgeRegistries.BIOMES,
			HypixelSkyBlockMod.MOD_ID);

	public static final RegistryObject<Biome> farm_biome = biome.register("farm_biome", FarmBiome::new);
}
