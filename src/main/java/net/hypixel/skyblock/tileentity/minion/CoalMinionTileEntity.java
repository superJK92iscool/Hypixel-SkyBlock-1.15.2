package net.hypixel.skyblock.tileentity.minion;

import net.hypixel.skyblock.HypixelSkyBlockMod;
import net.hypixel.skyblock.blocks.minion.CoalMinion;
import net.hypixel.skyblock.init.items.ItemInit;
import net.hypixel.skyblock.inventory.container.minion.CoalMinionContainer.CoalMC1;
import net.hypixel.skyblock.inventory.container.minion.CoalMinionContainer.CoalMC2;
import net.hypixel.skyblock.inventory.container.minion.CoalMinionContainer.CoalMC3;
import net.hypixel.skyblock.inventory.container.minion.CoalMinionContainer.CoalMC4;
import net.hypixel.skyblock.inventory.container.minion.CoalMinionContainer.CoalMC5;
import net.hypixel.skyblock.inventory.container.minion.CoalMinionContainer.CoalMC6;
import net.hypixel.skyblock.inventory.container.minion.CoalMinionContainer.CoalMC7;
import net.hypixel.skyblock.inventory.container.minion.CoalMinionContainer.CoalMC8;
import net.hypixel.skyblock.inventory.container.minion.CoalMinionContainer.CoalMC9;
import net.hypixel.skyblock.inventory.container.minion.CoalMinionContainer.CoalMCa;
import net.hypixel.skyblock.inventory.container.minion.CoalMinionContainer.CoalMCb;
import net.hypixel.skyblock.tileentity.ModTileEntityTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

/**
 * The base for Coal Minion TileEntity
 *
 * @author MrPineapple070
 * @version 23 July 2020
 */
public abstract class CoalMinionTileEntity extends AbstractMinionTileEntity {
	public static class CoalMTE1 extends CoalMinionTileEntity {
		public CoalMTE1() {
			super(ModTileEntityTypes.coal_minion_1.get(), MinionTier.I);
		}
	}

	public static class CoalMTE2 extends CoalMinionTileEntity {
		public CoalMTE2() {
			super(ModTileEntityTypes.coal_minion_2.get(), MinionTier.II);
		}
	}

	public static class CoalMTE3 extends CoalMinionTileEntity {
		public CoalMTE3() {
			super(ModTileEntityTypes.coal_minion_3.get(), MinionTier.III);
		}
	}

	public static class CoalMTE4 extends CoalMinionTileEntity {
		public CoalMTE4() {
			super(ModTileEntityTypes.coal_minion_4.get(), MinionTier.IV);
		}
	}

	public static class CoalMTE5 extends CoalMinionTileEntity {
		public CoalMTE5() {
			super(ModTileEntityTypes.coal_minion_5.get(), MinionTier.V);
		}
	}

	public static class CoalMTE6 extends CoalMinionTileEntity {
		public CoalMTE6() {
			super(ModTileEntityTypes.coal_minion_6.get(), MinionTier.VI);
		}
	}

	public static class CoalMTE7 extends CoalMinionTileEntity {
		public CoalMTE7() {
			super(ModTileEntityTypes.coal_minion_7.get(), MinionTier.VII);
		}
	}

	public static class CoalMTE8 extends CoalMinionTileEntity {
		public CoalMTE8() {
			super(ModTileEntityTypes.coal_minion_8.get(), MinionTier.VIII);
		}
	}

	public static class CoalMTE9 extends CoalMinionTileEntity {
		public CoalMTE9() {
			super(ModTileEntityTypes.coal_minion_9.get(), MinionTier.IX);
		}
	}

	public static class CoalMTEa extends CoalMinionTileEntity {
		public CoalMTEa() {
			super(ModTileEntityTypes.coal_minion_a.get(), MinionTier.X);
		}
	}

	public static class CoalMTEb extends CoalMinionTileEntity {
		public CoalMTEb() {
			super(ModTileEntityTypes.coal_minion_b.get(), MinionTier.XI);
		}
	}

	private static final NonNullList<Block> validBlocks = NonNullList.from(Blocks.AIR, Blocks.COAL_ORE);

	private static final Item[] comp = new Item[] { Items.COAL, Items.DIAMOND };
	private static final Item[] sup = new Item[] { Items.COAL, Items.COAL_BLOCK, Items.DIAMOND, Items.DIAMOND_BLOCK,
			ItemInit.enchanted_coal.get(), ItemInit.enchanted_diamond.get() };

	protected CoalMinionTileEntity(TileEntityType<? extends AbstractMinionTileEntity> typeIn, MinionTier tier) {
		super(typeIn, tier);
	}

	@Override
	protected Container createMenu(int id, PlayerInventory player) {
		switch (this.tier) {
		case I:
			return new CoalMC1(id, player, this);
		case II:
			return new CoalMC2(id, player, this);
		case III:
			return new CoalMC3(id, player, this);
		case IV:
			return new CoalMC4(id, player, this);
		case V:
			return new CoalMC5(id, player, this);
		case VI:
			return new CoalMC6(id, player, this);
		case VII:
			return new CoalMC7(id, player, this);
		case VIII:
			return new CoalMC8(id, player, this);
		case IX:
			return new CoalMC9(id, player, this);
		case X:
			return new CoalMCa(id, player, this);
		case XI:
			return new CoalMCb(id, player, this);
		}
		throw new IllegalStateException("Illegal Minion MinionTier");
	}

	@Override
	protected Item[] getCompactor() {
		return comp;
	}

	@Override
	public ITextComponent getDisplayName() {
		return new StringTextComponent("Coal Minion Tier " + this.tier.name());
	}

	@Override
	protected SoundEvent getSoundEvent() {
		return SoundEvents.BLOCK_STONE_BREAK;
	}

	@Override
	protected BlockState getState() {
		return Blocks.COAL_ORE.getDefaultState();
	}

	@Override
	protected Item[] getSuperCompactor() {
		return sup;
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
		if (!this.airSurround.isEmpty())
			return this.airSurround.get(rand.nextInt(this.airSurround.size()));
		if (!this.validSurround.isEmpty())
			return this.validSurround.get(rand.nextInt(this.validSurround.size()));
		return null;
	}

	@Override
	protected void setSurround() {
		HypixelSkyBlockMod.LOGGER.info("Gathering Surrounding BlockPos.");
		for (int x = 0; x < this.surround[0].length; x++)
			for (int z = 0; z < this.surround[0][x].length; z++)
				this.surround[0][x][z] = expanded_size[x] == 0 && expanded_size[z] == 0 ? null
						: new BlockPos(this.x + expanded_size[z], this.y - 1, this.z + expanded_size[x]);
	}

	@Override
	protected void setValidSurround() {
		HypixelSkyBlockMod.LOGGER.info("Gathering Surrounding BlockPos.");
		final boolean hasExpander = this.hasUpgrade(ItemInit.minion_expander.get());
		final int iStart = hasExpander ? 1 : 0, iEnd = hasExpander ? this.surround.length : this.surround.length - 1;
		final int jStart = hasExpander ? 1 : 0, jEnd = hasExpander ? this.surround.length : this.surround.length - 1;
		this.validSurround.clear();
		for (int x = iStart; x < iEnd; x++)
			for (int z = jStart; z < jEnd; z++) {
				BlockPos pos = this.surround[0][x][z];
				if (pos == null)
					continue;
				BlockState state = this.world.getBlockState(pos);
				if (state.getMaterial() == Material.AIR)
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
		this.tick = ++this.tick % (int) (CoalMinion.speed.get(this.tier.asInt) * this.getFuelSpeed());
		if (this.tick == 0)
			this.interact(this.pickBlock());
	}
}