package net.hypixel.skyblock.world.dimension;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;

/**
 * @author MrPineapple070
 * @version 07 August 2020
 * @since 07 August 2020
 */
public class SpiderDenDimension extends Dimension {
	public SpiderDenDimension(World p_i225788_1_, DimensionType p_i225788_2_, float p_i225788_3_) {
		super(p_i225788_1_, p_i225788_2_, p_i225788_3_);
	}

	@Override
	public float calculateCelestialAngle(long worldTime, float partialTicks) {
		return 0;
	}

	@Override
	public boolean canRespawnHere() {
		return false;
	}

	@Override
	public ChunkGenerator<?> createChunkGenerator() {
		return null;
	}

	@Override
	public boolean doesXZShowFog(int x, int z) {
		return false;
	}

	@Override
	public BlockPos findSpawn(ChunkPos chunkPosIn, boolean checkValid) {
		return null;
	}

	@Override
	public BlockPos findSpawn(int posX, int posZ, boolean checkValid) {
		return null;
	}

	@Override
	public Vec3d getFogColor(float celestialAngle, float partialTicks) {
		return null;
	}

	@Override
	public boolean isSurfaceWorld() {
		return false;
	}
}