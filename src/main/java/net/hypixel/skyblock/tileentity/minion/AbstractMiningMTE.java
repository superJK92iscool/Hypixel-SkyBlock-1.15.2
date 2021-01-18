package net.hypixel.skyblock.tileentity.minion;

import java.util.Arrays;

import net.hypixel.skyblock.HypixelSkyBlockMod;
import net.hypixel.skyblock.init.items.ItemInit;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;

public abstract class AbstractMiningMTE extends AbstractPlacerMTE {
	public AbstractMiningMTE(TileEntityType<? extends AbstractMinionTileEntity> typeIn, MinionTier tier) {
		super(typeIn, tier);
	}

	@Override
	protected final BlockPos[][][] initSurround() {
		return new BlockPos[1][7][7];
	}

	@Override
	protected BlockPos pickBlock() {
		HypixelSkyBlockMod.LOGGER.info("Picking a BlockPos");
		this.setValidSurround();
		this.setAirSurround();
		if (!this.airSurround.isEmpty())
			return this.airSurround.get(rand.nextInt(this.airSurround.size()));
		if (!this.validSurround.isEmpty())
			return this.validSurround.get(rand.nextInt(this.validSurround.size()));
		return null;
	}

	@Override
	protected final void setSurround() {
		HypixelSkyBlockMod.LOGGER.info("Gathering Surrounding BlockPos.");
		final int[] dx = expanded_size;

		for (int x = 0; x < this.surround[0].length; x++)
			for (int y = 0; y < this.surround[0][x].length; y++)
				this.surround[0][x][y] = dx[x] == 0 && dx[y] == 0 ? null
						: new BlockPos(this.x + dx[y], this.y - 1, this.z + dx[x]);
		HypixelSkyBlockMod.LOGGER.info(Arrays.deepToString(this.surround));
	}

	@Override
	protected final void setValidSurround() {
		HypixelSkyBlockMod.LOGGER.info("Gathering valid BlockPos");
		this.validSurround.clear();
		final boolean hasExpander = this.hasUpgrade(ItemInit.minion_expander.get());
		final int xStart = hasExpander ? 0 : 1,
				xEnd = hasExpander ? this.surround[0].length : this.surround[0].length - 1;
		final int zStart = hasExpander ? 0 : 1,
				zEnd = hasExpander ? this.surround[0].length : this.surround[0].length - 1;
		for (int x = xStart; x < xEnd; x++)
			for (int z = zStart; z < zEnd; z++) {
				final BlockPos pos = this.surround[0][x][z];
				if (pos == null)
					continue;
				final BlockState state = this.world.getBlockState(pos);
				if (state.getMaterial() == Material.AIR)
					this.validSurround.add(pos);
				else if (this.isBlockValid(state.getBlock()))
					this.validSurround.add(pos);
				else
					continue;
			}
		HypixelSkyBlockMod.LOGGER.info(this.validSurround.toString());
	}
}