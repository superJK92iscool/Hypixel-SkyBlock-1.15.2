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
 * The <a href="https://hypixel-skyblock.fandom.com/wiki/Red_Claw_Artifact">Red
 * Claw Artifact</a>.
 *
 * @author MrPineapple070
 * @version 19 August 2020
 * @since 19 August 2020
 */
public class RedClawArtifact extends Accessory {
	public RedClawArtifact() {
		super(ItemProperties.c1, ModItemRarity.Epic);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new StringTextComponent(
				ColorCodes.gray + "Increases " + ColorCodes.dark_blue + "Crit Damage " + ColorCodes.gray + "by 5%."));
	}
}