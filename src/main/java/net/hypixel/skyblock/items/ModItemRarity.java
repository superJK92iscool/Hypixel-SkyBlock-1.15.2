package net.hypixel.skyblock.items;

import com.google.common.collect.ImmutableList;

import net.minecraft.util.text.TextFormatting;

/**
 * A mod version of {@link net.minecraft.item.Rarity}
 *
 * @author MrPineapple070
 * @version 26 July 2020
 */
public enum ModItemRarity {
	Common(TextFormatting.WHITE), Epic(TextFormatting.DARK_PURPLE), Legendary(TextFormatting.GOLD),
	Mythic(TextFormatting.LIGHT_PURPLE), Rare(TextFormatting.BLUE), Special(TextFormatting.RED),
	Uncommon(TextFormatting.GREEN), Very_Special(TextFormatting.DARK_RED);

	/**
	 * A {@link ImmutableList} of {@link ModItemRarity} that are higher rarities.
	 */
	public static final ImmutableList<ModItemRarity> high = ImmutableList
			.copyOf(new ModItemRarity[] { Epic, Legendary });

	/**
	 * A {@link ImmutableList} of {@link ModItemRarity} that are lower rarities.
	 */
	public static final ImmutableList<ModItemRarity> low = ImmutableList
			.copyOf(new ModItemRarity[] { Common, Uncommon, Rare });

	/**
	 * A {@link ImmutableList} of {@link ModItemRarity} that are only obtained
	 * through artificial means.
	 */
	public static final ImmutableList<ModItemRarity> special = ImmutableList
			.copyOf(new ModItemRarity[] { Mythic, Special, Very_Special });

	/**
	 * The {@link TextFormatting} of this {@code ModItemRarity}
	 */
	public final TextFormatting color;

	/**
	 * Construct this.
	 *
	 * @param color {@link TextFormatting} for this.
	 */
	private ModItemRarity(TextFormatting color) {
		this.color = color;
	}

	/**
	 * Determines the next tier of {@link ModItemRarity}
	 *
	 * @return the next {@code ModItemRarity}.
	 */
	public ModItemRarity getNext() {
		switch (this) {
		case Common:
			return Uncommon;
		case Uncommon:
			return Rare;
		case Rare:
			return Epic;
		case Epic:
			return Legendary;
		case Legendary:
			return Mythic;
		case Mythic:
			return Special;
		case Special:
			return Very_Special;
		default:
			throw new IllegalStateException(this.name() + " does not have an upgrade.");
		}
	}
}