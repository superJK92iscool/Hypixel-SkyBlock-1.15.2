package net.hypixel.skyblock.tags;

import net.minecraft.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;

public class ModItemTags extends ItemTags {
	public static final Tag<Item> fuel = makeWrapperTag("fuel");
	public static final Tag<Item> upgrade = makeWrapperTag("upgrade");
	public static final Tag<Item> seller = makeWrapperTag("wrapper");
	public static final Tag<Item> pets = makeWrapperTag("pets");
	
	private static Tag<Item> makeWrapperTag(String location) {
		return new ItemTags.Wrapper(new ResourceLocation(location));
	}
}