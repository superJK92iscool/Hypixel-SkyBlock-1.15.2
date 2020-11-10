package net.hypixel.skyblock.events.accessories;

import net.hypixel.skyblock.HypixelSkyBlockMod;
import net.hypixel.skyblock.init.BiomeInit;
import net.hypixel.skyblock.init.items.AccessoriesInit;
import net.hypixel.skyblock.items.accessories.Accessory;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

/**
 * Handle removing effects applied by {@link Accessory}
 *
 * @author MrPineapple070
 * @version 10 September 2020
 * @since 10 September 2020
 */
@Mod.EventBusSubscriber(modid = HypixelSkyBlockMod.MOD_ID, bus = Bus.FORGE)
public class AccessoryTossEvent {
	@SubscribeEvent
	public static void catAccessoryEvent(ItemTossEvent event) {
		final Item thrown = event.getEntityItem().getItem().getItem();
		final PlayerEntity player = event.getPlayer();
		final IAttributeInstance spd = player.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
		if (thrown == AccessoriesInit.cheetah_talisman.get())
			spd.applyModifier(
					new AttributeModifier(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), -.003, Operation.ADDITION));
		else if (thrown == AccessoriesInit.lynx_talisman.get())
			spd.applyModifier(
					new AttributeModifier(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), -.002, Operation.ADDITION));
		else if (thrown == AccessoriesInit.cat_talisman.get())
			spd.applyModifier(
					new AttributeModifier(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), -.001, Operation.ADDITION));
	}

	@SubscribeEvent
	public static void farmingTalismanEvent(ItemTossEvent event) {
		final Item thrown = event.getEntityItem().getItem().getItem();
		final PlayerEntity player = event.getPlayer();
		final Biome biome = player.world.getBiome(player.getPosition());
		if (biome != BiomeInit.farm_biome.get())
			return;
		// else if (biome != null) return;
		final IAttributeInstance spd = player.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
		if (thrown == AccessoriesInit.farming_talisman.get())
			spd.applyModifier(
					new AttributeModifier(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), -.01, Operation.ADDITION));
	}
}