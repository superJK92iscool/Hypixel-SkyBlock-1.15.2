package net.hypixel.skyblock.init.items;

import net.hypixel.skyblock.HypixelSkyBlockMod;
import net.hypixel.skyblock.items.ModExperienceBottleItem;
import net.hypixel.skyblock.items.ModExperienceBottleItem.Type;
import net.hypixel.skyblock.items.enchanteditems.EnchantedFuelItem;
import net.hypixel.skyblock.items.enchanteditems.EnchantedItem;
import net.hypixel.skyblock.items.minion.UpgradeItem;
import net.hypixel.skyblock.util.ItemProperties;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
import net.minecraft.item.ShovelItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Initialize and Register all {@link Item} for this Mod. <br>
 * Certain {@code Item} are Initialized and Registered in other files.<br>
 *
 * @author MrPineapple070
 * @version 22 June 2020
 */
public class ItemInit {
	public static final DeferredRegister<Item> miningItems = new DeferredRegister<>(ForgeRegistries.ITEMS,
			HypixelSkyBlockMod.MOD_ID);

	// Mining Items
	public static final RegistryObject<Item> auto_smelter = miningItems.register("auto_smelter",
			() -> new UpgradeItem(ItemProperties.m64));

	public static final RegistryObject<Item> budget_hopper = miningItems.register("budget_hopper",
			() -> new EnchantedItem(ItemProperties.m64));
	public static final RegistryObject<Item> catalyst = miningItems.register("catalyst",
			() -> new EnchantedFuelItem(ItemProperties.m64, 216000, 1 / 3d));
	public static final RegistryObject<Item> compactor = miningItems.register("compactor",
			() -> new UpgradeItem(ItemProperties.m64));
	public static final RegistryObject<Item> desert_biome_stick = miningItems.register("desert_biome_stick",
			() -> new EnchantedItem(ItemProperties.m1));

	public static final RegistryObject<Item> diamond_spreading = miningItems.register("diamond_spreading",
			() -> new UpgradeItem(ItemProperties.m64));
	public static final RegistryObject<Item> enchanted_charcoal = miningItems.register("enchanted_charcoal",
			() -> new EnchantedFuelItem(ItemProperties.m64, 2592000, .8));
	public static final RegistryObject<Item> enchanted_coal = miningItems.register("enchanted_coal",
			() -> new EnchantedFuelItem(ItemProperties.m64, 1728000, .9));
	public static final RegistryObject<Item> enchanted_coal_block = miningItems.register("enchanted_coal_block",
			() -> new EnchantedItem(ItemProperties.m64));

	public static final RegistryObject<Item> enchanted_cobblestone = miningItems.register("enchanted_cobblestone",
			() -> new EnchantedItem(ItemProperties.m64));
	public static final RegistryObject<Item> enchanted_diamond = miningItems.register("enchanted_diamond",
			() -> new EnchantedItem(ItemProperties.m64));
	public static final RegistryObject<Item> enchanted_diamond_block = miningItems.register("enchanted_diamond_block",
			() -> new EnchantedItem(ItemProperties.m64));
	public static final RegistryObject<Item> enchanted_emerald = miningItems.register("enchanted_emerald",
			() -> new EnchantedItem(ItemProperties.m64));

	public static final RegistryObject<Item> enchanted_emerald_block = miningItems.register("enchanted_emerald_block",
			() -> new EnchantedItem(ItemProperties.m64));
	public static final RegistryObject<Item> enchanted_end_stone = miningItems.register("enchanted_end_stone",
			() -> new EnchantedItem(ItemProperties.m64));

	public static final RegistryObject<Item> enchanted_flint = miningItems.register("enchanted_flint",
			() -> new EnchantedItem(ItemProperties.m64));
	public static final RegistryObject<Item> enchanted_glowstone = miningItems.register("enchanted_glowstone",
			() -> new EnchantedItem(ItemProperties.m64));
	public static final RegistryObject<Item> enchanted_glowstone_dust = miningItems.register("enchanted_glowstone_dust",
			() -> new EnchantedItem(ItemProperties.m64));

	public static final RegistryObject<Item> enchanted_gold_block = miningItems.register("enchanted_gold_block",
			() -> new EnchantedItem(ItemProperties.m64));
	public static final RegistryObject<Item> enchanted_gold_ingot = miningItems.register("enchanted_gold_ingot",
			() -> new EnchantedItem(ItemProperties.m64));
	public static final RegistryObject<Item> enchanted_hopper = miningItems.register("enchanted_hopper",
			() -> new EnchantedItem(ItemProperties.m64));
	public static final RegistryObject<Item> enchanted_ice = miningItems.register("enchanted_ice",
			() -> new EnchantedItem(ItemProperties.m64));

	public static final RegistryObject<Item> enchanted_iron_block = miningItems.register("enchanted_iron_block",
			() -> new EnchantedItem(ItemProperties.m64));
	public static final RegistryObject<Item> enchanted_iron_ingot = miningItems.register("enchanted_iron_ingot",
			() -> new EnchantedItem(ItemProperties.m64));

	public static final RegistryObject<Item> enchanted_lapis_block = miningItems.register("enchanted_lapis_block",
			() -> new EnchantedItem(ItemProperties.m64));
	public static final RegistryObject<Item> enchanted_lapis_lazuli = miningItems.register("enchanted_lapis_lazuli",
			() -> new EnchantedItem(ItemProperties.m64));
	public static final RegistryObject<Item> enchanted_lava_bucket = miningItems.register("enchanted_lava_bucket",
			() -> new EnchantedFuelItem(ItemProperties.m64, -1, .75));
	public static final RegistryObject<Item> enchanted_obsidian = miningItems.register("enchanted_obsidian",
			() -> new EnchantedItem(ItemProperties.m64));

	public static final RegistryObject<Item> enchanted_packed_ice = miningItems.register("enchanted_packed_ice",
			() -> new EnchantedItem(ItemProperties.m64));
	public static final RegistryObject<Item> enchanted_quartz = miningItems.register("enchanted_quartz",
			() -> new EnchantedItem(ItemProperties.m64));
	public static final RegistryObject<Item> enchanted_quartz_block = miningItems.register("enchanted_quartz_block",
			() -> new EnchantedItem(ItemProperties.m64));
	public static final RegistryObject<Item> enchanted_redstone = miningItems.register("enchanted_redstone",
			() -> new EnchantedItem(ItemProperties.m64));

	public static final RegistryObject<Item> enchanted_redstone_block = miningItems.register("enchanted_redstone_block",
			() -> new EnchantedItem(ItemProperties.m64));

	public static final RegistryObject<Item> enchanted_redstone_lamp = miningItems.register("enchanted_redstone_lamp",
			() -> new EnchantedItem(ItemProperties.m64));
	public static final RegistryObject<Item> enchanted_sand = miningItems.register("enchanted_sand",
			() -> new EnchantedItem(ItemProperties.m64));

	public static final RegistryObject<Item> end_biome_stick = miningItems.register("end_biome_stick",
			() -> new EnchantedItem(ItemProperties.m1));
	public static final RegistryObject<Item> flint_shovel = miningItems.register("flint_shovel",
			() -> new ShovelItem(ItemTier.DIAMOND, 0, Float.MAX_VALUE, ItemProperties.m1));

	public static final RegistryObject<Item> grand_xp_bottle = miningItems.register("grand_xp_bottle",
			() -> new ModExperienceBottleItem(ItemProperties.m64, Type.GRAND));
	public static final RegistryObject<Item> ice_bait = miningItems.register("ice_bait",
			() -> new Item(ItemProperties.m64));
	public static final RegistryObject<Item> magical_water_bucket = miningItems.register("magical_water_bucket",
			() -> new Item(ItemProperties.m64));

	public static final RegistryObject<Item> minion_expander = miningItems.register("minion_expander",
			() -> new UpgradeItem(ItemProperties.m64));
	public static final RegistryObject<Item> solar_panel = miningItems.register("solar_panel",
			() -> new UpgradeItem(ItemProperties.m64));

	public static final RegistryObject<Item> super_compactor_3000 = miningItems.register("super_compactor_3000",
			() -> new UpgradeItem(ItemProperties.m64));
	public static final RegistryObject<Item> titanic_xp_bottle = miningItems.register("titanic_xp_bottle",
			() -> new ModExperienceBottleItem(ItemProperties.m64, Type.TITANIC));
	public static final RegistryObject<Item> weather_stick = miningItems.register("weather_stick",
			() -> new EnchantedItem(ItemProperties.m1));
}