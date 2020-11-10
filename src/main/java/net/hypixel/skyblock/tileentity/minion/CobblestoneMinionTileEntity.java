package net.hypixel.skyblock.tileentity.minion;

import java.util.Arrays;

import net.hypixel.skyblock.HypixelSkyBlockMod;
import net.hypixel.skyblock.blocks.minion.CobblestoneMinion;
import net.hypixel.skyblock.init.items.ItemInit;
import net.hypixel.skyblock.inventory.container.minion.CobblestoneMinionContainer.CobbleMC1;
import net.hypixel.skyblock.inventory.container.minion.CobblestoneMinionContainer.CobbleMC2;
import net.hypixel.skyblock.inventory.container.minion.CobblestoneMinionContainer.CobbleMC3;
import net.hypixel.skyblock.inventory.container.minion.CobblestoneMinionContainer.CobbleMC4;
import net.hypixel.skyblock.inventory.container.minion.CobblestoneMinionContainer.CobbleMC5;
import net.hypixel.skyblock.inventory.container.minion.CobblestoneMinionContainer.CobbleMC6;
import net.hypixel.skyblock.inventory.container.minion.CobblestoneMinionContainer.CobbleMC7;
import net.hypixel.skyblock.inventory.container.minion.CobblestoneMinionContainer.CobbleMC8;
import net.hypixel.skyblock.inventory.container.minion.CobblestoneMinionContainer.CobbleMC9;
import net.hypixel.skyblock.inventory.container.minion.CobblestoneMinionContainer.CobbleMCa;
import net.hypixel.skyblock.inventory.container.minion.CobblestoneMinionContainer.CobbleMCb;
import net.hypixel.skyblock.tileentity.ModTileEntityTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

/**
 * {@link TileEntity} for the <a href=
 * "https://hypixel-skyblock.fandom.com/wiki/Cobblestone_Minion">Cobblestone
 * Minion</a>
 *
 * @author MrPineapple070
 * @version 11 June 2019
 * @since 11 June 2019
 */
public abstract class CobblestoneMinionTileEntity extends AbstractMinionTileEntity {
	public static class CobbleMTE1 extends CobblestoneMinionTileEntity {
		public CobbleMTE1() {
			super(ModTileEntityTypes.cobblestone_minion_1.get(), MinionTier.I);
		}
	}

	public static class CobbleMTE2 extends CobblestoneMinionTileEntity {
		public CobbleMTE2() {
			super(ModTileEntityTypes.cobblestone_minion_2.get(), MinionTier.II);
		}
	}

	public static class CobbleMTE3 extends CobblestoneMinionTileEntity {
		public CobbleMTE3() {
			super(ModTileEntityTypes.cobblestone_minion_3.get(), MinionTier.III);
		}
	}

	public static class CobbleMTE4 extends CobblestoneMinionTileEntity {
		public CobbleMTE4() {
			super(ModTileEntityTypes.cobblestone_minion_4.get(), MinionTier.IV);
		}
	}

	public static class CobbleMTE5 extends CobblestoneMinionTileEntity {
		public CobbleMTE5() {
			super(ModTileEntityTypes.cobblestone_minion_5.get(), MinionTier.V);
		}
	}

	public static class CobbleMTE6 extends CobblestoneMinionTileEntity {
		public CobbleMTE6() {
			super(ModTileEntityTypes.cobblestone_minion_6.get(), MinionTier.VI);
		}
	}

	public static class CobbleMTE7 extends CobblestoneMinionTileEntity {
		public CobbleMTE7() {
			super(ModTileEntityTypes.cobblestone_minion_7.get(), MinionTier.VII);
		}
	}

	public static class CobbleMTE8 extends CobblestoneMinionTileEntity {
		public CobbleMTE8() {
			super(ModTileEntityTypes.cobblestone_minion_8.get(), MinionTier.VIII);
		}
	}

	public static class CobbleMTE9 extends CobblestoneMinionTileEntity {
		public CobbleMTE9() {
			super(ModTileEntityTypes.cobblestone_minion_9.get(), MinionTier.IX);
		}
	}

	public static class CobbleMTEa extends CobblestoneMinionTileEntity {
		public CobbleMTEa() {
			super(ModTileEntityTypes.cobblestone_minion_a.get(), MinionTier.X);
		}
	}

	public static class CobbleMTEb extends CobblestoneMinionTileEntity {
		public CobbleMTEb() {
			super(ModTileEntityTypes.cobblestone_minion_b.get(), MinionTier.XI);
		}
	}

	/**
	 * {@link NonNullList} of {@link Block} that this places
	 */
	protected static final NonNullList<Block> validBlocks = NonNullList.from(Blocks.AIR, Blocks.COBBLESTONE);

	/**
	 * Constructs {@code this}
	 *
	 * @param typeIn {@link TileEntityType}
	 * @param tier   {@link MinionTier}
	 */
	protected CobblestoneMinionTileEntity(TileEntityType<? extends AbstractMinionTileEntity> typeIn, MinionTier tier) {
		super(typeIn, tier);
	}

	@Override
	protected Container createMenu(int id, PlayerInventory player) {
		switch (this.tier) {
		case I:
			return new CobbleMC1(id, player, this);
		case II:
			return new CobbleMC2(id, player, this);
		case III:
			return new CobbleMC3(id, player, this);
		case IV:
			return new CobbleMC4(id, player, this);
		case V:
			return new CobbleMC5(id, player, this);
		case VI:
			return new CobbleMC6(id, player, this);
		case VII:
			return new CobbleMC7(id, player, this);
		case VIII:
			return new CobbleMC8(id, player, this);
		case IX:
			return new CobbleMC9(id, player, this);
		case X:
			return new CobbleMCa(id, player, this);
		case XI:
			return new CobbleMCb(id, player, this);
		}
		throw new IllegalStateException("Illegal Minion MinionTier");
	}

	@Override
	protected Item[] getCompactor() {
		return new Item[] { Items.DIAMOND };
	}

	@Override
	public ITextComponent getDisplayName() {
		return new StringTextComponent("Cobblestone Minion Tier " + this.tier.name());
	}

	@Override
	protected SoundEvent getSoundEvent() {
		return SoundEvents.BLOCK_STONE_BREAK;
	}

	@Override
	protected BlockState getState() {
		return Blocks.COBBLESTONE.getDefaultState();
	}

	@Override
	protected Item[] getSuperCompactor() {
		return new Item[] { Items.COBBLESTONE, Items.DIAMOND, Items.DIAMOND_BLOCK, ItemInit.enchanted_diamond.get() };
	}

	@Override
	protected BlockPos[][][] initSurround() {
		return new BlockPos[1][7][7];
	}

	@Override
	protected BlockPos pickBlock() {
		HypixelSkyBlockMod.LOGGER.info("Picking a BlockPos");
		this.setValidSurround();
		this.setAirSurround();
		if (this.airSurround.size() > 0)
			return this.airSurround.get(rand.nextInt(this.airSurround.size()));
		if (this.validSurround.size() > 0)
			return this.validSurround.get(rand.nextInt(this.validSurround.size()));
		return null;
	}

	@Override
	protected void setSurround() {
		HypixelSkyBlockMod.LOGGER.info("Gathering Surrounding BlockPos.");
		final int[] dx = expanded_size;

		for (int i = 0; i < this.surround[0].length; i++)
			for (int j = 0; j < this.surround[0][i].length; j++)
				this.surround[0][i][j] = dx[i] == 0 && dx[j] == 0 ? null
						: new BlockPos(this.x + dx[j], this.y - 1, this.z + dx[i]);
		HypixelSkyBlockMod.LOGGER.info(Arrays.deepToString(this.surround));
	}

	@Override
	protected void setValidSurround() {
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
				if (state.isAir(this.world, pos))
					this.validSurround.add(pos);
				else if (this.isBlockValid(state.getBlock(), validBlocks))
					this.validSurround.add(pos);
				else
					continue;
			}
		HypixelSkyBlockMod.LOGGER.info(this.validSurround.toString());
	}

	@Override
	public void tick() {
		if (this.world.isRemote)
			return;
		if (!this.isTicking)
			this.init();
		if (this.isCompletlyFull())
			return;
		this.tick = ++this.tick % (int) (CobblestoneMinion.speed.get(this.tier.asInt) * this.getFuelSpeed());
		if (this.tick == 0)
			this.interact(this.pickBlock());
	}
}