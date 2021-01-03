package net.hypixel.skyblock.pets;

import java.util.List;
import java.util.Objects;

import javax.annotation.Nonnull;

import net.hypixel.skyblock.entity.player.ModServerPlayerEntity;
import net.hypixel.skyblock.items.Collection;
import net.hypixel.skyblock.items.ModItemRarity;
import net.hypixel.skyblock.util.FormatingCodes;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

/**
 * {@link Pet} but as an {@link Item}.
 *
 * @author MrPineapple070
 * @version 23 June 2020
 */
public abstract class PetItem extends Item {
	/**
	 * {@link ITextComponent} containing an empty {@link String}.
	 */
	protected static final ITextComponent blank = new StringTextComponent("");

	/**
	 * {@link ITextComponent} containing the default progress to the next level.<br>
	 * By default this contains 20 '-' with {@link FormatingCodes#white}.<br>
	 * This can be changes to contain any amount of {@link FormatingCodes#green} and
	 * {@link FormatingCodes#white} '-' as long the total number of '-' is 20.
	 */
	protected ITextComponent lvl_progress = new StringTextComponent(FormatingCodes.white + "--------------------");

	/**
	 * The {@link Pet} of this.
	 */
	@Nonnull
	public final Pet pet;

	public PetItem(Properties properties, Pet pet) {
		super(properties);
		this.pet = Objects.requireNonNull(pet, "Pet for PetItem cannot be null");
	}

	@Override
	public abstract void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip,
			ITooltipFlag flagIn);

	@Override
	public ITextComponent getDisplayName(ItemStack stack) {
		ITextComponent lvl = new StringTextComponent("[Lvl " + this.pet.getLevel() + "]");
		return lvl.appendSibling(super.getDisplayName(stack).applyTextStyle(this.pet.getRarity().color));
	}

	/**
	 * @return {@link ModItemRarity} of {@link #pet}
	 */
	public ModItemRarity getPetRarity() {
		return this.pet.rarity;
	}

	/**
	 * @return {@link Collection} of {@link #pet}
	 */
	public Collection getPetType() {
		return this.pet.collection;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		final ItemStack held = playerIn.getHeldItem(handIn);
		if (playerIn instanceof ModServerPlayerEntity) {
			((ModServerPlayerEntity) playerIn).addPet((PetItem) held.getItem());
			playerIn.setItemStackToSlot(EquipmentSlotType.MAINHAND, ItemStack.EMPTY);
			return ActionResult.resultSuccess(held);
		}
		return ActionResult.resultFail(held);
	}
}