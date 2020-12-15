package net.hypixel.skyblock.items.accessories;

import java.util.List;

import net.hypixel.skyblock.items.ModItemRarity;
import net.hypixel.skyblock.util.FormatingCodes;
import net.hypixel.skyblock.util.ItemProperties;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

/**
 * An {@link Accessory} that increases the Player's speed.<br>
 * <a href="https://hypixel-skyblock.fandom.com/wiki/Cat_Talisman">Cat
 * Talisman</a>
 *
 * @author MrPineapple070
 * @version 26 July 2020
 */
public class CatTalisman extends Accessory {
	/**
	 * An {@link AttributeModifier} that increases {@link SharedMonsterAttributes#MOVEMENT_SPEED}
	 * by 1%
	 */
	private static final AttributeModifier speed_mod = new AttributeModifier(
			SharedMonsterAttributes.MOVEMENT_SPEED.getName(), .001, Operation.ADDITION);

	public CatTalisman() {
		super(ItemProperties.ms1, ModItemRarity.Uncommon);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new StringTextComponent(
				FormatingCodes.gray + "Increases " + FormatingCodes.white + "speed " + FormatingCodes.gray + "by 1%."));
	}

	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (worldIn.isRemote)
			return;
		if (!(entityIn instanceof PlayerEntity))
			return;
		final PlayerEntity player = (PlayerEntity) entityIn;
		final IAttributeInstance spd = player.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
		try {
			spd.applyModifier(speed_mod);
		} catch (final IllegalArgumentException iae) {
			return;
		}
	}
}