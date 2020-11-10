package net.hypixel.skyblock.tileentity.minion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.hypixel.skyblock.HypixelSkyBlockMod;
import net.hypixel.skyblock.init.items.ItemInit;
import net.hypixel.skyblock.items.minion.MinionFuelItem;
import net.hypixel.skyblock.items.minion.SellerItem;
import net.hypixel.skyblock.items.minion.UpgradeItem;
import net.hypixel.skyblock.util.ItemMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.crash.ReportedException;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IClearable;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants.BlockFlags;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.InvWrapper;

/**
 * A {@link TileEntity} for all Minions.<br>
 * <a href=https://hypixel-skyblock.fandom.com/wiki/Minions>Click here</a> for a
 * description of Minions.
 *
 * @author MrPineapple070
 * @version 5 July 2020
 */
public abstract class AbstractMinionTileEntity extends LockableLootTileEntity
		implements ITickableTileEntity, IClearable, INamedContainerProvider {
	/**
	 * The tier of each Minion
	 */
	public enum MinionTier {
		I(1, 0x0), II(3, 0x1), III(3, 0x2), IV(6, 0x3), IX(12, 0x8), V(6, 0x4), VI(9, 0x5), VII(9, 0x6), VIII(12, 0x7),
		X(15, 0x9), XI(15, 0xa);

		/**
		 * A conversion between this and an integer.
		 */
		@Nonnegative
		public final int asInt;

		/**
		 * The amount of slots for each tier.
		 */
		@Nonnegative
		public final int size;
		
		private MinionTier(@Nonnegative int size, @Nonnegative int asInt) {
			this.size = size;
			this.asInt = asInt;
		}
	}

	/**
	 * Fuel Slot index.
	 */
	protected static final int FUEL_INDEX = 0x0;

	/**
	 * Seller Slot index.
	 */
	protected static final int SELLER_INDEX = 0x1;

	/**
	 * Upgrade 1 Slot index.
	 */
	protected static final int UPGRADE_1_INDEX = 0x2;

	/**
	 * Upgrade 2 Slot index.
	 */
	protected static final int UPGRADE_2_INDEX = 0x3;
	
	/**
	 * Inventory start.
	 */
	protected static final int INVENTORY_START = 0x4;
	
	/**
	 * Expanded differentials from center {@link BlockPos}
	 */
	protected static final int[] expanded_size = { -3, -2, -1, 0, 1, 2, 3 };
	
	/**
	 * Default differentials from the center {@link BlockPos}
	 */
	protected static final int[] default_size = {-2, -1, 0, 1, 2};
	
	/**
	 * A Random Number Generator.
	 */
	@Nonnull
	protected static final Random rand = new Random();
	
	/**
	 * A {@link List} of {@link BlockPos} of all surrounding {@link Blocks#AIR}
	 */
	@Nonnull
	protected final List<BlockPos> airSurround = new ArrayList<>();
	
	/**
	 * A {@link List} of {@link BlockPos} of all surrounding valid {@code BlockPos}
	 */
	@Nonnull
	protected final List<BlockPos> validSurround = new ArrayList<>();

	/**
	 * Index of the current item to merge {@link ItemStack}.
	 */
	protected int currentItem;

	/**
	 * Determine if {@code this} is ticking.
	 */
	protected boolean isTicking = false;

	/**
	 * The {@link AbstractMinionChestTileEntity} next to {@code this}
	 */
	@Nullable
	protected AbstractMinionChestTileEntity mcte;

	/**
	 * The list of everything this will hold.
	 */
	@Nonnull
	public NonNullList<ItemStack> minionContents;

	/**
	 * {@link LazyOptional} of {@link IItemHandlerModifiable}
	 */
	@Nonnull
	protected LazyOptional<IItemHandlerModifiable> minionHandler = LazyOptional.of(() -> new InvWrapper(this));

	/**
	 * The Number of Players Using {@code this}
	 */
	@Nonnegative
	protected int numPlayersUsing;

	/**
	 * A {@link List} of {@link BlockPos} of all surrounding {@code BlockPos}
	 */
	@Nonnull
	protected final BlockPos[][][] surround;

	/**
	 * The current game tick of {@code this}
	 */
	@Nonnegative
	protected int tick, fuelTick;

	/**
	 * The tier of {@code this}
	 */
	@Nonnull
	protected final MinionTier tier;

	/**
	 * X coordinate of this.
	 */
	public int x;

	/**
	 * Y coordinate of this.
	 */
	public int y;

	/**
	 * Z coordinate of this.
	 */
	public int z;

	/**
	 * Create an Abstract {@link TileEntity} for {@code this}.
	 *
	 * @param typeIn {@code TileEntity} of this.
	 * @param tier   {@link MinionTier} of this.
	 */
	protected AbstractMinionTileEntity(TileEntityType<? extends AbstractMinionTileEntity> typeIn, MinionTier tier) {
		super(typeIn);
		this.tier = Objects.requireNonNull(tier, "Illegal Minion Tier");
		this.minionContents = NonNullList.withSize(4 + this.tier.size, ItemStack.EMPTY);
		this.surround = this.initSurround();
	}

	/**
	 * Adds the stack to the specified slot in the list.
	 *
	 * @param slotIn the specified slot
	 * @param stack  the {@link ItemStack} to add.
	 * @return {@code true} if {@code stack} has been successfully added.
	 *         {@code false} if it's not possible to place the entire stack in the
	 *         inventory.
	 */
	protected boolean add(int slotIn, ItemStack stack) {
		HypixelSkyBlockMod.LOGGER.info("Adding " + stack.toString() + " to slot " + slotIn);
		if (stack.isEmpty())
			return false;
		try {
			int i;
			while (true) {
				i = stack.getCount();
				if (slotIn == -1)
					stack.setCount(this.storePartialItemStack(stack));
				else
					stack.setCount(this.addResource(slotIn, stack));
				if (stack.isEmpty() || stack.getCount() >= i)
					break;
			}
			if (stack.getCount() == i) {
				stack.setCount(0);
				return true;
			} else
				return stack.getCount() < i;
		} catch (final Throwable throwable) {
			final CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Adding item to inventory");
			final CrashReportCategory crashreportcategory = crashreport.makeCategory("Item being added");
			crashreportcategory.addDetail("Registry Name", () -> String.valueOf(stack.getItem().getRegistryName()));
			crashreportcategory.addDetail("Item Class", () -> stack.getItem().getClass().getName());
			crashreportcategory.addDetail("Item ID", Item.getIdFromItem(stack.getItem()));
			crashreportcategory.addDetail("Item data", stack.getDamage());
			crashreportcategory.addDetail("Item name", () -> stack.getDisplayName().getString());
			throw new ReportedException(crashreport);
		}
	}

	/**
	 * Adds drops to {@link #minionContents}
	 *
	 * @param drops the items to add.
	 */
	protected void addItemStacks(ItemStack... drops) {
		HypixelSkyBlockMod.LOGGER.info("Adding " + Arrays.deepToString(drops) + " to inventory.");
		this.compactor();
		this.autoSmelt();
		this.superCompactor();
		for (ItemStack stack : drops)
			this.add(-1, stack);
		this.diamondSpreading();
		HypixelSkyBlockMod.LOGGER.info(this.minionContents.toString());
	}

	/**
	 * Adds drops to {@link #minionContents}
	 *
	 * @param drops the items to add.
	 */
	protected void addItemStacks(List<ItemStack> drops) {
		this.addItemStacks(drops.toArray(new ItemStack[drops.size()]));
	}

	/**
	 * Add {@link ItemStack} to {@link #minionContents}. Also handle if the
	 * {@code ItemStack} has a count more than max stack.
	 *
	 * @param index the index to add to
	 * @param stack the {@code ItemStack} to add.
	 * @return the number of items remainding after capping an {@code ItemStack}.
	 */
	protected int addResource(int index, ItemStack stack) {
		HypixelSkyBlockMod.LOGGER.info("Adding " + stack.toString() + " to slot " + index);
		int i = stack.getCount();
		ItemStack itemstack = this.getStackInSlot(index);
		if (itemstack.isEmpty()) {
			itemstack = stack.copy();
			itemstack.setCount(0);
			if (stack.hasTag())
				itemstack.setTag(stack.getTag().copy());
			this.setInventorySlotContents(index, itemstack);
		}

		int j = i;
		if (i > itemstack.getMaxStackSize() - itemstack.getCount())
			j = itemstack.getMaxStackSize() - itemstack.getCount();
		if (j > this.getInventoryStackLimit() - itemstack.getCount())
			j = this.getInventoryStackLimit() - itemstack.getCount();
		if (j == 0)
			return i;
		else {
			i -= j;
			itemstack.grow(j);
			itemstack.setAnimationsToGo(5);
			return i;
		}
	}

	/**
	 * Handle {@link ItemInit#auto_smelter}.<br>
	 * Certain smeltable items will not smelt through this method as
	 * {@link #superCompactor()} takes priority.
	 * <a href="https://hypixel-skyblock.fandom.com/wiki/Auto_Smelter">Auto
	 * Smelter.</a>
	 */
	protected void autoSmelt() {
		if (!this.hasUpgrade(ItemInit.auto_smelter.get()))
			return;
		HypixelSkyBlockMod.LOGGER.info("Auto smelting contents");
		final NonNullList<ItemStack> inv = this.getInventory();
		for (final ItemStack stack : inv) {
			final Item smelt = ItemMap.smeltMap.get(stack.getItem());
			if (smelt == null)
				continue;
			this.decrStackSize(inv.lastIndexOf(stack), 1);
			this.add(-1, new ItemStack(smelt, 1));
		}
	}

	/**
	 * Determine if two {@link ItemStack} can merge into one.
	 *
	 * @param stack1 one {@code ItemStack}
	 * @param stack2 another {@code ItemStack}
	 * @return {@code true} if can merge.<br>
	 *         {@code false} otherwise.
	 */
	protected boolean canMergeStacks(ItemStack stack1, ItemStack stack2) {
		return !stack1.isEmpty() && this.stackEqualExact(stack1, stack2) && stack1.isStackable()
				&& stack1.getCount() < stack1.getMaxStackSize() && stack1.getCount() < this.getInventoryStackLimit();
	}

	@Override
	public void clear() {
		super.clear();
		this.minionContents.clear();
	}

	/**
	 * Handle {@link ItemInit#compactor}.<br>
	 * Sometimes this will do nothing unless paired with
	 * <a href="https://hypixel-skyblock.fandom.com/wiki/Diamond_Spreading">diamond
	 * spreading</a>.<br>
	 * <a href="https://hypixel-skyblock.fandom.com/wiki/Compactor">Compactor</a>
	 */
	@SuppressWarnings("unlikely-arg-type")
	protected void compactor() {
		if (!this.hasUpgrade(ItemInit.compactor.get()))
			return;
		HypixelSkyBlockMod.LOGGER.info("Compacting contents");
		for (final Item item : this.getCompactor()) {
			Integer count = ItemMap.compCountMap.get(item);
			count = count == null ? 9 : count;
			if (this.count(item) == count) {
				this.decrStackSize(this.minionContents.lastIndexOf(item), count);
				this.add(-1, new ItemStack(ItemMap.compMap.get(item), 1));
			}
		}
	}

	/**
	 * Handle consuming fuel that this has.
	 *
	 * @param fuel the {@link MinionFuelItem} to consume.
	 */
	protected void consumeFuel(MinionFuelItem fuel) {
		HypixelSkyBlockMod.LOGGER.info("Consuming fuel: " + fuel.toString());
		final int time = fuel.getBurnTime();
		if (time == -1)
			return;
		this.fuelTick = (this.fuelTick + 1) % time;
		if (this.fuelTick == 0)
			this.decrStackSize(FUEL_INDEX, 1);
	}

	@Override
	protected abstract Container createMenu(int id, PlayerInventory player);

	@Override
	public ItemStack decrStackSize(int index, int count) {
		return ItemStackHelper.getAndSplit(this.minionContents, index, count);
	}

	/**
	 * Handle {@link ItemInit#diamond_spreading}<br>
	 * <a href="https://hypixel-skyblock.fandom.com/wiki/Diamond_Spreading">Diamond
	 * Spreading</a>
	 */
	protected void diamondSpreading() {
		HypixelSkyBlockMod.LOGGER.info("Diamond Spreading Loggic");
		if (this.hasUpgrade(ItemInit.diamond_spreading.get()) && rand.nextInt(10) == 0)
			this.add(-1, new ItemStack(Items.DIAMOND));
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return this.minionHandler.cast();
		return super.getCapability(cap, side);
	}

	/**
	 * Determine all possible {@link Item} that could be compacted using the
	 * {@link ItemInit#compactor}.
	 *
	 * @return a list of these {@code Item}.
	 */
	protected abstract Item[] getCompactor();

	@Override
	protected ITextComponent getDefaultName() {
		return new TranslationTextComponent("container." + this.getType().getRegistryName().toString().toLowerCase());
	}

	@Override
	public abstract ITextComponent getDisplayName();

	/**
	 * Gets First Empty {@link ItemStack}
	 *
	 * @return the index of empty stack.
	 */
	protected int getFirstEmptyStack() {
		for (int i = 4; i < this.minionContents.size(); ++i)
			if (this.minionContents.get(i).isEmpty())
				return i;
		return -1;
	}

	/**
	 * @return the {@link ItemStack} in the Fuel Slot.
	 */
	protected ItemStack getFuel() {
		return this.getStackInSlot(FUEL_INDEX);
	}

	/**
	 * Determines the fuel speed of the Item in the fuel slot.
	 *
	 * @return the fuel speed. 1 if no fuel.
	 */
	protected double getFuelSpeed() {
		final ItemStack fuel = this.getFuel();
		if (fuel.isEmpty())
			return 1d;
		final Item fuelItem = fuel.getItem();
		if (fuelItem instanceof MinionFuelItem) {
			this.consumeFuel((MinionFuelItem) fuelItem);
			return ((MinionFuelItem) fuelItem).getSpeed();
		}
		throw new IllegalStateException("Found impossible item in fuel slot: " + fuelItem.getRegistryName().toString());
	}

	/**
	 * @return a list of the main inventory.
	 */
	protected NonNullList<ItemStack> getInventory() {
		final NonNullList<ItemStack> inv = NonNullList.withSize(this.getSizeInventory() - 4, ItemStack.EMPTY);
		for (int i = INVENTORY_START; i < this.getSizeInventory(); i++)
			inv.set(i - 4, this.minionContents.get(i));
		return inv;
	}

	@Override
	public NonNullList<ItemStack> getItems() {
		return this.minionContents;
	}

	/**
	 * @return {@link #mcte}
	 */
	public AbstractMinionChestTileEntity getMcte() {
		return this.mcte;
	}

	/**
	 * @return the {@link ItemStack} in the Seller Slot.
	 */
	protected ItemStack getSeller() {
		return this.getStackInSlot(SELLER_INDEX);
	}

	@Override
	public int getSizeInventory() {
		return this.minionContents.size();
	}

	/**
	 * @return {@link SoundEvent} to play when interacting.
	 */
	protected abstract SoundEvent getSoundEvent();

	@Override
	public ItemStack getStackInSlot(int index) {
		return this.minionContents.get(index);
	}

	/**
	 * @return {@link BlockState} to place when interacting.
	 */
	protected abstract BlockState getState();

	/**
	 * Determine all possible {@link Item} that could be super compacted.<br>
	 * This method assumes that all {@code Item} are keys in
	 * {@link ItemMap#enchMap}.
	 *
	 * @return a list of these {@code Item}.
	 */
	protected abstract Item[] getSuperCompactor();

	/**
	 * Determine the indexes of 160 {@link Item} to remove.
	 *
	 * @param item {@link Item} to find
	 * @return the indexes
	 */
	protected int[] getSuperCompIndex(Item item) {
		HypixelSkyBlockMod.LOGGER.info("Finding super compactor indexes for: " + item.getRegistryName().toString());
		final int[] indexs = new int[3];
		int index = 0;
		boolean half = false;
		for (int i = this.minionContents.size() - 1; i > -1; i--) {
			final ItemStack stack = this.minionContents.get(i);
			if (stack.getItem() == item)
				switch (stack.getCount()) {
				case 32:
					if (!half) {
						indexs[index] = i;
						index++;
						half = true;
					}
					break;
				case 64:
					indexs[index] = i;
					index++;
					break;
				default:
					continue;
				}
			else
				continue;
		}
		return indexs;
	}

	/**
	 * @return {@link #tier}
	 */
	public MinionTier getTier() {
		return this.tier;
	}

	@Override
	public SUpdateTileEntityPacket getUpdatePacket() {
		final CompoundNBT nbt = new CompoundNBT();
		this.write(nbt);
		return new SUpdateTileEntityPacket(this.getPos(), 1, nbt);
	}

	@Override
	public CompoundNBT getUpdateTag() {
		return this.write(new CompoundNBT());
	}

	/**
	 * @return the {@link ItemStack} in both Upgrade Slots.
	 */
	protected ItemStack[] getUpgrades() {
		return new ItemStack[] { this.getStackInSlot(UPGRADE_1_INDEX), this.getStackInSlot(UPGRADE_2_INDEX) };
	}

	@Override
	public void handleUpdateTag(CompoundNBT tag) {
		this.read(tag);
	}

	/**
	 * Determines if {@code this} has fuel.
	 *
	 * @return {@code true} if {@code this} has fuel.<br>
	 *         {@code false} otherwise.
	 */
	protected boolean hasFuel() {
		return !this.getFuel().isEmpty();
	}

	/**
	 * Determines if {@code this} has a seller.
	 *
	 * @return {@code true} if {@code this} has fuel.<br>
	 *         {@code false} otherwise.
	 */
	protected boolean hasSeller() {
		return !this.getSeller().isEmpty();
	}

	/**
	 * Determines if {@code this} has the upgrade {@code upgrade}
	 *
	 * @param upgrade is the upgrade
	 * @return {@code true} if this has {@code upgrade}<br>
	 *         {@code flase} otherwise.
	 */
	protected boolean hasUpgrade(Item upgrade) {
		return this.getUpgrades()[0].getItem() == upgrade || this.getUpgrades()[1].getItem() == upgrade;
	}

	/**
	 * Initialize all variables.
	 */
	public void init() {
		this.isTicking = true;
		this.x = this.pos.getX();
		this.y = this.pos.getY();
		this.z = this.pos.getZ();
		this.tick = 0;
		this.fuelTick = 0;
		this.setSurround();
		HypixelSkyBlockMod.LOGGER.info(this.toString());
	}
	
	public void init(BlockPos pos) {
		this.pos = pos;
		this.init();
	}

	/**
	 * Create a 3d Array of all {@link BlockPos} that surround this.<br>
	 * This Array is 3d as certain minions interact with blocks that require these 3
	 * dimensions.
	 *
	 * @return the 3d Array with the correct bounds.
	 */
	protected abstract BlockPos[][][] initSurround();

	/**
	 * Interact with {@code pos}
	 *
	 * @param pos is the result of {@link #pickBlock()}
	 * @return {@code true} if a {@link Block} has been changed.<br>
	 *         {@code false} if a {@link Block} has not been changed.
	 */
	protected boolean interact(BlockPos pos) {
		if (pos == null)
			return false;
		HypixelSkyBlockMod.LOGGER.info("Interacting with " + pos.toString());
		final BlockState state = this.world.getBlockState(pos);
		if (state.isAir(this.world, pos)) {
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
	 * @param validBlocks A {@link NonNullList} of valid {@code Block} that this can
	 *                    interact with
	 * @return {@code true} if {@code Block} is valid.<br>
	 *         {@code false} otherwise.
	 */
	protected boolean isBlockValid(@Nonnull Block block, @Nonnull NonNullList<Block> validBlocks) {
		if (validBlocks.size() < 1)
			throw new IllegalArgumentException("validBlocks must have at least one element");
		return validBlocks.contains(block);
	}

	/**
	 * Determine if {@code this} and any additional storage are full.
	 *
	 * @return {@code true} if full. {@code false} otherwise
	 */
	public boolean isCompletlyFull() {
		if (this.isInventoryFull())
			if (this.mcte != null)
				return this.mcte.isFull();
		return this.hasSeller();
	}

	@Override
	public boolean isEmpty() {
		for (final ItemStack stack : this.minionContents)
			if (!stack.isEmpty())
				return false;
		return true;
	}

	/**
	 * Determine if {@code this} is fill.
	 *
	 * @return {@code true} if full. {@code false} otherwise.
	 */
	protected boolean isInventoryFull() {
		for (final ItemStack stack : this.getInventory())
			if (stack.isEmpty())
				return false;
			else if (stack.getCount() < stack.getMaxStackSize())
				return false;
		return true;
	}

	@Override
	public boolean isItemValidForSlot(int index, @Nonnull ItemStack stack) {
		final Item item = stack.getItem();
		switch (index) {
		case FUEL_INDEX:
			return item instanceof MinionFuelItem;
		case SELLER_INDEX:
			return item instanceof SellerItem;
		case UPGRADE_1_INDEX:
		case UPGRADE_2_INDEX:
			return item instanceof UpgradeItem;
		default:
			return false;
		}
	}

	@Override
	public boolean isUsableByPlayer(@Nonnull PlayerEntity player) {
		if (this.world.getTileEntity(this.pos) != this)
			return false;
		return player.getDistanceSq(this.pos.getX() + .5, this.pos.getY() + .5, this.pos.getZ() + .5) <= 64.0;
	}

	@Override
	public void markDirty() {
		super.markDirty();
		BlockState state = this.getBlockState();
		this.world.notifyBlockUpdate(this.pos, state, state, BlockFlags.BLOCK_UPDATE);
	}

	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
		this.read(pkt.getNbtCompound());
	}

	/**
	 * Pick a random valid {@link BlockPos} to interact with.<br>
	 * This method depends heavily on the type of minion.<br>
	 * This method will return {@code null} if no valid blocks are chosen.
	 *
	 * @return {@link BlockPos} picked.
	 */
	protected abstract BlockPos pickBlock();

	/**
	 * Play a sound in the world.
	 *
	 * @param sound the {@link SoundEvent} to play.
	 */
	protected void playSound(SoundEvent sound) {
		this.world.playSound(null, this.pos.getX() + .5, this.pos.getY() + .5, this.pos.getZ() + .5, sound,
				SoundCategory.BLOCKS, .5f, this.world.rand.nextFloat() * .1f + .9f);
	}

	@Override
	public void read(CompoundNBT compound) {
		HypixelSkyBlockMod.LOGGER.info("Minion NBT Reading...");
		super.read(compound);
		this.minionContents = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, this.minionContents);
	}

	@Override
	public boolean receiveClientEvent(int id, int type) {
		if (id == 1)
			return true;
		return super.receiveClientEvent(id, type);
	}

	@Override
	public void remove() {
		super.remove();
		if (this.minionHandler != null)
			this.minionHandler.invalidate();
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		return ItemStackHelper.getAndRemove(this.minionContents, index);
	}

	/**
	 * Removes a stack from the indexes.
	 *
	 * @param indexes a list of indexes
	 * @return primative type array of {@link ItemStack} removed.
	 */
	protected ItemStack[] removeStacksFromSlot(int... indexes) {
		final ItemStack[] temp = new ItemStack[indexes.length];
		for (int i = 0; i < indexes.length; i++)
			temp[i] = this.getStackInSlot(i).getCount() == 32 ? this.decrStackSize(i, 32) : this.removeStackFromSlot(i);
		return temp;
	}

	/**
	 * Determine all surround {@link BlockPos} that is Air
	 */
	protected void setAirSurround() {
		HypixelSkyBlockMod.LOGGER.info("Finding Air in valid BlockPos");
		this.airSurround.clear();
		for (final BlockPos pos : this.validSurround)
			if (this.world.getBlockState(pos).isAir(this.world, pos))
				this.airSurround.add(pos);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		final ItemStack indexStack = this.minionContents.get(index);
		final boolean flag = !stack.isEmpty() && stack.isItemEqual(indexStack)
				&& ItemStack.areItemStackTagsEqual(stack, indexStack);
		this.minionContents.set(index, stack);
		if (stack.getCount() > this.getInventoryStackLimit())
			stack.setCount(this.getInventoryStackLimit());
		if (!flag)
			this.markDirty();
	}

	@Override
	public void setItems(NonNullList<ItemStack> itemsIn) {
		this.minionContents = Objects.requireNonNull(itemsIn, "New minionContents must be non-null.");
	}

	/**
	 * Changes {@link #mcte}
	 *
	 * @param mcte the new {@link AbstractMinionChestTileEntity}
	 */
	public void setMcte(AbstractMinionChestTileEntity mcte) {
		this.mcte = mcte;
	}

	/**
	 * Determine all surrounding {@link BlockPos}.<br>
	 * Due to the effects of {@link ItemInit#minion_expander}, this method will
	 * automatically gather the blocks from the enlarged area
	 */
	protected abstract void setSurround();

	/**
	 * Determine all valid surrounding {@link BlockPos}
	 */
	protected abstract void setValidSurround();

	/**
	 * Checks item, NBT, and meta if the item is not damageable.
	 *
	 * @param stack1 one {@link ItemStack}
	 * @param stack2 another {@link ItemStack}
	 * @return {@code true} if equal.<br>
	 *         {@code false} otherwise.
	 */
	protected boolean stackEqualExact(ItemStack stack1, ItemStack stack2) {
		return stack1.getItem() == stack2.getItem() && ItemStack.areItemStackTagsEqual(stack1, stack2);
	}

	/**
	 * Stores a stack in the player's inventory. It first tries to place it in the
	 * selected slot in the player's hotbar, then the offhand slot, then any
	 * available/empty slot in the player's inventory.
	 *
	 * @param itemStackIn {@link ItemStack} to add.
	 * @return index of stack
	 */
	protected int storeItemStack(ItemStack itemStackIn) {
		if (this.canMergeStacks(this.getStackInSlot(this.currentItem), itemStackIn))
			return this.currentItem;
		for (int i = 4; i < this.minionContents.size(); ++i)
			if (this.canMergeStacks(this.minionContents.get(i), itemStackIn))
				return i;
		return -1;
	}

	/**
	 * This function stores as many items of an {@link ItemStack} as possible in a
	 * matching slot and returns the quantity of left over items.
	 *
	 * @param itemStackIn {@code ItemStack} to store.
	 * @return the quantity of left over {@code Item}
	 */
	protected int storePartialItemStack(ItemStack itemStackIn) {
		int i = this.storeItemStack(itemStackIn);
		if (i == -1)
			i = this.getFirstEmptyStack();
		return i == -1 ? itemStackIn.getCount() : this.addResource(i, itemStackIn);
	}

	/**
	 * Handle {@link ItemInit#super_compactor_3000}.<br>
	 * <a href="https://hypixel-skyblock.fandom.com/wiki/Super_Compactor_3000">Super
	 * Compactor 3000</a>
	 */
	protected void superCompactor() {
		if (!this.hasUpgrade(ItemInit.super_compactor_3000.get()))
			return;
		HypixelSkyBlockMod.LOGGER.info("Super Compacting contents");
		for (final Item item : this.getSuperCompactor()) {
			Integer count = ItemMap.enchCountMap.get(item);
			count = count == null ? 160 : count;
			if (this.count(item) == count) {
				this.removeStacksFromSlot(this.getSuperCompIndex(item));
				this.add(-1, new ItemStack(ItemMap.enchMap.get(item), ItemMap.enchCountMap.get(item)));
			}
		}
	}

	@Override
	public abstract void tick();

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [x=" + this.x + ", y=" + this.y + ", z=" + this.z + ", tier="
				+ this.tier + "]";
	}

	@Override
	public void updateContainingBlockInfo() {
		super.updateContainingBlockInfo();
		if (this.minionHandler != null) {
			this.minionHandler.invalidate();
			this.minionHandler = null;
		}
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		HypixelSkyBlockMod.LOGGER.info("Minion NBT Writting...");
		super.write(compound);
		ItemStackHelper.saveAllItems(compound, this.minionContents);
		return compound;
	}
}