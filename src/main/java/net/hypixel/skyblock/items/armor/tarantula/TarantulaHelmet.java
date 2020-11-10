package net.hypixel.skyblock.items.armor.tarantula;

import java.util.ArrayList;
import java.util.List;

import net.hypixel.skyblock.items.armor.ModArmorItem;
import net.hypixel.skyblock.util.ColorCodes;
import net.hypixel.skyblock.util.ItemProperties;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class TarantulaHelmet extends ModArmorItem {
	private static final List<ITextComponent> discription = new ArrayList<>();

	public TarantulaHelmet() {
		super(TarantulaSet.instance.getMaterial(), EquipmentSlotType.HEAD, ItemProperties.c1,
				TarantulaSet.instance.getRarity());
	}

	static {
		try {
			discription.addAll(TarantulaSet.instance.getDescription(EquipmentSlotType.HEAD));
			discription.add(new StringTextComponent(ColorCodes.gold + "Piece Bonus: Radioactive"));
			discription.add(new StringTextComponent(ColorCodes.gray + "Gain +1 Crit Damage per 10 Strength"));
			discription.add(new StringTextComponent(ColorCodes.gold + "Piece Bonus: Spider Bulwark"));
			discription.add(new StringTextComponent(
					ColorCodes.gray + "Every spider you kill will accumulate defense against them."));
		} catch (final IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.addAll(discription);
	}
}