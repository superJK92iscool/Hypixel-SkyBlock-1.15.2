package net.hypixel.skyblock.items.sacks;

import com.google.common.collect.ImmutableList;

import net.hypixel.skyblock.util.ItemProperties;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

/**
 * @author MrPineapple070
 * @version 01 September 2020
 * @since 01 September 2020
 */
public class ForagingSack extends Sack {
	/**
	 * An {@link ImmutableList} of {@link Item} that this will hold.
	 */
	private static final ImmutableList<Item> items = ImmutableList.copyOf(new Item[] { Items.OAK_LOG, Items.SPRUCE_LOG,
			Items.BIRCH_LOG, Items.DARK_OAK_LOG, Items.ACACIA_LOG, Items.JUNGLE_LOG });

	/**
	 * @param type {@link SackType} of this.
	 */
	public ForagingSack(SackType type) {
		super(ItemProperties.fg1, type);
	}

	@Override
	protected ImmutableList<Item> getItems() {
		return items;
	}
}