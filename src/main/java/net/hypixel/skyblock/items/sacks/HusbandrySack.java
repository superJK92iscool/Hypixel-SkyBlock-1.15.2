package net.hypixel.skyblock.items.sacks;

import com.google.common.collect.ImmutableList;

import net.hypixel.skyblock.util.ItemProperties;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

/**
 * <a href="https://hypixel-skyblock.fandom.com/wiki/Husbandry_Sack">Husbandry
 * Sack</a>.
 *
 * @author MrPineapple070
 * @version 01 September 2020
 * @since 01 September 2020
 */
public class HusbandrySack extends Sack {
	/**
	 * An {@link ImmutableList} of {@link Item} that this will hold.
	 */
	private static final ImmutableList<Item> items = ImmutableList.copyOf(
			new Item[] { Items.FEATHER, Items.LEATHER, Items.PORKCHOP, Items.CHICKEN, Items.MUTTON, Items.RABBIT });

	public HusbandrySack(SackType type) {
		super(ItemProperties.f1, type);
	}

	@Override
	protected ImmutableList<Item> getItems() {
		return items;
	}
}