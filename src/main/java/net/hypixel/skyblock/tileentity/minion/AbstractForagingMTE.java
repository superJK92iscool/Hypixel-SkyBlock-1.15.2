package net.hypixel.skyblock.tileentity.minion;

import java.util.Arrays;

import net.hypixel.skyblock.init.items.ItemInit;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;

/**
 * Serve as a base for all Foraging Minions.
 * 
 * @author MrPineapple070
 * @version 21 February 2020
 * @since 11 July 2019
 */
public abstract class AbstractForagingMTE extends AbstractPlacerMTE {
	/**
	 * Array of {@link Item} that {@link #getCompactor()} will return.
	 */
	protected static final Item[] comp = new Item[] { Items.DIAMOND };
	
	/**
	 * Array of differentials when {@link #count(Item)} of {@link ItemInit#minion_expander} == 0
	 */
	protected static final int[] default_size = { -2, 0, 2 };
	
	/**
	 * Array of differentials when {@link #count(Item)} of {@link ItemInit#minion_expander} == 1
	 */
	protected static final int[] expanded_size = { -3, -1, 1, 3 };
	
	/**
	 * Array of differentials when {@link #count(Item)} of {@link ItemInit#minion_expander} == 2
	 */
	protected static final int[] expanded_1_size = { -4, -2, 0, 2, 4 };

	public AbstractForagingMTE(TileEntityType<? extends AbstractMinionTileEntity> typeIn, MinionTier tier) {
		super(typeIn, tier);
	}

	@Override
	protected final Item[] getCompactor() {
		return comp;
	}

	@Override
	protected final BlockPos[][][] initSurround() {
		return new BlockPos[1][5][9];
	}
	
	@Override
	protected final void setAirSurround() {
		LOGGER.info("Finding Air in valid BlockPos");
		this.airSurround.clear();
		int x_s, x_e, z_s, z_e;
		switch (this.count(ItemInit.minion_expander.get())) {
		default:
			x_s = 1; x_e = 4; z_s = 2; z_e = 7;
			break;
		case 1:
			x_s = 0; x_e = 5; z_s = 0; z_e = 9;
			break;
		case 2:
			for (int x = 0; x < 5; ++x)
				for (int z = 0; z < 9; z += 2)
					if (this.surround[0][x][z] == null)
						continue;
					else if (this.world.getBlockState(this.surround[0][x][z]).getMaterial() != Material.AIR)
						continue;
					else
						switch (x) {
						case 1: case 2: case 3:
							if (z == 0 || z == 8)
								this.airSurround.add(this.surround[0][x][z]);
						default:
							this.airSurround.add(this.surround[0][x][z]);
						}
			LOGGER.info(this.airSurround.toString());
			return;
		}
		for (int x = x_s; x < x_e; ++x)
			for (int z = z_s; z < z_e; z += 2)
				if (this.surround[0][x][z] == null)
					continue;
				else if (this.world.getBlockState(this.surround[0][x][z]).getMaterial() == Material.AIR)
					this.airSurround.add(this.surround[0][x][z]);
		LOGGER.info(this.airSurround.toString());
	}

	@Override
	protected final void setSurround() {
		LOGGER.info("Gathering Surrounding BlockPos.");
		int index_x = 0;
		for (int x : expanded_1_size) {
			int index_z = 0;
			for (int z : expanded_1_size) {
				this.surround[0][index_x][index_z] = x == 0 && z == 0 ? null
						: new BlockPos(this.x + x, this.y, this.z + z);
				index_z += 2;
			}
			index_x += 1;
		}
		
		index_x = 0;
		for (int x : expanded_size) {
			int index_z = 1;
			for (int z : expanded_size) {
				this.surround[0][index_x][index_z] = Math.abs(x) <= 1 && Math.abs(z) <= 1 ? null
						: new BlockPos(this.x + x, this.y, this.z + z);
				index_z += 2;
			}
			index_x += 1;
		}
		
		for (BlockPos[] row : this.surround[0])
			LOGGER.info(Arrays.deepToString(row));
	}

	@Override
	protected final void setValidSurround() {
		LOGGER.info("Gathering valid BlockPos");
		this.validSurround.clear();
		int x_s, x_e, z_s, z_e;
		switch (this.count(ItemInit.minion_expander.get())) {
		default:
			x_s = 1; x_e = 4; z_s = 2; z_e = 7;
			break;
		case 1:
			x_s = 0; x_e = 5; z_s = 0; z_e = 9;
			break;
		case 2:
			for (int x = 0; x < 5; ++x)
				for (int z = 0; z < 9; z += 2)
					if (this.surround[0][x][z] == null)
						continue;
					if (this.world.getBlockState(this.surround[0][x][z].down()).getMaterial() == Material.EARTH)
						switch (x) {
						case 1: case 2: case 3:
							if (z == 0 || z == 8)
								this.validSurround.add(this.surround[0][x][z]);
						default:
							this.validSurround.add(this.surround[0][x][z]);
						}
			LOGGER.info(this.validSurround.toString());
			return;
		}
		for (int x = x_s; x < x_e; ++x)
			for (int z = z_s; z < z_e; z += 2)
				if (this.surround[0][x][z] == null)
					continue;
				else if (this.world.getBlockState(this.surround[0][x][z].down()).getMaterial() == Material.EARTH)
					this.validSurround.add(this.surround[0][x][z]);
		LOGGER.info(this.validSurround.toString());
	}
}