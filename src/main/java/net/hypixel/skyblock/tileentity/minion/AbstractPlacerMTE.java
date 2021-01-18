package net.hypixel.skyblock.tileentity.minion;

import javax.annotation.Nonnull;

import com.google.common.collect.ImmutableSet;

import net.hypixel.skyblock.HypixelSkyBlockMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;

/**
 * Place and Break {@link Block}.
 * 
 * @author MrPineapple070
 * @version 17 January 2021
 * @since 11 July 2019
 */
public abstract class AbstractPlacerMTE extends AbstractMinionTileEntity {
	public AbstractPlacerMTE(TileEntityType<? extends AbstractMinionTileEntity> typeIn, MinionTier tier) {
		super(typeIn, tier);
	}
	
	protected abstract SoundEvent getSoundEvent();

	@Override
	protected BlockState getState() {
		return null;
	}

	@Override
	protected Item[] getSuperCompactor() {
		return null;
	}
	
	protected abstract ImmutableSet<Block> getValidBlocks();

	@Override
	protected StringTextComponent initDisplayName() {
		return null;
	}

	@Override
	protected BlockPos[][][] initSurround() {
		return new BlockPos[7][7][7];
	}
	
	@Override
	protected boolean interact(BlockPos pos) {
		if (pos == null)
			return false;
		HypixelSkyBlockMod.LOGGER.info("Interacting with " + pos.toString());
		final BlockState state = this.world.getBlockState(pos);
		if (state.getMaterial() == Material.AIR) {
			this.world.playSound(pos.getX(), pos.getY(), pos.getZ(), this.getSoundEvent(), SoundCategory.BLOCKS, 1f, 1f,
					true);
			this.world.setBlockState(pos, this.getState());
		} else {
			this.addItemStacks(Block.getDrops(state, (ServerWorld) this.world, pos, this));
			this.world.playEvent(2001, pos, Block.getStateId(state));
			this.world.setBlockState(pos, Blocks.AIR.getDefaultState());
		}
		return true;
	}
	
	/**
	 * Determine if {@link Block} can be added to {@link #validSurround}.<br>
	 * Help {@link #setValidSurround()} in adding.<br>
	 * {@code AbstractMinionTileEntity} can interact with {@code Block} in 3
	 * dimensions
	 *
	 * @param block       The {@code Block} to check.
	 * @param validBlocks A {@link ImmutableSet} of valid {@code Block} that this can
	 *                    interact with
	 * @return {@code true} if {@code Block} is valid.<br>
	 *         {@code false} otherwise.
	 */
	protected final boolean isBlockValid(@Nonnull Block block) {
		ImmutableSet<Block> blocks = this.getValidBlocks();
		if (blocks.size() < 1)
			throw new IllegalArgumentException("validBlocks must have at least one element");
		return blocks.contains(block);
	}
	
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
	public void tick() {
		if (this.world.isRemote)
			return;
		if (!this.isTicking)
			this.init();
		if (this.isCompletlyFull())
			return;
		this.tick = ++this.tick % (int) (getSpeed(this.tier) * this.getFuelSpeed());
		if (this.tick == 0)
			this.interact(this.pickBlock());
	}
}