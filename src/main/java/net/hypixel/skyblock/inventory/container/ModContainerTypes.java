package net.hypixel.skyblock.inventory.container;

import net.hypixel.skyblock.HypixelSkyBlockMod;
import net.hypixel.skyblock.inventory.container.AbstractBackpackContainer.GreaterBC;
import net.hypixel.skyblock.inventory.container.AbstractBackpackContainer.LargeBC;
import net.hypixel.skyblock.inventory.container.AbstractBackpackContainer.MediumBC;
import net.hypixel.skyblock.inventory.container.AbstractBackpackContainer.SmallBC;
import net.hypixel.skyblock.inventory.container.minion.CoalMinionContainer.CoalMC1;
import net.hypixel.skyblock.inventory.container.minion.CoalMinionContainer.CoalMC2;
import net.hypixel.skyblock.inventory.container.minion.CoalMinionContainer.CoalMC3;
import net.hypixel.skyblock.inventory.container.minion.CoalMinionContainer.CoalMC4;
import net.hypixel.skyblock.inventory.container.minion.CoalMinionContainer.CoalMC5;
import net.hypixel.skyblock.inventory.container.minion.CoalMinionContainer.CoalMC6;
import net.hypixel.skyblock.inventory.container.minion.CoalMinionContainer.CoalMC7;
import net.hypixel.skyblock.inventory.container.minion.CoalMinionContainer.CoalMC8;
import net.hypixel.skyblock.inventory.container.minion.CoalMinionContainer.CoalMC9;
import net.hypixel.skyblock.inventory.container.minion.CoalMinionContainer.CoalMCa;
import net.hypixel.skyblock.inventory.container.minion.CoalMinionContainer.CoalMCb;
import net.hypixel.skyblock.inventory.container.minion.CobblestoneMinionContainer.CobbleMC1;
import net.hypixel.skyblock.inventory.container.minion.CobblestoneMinionContainer.CobbleMC2;
import net.hypixel.skyblock.inventory.container.minion.CobblestoneMinionContainer.CobbleMC3;
import net.hypixel.skyblock.inventory.container.minion.CobblestoneMinionContainer.CobbleMC4;
import net.hypixel.skyblock.inventory.container.minion.CobblestoneMinionContainer.CobbleMC5;
import net.hypixel.skyblock.inventory.container.minion.CobblestoneMinionContainer.CobbleMC6;
import net.hypixel.skyblock.inventory.container.minion.CobblestoneMinionContainer.CobbleMC7;
import net.hypixel.skyblock.inventory.container.minion.CobblestoneMinionContainer.CobbleMC8;
import net.hypixel.skyblock.inventory.container.minion.CobblestoneMinionContainer.CobbleMC9;
import net.hypixel.skyblock.inventory.container.minion.CobblestoneMinionContainer.CobbleMCa;
import net.hypixel.skyblock.inventory.container.minion.CobblestoneMinionContainer.CobbleMCb;
import net.hypixel.skyblock.inventory.container.minion.MinionChestContainer.LargeMCC;
import net.hypixel.skyblock.inventory.container.minion.MinionChestContainer.MediumMCC;
import net.hypixel.skyblock.inventory.container.minion.MinionChestContainer.SmallMCC;
import net.hypixel.skyblock.inventory.container.minion.WheatMinionContainer.WheatMC1;
import net.hypixel.skyblock.inventory.container.minion.WheatMinionContainer.WheatMC2;
import net.hypixel.skyblock.inventory.container.minion.WheatMinionContainer.WheatMC3;
import net.hypixel.skyblock.inventory.container.minion.WheatMinionContainer.WheatMC4;
import net.hypixel.skyblock.inventory.container.minion.WheatMinionContainer.WheatMC5;
import net.hypixel.skyblock.inventory.container.minion.WheatMinionContainer.WheatMC6;
import net.hypixel.skyblock.inventory.container.minion.WheatMinionContainer.WheatMC7;
import net.hypixel.skyblock.inventory.container.minion.WheatMinionContainer.WheatMC8;
import net.hypixel.skyblock.inventory.container.minion.WheatMinionContainer.WheatMC9;
import net.hypixel.skyblock.inventory.container.minion.WheatMinionContainer.WheatMCa;
import net.hypixel.skyblock.inventory.container.minion.WheatMinionContainer.WheatMCb;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Initialize and Register all {@link ContainerType} for this mod.
 *
 * @author MrPineapple070
 * @version 5 June 2020
 */
public class ModContainerTypes {
	public static final DeferredRegister<ContainerType<?>> container_types = new DeferredRegister<>(ForgeRegistries.CONTAINERS, HypixelSkyBlockMod.MOD_ID);

	public static final RegistryObject<ContainerType<CoalMC1>> coal_minion_1 = container_types.register("coal_minion_1",
			() -> IForgeContainerType.create(CoalMC1::new));
	public static final RegistryObject<ContainerType<CoalMC2>> coal_minion_2 = container_types.register("coal_minion_2",
			() -> IForgeContainerType.create(CoalMC2::new));
	public static final RegistryObject<ContainerType<CoalMC3>> coal_minion_3 = container_types.register("coal_minion_3",
			() -> IForgeContainerType.create(CoalMC3::new));
	public static final RegistryObject<ContainerType<CoalMC4>> coal_minion_4 = container_types.register("coal_minion_4",
			() -> IForgeContainerType.create(CoalMC4::new));
	public static final RegistryObject<ContainerType<CoalMC5>> coal_minion_5 = container_types.register("coal_minion_5",
			() -> IForgeContainerType.create(CoalMC5::new));
	public static final RegistryObject<ContainerType<CoalMC6>> coal_minion_6 = container_types.register("coal_minion_6",
			() -> IForgeContainerType.create(CoalMC6::new));
	public static final RegistryObject<ContainerType<CoalMC7>> coal_minion_7 = container_types.register("coal_minion_7",
			() -> IForgeContainerType.create(CoalMC7::new));
	public static final RegistryObject<ContainerType<CoalMC8>> coal_minion_8 = container_types.register("coal_minion_8",
			() -> IForgeContainerType.create(CoalMC8::new));
	public static final RegistryObject<ContainerType<CoalMC9>> coal_minion_9 = container_types.register("coal_minion_9",
			() -> IForgeContainerType.create(CoalMC9::new));
	public static final RegistryObject<ContainerType<CoalMCa>> coal_minion_a = container_types.register("coal_minion_a",
			() -> IForgeContainerType.create(CoalMCa::new));
	public static final RegistryObject<ContainerType<CoalMCb>> coal_minion_b = container_types.register("coal_minion_b",
			() -> IForgeContainerType.create(CoalMCb::new));

	public static final RegistryObject<ContainerType<CobbleMC1>> cobblestone_minion_1 = container_types
			.register("cobblestone_minion_1", () -> IForgeContainerType.create(CobbleMC1::new));
	public static final RegistryObject<ContainerType<CobbleMC2>> cobblestone_minion_2 = container_types
			.register("cobblestone_minion_2", () -> IForgeContainerType.create(CobbleMC2::new));
	public static final RegistryObject<ContainerType<CobbleMC3>> cobblestone_minion_3 = container_types
			.register("cobblestone_minion_3", () -> IForgeContainerType.create(CobbleMC3::new));
	public static final RegistryObject<ContainerType<CobbleMC4>> cobblestone_minion_4 = container_types
			.register("cobblestone_minion_4", () -> IForgeContainerType.create(CobbleMC4::new));
	public static final RegistryObject<ContainerType<CobbleMC5>> cobblestone_minion_5 = container_types
			.register("cobblestone_minion_5", () -> IForgeContainerType.create(CobbleMC5::new));
	public static final RegistryObject<ContainerType<CobbleMC6>> cobblestone_minion_6 = container_types
			.register("cobblestone_minion_6", () -> IForgeContainerType.create(CobbleMC6::new));
	public static final RegistryObject<ContainerType<CobbleMC7>> cobblestone_minion_7 = container_types
			.register("cobblestone_minion_7", () -> IForgeContainerType.create(CobbleMC7::new));
	public static final RegistryObject<ContainerType<CobbleMC8>> cobblestone_minion_8 = container_types
			.register("cobblestone_minion_8", () -> IForgeContainerType.create(CobbleMC8::new));
	public static final RegistryObject<ContainerType<CobbleMC9>> cobblestone_minion_9 = container_types
			.register("cobblestone_minion_9", () -> IForgeContainerType.create(CobbleMC9::new));
	public static final RegistryObject<ContainerType<CobbleMCa>> cobblestone_minion_a = container_types
			.register("cobblestone_minion_a", () -> IForgeContainerType.create(CobbleMCa::new));
	public static final RegistryObject<ContainerType<CobbleMCb>> cobblestone_minion_b = container_types
			.register("cobblestone_minion_b", () -> IForgeContainerType.create(CobbleMCb::new));

	public static final RegistryObject<ContainerType<WheatMC1>> wheat_minion_1 = container_types
			.register("wheat_minion_1", () -> IForgeContainerType.create(WheatMC1::new));
	public static final RegistryObject<ContainerType<WheatMC2>> wheat_minion_2 = container_types
			.register("wheat_minion_2", () -> IForgeContainerType.create(WheatMC2::new));
	public static final RegistryObject<ContainerType<WheatMC3>> wheat_minion_3 = container_types
			.register("wheat_minion_3", () -> IForgeContainerType.create(WheatMC3::new));
	public static final RegistryObject<ContainerType<WheatMC4>> wheat_minion_4 = container_types
			.register("wheat_minion_4", () -> IForgeContainerType.create(WheatMC4::new));
	public static final RegistryObject<ContainerType<WheatMC5>> wheat_minion_5 = container_types
			.register("wheat_minion_5", () -> IForgeContainerType.create(WheatMC5::new));
	public static final RegistryObject<ContainerType<WheatMC6>> wheat_minion_6 = container_types
			.register("wheat_minion_6", () -> IForgeContainerType.create(WheatMC6::new));
	public static final RegistryObject<ContainerType<WheatMC7>> wheat_minion_7 = container_types
			.register("wheat_minion_7", () -> IForgeContainerType.create(WheatMC7::new));
	public static final RegistryObject<ContainerType<WheatMC8>> wheat_minion_8 = container_types
			.register("wheat_minion_8", () -> IForgeContainerType.create(WheatMC8::new));
	public static final RegistryObject<ContainerType<WheatMC9>> wheat_minion_9 = container_types
			.register("wheat_minion_9", () -> IForgeContainerType.create(WheatMC9::new));
	public static final RegistryObject<ContainerType<WheatMCa>> wheat_minion_a = container_types
			.register("wheat_minion_a", () -> IForgeContainerType.create(WheatMCa::new));
	public static final RegistryObject<ContainerType<WheatMCb>> wheat_minion_b = container_types
			.register("wheat_minion_b", () -> IForgeContainerType.create(WheatMCb::new));

	public static final RegistryObject<ContainerType<GreaterBC>> greater_backpack = container_types
			.register("greater_backpack", () -> IForgeContainerType.create(GreaterBC::new));
	public static final RegistryObject<ContainerType<LargeBC>> large_backpack = container_types
			.register("large_backpack", () -> IForgeContainerType.create(LargeBC::new));
	public static final RegistryObject<ContainerType<MediumBC>> medium_backpack = container_types
			.register("medium_backpack", () -> IForgeContainerType.create(MediumBC::new));
	public static final RegistryObject<ContainerType<SmallBC>> small_backpack = container_types
			.register("small_backpack", () -> IForgeContainerType.create(SmallBC::new));

	public static final RegistryObject<ContainerType<LargeMCC>> large_mcc = container_types.register("large_mcc",
			() -> IForgeContainerType.create(LargeMCC::new));
	public static final RegistryObject<ContainerType<MediumMCC>> medium_mcc = container_types.register("medium_mcc",
			() -> IForgeContainerType.create(MediumMCC::new));
	public static final RegistryObject<ContainerType<SmallMCC>> small_mcc = container_types.register("small_mcc",
			() -> IForgeContainerType.create(SmallMCC::new));
}