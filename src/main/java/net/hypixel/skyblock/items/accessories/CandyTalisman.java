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
 * An {@link Accessory} that increases the chance for mobs to drop Candy by
 * 5%<br>
 * <a href="https://hypixel-skyblock.fandom.com/wiki/Candy_Talisman">Candy
 * Talisman</a>
 *
 * @author MrPineapple070
 * @version 25 July 2020
 */
public class CandyTalisman extends Accessory {
	public CandyTalisman() {
		super(ItemProperties.ms1, ModItemRarity.Uncommon);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new StringTextComponent(ColorCodes.gray + "Increases the chance for mobs to drop Candy by"
				+ ColorCodes.green + " 5%" + ColorCodes.gray + "."));
	}
}