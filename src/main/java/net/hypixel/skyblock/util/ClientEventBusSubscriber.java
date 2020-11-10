package net.hypixel.skyblock.util;

import net.hypixel.skyblock.HypixelSkyBlockMod;
import net.hypixel.skyblock.client.gui.screen.AbstractMinionScreen;
import net.hypixel.skyblock.client.gui.screen.MinionChestScreen.LargeMCS;
import net.hypixel.skyblock.client.gui.screen.MinionChestScreen.MediumMCS;
import net.hypixel.skyblock.client.gui.screen.MinionChestScreen.SmallMCS;
import net.hypixel.skyblock.inventory.container.ModContainerTypes;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

/**
 * EventBusSibscriber for Client
 *
 * @author MrPineapple070
 * @version 8 June 2020
 */
@Mod.EventBusSubscriber(modid = HypixelSkyBlockMod.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {
	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		ScreenManager.registerFactory(ModContainerTypes.cobblestone_minion_1.get(), AbstractMinionScreen::new);
		ScreenManager.registerFactory(ModContainerTypes.cobblestone_minion_2.get(), AbstractMinionScreen::new);
		ScreenManager.registerFactory(ModContainerTypes.cobblestone_minion_3.get(), AbstractMinionScreen::new);
		ScreenManager.registerFactory(ModContainerTypes.cobblestone_minion_4.get(), AbstractMinionScreen::new);
		ScreenManager.registerFactory(ModContainerTypes.cobblestone_minion_5.get(), AbstractMinionScreen::new);
		ScreenManager.registerFactory(ModContainerTypes.cobblestone_minion_6.get(), AbstractMinionScreen::new);
		ScreenManager.registerFactory(ModContainerTypes.cobblestone_minion_7.get(), AbstractMinionScreen::new);
		ScreenManager.registerFactory(ModContainerTypes.cobblestone_minion_8.get(), AbstractMinionScreen::new);
		ScreenManager.registerFactory(ModContainerTypes.cobblestone_minion_9.get(), AbstractMinionScreen::new);
		ScreenManager.registerFactory(ModContainerTypes.cobblestone_minion_a.get(), AbstractMinionScreen::new);
		ScreenManager.registerFactory(ModContainerTypes.cobblestone_minion_b.get(), AbstractMinionScreen::new);

		ScreenManager.registerFactory(ModContainerTypes.small_mcc.get(), SmallMCS::new);
		ScreenManager.registerFactory(ModContainerTypes.medium_mcc.get(), MediumMCS::new);
		ScreenManager.registerFactory(ModContainerTypes.large_mcc.get(), LargeMCS::new);
	}
}