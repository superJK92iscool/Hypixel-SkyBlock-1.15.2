package net.hypixel.skyblock.items.accessories;

import java.util.List;

import net.hypixel.skyblock.items.ModItemRarity;
import net.hypixel.skyblock.util.ColorCodes;
import net.hypixel.skyblock.util.ItemProperties;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

/**
 * An {@link Accessory} that buffs.<br>
 * <a href="https://hypixel-skyblock.fandom.com/wiki/Bat_Ring">Bat Ring</a>
 *
 * @author MrPineapple070
 * @version 25 July 2020
 */
public class BatRing extends Accessory {
	public BatRing() {
		super(ItemProperties.ms1, ModItemRarity.Epic);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new StringTextComponent(ColorCodes.gray + "Grants: " + ColorCodes.red + "+3% Health "
				+ ColorCodes.white + "+2% Speed " + ColorCodes.aqua + " +2 Intelligence"));
	}
}