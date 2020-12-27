package net.hypixel.skyblock.init.items;

import java.util.List;

import net.hypixel.skyblock.HypixelSkyBlockMod;
import net.hypixel.skyblock.pets.PetItem;
import net.hypixel.skyblock.pets.Pets;
import net.hypixel.skyblock.util.ItemProperties;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PetItemInit {
	public static final DeferredRegister<Item> pets = new DeferredRegister<Item>(ForgeRegistries.ITEMS,
			HypixelSkyBlockMod.MOD_ID);
	
	public static final RegistryObject<Item> bat_pet = pets.register("bat_pet", () -> new PetItem(ItemProperties.ms64, Pets.bat) {
		@Override
		public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		}
	});
}