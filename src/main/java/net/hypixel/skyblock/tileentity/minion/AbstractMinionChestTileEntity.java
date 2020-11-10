package net.hypixel.skyblock.tileentity.minion;

import java.util.Objects;

import net.hypixel.skyblock.blocks.minion.MinionChestBlock.Type;
import net.hypixel.skyblock.init.blocks.BlockInit;
import net.hypixel.skyblock.inventory.container.minion.MinionChestContainer.LargeMCC;
import net.hypixel.skyblock.inventory.container.minion.MinionChestContainer.MediumMCC;
import net.hypixel.skyblock.inventory.container.minion.MinionChestContainer.SmallMCC;
import net.hypixel.skyblock.tileentity.ModTileEntityTypes;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.IChestLid;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
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
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.InvWrapper;

/**
 * Server as a base for MinionChest TileEntity
 *
 * @author MrPineapple070
 * @version 5 July 2020
 */
public abstract class AbstractMinionChestTileEntity extends LockableLootTileEntity
		implements IChestLid, ITickableTileEntity {
	public static class LargeMCTE extends AbstractMinionChestTileEntity {
		public LargeMCTE() {
			super(ModTileEntityTypes.large_mcte.get(), Type.LARGE);
		}
	}

	public static class MediumMCTE extends AbstractMinionChestTileEntity {
		public MediumMCTE() {
			super(ModTileEntityTypes.medium_mcte.get(), Type.MEDIUM);
		}
	}

	public static class SmallMCTE extends AbstractMinionChestTileEntity {
		public SmallMCTE() {
			super(ModTileEntityTypes.small_mcte.get(), Type.SMALL);
		}
	}

	/**
	 * {@link LazyOptional} of {@link IItemHandlerModifiable}
	 */
	protected LazyOptional<IItemHandlerModifiable> handler = LazyOptional.of(() -> new InvWrapper(this));

	/**
	 * The {@link NonNullList} of things that {@code this} contains.
	 */
	protected NonNullList<ItemStack> items;

	/**
	 * The angle of the chest lid
	 */
	public float lidAngle;

	/**
	 * The number of Players using {@code this}
	 */
	protected int numPlayersUsing;

	/**
	 * The angle of the chest lid last tick
	 */
	public float prevLidAngle;

	/**
	 * A counter that is incremented once each tick. Used to determine when to
	 * determine when to sync with the client; this happens every 80 ticks. However,
	 * the number of players is not re-counted.
	 */
	protected int ticksSinceSync;

	/**
	 * The {@link Type} of this.
	 */
	protected final Type type;

	/**
	 * Construct this.
	 *
	 * @param typeIn the {@link TileEntityType}
	 * @param type   the {@link Type}
	 */
	protected AbstractMinionChestTileEntity(TileEntityType<?> typeIn, Type type) {
		super(typeIn);
		this.type = Objects.requireNonNull(type, "Minion Chest Tile Entity must have a type.");
		this.items = NonNullList.withSize(this.type.additional, ItemStack.EMPTY);
	}

	public boolean canBeUsed(PlayerEntity player) {
		if (this.world.getTileEntity(this.pos) != this)
			return false;
		else
			return (player.getDistanceSq(this.pos.getX() + 0.5D, this.pos.getY() + 0.5D,
					this.pos.getZ() + 0.5D) <= 64.0D);
	}

	@Override
	protected Container createMenu(int id, PlayerInventory player) {
		switch (this.type) {
		case SMALL:
		default:
			return new SmallMCC(id, player, this);
		case MEDIUM:
			return new MediumMCC(id, player, this);
		case LARGE:
			return new LargeMCC(id, player, this);
		}
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		return ItemStackHelper.getAndSplit(this.items, index, count);
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return this.handler.cast();
		return super.getCapability(cap, side);
	}

	/**
	 * @return {@link #type}
	 */
	public Type getChestType() {
		return this.type;
	}

	@Override
	protected ITextComponent getDefaultName() {
		return new TranslationTextComponent("container." + this.getType().getRegistryName().toString());
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
		return this.type.additional;
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
		super.read(compound);
		this.items = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
		if (!this.checkLootAndRead(compound))
			ItemStackHelper.loadAllItems(compound, this.items);
	}

	@Override
	public boolean receiveClientEvent(int id, int type) {
		if (id == 1) {
			this.numPlayersUsing = type;
			return true;
		}
		return super.receiveClientEvent(id, type);
	}

	@Override
	public void remove() {
		this.updateContainingBlockInfo();
		super.remove();
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		return ItemStackHelper.getAndRemove(this.items, index);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
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
		Block block;
		switch (this.type) {
		case SMALL:
		default:
			block = BlockInit.small_mcb.get();
		case MEDIUM:
			block = BlockInit.medium_mcb.get();
		case LARGE:
			block = BlockInit.large_mcb.get();
		}
		if (++this.ticksSinceSync % 20 * 4 == 0)
			this.world.addBlockEvent(this.pos, block, 1, this.numPlayersUsing);

		this.prevLidAngle = this.lidAngle;
		final int i = this.pos.getX();
		final int j = this.pos.getY();
		final int k = this.pos.getZ();
		if (this.numPlayersUsing > 0 && this.lidAngle == 0.0F)
			this.world.playSound((PlayerEntity) null, i + .5, j + .5, k + .5, SoundEvents.BLOCK_CHEST_OPEN,
					SoundCategory.BLOCKS, .5f, this.world.rand.nextFloat() * .1f + .9f);
		if (this.numPlayersUsing == 0 && this.lidAngle > 0f || this.numPlayersUsing > 0 && this.lidAngle < 1f) {
			final float f2 = this.lidAngle;
			if (this.numPlayersUsing > 0)
				this.lidAngle += .1f;
			else
				this.lidAngle -= .1f;
			if (this.lidAngle > 1f)
				this.lidAngle = 1f;
			if (this.lidAngle < .5f && f2 >= .5f)
				this.world.playSound((PlayerEntity) null, i + .5d, j + .5d, k + .5d, SoundEvents.BLOCK_CHEST_CLOSE,
						SoundCategory.BLOCKS, .5f, this.world.rand.nextFloat() * .1f + .9f);
			if (this.lidAngle < 0f)
				this.lidAngle = 0f;
		}
	}

	@Override
	public void updateContainingBlockInfo() {
		super.updateContainingBlockInfo();
		if (this.handler != null) {
			this.handler.invalidate();
			this.handler = null;
		}
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		super.write(compound);
		if (!this.checkLootAndWrite(compound))
			ItemStackHelper.saveAllItems(compound, this.items);
		return compound;
	}
}