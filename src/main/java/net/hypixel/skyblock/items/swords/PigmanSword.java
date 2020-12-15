package net.hypixel.skyblock.items.swords;

import java.util.List;

import net.hypixel.skyblock.items.ModItemRarity;
import net.hypixel.skyblock.util.FormatingCodes;
import net.hypixel.skyblock.util.ItemProperties;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

/**
 * The <a href="https://hypixel-skyblock.fandom.com/wiki/Pigman_Sword">Pigman
 * Sword</a>.
 *
 * @author MrPineapple070
 * @version 30 July 2020
 * @since 11 June 2019
 */
public class PigmanSword extends ModSwordItem {
	public PigmanSword() {
		super(ModSwordTier.Pigman_Sword, ItemProperties.f1, ModItemRarity.Legendary);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new StringTextComponent(FormatingCodes.gold + "Item Ability: Burning Souls\n" + FormatingCodes.gray
				+ "Gain +10 Defense for 5s and cast a vortex of flamies towards nearby enemies dealing up to 1500 damage over 5 seconds."));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
	}
}