package net.hypixel.skyblock.items.accessories;

import java.util.List;

import net.hypixel.skyblock.items.ModItemRarity;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

/**
 * An {@link Accessory} that provides immunity to fire.
 *
 * @author MrPineapple070
 * @version 26 July 2020
 */
public class FireTalisman extends Accessory {
	/**
	 * Construct this
	 *
	 * @param properties {@link Properties}
	 */
	public FireTalisman(Properties properties) {
		super(properties, ModItemRarity.Common);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new StringTextComponent("Provides immunity to fire damage."));
	}

	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (!(entityIn instanceof PlayerEntity))
			return;
		final PlayerEntity player = (PlayerEntity) entityIn;
		player.extinguish();
	}
}