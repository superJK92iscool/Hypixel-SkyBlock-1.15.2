package net.hypixel.skyblock.init.blocks;

import net.hypixel.skyblock.HypixelSkyBlockMod;
import net.hypixel.skyblock.blocks.minion.AbstractMinionBlock;
import net.hypixel.skyblock.blocks.minion.CoalMinion;
import net.hypixel.skyblock.blocks.minion.CobblestoneMinion;
import net.hypixel.skyblock.blocks.minion.WheatMinion;
import net.hypixel.skyblock.tileentity.minion.AbstractMinionTileEntity.MinionTier;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Initialize and Register all {@link AbstractMinionBlock} for this Mod.
 *
 * @author MrPineapple070
 * @version 21 February 2020
 * @since 11 June 2019
 */
public class MinionBlockInit {
	public static final DeferredRegister<Block> minionBlocks = new DeferredRegister<>(ForgeRegistries.BLOCKS,
			HypixelSkyBlockMod.MOD_ID);

	public static final RegistryObject<Block> coal_minion_1 = minionBlocks.register("coal_minion_1",
			() -> new CoalMinion(MinionTier.I));
	public static final RegistryObject<Block> coal_minion_2 = minionBlocks.register("coal_minion_2",
			() -> new CoalMinion(MinionTier.II));
	public static final RegistryObject<Block> coal_minion_3 = minionBlocks.register("coal_minion_3",
			() -> new CoalMinion(MinionTier.III));
	public static final RegistryObject<Block> coal_minion_4 = minionBlocks.register("coal_minion_4",
			() -> new CoalMinion(MinionTier.IV));
	public static final RegistryObject<Block> coal_minion_5 = minionBlocks.register("coal_minion_5",
			() -> new CoalMinion(MinionTier.V));
	public static final RegistryObject<Block> coal_minion_6 = minionBlocks.register("coal_minion_6",
			() -> new CoalMinion(MinionTier.VI));
	public static final RegistryObject<Block> coal_minion_7 = minionBlocks.register("coal_minion_7",
			() -> new CoalMinion(MinionTier.VII));
	public static final RegistryObject<Block> coal_minion_8 = minionBlocks.register("coal_minion_8",
			() -> new CoalMinion(MinionTier.VIII));
	public static final RegistryObject<Block> coal_minion_9 = minionBlocks.register("coal_minion_9",
			() -> new CoalMinion(MinionTier.IX));
	public static final RegistryObject<Block> coal_minion_a = minionBlocks.register("coal_minion_a",
			() -> new CoalMinion(MinionTier.X));
	public static final RegistryObject<Block> coal_minion_b = minionBlocks.register("coal_minion_b",
			() -> new CoalMinion(MinionTier.XI));

	public static final RegistryObject<Block> cobblestone_minion_1 = minionBlocks.register("cobblestone_minion_1",
			() -> new CobblestoneMinion(MinionTier.I));
	public static final RegistryObject<Block> cobblestone_minion_2 = minionBlocks.register("cobblestone_minion_2",
			() -> new CobblestoneMinion(MinionTier.II));
	public static final RegistryObject<Block> cobblestone_minion_3 = minionBlocks.register("cobblestone_minion_3",
			() -> new CobblestoneMinion(MinionTier.III));
	public static final RegistryObject<Block> cobblestone_minion_4 = minionBlocks.register("cobblestone_minion_4",
			() -> new CobblestoneMinion(MinionTier.IV));
	public static final RegistryObject<Block> cobblestone_minion_5 = minionBlocks.register("cobblestone_minion_5",
			() -> new CobblestoneMinion(MinionTier.V));
	public static final RegistryObject<Block> cobblestone_minion_6 = minionBlocks.register("cobblestone_minion_6",
			() -> new CobblestoneMinion(MinionTier.VI));
	public static final RegistryObject<Block> cobblestone_minion_7 = minionBlocks.register("cobblestone_minion_7",
			() -> new CobblestoneMinion(MinionTier.VII));
	public static final RegistryObject<Block> cobblestone_minion_8 = minionBlocks.register("cobblestone_minion_8",
			() -> new CobblestoneMinion(MinionTier.VIII));
	public static final RegistryObject<Block> cobblestone_minion_9 = minionBlocks.register("cobblestone_minion_9",
			() -> new CobblestoneMinion(MinionTier.IX));
	public static final RegistryObject<Block> cobblestone_minion_a = minionBlocks.register("cobblestone_minion_a",
			() -> new CobblestoneMinion(MinionTier.X));
	public static final RegistryObject<Block> cobblestone_minion_b = minionBlocks.register("cobblestone_minion_b",
			() -> new CobblestoneMinion(MinionTier.XI));

	public static final RegistryObject<Block> wheat_minion_1 = minionBlocks.register("wheat_minion_1",
			() -> new WheatMinion(MinionTier.I));
	public static final RegistryObject<Block> wheat_minion_2 = minionBlocks.register("wheat_minion_2",
			() -> new WheatMinion(MinionTier.II));
	public static final RegistryObject<Block> wheat_minion_3 = minionBlocks.register("wheat_minion_3",
			() -> new WheatMinion(MinionTier.III));
	public static final RegistryObject<Block> wheat_minion_4 = minionBlocks.register("wheat_minion_4",
			() -> new WheatMinion(MinionTier.IV));
	public static final RegistryObject<Block> wheat_minion_5 = minionBlocks.register("wheat_minion_5",
			() -> new WheatMinion(MinionTier.V));
	public static final RegistryObject<Block> wheat_minion_6 = minionBlocks.register("wheat_minion_6",
			() -> new WheatMinion(MinionTier.VI));
	public static final RegistryObject<Block> wheat_minion_7 = minionBlocks.register("wheat_minion_7",
			() -> new WheatMinion(MinionTier.VII));
	public static final RegistryObject<Block> wheat_minion_8 = minionBlocks.register("wheat_minion_8",
			() -> new WheatMinion(MinionTier.VIII));
	public static final RegistryObject<Block> wheat_minion_9 = minionBlocks.register("wheat_minion_9",
			() -> new WheatMinion(MinionTier.IX));
	public static final RegistryObject<Block> wheat_minion_a = minionBlocks.register("wheat_minion_a",
			() -> new WheatMinion(MinionTier.X));
	public static final RegistryObject<Block> wheat_minion_b = minionBlocks.register("wheat_minion_b",
			() -> new WheatMinion(MinionTier.XI));
}