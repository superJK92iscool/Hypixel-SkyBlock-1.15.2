package net.hypixel.skyblock.tileentity.minion;

import net.hypixel.skyblock.init.items.ItemInit;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;

/**
 * These Minions will summon {@link Entity}.
 * 
 * @author MrPineapple070
 * @version 26 January 2021
 * @since 11 July 2019
 */
public abstract class AbstractSummonerMTE extends AbstractMinionTileEntity {
	protected static final int[] dx = { -4, -3, -2, -1, 0, 1, 2, 3, 4 };

	public AbstractSummonerMTE(TileEntityType<? extends AbstractMinionTileEntity> typeIn, MinionTier tier) {
		super(typeIn, tier);
	}

	@Override
	protected BlockPos[][][] initSurround() {
		return new BlockPos[9][9][9];
	}

	@Override
	protected void setSurround() {
		for (int y = 0; y < this.surround.length; ++y)
			for (int x = 0; x < this.surround[y].length; ++x)
				for (int z = 0; z < this.surround[y][x].length; ++z)
					this.surround[y][x][z] = dx[x] == 0 && dx[y] == 0 && dx[z] == 0 ? null
							: new BlockPos(this.x + dx[x], this.y + dx[y], this.z + dx[z]);
	}

	@Override
	protected void setValidSurround() {
		this.validSurround.clear();
		int xStart, xEnd, yStart, yEnd, zStart, zEnd;
		switch (this.count(ItemInit.minion_expander.get())) {
		case 0:
		default:
			xStart = 2; yStart = 2; zStart = 2;
			xEnd = 7; yEnd = 7; zEnd = 7;
			break;
		case 1:
			xStart = 1; yStart = 1; zStart = 1;
			xEnd = 8; yEnd = 8; zEnd = 8;
			break;
		case 2:
			xStart = 0; yStart = 0; zStart = 0;
			xEnd = 9; yEnd = 9; zEnd = 0;
			break;
		}
		for (int y = yStart; y < yEnd; ++y)
			for (int x = xStart; x < xEnd; ++x)
				for (int z = zStart; z < zEnd; ++z)
					this.validSurround.add(this.surround[y][x][z]);
	}
}