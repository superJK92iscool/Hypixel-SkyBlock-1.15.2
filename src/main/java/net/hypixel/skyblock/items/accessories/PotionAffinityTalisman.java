package net.hypixel.skyblock.items.accessories;

import java.util.List;

import net.hypixel.skyblock.items.ModItemRarity;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

/**
 * An {@link Accessory} that increases potion duration by 10%.<br>
 * <a href=
 * "https://hypixel-skyblock.fandom.com/wiki/Potion_Affinity_Talisman">Potion
 * Affinity Talisman</a>
 *
 * @author MrPineapple070
 * @version 28 July 2020
 */
public class PotionAffinityTalisman extends Accessory {
	public PotionAffinityTalisman() {
		super(new Properties().maxStackSize(1).group(ItemGroup.BREWING), ModItemRarity.Common);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new StringTextComponent("Increases potion duration by 10%."));
	}
}