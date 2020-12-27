package net.hypixel.skyblock.pets;

import java.util.List;
import java.util.Objects;

import javax.annotation.Nonnull;

import net.hypixel.skyblock.entity.player.ModServerPlayerEntity;
import net.hypixel.skyblock.items.Collection;
import net.hypixel.skyblock.items.ModItemRarity;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

/**
 * {@link Pet} but as an {@link Item}.
 *
 * @author MrPineapple070
 * @version 23 June 2020
 */
public abstract class PetItem extends Item {
	/**
	 * The {@link Pet} of this.
	 */
	@Nonnull
	private final Pet pet;

	public PetItem(Properties properties, Pet pet) {
		super(properties);
		this.pet = Objects.requireNonNull(pet, "Pet for PetItem cannot be null");
	}

	@Override
	public abstract void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip,
			ITooltipFlag flagIn);

	/**
	 * @return {@link #pet}
	 */
	public Pet getPet() {
		return this.pet;
	}

	/**
	 * @return {@link ModItemRarity} of {@link #pet}
	 */
	public ModItemRarity getPetTier() {
		return this.pet.rarity;
	}

	/**
	 * @return {@link Collection} of {@link #pet}
	 */
	public Collection getPetType() {
		return this.pet.collection;
	}

	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (entityIn instanceof ModServerPlayerEntity) {
			this.pet.effect((ModServerPlayerEntity) entityIn);
			super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
		}
		return;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		final ItemStack held = playerIn.getHeldItem(handIn);
		if (playerIn instanceof ModServerPlayerEntity) {
			((ModServerPlayerEntity) playerIn).addPet((PetItem) held.getItem());
			playerIn.setItemStackToSlot(EquipmentSlotType.MAINHAND, ItemStack.EMPTY);
			return new ActionResult<>(ActionResultType.SUCCESS, held);
		}
		return new ActionResult<>(ActionResultType.FAIL, held);
	}
}