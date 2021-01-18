package net.hypixel.skyblock.tileentity.minion;

import java.util.Arrays;

import net.hypixel.skyblock.HypixelSkyBlockMod;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;

public abstract class AbstractForagingMTE extends AbstractPlacerMTE {
	protected static final Item[] comp = new Item[] {Items.DIAMOND};
	protected static final int[] dx = {-2, 0, 2};
	
	public AbstractForagingMTE(TileEntityType<? extends AbstractMinionTileEntity> typeIn, MinionTier tier) {
		super(typeIn, tier);
	}
	
	@Override
	protected final Item[] getCompactor() {
		return comp;
	}

	@Override
	protected final BlockPos[][][] initSurround() {
		return new BlockPos[1][3][3];
	}

	@Override
	protected final void setSurround() {
		HypixelSkyBlockMod.LOGGER.info("Gathering Surrounding BlockPos.");
		for (int y = 0; y < this.surround.length; ++y) 
			for (int x = 0; x < this.surround[y].length; ++x)
				for (int z = 0; z < this.surround[y][x].length; ++z)
					this.surround[y][x][z] = dx[x] == 0 && dx[z] == 0 ? null : new BlockPos(this.x + dx[x], this.y, this.z + dx[z]);
		HypixelSkyBlockMod.LOGGER.info(Arrays.deepToString(this.surround));
	}

	@Override
	protected void setValidSurround() {
		HypixelSkyBlockMod.LOGGER.info("Gathering valid BlockPos");
		this.validSurround.clear();
		for (BlockPos[][] y : this.surround)
			for (BlockPos[] x : y)
				for (BlockPos pos : x) {
					if (pos == null)
						continue;
					BlockState state = this.world.getBlockState(pos);
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