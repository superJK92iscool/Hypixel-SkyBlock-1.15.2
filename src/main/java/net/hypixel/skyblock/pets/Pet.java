package net.hypixel.skyblock.pets;

import java.util.Objects;
import java.util.Random;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import com.google.common.collect.ImmutableList;

import net.hypixel.skyblock.entity.player.ModServerPlayerEntity;
import net.hypixel.skyblock.items.Collection;
import net.hypixel.skyblock.items.ModItemRarity;
import net.hypixel.skyblock.util.PetLevelRequirement;
import net.minecraft.item.Item;

/**
 * Basis for all
 * <a href="https://hypixel-skyblock.fandom.com/wiki/Pets">Pets</a>.
 *
 * @author MrPineapple070
 * @version 08 September 2020
 * @since 11 October 2019
 */
public abstract class Pet {
	public enum PetType {
		Baby_Yeti, Bat, Bee, Black_Cat, Blaze, Blue_Whale, Chicken, Dolphin, Elephant, Ender_Dragon, Enderman,
		Endermite, Flying_Fish, Ghoul, Giraffe, Golem, Guardian, Horse, Hound, Jellyfish, Jerry, Lion, Magma_Cube,
		Monkey, Ocelot, Parrot, Phoenix, Pig, Pigman, Rabbit, Rock, Sheep, Silverfish, Skeleton, Skeleton_Horse,
		Snowman, Spider, Spirit, Squid, Tarantula, Tiger, Turtle,
		// Wither ,
		Wither_Skeleton, Wolf, Zombie;
	}

	/**
	 * {@link Random}
	 */
	@Nonnull
	protected static final Random rand = new Random();

	/**
	 * @return {@link ModItemRarity#Epic} or {@link ModItemRarity#Legendary}
	 *         randomly.
	 */
	protected static ModItemRarity getRandomRarityHigh() {
		return ModItemRarity.high.get(rand.nextInt(ModItemRarity.high.size()));
	}

	/**
	 * @return {@link ModItemRarity#Common}, {@link ModItemRarity#Uncommon}, or
	 *         {@link ModItemRarity#Rare} randomly.
	 */
	protected static ModItemRarity getRandomRarityLow() {
		return ModItemRarity.low.get(rand.nextInt(ModItemRarity.low.size()));
	}

	/**
	 * {@link Collection}
	 */
	@Nonnull
	public final Collection collection;

	/**
	 * Current amount of experience held.
	 */
	@Nonnegative
	protected float current_xp;
	
	@Nonnegative
	protected int req_xp;
	
	protected Item held_item;

	/**
	 * Level of this.
	 */
	@Nonnegative
	protected int level;

	/**
	 * {@link PetType}.
	 */
	@Nonnull
	public final PetType petType;

	/**
	 * {@link ModItemRarity}
	 */
	@Nonnull
	protected ModItemRarity rarity;

	protected Pet(ModItemRarity rarity, Collection collection, PetType petType) {
		this.rarity = Objects.requireNonNull(rarity, "Pet must have a rarity.");
		this.petType = Objects.requireNonNull(petType, "Pet must have a PetType");
		this.collection = Objects.requireNonNull(collection, "Pet must have a Collection");
		this.level = 1;
		this.current_xp = 0;
		this.req_xp = PetLevelRequirement.getRequirement(this.rarity, this.level);
	}

	public void addExperience(float xp) {
		ImmutableList<Integer> lvl = PetLevelRequirement.getRequirement(this.rarity);
		int amount = lvl.get(this.level + 1);
		int inc = this.level + 1;
		while (amount < xp)
			amount += lvl.get(++inc);
		this.level += inc;
		this.req_xp = lvl.get(this.level + 1);
	}

	/**
	 * Handle the effects that {@code this} will have.
	 *
	 * @param player the player.
	 */
	public abstract void effect(ModServerPlayerEntity player);

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Pet))
			return false;
		final Pet other = (Pet) obj;
		if (this.collection != other.collection)
			return false;
		if (this.current_xp != other.current_xp)
			return false;
		if (this.level != other.level)
			return false;
		if (this.petType != other.petType)
			return false;
		if (this.rarity != other.rarity)
			return false;
		return true;
	}

	public abstract double[] getBuffs();

	/**
	 * @return {@link #collection}
	 */
	public Collection getCollection() {
		return this.collection;
	}

	/**
	 * @return {@link #level}
	 */
	public int getLevel() {
		return this.level;
	}

	/**
	 * @return {@link #petType}
	 */
	public PetType getPetType() {
		return this.petType;
	}

	/**
	 * @return {@link #rarity}
	 */
	public ModItemRarity getRarity() {
		return this.rarity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (this.collection == null ? 0 : this.collection.hashCode());
		result = prime * result + (int)this.current_xp;
		result = prime * result + this.level;
		result = prime * result + (this.petType == null ? 0 : this.petType.hashCode());
		result = prime * result + (this.rarity == null ? 0 : this.rarity.hashCode());
		return result;
	}

	public boolean isIdentical(Pet other) {
		return this.petType == other.petType;
	}
	
	@Override
	public String toString() {
		return "Pet [collection=" + this.collection + ", current_xp=" + this.current_xp + ", level=" + this.level
				+ ", petType=" + this.petType + ", rarity=" + this.rarity + "]";
	}

	/**
	 * Sets {@link #level} to the next level.
	 */
	public void upgradeLevel() {
		if (this.level < 100)
			++this.level;
	}
	
	/**
	 * Sets {@link #rarity} to the next tier.
	 */
	public void upgradeTier() {
		switch (this.rarity) {
		case Legendary:
			return;
		default:
			this.rarity = this.rarity.getNext();
		}
	}
}