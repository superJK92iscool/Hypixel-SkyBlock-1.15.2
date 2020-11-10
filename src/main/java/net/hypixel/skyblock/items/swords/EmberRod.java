package net.hypixel.skyblock.items.swords;

import java.util.List;

import net.hypixel.skyblock.items.ModItemRarity;
import net.hypixel.skyblock.util.ColorCodes;
import net.hypixel.skyblock.util.ItemProperties;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * The <a href="https://hypixel-skyblock.fandom.com/wiki/Ember_Rod">Ember
 * Rod</a>.
 *
 * @author MrPineapple070
 * @version 13 August 2020
 * @since 13 August 2020
 */
public class EmberRod extends ModSwordItem {
	public EmberRod() {
		super(ModSwordTier.Ember_Rod, ItemProperties.c1, ModItemRarity.Epic);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new StringTextComponent(ColorCodes.gold + "Item Ability: Fire Blast\n"));
		tooltip.add(new StringTextComponent(ColorCodes.gray + "Shoot 3 fireballs forwards in rapid succession."));
	}

	@OnlyIn(Dist.DEDICATED_SERVER)
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		playerIn.getCooldownTracker().setCooldown(this, 600);
		return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
	}
}