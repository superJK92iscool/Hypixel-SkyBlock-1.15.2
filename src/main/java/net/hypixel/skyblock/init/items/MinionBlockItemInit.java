package net.hypixel.skyblock.init.items;

import net.hypixel.skyblock.HypixelSkyBlockMod;
import net.hypixel.skyblock.init.blocks.MinionBlockInit;
import net.hypixel.skyblock.items.minion.MinionBlockItem;
import net.hypixel.skyblock.util.ItemProperties;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Initialize and Register all {@link MinionBlockItem} for this Mod.
 *
 * @author MrPineapple070
 * @version 18 May 2020
 */
public class MinionBlockItemInit {
	public static final DeferredRegister<Item> minionItems = new DeferredRegister<>(ForgeRegistries.ITEMS,
			HypixelSkyBlockMod.MOD_ID);

	public static final RegistryObject<Item> com1 = minionItems.register("coal_minion_1",
			() -> new MinionBlockItem(MinionBlockInit.coal_minion_1.get(), ItemProperties.mn1));
	public static final RegistryObject<Item> com2 = minionItems.register("coal_minion_2",
			() -> new MinionBlockItem(MinionBlockInit.coal_minion_2.get(), ItemProperties.mn1));
	public static final RegistryObject<Item> com3 = minionItems.register("coal_minion_3",
			() -> new MinionBlockItem(MinionBlockInit.coal_minion_3.get(), ItemProperties.mn1));
	public static final RegistryObject<Item> com4 = minionItems.register("coal_minion_4",
			() -> new MinionBlockItem(MinionBlockInit.coal_minion_4.get(), ItemProperties.mn1));
	public static final RegistryObject<Item> com5 = minionItems.register("coal_minion_5",
			() -> new MinionBlockItem(MinionBlockInit.coal_minion_5.get(), ItemProperties.mn1));
	public static final RegistryObject<Item> com6 = minionItems.register("coal_minion_6",
			() -> new MinionBlockItem(MinionBlockInit.coal_minion_6.get(), ItemProperties.mn1));
	public static final RegistryObject<Item> com7 = minionItems.register("coal_minion_7",
			() -> new MinionBlockItem(MinionBlockInit.coal_minion_7.get(), ItemProperties.mn1));
	public static final RegistryObject<Item> com8 = minionItems.register("coal_minion_8",
			() -> new MinionBlockItem(MinionBlockInit.coal_minion_8.get(), ItemProperties.mn1));
	public static final RegistryObject<Item> com9 = minionItems.register("coal_minion_9",
			() -> new MinionBlockItem(MinionBlockInit.coal_minion_9.get(), ItemProperties.mn1));
	public static final RegistryObject<Item> coma = minionItems.register("coal_minion_a",
			() -> new MinionBlockItem(MinionBlockInit.coal_minion_a.get(), ItemProperties.mn1));
	public static final RegistryObject<Item> comb = minionItems.register("coal_minion_b",
			() -> new MinionBlockItem(MinionBlockInit.coal_minion_b.get(), ItemProperties.mn1));
	public static final RegistryObject<Item> csm1 = minionItems.register("cobblestone_minion_1",
			() -> new MinionBlockItem(MinionBlockInit.cobblestone_minion_1.get(), ItemProperties.mn1));

	public static final RegistryObject<Item> csm2 = minionItems.register("cobblestone_minion_2",
			() -> new MinionBlockItem(MinionBlockInit.cobblestone_minion_2.get(), ItemProperties.mn1));
	public static final RegistryObject<Item> csm3 = minionItems.register("cobblestone_minion_3",
			() -> new MinionBlockItem(MinionBlockInit.cobblestone_minion_3.get(), ItemProperties.mn1));
	public static final RegistryObject<Item> csm4 = minionItems.register("cobblestone_minion_4",
			() -> new MinionBlockItem(MinionBlockInit.cobblestone_minion_4.get(), ItemProperties.mn1));
	public static final RegistryObject<Item> csm5 = minionItems.register("cobblestone_minion_5",
			() -> new MinionBlockItem(MinionBlockInit.cobblestone_minion_5.get(), ItemProperties.mn1));
	public static final RegistryObject<Item> csm6 = minionItems.register("cobblestone_minion_6",
			() -> new MinionBlockItem(MinionBlockInit.cobblestone_minion_6.get(), ItemProperties.mn1));
	public static final RegistryObject<Item> csm7 = minionItems.register("cobblestone_minion_7",
			() -> new MinionBlockItem(MinionBlockInit.cobblestone_minion_7.get(), ItemProperties.mn1));
	public static final RegistryObject<Item> csm8 = minionItems.register("cobblestone_minion_8",
			() -> new MinionBlockItem(MinionBlockInit.cobblestone_minion_8.get(), ItemProperties.mn1));
	public static final RegistryObject<Item> csm9 = minionItems.register("cobblestone_minion_9",
			() -> new MinionBlockItem(MinionBlockInit.cobblestone_minion_9.get(), ItemProperties.mn1));
	public static final RegistryObject<Item> csma = minionItems.register("cobblestone_minion_a",
			() -> new MinionBlockItem(MinionBlockInit.cobblestone_minion_a.get(), ItemProperties.mn1));
	public static final RegistryObject<Item> csmb = minionItems.register("cobblestone_minion_b",
			() -> new MinionBlockItem(MinionBlockInit.cobblestone_minion_b.get(), ItemProperties.mn1));

	public static final RegistryObject<Item> whm1 = minionItems.register("wheat_minion_1",
			() -> new MinionBlockItem(MinionBlockInit.wheat_minion_1.get(), ItemProperties.mn1));
	public static final RegistryObject<Item> whm2 = minionItems.register("wheat_minion_2",
			() -> new MinionBlockItem(MinionBlockInit.wheat_minion_2.get(), ItemProperties.mn1));
	public static final RegistryObject<Item> whm3 = minionItems.register("wheat_minion_3",
			() -> new MinionBlockItem(MinionBlockInit.wheat_minion_3.get(), ItemProperties.mn1));
	public static final RegistryObject<Item> whm4 = minionItems.register("wheat_minion_4",
			() -> new MinionBlockItem(MinionBlockInit.wheat_minion_4.get(), ItemProperties.mn1));
	public static final RegistryObject<Item> whm5 = minionItems.register("wheat_minion_5",
			() -> new MinionBlockItem(MinionBlockInit.wheat_minion_5.get(), ItemProperties.mn1));
	public static final RegistryObject<Item> whm6 = minionItems.register("wheat_minion_6",
			() -> new MinionBlockItem(MinionBlockInit.wheat_minion_6.get(), ItemProperties.mn1));
	public static final RegistryObject<Item> whm7 = minionItems.register("wheat_minion_7",
			() -> new MinionBlockItem(MinionBlockInit.wheat_minion_7.get(), ItemProperties.mn1));
	public static final RegistryObject<Item> whm8 = minionItems.register("wheat_minion_8",
			() -> new MinionBlockItem(MinionBlockInit.wheat_minion_8.get(), ItemProperties.mn1));
	public static final RegistryObject<Item> whm9 = minionItems.register("wheat_minion_9",
			() -> new MinionBlockItem(MinionBlockInit.wheat_minion_9.get(), ItemProperties.mn1));
	public static final RegistryObject<Item> whma = minionItems.register("wheat_minion_a",
			() -> new MinionBlockItem(MinionBlockInit.wheat_minion_a.get(), ItemProperties.mn1));
	public static final RegistryObject<Item> whmb = minionItems.register("wheat_minion_b",
			() -> new MinionBlockItem(MinionBlockInit.wheat_minion_b.get(), ItemProperties.mn1));
}