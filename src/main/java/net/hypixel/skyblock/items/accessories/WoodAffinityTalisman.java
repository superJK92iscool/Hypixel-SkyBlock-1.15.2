package net.hypixel.skyblock.items.accessories;

import java.util.List;

import net.hypixel.skyblock.items.ModItemRarity;
import net.hypixel.skyblock.util.ItemProperties;
import net.hypixel.skyblock.world.biomes.ForestBiome;
import net.hypixel.skyblock.world.biomes.GraveyardBiome;
import net.hypixel.skyblock.world.biomes.WildernessBiome;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

/**
 * An {@link Accessory} that gives +10% speed while in {@link ForestBiome},
 * {@link GraveyardBiome}, and {@link WildernessBiome}.<br>
 * <a href=
 * "https://hypixel-skyblock.fandom.com/wiki/Wood_Affinity_Talisman">Wood
 * Affinity Talisman</a>
 *
 * @author MrPineapple070
 * @version 29 July 2020
 */
public class WoodAffinityTalisman extends Accessory {
	public WoodAffinityTalisman() {
		super(ItemProperties.fg1, ModItemRarity.Uncommon);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new StringTextComponent("Increases speed by 10% while in the Forest, Graveyard, and Wilderness"));
	}
}