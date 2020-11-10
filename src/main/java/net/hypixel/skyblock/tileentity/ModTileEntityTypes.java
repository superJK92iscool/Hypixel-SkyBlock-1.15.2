package net.hypixel.skyblock.tileentity;

import net.hypixel.skyblock.HypixelSkyBlockMod;
import net.hypixel.skyblock.init.blocks.BlockInit;
import net.hypixel.skyblock.init.blocks.MinionBlockInit;
import net.hypixel.skyblock.tileentity.TimeSaverTileEntity.DaySaverTileEntity;
import net.hypixel.skyblock.tileentity.TimeSaverTileEntity.NightSaverTileEntity;
import net.hypixel.skyblock.tileentity.minion.AbstractMinionChestTileEntity.LargeMCTE;
import net.hypixel.skyblock.tileentity.minion.AbstractMinionChestTileEntity.MediumMCTE;
import net.hypixel.skyblock.tileentity.minion.AbstractMinionChestTileEntity.SmallMCTE;
import net.hypixel.skyblock.tileentity.minion.CoalMinionTileEntity.CoalMTE1;
import net.hypixel.skyblock.tileentity.minion.CoalMinionTileEntity.CoalMTE2;
import net.hypixel.skyblock.tileentity.minion.CoalMinionTileEntity.CoalMTE3;
import net.hypixel.skyblock.tileentity.minion.CoalMinionTileEntity.CoalMTE4;
import net.hypixel.skyblock.tileentity.minion.CoalMinionTileEntity.CoalMTE5;
import net.hypixel.skyblock.tileentity.minion.CoalMinionTileEntity.CoalMTE6;
import net.hypixel.skyblock.tileentity.minion.CoalMinionTileEntity.CoalMTE7;
import net.hypixel.skyblock.tileentity.minion.CoalMinionTileEntity.CoalMTE8;
import net.hypixel.skyblock.tileentity.minion.CoalMinionTileEntity.CoalMTE9;
import net.hypixel.skyblock.tileentity.minion.CoalMinionTileEntity.CoalMTEa;
import net.hypixel.skyblock.tileentity.minion.CoalMinionTileEntity.CoalMTEb;
import net.hypixel.skyblock.tileentity.minion.CobblestoneMinionTileEntity.CobbleMTE1;
import net.hypixel.skyblock.tileentity.minion.CobblestoneMinionTileEntity.CobbleMTE2;
import net.hypixel.skyblock.tileentity.minion.CobblestoneMinionTileEntity.CobbleMTE3;
import net.hypixel.skyblock.tileentity.minion.CobblestoneMinionTileEntity.CobbleMTE4;
import net.hypixel.skyblock.tileentity.minion.CobblestoneMinionTileEntity.CobbleMTE5;
import net.hypixel.skyblock.tileentity.minion.CobblestoneMinionTileEntity.CobbleMTE6;
import net.hypixel.skyblock.tileentity.minion.CobblestoneMinionTileEntity.CobbleMTE7;
import net.hypixel.skyblock.tileentity.minion.CobblestoneMinionTileEntity.CobbleMTE8;
import net.hypixel.skyblock.tileentity.minion.CobblestoneMinionTileEntity.CobbleMTE9;
import net.hypixel.skyblock.tileentity.minion.CobblestoneMinionTileEntity.CobbleMTEa;
import net.hypixel.skyblock.tileentity.minion.CobblestoneMinionTileEntity.CobbleMTEb;
import net.hypixel.skyblock.tileentity.minion.WheatMinionTileEntity.WheatMTE1;
import net.hypixel.skyblock.tileentity.minion.WheatMinionTileEntity.WheatMTE2;
import net.hypixel.skyblock.tileentity.minion.WheatMinionTileEntity.WheatMTE3;
import net.hypixel.skyblock.tileentity.minion.WheatMinionTileEntity.WheatMTE4;
import net.hypixel.skyblock.tileentity.minion.WheatMinionTileEntity.WheatMTE5;
import net.hypixel.skyblock.tileentity.minion.WheatMinionTileEntity.WheatMTE6;
import net.hypixel.skyblock.tileentity.minion.WheatMinionTileEntity.WheatMTE7;
import net.hypixel.skyblock.tileentity.minion.WheatMinionTileEntity.WheatMTE8;
import net.hypixel.skyblock.tileentity.minion.WheatMinionTileEntity.WheatMTE9;
import net.hypixel.skyblock.tileentity.minion.WheatMinionTileEntity.WheatMTEa;
import net.hypixel.skyblock.tileentity.minion.WheatMinionTileEntity.WheatMTEb;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.tileentity.TileEntityType.Builder;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Initialize and Register all {@link TileEntityType} for this mod.
 *
 * @author MrPineapple070
 * @version 5 June 2020
 */
public class ModTileEntityTypes {
	public static final DeferredRegister<TileEntityType<?>> tile_entity_types = new DeferredRegister<>(
			ForgeRegistries.TILE_ENTITIES, HypixelSkyBlockMod.MOD_ID);

	public static final RegistryObject<TileEntityType<CoalMTE1>> coal_minion_1 = tile_entity_types.register(
			"coal_minion_1", () -> Builder.create(CoalMTE1::new, MinionBlockInit.coal_minion_1.get()).build(null));
	public static final RegistryObject<TileEntityType<CoalMTE2>> coal_minion_2 = tile_entity_types.register(
			"coal_minion_2", () -> Builder.create(CoalMTE2::new, MinionBlockInit.coal_minion_2.get()).build(null));
	public static final RegistryObject<TileEntityType<CoalMTE3>> coal_minion_3 = tile_entity_types.register(
			"coal_minion_3", () -> Builder.create(CoalMTE3::new, MinionBlockInit.coal_minion_3.get()).build(null));
	public static final RegistryObject<TileEntityType<CoalMTE4>> coal_minion_4 = tile_entity_types.register(
			"coal_minion_4", () -> Builder.create(CoalMTE4::new, MinionBlockInit.coal_minion_4.get()).build(null));
	public static final RegistryObject<TileEntityType<CoalMTE5>> coal_minion_5 = tile_entity_types.register(
			"coal_minion_5", () -> Builder.create(CoalMTE5::new, MinionBlockInit.coal_minion_5.get()).build(null));
	public static final RegistryObject<TileEntityType<CoalMTE6>> coal_minion_6 = tile_entity_types.register(
			"coal_minion_6", () -> Builder.create(CoalMTE6::new, MinionBlockInit.coal_minion_6.get()).build(null));
	public static final RegistryObject<TileEntityType<CoalMTE7>> coal_minion_7 = tile_entity_types.register(
			"coal_minion_7", () -> Builder.create(CoalMTE7::new, MinionBlockInit.coal_minion_7.get()).build(null));
	public static final RegistryObject<TileEntityType<CoalMTE8>> coal_minion_8 = tile_entity_types.register(
			"coal_minion_8", () -> Builder.create(CoalMTE8::new, MinionBlockInit.coal_minion_8.get()).build(null));
	public static final RegistryObject<TileEntityType<CoalMTE9>> coal_minion_9 = tile_entity_types.register(
			"coal_minion_9", () -> Builder.create(CoalMTE9::new, MinionBlockInit.coal_minion_9.get()).build(null));
	public static final RegistryObject<TileEntityType<CoalMTEa>> coal_minion_a = tile_entity_types.register(
			"coal_minion_a", () -> Builder.create(CoalMTEa::new, MinionBlockInit.coal_minion_a.get()).build(null));
	public static final RegistryObject<TileEntityType<CoalMTEb>> coal_minion_b = tile_entity_types.register(
			"coal_minion_b", () -> Builder.create(CoalMTEb::new, MinionBlockInit.coal_minion_b.get()).build(null));

	public static final RegistryObject<TileEntityType<CobbleMTE1>> cobblestone_minion_1 = tile_entity_types.register(
			"cobblestone_minion_1",
			() -> Builder.create(CobbleMTE1::new, MinionBlockInit.cobblestone_minion_1.get()).build(null));
	public static final RegistryObject<TileEntityType<CobbleMTE2>> cobblestone_minion_2 = tile_entity_types.register(
			"cobblestone_minion_2",
			() -> Builder.create(CobbleMTE2::new, MinionBlockInit.cobblestone_minion_2.get()).build(null));
	public static final RegistryObject<TileEntityType<CobbleMTE3>> cobblestone_minion_3 = tile_entity_types.register(
			"cobblestone_minion_3",
			() -> Builder.create(CobbleMTE3::new, MinionBlockInit.cobblestone_minion_3.get()).build(null));
	public static final RegistryObject<TileEntityType<CobbleMTE4>> cobblestone_minion_4 = tile_entity_types.register(
			"cobblestone_minion_4",
			() -> Builder.create(CobbleMTE4::new, MinionBlockInit.cobblestone_minion_4.get()).build(null));
	public static final RegistryObject<TileEntityType<CobbleMTE5>> cobblestone_minion_5 = tile_entity_types.register(
			"cobblestone_minion_5",
			() -> Builder.create(CobbleMTE5::new, MinionBlockInit.cobblestone_minion_5.get()).build(null));
	public static final RegistryObject<TileEntityType<CobbleMTE6>> cobblestone_minion_6 = tile_entity_types.register(
			"cobblestone_minion_6",
			() -> Builder.create(CobbleMTE6::new, MinionBlockInit.cobblestone_minion_6.get()).build(null));
	public static final RegistryObject<TileEntityType<CobbleMTE7>> cobblestone_minion_7 = tile_entity_types.register(
			"cobblestone_minion_7",
			() -> Builder.create(CobbleMTE7::new, MinionBlockInit.cobblestone_minion_7.get()).build(null));
	public static final RegistryObject<TileEntityType<CobbleMTE8>> cobblestone_minion_8 = tile_entity_types.register(
			"cobblestone_minion_8",
			() -> Builder.create(CobbleMTE8::new, MinionBlockInit.cobblestone_minion_8.get()).build(null));
	public static final RegistryObject<TileEntityType<CobbleMTE9>> cobblestone_minion_9 = tile_entity_types.register(
			"cobblestone_minion_9",
			() -> Builder.create(CobbleMTE9::new, MinionBlockInit.cobblestone_minion_9.get()).build(null));
	public static final RegistryObject<TileEntityType<CobbleMTEa>> cobblestone_minion_a = tile_entity_types.register(
			"cobblestone_minion_a",
			() -> Builder.create(CobbleMTEa::new, MinionBlockInit.cobblestone_minion_a.get()).build(null));
	public static final RegistryObject<TileEntityType<CobbleMTEb>> cobblestone_minion_b = tile_entity_types.register(
			"cobblestone_minion_b",
			() -> Builder.create(CobbleMTEb::new, MinionBlockInit.cobblestone_minion_b.get()).build(null));

	public static final RegistryObject<TileEntityType<DaySaverTileEntity>> day_saver = tile_entity_types.register(
			"day_saver", () -> Builder.create(DaySaverTileEntity::new, BlockInit.day_saver.get()).build(null));
	public static final RegistryObject<TileEntityType<LargeMCTE>> large_mcte = tile_entity_types.register("large_mcte",
			() -> Builder.create(LargeMCTE::new, BlockInit.large_mcb.get()).build(null));
	public static final RegistryObject<TileEntityType<MediumMCTE>> medium_mcte = tile_entity_types
			.register("medium_mcte", () -> Builder.create(MediumMCTE::new, BlockInit.medium_mcb.get()).build(null));
	public static final RegistryObject<TileEntityType<NightSaverTileEntity>> night_saver = tile_entity_types.register(
			"night_saver", () -> Builder.create(NightSaverTileEntity::new, BlockInit.night_saver.get()).build(null));
	public static final RegistryObject<TileEntityType<SmallMCTE>> small_mcte = tile_entity_types.register("small_mcte",
			() -> Builder.create(SmallMCTE::new, BlockInit.small_mcb.get()).build(null));

	public static final RegistryObject<TileEntityType<WheatMTE1>> wheat_minion_1 = tile_entity_types.register(
			"wheat_minion_1", () -> Builder.create(WheatMTE1::new, MinionBlockInit.wheat_minion_1.get()).build(null));
	public static final RegistryObject<TileEntityType<WheatMTE2>> wheat_minion_2 = tile_entity_types.register(
			"wheat_minion_2", () -> Builder.create(WheatMTE2::new, MinionBlockInit.wheat_minion_2.get()).build(null));
	public static final RegistryObject<TileEntityType<WheatMTE3>> wheat_minion_3 = tile_entity_types.register(
			"wheat_minion_3", () -> Builder.create(WheatMTE3::new, MinionBlockInit.wheat_minion_3.get()).build(null));
	public static final RegistryObject<TileEntityType<WheatMTE4>> wheat_minion_4 = tile_entity_types.register(
			"wheat_minion_4", () -> Builder.create(WheatMTE4::new, MinionBlockInit.wheat_minion_4.get()).build(null));
	public static final RegistryObject<TileEntityType<WheatMTE5>> wheat_minion_5 = tile_entity_types.register(
			"wheat_minion_5", () -> Builder.create(WheatMTE5::new, MinionBlockInit.wheat_minion_5.get()).build(null));
	public static final RegistryObject<TileEntityType<WheatMTE6>> wheat_minion_6 = tile_entity_types.register(
			"wheat_minion_6", () -> Builder.create(WheatMTE6::new, MinionBlockInit.wheat_minion_6.get()).build(null));
	public static final RegistryObject<TileEntityType<WheatMTE7>> wheat_minion_7 = tile_entity_types.register(
			"wheat_minion_7", () -> Builder.create(WheatMTE7::new, MinionBlockInit.wheat_minion_7.get()).build(null));
	public static final RegistryObject<TileEntityType<WheatMTE8>> wheat_minion_8 = tile_entity_types.register(
			"wheat_minion_8", () -> Builder.create(WheatMTE8::new, MinionBlockInit.wheat_minion_8.get()).build(null));
	public static final RegistryObject<TileEntityType<WheatMTE9>> wheat_minion_9 = tile_entity_types.register(
			"wheat_minion_9", () -> Builder.create(WheatMTE9::new, MinionBlockInit.wheat_minion_9.get()).build(null));
	public static final RegistryObject<TileEntityType<WheatMTEa>> wheat_minion_a = tile_entity_types.register(
			"wheat_minion_a", () -> Builder.create(WheatMTEa::new, MinionBlockInit.wheat_minion_a.get()).build(null));
	public static final RegistryObject<TileEntityType<WheatMTEb>> wheat_minion_b = tile_entity_types.register(
			"wheat_minion_b", () -> Builder.create(WheatMTEb::new, MinionBlockInit.wheat_minion_b.get()).build(null));
}