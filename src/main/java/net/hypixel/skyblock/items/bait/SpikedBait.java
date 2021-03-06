package net.hypixel.skyblock.items.bait;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

/**
 * A {@link Bait} that increases Sea Creature spawn rate by 6%.
 *
 * @author MrPineapple070
 * @version 2 June 2020
 * @since 2 July 2019
 */
public class SpikedBait extends Bait {
	public SpikedBait() {
		super();
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new StringTextComponent("When cast, increase Sea Creature spawn rate by 6%."));
	}

}
