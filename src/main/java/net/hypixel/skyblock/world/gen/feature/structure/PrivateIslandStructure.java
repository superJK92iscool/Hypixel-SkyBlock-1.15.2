package net.hypixel.skyblock.world.gen.feature.structure;

import java.util.Random;

import net.hypixel.skyblock.HypixelSkyBlockMod;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeManager;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

/**
 * {@link Structure} for
 * ".\src\main\resources\data\hypixelskyblockmod\structures\private_island.nbt"
 * 
 * @author MrPineapple070
 * @version 03 December 2020
 * @since 11 July 2019
 */
public class PrivateIslandStructure extends Structure<NoFeatureConfig> {
	public PrivateIslandStructure() {
		super(NoFeatureConfig::deserialize);
	}

	@Override
	public IStartFactory getStartFactory() {
		return PrivateIslandStructure.Start::new;
	}

	@Override
	public String getStructureName() {
		return HypixelSkyBlockMod.MOD_ID + ":private_island";
	}

	protected int getSeedModifier() {
		return 0x0;
	}

	public static class Start extends StructureStart {
		public Start(Structure<?> structure, int chunkX, int chunkZ, MutableBoundingBox boundingBox, int reference,
				long seed) {
			super(structure, chunkX, chunkZ, boundingBox, reference, seed);
		}

		@Override
		public void init(ChunkGenerator<?> generator, TemplateManager templateManagerIn, int chunkX, int chunkZ,
				Biome biomeIn) {
			Rotation rotation = Rotation.values()[this.rand.nextInt(Rotation.values().length)];
			int x = (chunkX << 4) + 7;
			int z = (chunkX << 4) + 7;
			int y = generator.func_222531_c(x, z, Heightmap.Type.WORLD_SURFACE_WG);
			BlockPos pos = new BlockPos(x, y, z);
			PrivateIslandPieces.start(templateManagerIn, pos, rotation, this.components, this.rand);
			this.recalculateStructureSize();
			HypixelSkyBlockMod.LOGGER.info("We can find a private island at: " + pos);
		}
	}

	@Override
	public boolean canBeGenerated(BiomeManager manager, ChunkGenerator<?> generator, Random rand, int chunkX,
			int chunkZ, Biome biome) {
		ChunkPos pos = this.getStartPositionForPosition(generator, rand, chunkX, chunkZ, 0, 0);
		if (chunkX == pos.x && chunkZ == pos.z)
			if (generator.hasStructure(biome, this))
				return true;
		return false;
	}

	@Override
	public int getSize() {
		return 0;
	}
}