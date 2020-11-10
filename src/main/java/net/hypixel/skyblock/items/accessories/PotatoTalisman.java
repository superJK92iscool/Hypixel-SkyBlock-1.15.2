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
 * An {@link Accessory} that gives +1 speed and increases Potato Minion speed by
 * 5%.<br>
 * <a href="https://hypixel-skyblock.fandom.com/wiki/Potato_Talisman">Potato
 * Talisman</a>
 *
 * @author MrPineapple070
 * @version 28 July 2020
 */
public class PotatoTalisman extends Accessory {
	public PotatoTalisman() {
		super(ItemProperties.f1, ModItemRarity.Common);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new StringTextComponent(ColorCodes.gray + "Gives +1 " + ColorCodes.white + "speed "
				+ ColorCodes.gray + "and increases Potato Minion speed by 5%"));
	}
}