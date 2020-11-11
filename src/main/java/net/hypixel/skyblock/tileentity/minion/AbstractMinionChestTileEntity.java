package net.hypixel.skyblock.tileentity.minion;

import java.util.Objects;

import net.hypixel.skyblock.blocks.minion.MinionChestBlock.ChestType;
import net.hypixel.skyblock.inventory.container.minion.MinionChestContainer;
import net.hypixel.skyblock.inventory.container.minion.MinionChestContainer.LargeMCC;
import net.hypixel.skyblock.inventory.container.minion.MinionChestContainer.MediumMCC;
import net.hypixel.skyblock.inventory.container.minion.MinionChestContainer.SmallMCC;
import net.hypixel.skyblock.tileentity.ModTileEntityTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.IChestLid;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
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
			super(ModTileEntityTypes.large_mcte.get(), ChestType.LARGE);
		}
	}

	public static class MediumMCTE extends AbstractMinionChestTileEntity {
		public MediumMCTE() {
			super(ModTileEntityTypes.medium_mcte.get(), ChestType.MEDIUM);
		}
	}

	public static class SmallMCTE extends AbstractMinionChestTileEntity {
		public SmallMCTE() {
			super(ModTileEntityTypes.small_mcte.get(), ChestType.SMALL);
		}
	}

	/**
	 * {@link LazyOptional} of {@link IItemHandlerModifiable}
	 */
	protected LazyOptional<IItemHandlerModifiable> chestHandler = LazyOptional.of(() -> new InvWrapper(this));

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
	 * The {@link ChestType} of this.
	 */
	public final ChestType type;

	/**
	 * Construct this.
	 *
	 * @param typeIn the {@link TileEntityChestType}
	 * @param type   the {@link ChestType}
	 */
	protected AbstractMinionChestTileEntity(TileEntityType<? extends AbstractMinionChestTileEntity> typeIn,
			ChestType type) {
		super(typeIn);
		this.type = Objects.requireNonNull(type, "Minion Chest Tile Entity must have a ChestType.");
		this.items = NonNullList.withSize(this.type.additional, ItemStack.EMPTY);
	}

	public static int calculatePlayersUsing(World worldIn, LockableTileEntity tileEntity, int x, int y, int z) {
		int i = 0;
		for (PlayerEntity player : worldIn.getEntitiesWithinAABB(PlayerEntity.class,
				new AxisAlignedBB(x - 5, y - 5, z - 5, x + 6, y + 6, z + 6)))
			if (player.openContainer instanceof MinionChestContainer)
				++i;
		return i;
	}

	public static int calculatePlayersUsingSync(World worldIn, LockableTileEntity tileEntity, int ticksSinceSync, int x,
			int y, int z, int numPlayerUsing) {
		if (!worldIn.isRemote && numPlayerUsing != 0 && (ticksSinceSync + x + y + z) % 200 == 0)
			numPlayerUsing = calculatePlayersUsing(worldIn, tileEntity, x, y, z);
		return numPlayerUsing;
	}

	public boolean canBeUsed(PlayerEntity player) {
		if (this.world.getTileEntity(this.pos) != this)
			return false;
		else
			return (player.getDistanceSq(this.pos.getX() + .5, this.pos.getY() + .5, this.pos.getZ() + .5) <= 64d);
	}

	@Override
	protected Container createMenu(int id, PlayerInventory player) {
		switch (this.type) {
		case SMALL:
		default:
			return new SmallMCC(id, player);
		case MEDIUM:
			return new MediumMCC(id, player);
		case LARGE:
			return new LargeMCC(id, player);
		}
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		return ItemStackHelper.getAndSplit(this.items, index, count);
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return this.chestHandler.cast();
		return super.getCapability(cap, side);
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
	public boolean receiveClientEvent(int id, int ChestType) {
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
		int i = this.pos.getX();
		int j = this.pos.getY();
		int k = this.pos.getZ();
		++this.ticksSinceSync;
		this.numPlayersUsing = calculatePlayersUsingSync(this.world, this, this.ticksSinceSync, i, j, k,
				this.numPlayersUsing);
		this.prevLidAngle = this.lidAngle;
		if (this.numPlayersUsing > 0 && this.lidAngle == 0f) {
			this.playSound(SoundEvents.BLOCK_CHEST_OPEN);
		}

		if (this.numPlayersUsing == 0 && this.lidAngle > 0f || this.numPlayersUsing > 0 && this.lidAngle < 1f) {
			float f1 = this.lidAngle;
			if (this.numPlayersUsing > 0) {
				this.lidAngle += .1f;
			} else {
				this.lidAngle -= .1f;
			}

			if (this.lidAngle > 1f) {
				this.lidAngle = 1f;
			}

			if (this.lidAngle < .5f && f1 >= .5f) {
				this.playSound(SoundEvents.BLOCK_CHEST_CLOSE);
			}

			if (this.lidAngle < 0f) {
				this.lidAngle = 0f;
			}
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
		super.write(compound);
		if (!this.checkLootAndWrite(compound))
			ItemStackHelper.saveAllItems(compound, this.items);
		return compound;
	}
}