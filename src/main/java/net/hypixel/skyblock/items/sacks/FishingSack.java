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
public class FishingSack extends Sack {
	/**
	 * An {@link ImmutableList} of {@link Item} that this will hold.
	 */
	private static final ImmutableList<Item> items = ImmutableList
			.copyOf(new Item[] { Items.COD, Items.SALMON, Items.PUFFERFISH, Items.PRISMARINE_SHARD,
					Items.PRISMARINE_CRYSTALS, Items.CLAY_BALL, Items.LILY_PAD, Items.INK_SAC, Items.SPONGE });

	public FishingSack(SackType type) {
		super(ItemProperties.fi1, type);
	}

	@Override
	protected ImmutableList<Item> getItems() {
		return items;
	}
}