package net.hypixel.skyblock.tags;

import net.minecraft.entity.EntityType;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;

public class ModEntityTypesTag extends EntityTypeTags {
	public static final Tag<EntityType<?>> boss = makeWrapperTag("boss");
	public static final Tag<EntityType<?>> hostile = makeWrapperTag("hostile");
	public static final Tag<EntityType<?>> neutral = makeWrapperTag("neutral");
	public static final Tag<EntityType<?>> passive = makeWrapperTag("passive");

	private static Tag<EntityType<?>> makeWrapperTag(String location) {
		return new EntityTypeTags.Wrapper(new ResourceLocation(location));
	}
}