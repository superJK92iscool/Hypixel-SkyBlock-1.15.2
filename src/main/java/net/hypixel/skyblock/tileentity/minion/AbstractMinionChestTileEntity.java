package net.hypixel.skyblock.tileentity.minion;

import java.util.Objects;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.hypixel.skyblock.HypixelSkyBlockMod;
import net.hypixel.skyblock.blocks.minion.MinionChestBlock;
import net.hypixel.skyblock.blocks.minion.MinionChestBlock.MinionChestType;
import net.hypixel.skyblock.inventory.container.minion.MinionChestContainer.LargeMCC;
import net.hypixel.skyblock.inventory.container.minion.MinionChestContainer.MediumMCC;
import net.hypixel.skyblock.inventory.container.minion.MinionChestContainer.SmallMCC;
import net.hypixel.skyblock.tileentity.ModTileEntityTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ChestBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.ChestType;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.IChestLid;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;
import net.minecraftforge.items.wrapper.InvWrapper;

/**
 * Serve as a base for
 * <a href="https://hypixel-skyblock.fandom.com/wiki/Minion_Chests">Minion
 * Chest</a> {@link TileEntity}
 *
 * @author MrPineapple070
 * @version 5 July 2020
 * @since 11 July 2019
 */
@OnlyIn(value = Dist.CLIENT, _interface = IChestLid.class)
public abstract class AbstractMinionChestTileEntity extends LockableLootTileEntity
implements IChestLid, ITickableTileEntity {
	public static class LargeMCTE extends AbstractMinionChestTileEntity {
		public LargeMCTE() {
			super(ModTileEntityTypes.large_mcte.get(), MinionChestType.Large);
		}
	}

	public static class MediumMCTE extends AbstractMinionChestTileEntity {
		public MediumMCTE() {
			super(ModTileEntityTypes.medium_mcte.get(), MinionChestType.Medium);
		}
	}

	public static class SmallMCTE extends AbstractMinionChestTileEntity {
		public SmallMCTE() {
			super(ModTileEntityTypes.small_mcte.get(), MinionChestType.Small);
		}
	}

	/**
	 * The angle of the chest lid
	 */
	public float lidAngle;

	/**
	 * The angle of the chest lid last tick
	 */
	public float prevLidAngle;

	/**
	 * The {@link MinionChestType} of this.
	 */
	@Nonnull
	public final MinionChestType type;

	@Nullable
	protected LazyOptional<IItemHandler> chestHandler;

	/**
	 * The {@link NonNullList} of things that {@code this} contains.
	 */
	@Nonnull
	protected NonNullList<ItemStack> items;

	/**
	 * The number of Players using {@code this}
	 */
	@Nonnegative
	protected int numPlayersUsing;

	/**
	 * A counter that is incremented once each tick. Used to determine when to
	 * determine when to sync with the client; this happens every 80 ticks. However,
	 * the number of players is not re-counted.
	 */
	protected int ticksSinceSync;

	/**
	 * Construct this.
	 *
	 * @param typeIn the {@link TileEntityType}
	 * @param type   the {@link MinionChestType}
	 */
	protected AbstractMinionChestTileEntity(TileEntityType<? extends AbstractMinionChestTileEntity> typeIn,
			MinionChestType type) {
		super(typeIn);
		this.type = Objects.requireNonNull(type, "Minion Chest Tile Entity must have a MinionChestType.");
		this.items = NonNullList.withSize(this.type.additional, ItemStack.EMPTY);
	}

	public boolean canBeUsed(PlayerEntity player) {
		if (this.world.getTileEntity(this.pos) != this)
			return false;
		else
			return (player.getDistanceSq(this.pos.getX() + .5, this.pos.getY() + .5, this.pos.getZ() + .5) <= 64d);
	}

	public void closeInventory(PlayerEntity player) {
		if (!player.isSpectator()) {
			--this.numPlayersUsing;
			this.onOpenOrClose();
		}

	}
	
	/**
	 * Creates an {@link IItemHandlerModifiable}
	 * 
	 * @return {@link IItemHandlerModifiable}
	 */
	private IItemHandlerModifiable createHandler() {
		BlockState state = this.getBlockState();
		if (!(state.getBlock() instanceof ChestBlock))
			return new InvWrapper(this);
		ChestType type = state.get(ChestBlock.TYPE);
		if (type != ChestType.SINGLE) {
			BlockPos opos = this.getPos().offset(ChestBlock.getDirectionToAttached(state));
			BlockState ostate = this.getWorld().getBlockState(opos);
			if (state.getBlock() == ostate.getBlock()) {
				ChestType otype = ostate.get(ChestBlock.TYPE);
				if (otype != ChestType.SINGLE && type != otype
						&& state.get(ChestBlock.FACING) == ostate.get(ChestBlock.FACING)) {
					TileEntity ote = this.getWorld().getTileEntity(opos);
					if (ote instanceof ChestTileEntity) {
						IInventory top = type == ChestType.RIGHT ? this : (IInventory) ote;
						IInventory bottom = type == ChestType.RIGHT ? (IInventory) ote : this;
						return new CombinedInvWrapper(new InvWrapper(top), new InvWrapper(bottom));
					}
				}
			}
		}
		return new InvWrapper(this);
	}

	@Override
	protected Container createMenu(int id, PlayerInventory player) {
		switch (this.type) {
		case Small:
			return new SmallMCC(id, player);
		case Medium:
			return new MediumMCC(id, player);
		case Large:
			return new LargeMCC(id, player);
		default:
			throw new IllegalStateException("Illegal MinionChestType " + this.type.name());
		}
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		return ItemStackHelper.getAndSplit(this.items, index, count);
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		if (!this.removed && cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			if (this.chestHandler == null)
				this.chestHandler = LazyOptional.of(this::createHandler);
			return this.chestHandler.cast();
		}
		return super.getCapability(cap, side);
	}

	@Override
	protected ITextComponent getDefaultName() {
		switch (this.type) {
		case Small:
			return MinionChestBlock.container_name_s;
		case Medium:
			return MinionChestBlock.container_name_m;
		case Large:
			return MinionChestBlock.container_name_l;
		default:
			throw new IllegalStateException("Illegal Minion Chest Type:\t" + this.type.name());
		}
	}

	@Override
	public ITextComponent getDisplayName() {
		return new StringTextComponent(this.type.name() + " Minion Chest");
	}

	@Override
	public NonNullList<ItemStack> getItems() {
		return this.items;
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public float getLidAngle(float partialTicks) {
		return MathHelper.lerp(partialTicks, this.prevLidAngle, this.lidAngle);
	}

	@Override
	public int getSizeInventory() {
		return this.items.size();
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return this.items.get(index);
	}

	@Override
	public boolean isEmpty() {
		for (final ItemStack stack : this.items)
			if (!stack.isEmpty())
				return false;
		return true;
	}

	/**
	 * Determine if {@code this} is full.
	 *
	 * @return {@code true} if full. {@code false} otherwise.
	 */
	public boolean isFull() {
		for (final ItemStack stack : this.items)
			if (stack.isEmpty())
				return false;
			else if (stack.getCount() < stack.getMaxStackSize())
				return false;
		return true;
	}

	protected void onOpenOrClose() {
		Block block = this.getBlockState().getBlock();
		if (block instanceof ChestBlock) {
			this.world.addBlockEvent(this.pos, block, 1, this.numPlayersUsing);
			this.world.notifyNeighborsOfStateChange(this.pos, block);
		}

	}

	public void openInventory(PlayerEntity player) {
		if (!player.isSpectator()) {
			if (this.numPlayersUsing < 0) {
				this.numPlayersUsing = 0;
			}

			++this.numPlayersUsing;
			this.onOpenOrClose();
		}

	}

	/**
	 * Play a sound in the world.
	 *
	 * @param sound the {@link SoundEvent} to play.
	 */
	protected void playSound(SoundEvent sound) {
		this.world.playSound((PlayerEntity) null,
				new BlockPos(this.pos.getX() + .5, this.pos.getY() + .5, this.pos.getZ() + .5), sound,
				SoundCategory.BLOCKS, .5f, this.world.rand.nextFloat() * .1f + .9f);
	}

	@Override
	public void read(CompoundNBT compound) {
		HypixelSkyBlockMod.LOGGER.info("AbstractMinionChestTileEntity Reading");
		HypixelSkyBlockMod.LOGGER.info("CompoundNBT:\t" + compound.toString());
		super.read(compound);
		this.items = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
		if (!this.checkLootAndRead(compound))
			ItemStackHelper.loadAllItems(compound, this.items);
	}

	@Override
	public boolean receiveClientEvent(int id, int ChestType) {
		HypixelSkyBlockMod.LOGGER.info("Receiving Client Event id " + id + " of chest type " + ChestType);
		if (id == 1) {
			this.numPlayersUsing = ChestType;
			return true;
		}
		return super.receiveClientEvent(id, ChestType);
	}

	@Override
	public void remove() {
		this.updateContainingBlockInfo();
		super.remove();
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		HypixelSkyBlockMod.LOGGER.info("Remove stack form slot " + index);
		return ItemStackHelper.getAndRemove(this.items, index);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		HypixelSkyBlockMod.LOGGER.info("Setting slot " + index + " to " + stack.toString());
		final ItemStack indexStack = this.items.get(index);
		final boolean flag = !stack.isEmpty() && stack.isItemEqual(indexStack)
				&& ItemStack.areItemStackTagsEqual(stack, indexStack);
		this.items.set(index, stack);
		if (stack.getCount() > this.getInventoryStackLimit())
			stack.setCount(this.getInventoryStackLimit());
		if (!flag)
			this.markDirty();
	}

	@Override
	public void setItems(NonNullList<ItemStack> itemsIn) {
		this.items = itemsIn;
	}

	@Override
	public void tick() {
		if (++this.ticksSinceSync % 20 * 4 == 0)
			this.world.addBlockEvent(this.pos, Blocks.ENDER_CHEST, 1, this.numPlayersUsing);

		this.prevLidAngle = this.lidAngle;
		int x = this.pos.getX();
		int y = this.pos.getY();
		int z = this.pos.getZ();
		if (this.numPlayersUsing > 0 && this.lidAngle == 0f)
			this.world.playSound((PlayerEntity) null, x + .5, y + .5d, z + .5, SoundEvents.BLOCK_CHEST_OPEN,
					SoundCategory.BLOCKS, 0.5F, this.world.rand.nextFloat() * .1f + .9f);
		if (this.numPlayersUsing == 0 && this.lidAngle > 0f || this.numPlayersUsing > 0 && this.lidAngle < 1f) {
			if (this.numPlayersUsing > 0)
				this.lidAngle += .1f;
			else
				this.lidAngle -= .1f;
			if (this.lidAngle > 1f)
				this.lidAngle = 1f;
			if (this.lidAngle < .5f && this.lidAngle >= .5f)
				this.world.playSound((PlayerEntity) null, x + 0.5D, (double) y + .5d, z + 0.5D,
						SoundEvents.BLOCK_CHEST_CLOSE, SoundCategory.BLOCKS, .5f,
						this.world.rand.nextFloat() * .1f + .9f);
			if (this.lidAngle < 0f)
				this.lidAngle = 0f;
		}
	}

	@Override
	public void updateContainingBlockInfo() {
		super.updateContainingBlockInfo();
		if (this.chestHandler != null) {
			this.chestHandler.invalidate();
			this.chestHandler = null;
		}
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		HypixelSkyBlockMod.LOGGER.info("Writing AbstractMinionChestTileEntity:\t" + compound.toString());
		super.write(compound);
		if (!this.checkLootAndWrite(compound))
			ItemStackHelper.saveAllItems(compound, this.items);
		return compound;
	}
}