package net.hypixel.skyblock.init.items;

import net.hypixel.skyblock.HypixelSkyBlockMod;
import net.hypixel.skyblock.items.enchanteditems.EnchantedItem;
import net.hypixel.skyblock.util.ItemProperties;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Initialize and Register {@link Item}. <br>
 * This class should only register {@code Item} that mimic {@link ItemStack} but
 * as a single {@code Item}.
 *
 * @author MrPineapple070
 * @version 31 May 2020
 */
public class ExtraItemInit {
	public static final DeferredRegister<Item> miningExtraItems = new DeferredRegister<>(ForgeRegistries.ITEMS,
			HypixelSkyBlockMod.MOD_ID);

	public static final RegistryObject<Item> coal_16 = miningExtraItems.register("coal_16",
			() -> new Item(ItemProperties.m64));
	public static final RegistryObject<Item> coal_32 = miningExtraItems.register("coal_32",
			() -> new Item(ItemProperties.m64));
	public static final RegistryObject<Item> coal_64 = miningExtraItems.register("coal_64",
			() -> new Item(ItemProperties.m64));
	public static final RegistryObject<Item> coal_8 = miningExtraItems.register("coal_8",
			() -> new Item(ItemProperties.m64));
	public static final RegistryObject<Item> coal_blk_16 = miningExtraItems.register("coal_blk_16",
			() -> new Item(ItemProperties.m64));
	public static final RegistryObject<Item> coal_blk_32 = miningExtraItems.register("coal_blk_32",
			() -> new Item(ItemProperties.m64));
	public static final RegistryObject<Item> coal_blk_64 = miningExtraItems.register("coal_blk_64",
			() -> new Item(ItemProperties.m64));
	public static final RegistryObject<Item> coal_blk_8 = miningExtraItems.register("coal_blk_8",
			() -> new Item(ItemProperties.m64));
	public static final RegistryObject<Item> cobblestone_16 = miningExtraItems.register("cobblestone_16",
			() -> new Item(ItemProperties.m64));
	public static final RegistryObject<Item> cobblestone_32 = miningExtraItems.register("cobblestone_32",
			() -> new Item(ItemProperties.m64));
	public static final RegistryObject<Item> cobblestone_64 = miningExtraItems.register("cobblestone_64",
			() -> new Item(ItemProperties.m64));
	public static final RegistryObject<Item> cobblestone_8 = miningExtraItems.register("cobblestone_8",
			() -> new Item(ItemProperties.m64));

	public static final RegistryObject<Item> diamond_16 = miningExtraItems.register("diamond_16",
			() -> new Item(ItemProperties.m64));
	public static final RegistryObject<Item> diamond_32 = miningExtraItems.register("diamond_32",
			() -> new Item(ItemProperties.m64));
	public static final RegistryObject<Item> diamond_64 = miningExtraItems.register("diamond_64",
			() -> new Item(ItemProperties.m64));
	public static final RegistryObject<Item> diamond_8 = miningExtraItems.register("diamond_8",
			() -> new Item(ItemProperties.m64));
	public static final RegistryObject<Item> diamond_blk_16 = miningExtraItems.register("diamond_blk_16",
			() -> new Item(ItemProperties.m64));
	public static final RegistryObject<Item> diamond_blk_32 = miningExtraItems.register("diamond_blk_32",
			() -> new Item(ItemProperties.m64));
	public static final RegistryObject<Item> diamond_blk_64 = miningExtraItems.register("diamond_blk_64",
			() -> new Item(ItemProperties.m64));
	public static final RegistryObject<Item> diamond_blk_8 = miningExtraItems.register("diamond_blk_8",
			() -> new Item(ItemProperties.m64));

	public static final RegistryObject<Item> e_coal_16 = miningExtraItems.register("e_coal_16",
			() -> new EnchantedItem(ItemProperties.m64));
	public static final RegistryObject<Item> e_coal_32 = miningExtraItems.register("e_coal_32",
			() -> new EnchantedItem(ItemProperties.m64));
	public static final RegistryObject<Item> e_coal_4 = miningExtraItems.register("e_coal_4",
			() -> new EnchantedItem(ItemProperties.m64));
	public static final RegistryObject<Item> e_coal_64 = miningExtraItems.register("e_coal_64",
			() -> new EnchantedItem(ItemProperties.m64));
	public static final RegistryObject<Item> e_coal_8 = miningExtraItems.register("e_coal_8",
			() -> new EnchantedItem(ItemProperties.m64));

	public static final RegistryObject<Item> e_cobblestone_16 = miningExtraItems.register("e_cobblestone_16",
			() -> new EnchantedItem(ItemProperties.m64));
	public static final RegistryObject<Item> e_cobblestone_2 = miningExtraItems.register("e_cobblestone_2",
			() -> new EnchantedItem(ItemProperties.m64));
	public static final RegistryObject<Item> e_cobblestone_32 = miningExtraItems.register("e_cobblestone_32",
			() -> new EnchantedItem(ItemProperties.m64));
	public static final RegistryObject<Item> e_cobblestone_4 = miningExtraItems.register("e_cobblestone_4",
			() -> new EnchantedItem(ItemProperties.m64));
	public static final RegistryObject<Item> e_cobblestone_64 = miningExtraItems.register("e_cobblestone_64",
			() -> new EnchantedItem(ItemProperties.m64));
	public static final RegistryObject<Item> e_cobblestone_8 = miningExtraItems.register("e_cobblestone_8",
			() -> new EnchantedItem(ItemProperties.m64));

	public static final RegistryObject<Item> e_diamond_16 = miningExtraItems.register("e_diamond_16",
			() -> new EnchantedItem(ItemProperties.m64));
	public static final RegistryObject<Item> e_diamond_32 = miningExtraItems.register("e_diamond_32",
			() -> new EnchantedItem(ItemProperties.m64));
	public static final RegistryObject<Item> e_diamond_4 = miningExtraItems.register("e_diamond_4",
			() -> new EnchantedItem(ItemProperties.m64));
	public static final RegistryObject<Item> e_diamond_64 = miningExtraItems.register("e_diamond_64",
			() -> new EnchantedItem(ItemProperties.m64));
	public static final RegistryObject<Item> e_diamond_8 = miningExtraItems.register("e_diamond_8",
			() -> new EnchantedItem(ItemProperties.m64));

	public static final RegistryObject<Item> e_end_stone_64 = miningExtraItems.register("e_end_stone_64",
			() -> new EnchantedItem(ItemProperties.m64));
	public static final RegistryObject<Item> e_end_stone_8 = miningExtraItems.register("e_end_stone_8",
			() -> new EnchantedItem(ItemProperties.m64));
}