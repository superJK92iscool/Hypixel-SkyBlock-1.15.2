package net.hypixel.skyblock.items.accessories;

import java.util.List;

import net.hypixel.skyblock.items.ModItemRarity;
import net.hypixel.skyblock.util.ItemProperties;
import net.hypixel.skyblock.world.biomes.VillageBiome;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

/**
 * An {@link Accessory} that gives +10% speed while in the
 * {@link VillageBiome}.<br>
 * <a href=
 * "https://hypixel-skyblock.fandom.com/wiki/Village_Affinity_Talisman">Village
 * Affinity Talisman</a>
 *
 * @author MrPineapple070
 * @version 29 July 2020
 */
public class VillageAffinityTalisman extends Accessory {
	public VillageAffinityTalisman() {
		super(ItemProperties.ms1, ModItemRarity.Common);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new StringTextComponent("Increases you speed by 10% while in the Village."));
	}
}