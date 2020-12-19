package net.hypixel.skyblock.items;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Nullable;

import net.hypixel.skyblock.init.items.BackpackInit;
import net.hypixel.skyblock.inventory.container.AbstractBackpackContainer;
import net.hypixel.skyblock.inventory.container.ModContainerTypes;
import net.hypixel.skyblock.util.ItemProperties;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;

/**
 * Server as a base for Backpacks.
 *
 * @author MrPineapple070
 * @version 23 June 2020
 */
public abstract class AbstractBackpack extends Item {
	public static final List<RegistryObject<Item>> none = Arrays.asList(BackpackInit.small_backpack,
			BackpackInit.medium_backpack, BackpackInit.large_backpack, BackpackInit.greater_backpack);
	public static final List<RegistryObject<Item>> white = Arrays.asList(BackpackInit.white_small_backpack,
			BackpackInit.white_medium_backpack, BackpackInit.white_large_backpack, BackpackInit.white_greater_backpack);
	public static final List<RegistryObject<Item>> orange = Arrays.asList(BackpackInit.orange_small_backpack,
			BackpackInit.orange_medium_backpack, BackpackInit.orange_large_backpack, BackpackInit.orange_greater_backpack);
	public static final List<RegistryObject<Item>> magenta = Arrays.asList(BackpackInit.magenta_small_backpack,
			BackpackInit.magenta_medium_backpack, BackpackInit.magenta_large_backpack, BackpackInit.magenta_greater_backpack);
	public static final List<RegistryObject<Item>> light_blue = Arrays.asList(BackpackInit.light_blue_small_backpack,
			BackpackInit.light_blue_medium_backpack, BackpackInit.light_blue_large_backpack, BackpackInit.light_blue_greater_backpack);
	public static final List<RegistryObject<Item>> yellow = Arrays.asList(BackpackInit.yellow_small_backpack,
			BackpackInit.yellow_medium_backpack, BackpackInit.yellow_large_backpack, BackpackInit.yellow_greater_backpack);
	public static final List<RegistryObject<Item>> lime = Arrays.asList(BackpackInit.lime_small_backpack,
			BackpackInit.lime_medium_backpack, BackpackInit.lime_large_backpack, BackpackInit.lime_greater_backpack);
	public static final List<RegistryObject<Item>> pink = Arrays.asList(BackpackInit.pink_small_backpack,
			BackpackInit.pink_medium_backpack, BackpackInit.pink_large_backpack, BackpackInit.pink_greater_backpack);
	public static final List<RegistryObject<Item>> gray = Arrays.asList(BackpackInit.gray_small_backpack,
			BackpackInit.gray_medium_backpack, BackpackInit.gray_large_backpack, BackpackInit.gray_greater_backpack);
	public static final List<RegistryObject<Item>> light_gray = Arrays.asList(BackpackInit.light_gray_small_backpack,
			BackpackInit.light_gray_medium_backpack, BackpackInit.light_gray_large_backpack, BackpackInit.light_gray_greater_backpack);
	public static final List<RegistryObject<Item>> cyan = Arrays.asList(BackpackInit.cyan_small_backpack,
			BackpackInit.cyan_medium_backpack, BackpackInit.cyan_large_backpack, BackpackInit.cyan_greater_backpack);
	public static final List<RegistryObject<Item>> purple = Arrays.asList(BackpackInit.purple_small_backpack,
			BackpackInit.purple_medium_backpack, BackpackInit.purple_large_backpack, BackpackInit.purple_greater_backpack);
	public static final List<RegistryObject<Item>> blue = Arrays.asList(BackpackInit.blue_small_backpack,
			BackpackInit.blue_medium_backpack, BackpackInit.blue_large_backpack, BackpackInit.blue_greater_backpack);
	public static final List<RegistryObject<Item>> brown = Arrays.asList(BackpackInit.brown_small_backpack,
			BackpackInit.brown_medium_backpack, BackpackInit.brown_large_backpack, BackpackInit.brown_greater_backpack);
	public static final List<RegistryObject<Item>> green = Arrays.asList(BackpackInit.green_small_backpack,
			BackpackInit.green_medium_backpack, BackpackInit.green_large_backpack, BackpackInit.green_greater_backpack);
	public static final List<RegistryObject<Item>> red = Arrays.asList(BackpackInit.red_small_backpack,
			BackpackInit.red_medium_backpack, BackpackInit.red_large_backpack, BackpackInit.red_greater_backpack);
	public static final List<RegistryObject<Item>> black = Arrays.asList(BackpackInit.black_small_backpack,
			BackpackInit.black_medium_backpack, BackpackInit.black_large_backpack, BackpackInit.black_greater_backpack);

	/**
	 * The {@link DyeColor} of this.
	 */
	@Nullable
	protected final DyeColor color;

	/**
	 * A {@link NonNullList} of {@link ItemStack} that this will hold.
	 */
	protected NonNullList<ItemStack> items;

	/**
	 * The {@link Type} of this.
	 */
	protected final Type type;

	public AbstractBackpack(Type type, @Nullable DyeColor color) {
		super(ItemProperties.ms64);
		this.type = type;
		this.color = color;
		this.items = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
	}

	protected static ItemStack getColoredItemStack(AbstractBackpack backpack) {
		return new ItemStack(getItem(backpack));
	}

	protected static Item getItem(AbstractBackpack backpack) {
		int index;
		switch (backpack.type) {
		case Small:
			index = 0;
			break;
		case Medium:
			index = 1;
			break;
		case Large:
			index = 2;
			break;
		case Greater:
			index = 3;
			break;
		default:
			throw new IllegalStateException("Illegal AbstractBackpack.Type:\t" + backpack.type.name());
		}

		switch (backpack.color) {
		case BLACK:
			return null;
		case BLUE:
			return null;
		case BROWN:
			return null;
		case CYAN:
			return null;
		case GRAY:
			return null;
		case GREEN:
			return null;
		case LIGHT_BLUE:
			return null;
		case LIGHT_GRAY:
			return null;
		case LIME:
			return null;
		case MAGENTA:
			return null;
		case ORANGE:
			return null;
		case PINK:
			return null;
		case PURPLE:
			return null;
		case RED:
			return null;
		case WHITE:
			return null;
		case YELLOW:
			return null;
		default:
			return none.get(index).get();
		}
	}

	/**
	 * @return {@link #color}
	 */
	@Nullable
	public DyeColor getColor() {
		return this.color;
	}

	/**
	 * @param player {@link PlayerEntity} opening this.
	 * @return {@link AbstractBackpackContainer} associated with this.
	 */
	public AbstractBackpackContainer getContainer(PlayerEntity player) {
		switch (this.type) {
		case Small:
		default:
			return ModContainerTypes.small_backpack.get().create(player.openContainer.windowId, player.inventory);
		case Medium:
			return ModContainerTypes.medium_backpack.get().create(player.openContainer.windowId, player.inventory);
		case Large:
			return ModContainerTypes.large_backpack.get().create(player.openContainer.windowId, player.inventory);
		case Greater:
			return ModContainerTypes.greater_backpack.get().create(player.openContainer.windowId, player.inventory);
		}
	}

	/**
	 * @return {@link #items}
	 */
	public NonNullList<ItemStack> getItems() {
		return this.items;
	}

	/**
	 * @return {@link Type#size}
	 */
	public int getSizeInventory() {
		return this.type.size;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		final ItemStack heldItem = playerIn.getHeldItem(handIn);
		if (worldIn.isRemote)
			return ActionResult.resultPass(heldItem);
		final AbstractBackpack backpack = (AbstractBackpack) heldItem.getItem();
		playerIn.openContainer = backpack.getContainer(playerIn);
		return ActionResult.resultSuccess(heldItem);
	}

	public static enum Type {
		Greater(36), Large(27), Medium(18), Small(9);

		/**
		 * The size of this.
		 */
		public final int size;

		private Type(int size) {
			this.size = size;
		}
	}

	public static class SmallBackpack extends AbstractBackpack {
		public SmallBackpack(DyeColor color) {
			super(Type.Small, color);
		}
	}

	public static class MediumBackpack extends AbstractBackpack {
		public MediumBackpack(DyeColor color) {
			super(Type.Medium, color);
		}
	}

	public static class LargeBackpack extends AbstractBackpack {
		public LargeBackpack(DyeColor color) {
			super(Type.Large, color);
		}
	}

	public static class GreaterBackpack extends AbstractBackpack {
		public GreaterBackpack(DyeColor color) {
			super(Type.Greater, color);
		}
	}
}