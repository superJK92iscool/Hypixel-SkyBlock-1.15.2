package net.hypixel.skyblock.init.items;

import net.hypixel.skyblock.HypixelSkyBlockMod;
import net.hypixel.skyblock.items.pets.BatPetItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PetItemInit {
	public static final DeferredRegister<Item> pets = new DeferredRegister<Item>(ForgeRegistries.ITEMS,
			HypixelSkyBlockMod.MOD_ID);
	
	public static final RegistryObject<Item> bat_pet = pets.register("bat_pet", BatPetItem::new);
}