package net.hypixel.skyblock.init.items;

import net.hypixel.skyblock.HypixelSkyBlockMod;
import net.hypixel.skyblock.items.swords.AspectOfTheDragon;
import net.hypixel.skyblock.items.swords.AspectOfTheEnd;
import net.hypixel.skyblock.items.swords.AspectOfTheJerry;
import net.hypixel.skyblock.items.swords.Cleaver;
import net.hypixel.skyblock.items.swords.EdibleMace;
import net.hypixel.skyblock.items.swords.EmberRod;
import net.hypixel.skyblock.items.swords.EmeraldBlade;
import net.hypixel.skyblock.items.swords.EndStoneSword;
import net.hypixel.skyblock.items.swords.EndSword;
import net.hypixel.skyblock.items.swords.FancySword;
import net.hypixel.skyblock.items.swords.FlamingSword;
import net.hypixel.skyblock.items.swords.FrozenScythe;
import net.hypixel.skyblock.items.swords.GolemSword;
import net.hypixel.skyblock.items.swords.HunterKnife;
import net.hypixel.skyblock.items.swords.InkWand;
import net.hypixel.skyblock.items.swords.LeapingSword;
import net.hypixel.skyblock.items.swords.MidasSword;
import net.hypixel.skyblock.items.swords.OrnateZombieSword;
import net.hypixel.skyblock.items.swords.PigmanSword;
import net.hypixel.skyblock.items.swords.PoochSword;
import net.hypixel.skyblock.items.swords.PrismarineBlade;
import net.hypixel.skyblock.items.swords.RaiderAxe;
import net.hypixel.skyblock.items.swords.ReaperFalchion;
import net.hypixel.skyblock.items.swords.ReaperScythe;
import net.hypixel.skyblock.items.swords.RecluseFang;
import net.hypixel.skyblock.items.swords.RevenantFalchion;
import net.hypixel.skyblock.items.swords.RogueSword;
import net.hypixel.skyblock.items.swords.ScorpionFoil;
import net.hypixel.skyblock.items.swords.ShamanSword;
import net.hypixel.skyblock.items.swords.SilkEdgeSword;
import net.hypixel.skyblock.items.swords.SilverFang;
import net.hypixel.skyblock.items.swords.SpiderSword;
import net.hypixel.skyblock.items.swords.TacticianSword;
import net.hypixel.skyblock.items.swords.ThickAspectOfTheJerry;
import net.hypixel.skyblock.items.swords.ThickScorpionFoil;
import net.hypixel.skyblock.items.swords.ThickTacticianSword;
import net.hypixel.skyblock.items.swords.UndeadSword;
import net.hypixel.skyblock.items.swords.YetiSword;
import net.hypixel.skyblock.items.swords.ZombieSword;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Initialize and Register all {@link SwordItem} for this Mod.<br>
 * <a href="https://hypixel-skyblock.fandom.com/wiki/Swords">List of all
 * Swords</a>.
 *
 * @author MrPineapple070
 * @version 18 Decmeber 2019
 * @since 11 November 2019
 */
public class SwordInit {
	public static final DeferredRegister<Item> sword = new DeferredRegister<>(ForgeRegistries.ITEMS,
			HypixelSkyBlockMod.MOD_ID);

	public static final RegistryObject<SwordItem> aotd = sword.register("aspect_of_the_dragons",
			AspectOfTheDragon::new);
	public static final RegistryObject<SwordItem> aote = sword.register("aspect_of_the_end", AspectOfTheEnd::new);
	public static final RegistryObject<SwordItem> aotj = sword.register("aspect_of_the_jerry", AspectOfTheJerry::new);
	public static final RegistryObject<SwordItem> cleaver = sword.register("cleaver", Cleaver::new);
	public static final RegistryObject<SwordItem> edible_mace = sword.register("edible_mace", EdibleMace::new);
	public static final RegistryObject<SwordItem> ember_rod = sword.register("ember_rod", EmberRod::new);
	public static final RegistryObject<SwordItem> emerald_blade = sword.register("emerald_blade", EmeraldBlade::new);
	public static final RegistryObject<SwordItem> end_stone_sword = sword.register("end_stone_sword",
			EndStoneSword::new);
	public static final RegistryObject<SwordItem> end_sword = sword.register("end_sword", EndSword::new);
	public static final RegistryObject<SwordItem> fancy_sword = sword.register("fancy_sword", FancySword::new);
	public static final RegistryObject<SwordItem> flaming_sword = sword.register("flaming_sword", FlamingSword::new);
	public static final RegistryObject<SwordItem> frozen_scythe = sword.register("frozen_scythe", FrozenScythe::new);
	public static final RegistryObject<SwordItem> golem_sword = sword.register("golem_sword", GolemSword::new);
	public static final RegistryObject<SwordItem> hunter_knife = sword.register("hunter_knife", HunterKnife::new);
	public static final RegistryObject<SwordItem> ink_wand = sword.register("ink_wand", InkWand::new);
	public static final RegistryObject<SwordItem> leaping_sword = sword.register("leaping_sword", LeapingSword::new);
	public static final RegistryObject<SwordItem> midas_sword = sword.register("midas_sword", MidasSword::new);
	public static final RegistryObject<SwordItem> ornate_zombie_sword = sword.register("ornate_zombie_sword",
			OrnateZombieSword::new);
	public static final RegistryObject<SwordItem> pigman_sword = sword.register("pigman_sword", PigmanSword::new);
	public static final RegistryObject<SwordItem> pooch_sword = sword.register("pooch_sword", PoochSword::new);
	public static final RegistryObject<SwordItem> prismarine_blade = sword.register("prismarine_blade",
			PrismarineBlade::new);
	public static final RegistryObject<SwordItem> raider_axe = sword.register("raider_axe", RaiderAxe::new);
	public static final RegistryObject<SwordItem> reaper_falchion = sword.register("reaper_falchion",
			ReaperFalchion::new);
	public static final RegistryObject<SwordItem> reaper_scythe = sword.register("reaper_scythe", ReaperScythe::new);
	public static final RegistryObject<SwordItem> recluse_fang = sword.register("recluse_fang", RecluseFang::new);
	public static final RegistryObject<SwordItem> revenant_falchion = sword.register("revenant_falchion",
			RevenantFalchion::new);
	public static final RegistryObject<SwordItem> rogue_sword = sword.register("rogue_sword", RogueSword::new);
	public static final RegistryObject<SwordItem> scorpion_foil = sword.register("scorpion_foil", ScorpionFoil::new);
	public static final RegistryObject<SwordItem> shaman_sword = sword.register("shaman_sword", ShamanSword::new);
	public static final RegistryObject<SwordItem> silk_edge_sword = sword.register("silk_edge_sword",
			SilkEdgeSword::new);
	public static final RegistryObject<SwordItem> silver_fang = sword.register("silver_fang", SilverFang::new);
	public static final RegistryObject<SwordItem> spider_sword = sword.register("spider_sword", SpiderSword::new);
	public static final RegistryObject<SwordItem> tactician_sword = sword.register("tactician_sword",
			TacticianSword::new);
	public static final RegistryObject<SwordItem> thick_aotj = sword.register("thick_aspect_of_the_jerry",
			ThickAspectOfTheJerry::new);
	public static final RegistryObject<SwordItem> thick_scorpion_foil = sword.register("thick_scorpion_foil",
			ThickScorpionFoil::new);
	public static final RegistryObject<SwordItem> thick_tactician_sword = sword.register("thick_tactician_sword",
			ThickTacticianSword::new);
	public static final RegistryObject<SwordItem> undead_sword = sword.register("undead_sword", UndeadSword::new);
	public static final RegistryObject<SwordItem> yeti_sword = sword.register("yeti_sword", YetiSword::new);
	public static final RegistryObject<SwordItem> zombie_sword = sword.register("zombie_sword", ZombieSword::new);
}