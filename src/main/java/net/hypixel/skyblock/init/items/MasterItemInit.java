package net.hypixel.skyblock.init.items;

import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;

/**
 * Initialize and Register every {@link Item} for this mod.<br>
 * {@link AccessoriesInit}, {@link ArmorInit}, {@link BlockItemInit},
 * {@link ExtraItemInit}, {@link ItemInit}, {@link MinionBlockItemInit} and
 * {@link WeaponInit}.
 *
 * @author MrPineapple070
 * @version 07 July 2020
 * @since 11 July 2019
 */
public class MasterItemInit {
	public static void register(final IEventBus bus) {
		AccessoriesInit.accessories.register(bus);
		ArmorInit.armor.register(bus);
		BlockItemInit.blockItems.register(bus);
		ItemInit.items.register(bus);
		MinionBlockItemInit.minionItems.register(bus);
		WeaponInit.weapon.register(bus);
	}
}