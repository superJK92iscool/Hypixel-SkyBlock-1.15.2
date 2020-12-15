package net.hypixel.skyblock.items;

import javax.annotation.Nullable;

import net.hypixel.skyblock.inventory.container.AbstractBackpackContainer;
import net.hypixel.skyblock.inventory.container.ModContainerTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

/**
 * Server as a base for Backpacks.
 *
 * @author MrPineapple070
 * @version 23 June 2020
 */
public abstract class AbstractBackpack extends Item {
	public static class GreaterBackpack extends AbstractBackpack {
		public GreaterBackpack(Properties properties, DyeColor color) {
			super(properties, Type.Greater, color);
		}
	}

	public static class LargeBackpack extends AbstractBackpack {
		public LargeBackpack(Properties properties, DyeColor color) {
			super(properties, Type.Large, color);
		}
	}

	public static class MediumBackpack extends AbstractBackpack {
		public MediumBackpack(Properties properties, DyeColor color) {
			super(properties, Type.Medium, color);
		}
	}

	public static class SmallBackpack extends AbstractBackpack {
		public SmallBackpack(Properties properties, DyeColor color) {
			super(properties, Type.Small, color);
		}
	}

	/**
	 * Handle the different types of backpacks.
	 */
	public enum Type {
		Greater(36), Large(27), Medium(18), Small(9);

		/**
		 * The size of this.
		 */
		public final int size;

		private Type(int size) {
			this.size = size;
		}
	}

	protected static ItemStack getColoredItemStack(@Nullable DyeColor colorIn) {
		return new ItemStack(getItemByColor(colorIn));
	}

	protected static Item getItemByColor(@Nullable DyeColor colorIn) {
		if (colorIn == null)
			return Items.SHULKER_BOX;
		else
			switch (colorIn) {
			case WHITE:
			default:
				return Items.WHITE_SHULKER_BOX;
			case ORANGE:
				return Items.ORANGE_SHULKER_BOX;
			case MAGENTA:
				return Items.MAGENTA_SHULKER_BOX;
			case LIGHT_BLUE:
				return Items.LIGHT_BLUE_SHULKER_BOX;
			case YELLOW:
				return Items.YELLOW_SHULKER_BOX;
			case LIME:
				return Items.LIME_SHULKER_BOX;
			case PINK:
				return Items.PINK_SHULKER_BOX;
			case GRAY:
				return Items.GRAY_SHULKER_BOX;
			case LIGHT_GRAY:
				return Items.LIGHT_GRAY_SHULKER_BOX;
			case CYAN:
				return Items.CYAN_SHULKER_BOX;
			case PURPLE:
				return Items.PURPLE_SHULKER_BOX;
			case BLUE:
				return Items.BLUE_SHULKER_BOX;
			case BROWN:
				return Items.BROWN_SHULKER_BOX;
			case GREEN:
				return Items.GREEN_SHULKER_BOX;
			case RED:
				return Items.RED_SHULKER_BOX;
			case BLACK:
				return Items.BLACK_SHULKER_BOX;
			}
	}

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

	/**
	 * Construct this
	 *
	 * @param properties {@link Properties}
	 * @param type       {@link Type}
	 * @param color      {@link DyeColor}
	 */
	public AbstractBackpack(Properties properties, Type type, @Nullable DyeColor color) {
		super(properties);
		this.type = type;
		this.color = color;
		this.items = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
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
		final AbstractBackpack backpack = (AbstractBackpack) heldItem.getItem();
		playerIn.openContainer = backpack.getContainer(playerIn);
		return new ActionResult<>(ActionResultType.SUCCESS, heldItem);
	}
}