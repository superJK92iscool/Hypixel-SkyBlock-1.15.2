package net.hypixel.skyblock.items.accessories;

import java.util.List;

import net.hypixel.skyblock.items.ModItemRarity;
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
 * An {@link Accessory} that empowers the {@link PlayerEntity} during the
 * day.<br>
 * <a href="https://hypixel-skyblock.fandom.com/wiki/Day_Crystal">Day
 * Crystal</a>
 *
 * @author MrPineapple070
 * @version 18 May 2020
 */
public class DayCrystal extends Accessory {
	/**
	 * An {@link AttributeModifier} that increases {@link SharedMonsterAttributes#ATTACK_DAMAGE}
	 * by 0xA.
	 */
	private static final AttributeModifier attack_mod = new AttributeModifier(
			SharedMonsterAttributes.ATTACK_DAMAGE.getName(), 0xA, Operation.ADDITION);

	/**
	 * An {@link AttributeModifier} that increases {@link SharedMonsterAttributes#ARMOR} by 0xA.
	 */
	private static final AttributeModifier defense_mod = new AttributeModifier(SharedMonsterAttributes.ARMOR.getName(), 0xA,
			Operation.ADDITION);

	public DayCrystal() {
		super(ItemProperties.m1, ModItemRarity.Rare);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new StringTextComponent("Gives extra strength & defense during the day."));
	}

	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (worldIn.isRemote)
			return;
		if (worldIn.isNightTime())
			return;
		if (entityIn instanceof PlayerEntity) {
			final PlayerEntity player = (PlayerEntity) entityIn;
			final IAttributeInstance atk_dmg = player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
			final IAttributeInstance def = player.getAttribute(SharedMonsterAttributes.ARMOR);
			atk_dmg.applyModifier(attack_mod);
			def.applyModifier(defense_mod);
		}
	}
}