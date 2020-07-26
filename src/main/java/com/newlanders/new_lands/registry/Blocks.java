package com.newlanders.new_lands.registry;

import com.newlanders.new_lands.CreativeTabs;
import com.newlanders.new_lands.NewLands;
import com.newlanders.new_lands.blocks.*;
import com.newlanders.new_lands.blocks.DoorBlock;
import com.newlanders.new_lands.features.PalmTreeFeature;
import com.newlanders.new_lands.utils.BiRegistry;
import net.minecraft.block.*;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.function.Supplier;

//GiantLuigi4:Copy general methods from another one of my mods, because it's another one of my mods that I copied from, so why shouldn't I?
public class Blocks {
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, NewLands.ModID);
	
	//BUILDING BLOCKS
	//TROPICAL
	public static final BiRegistry<RegistryObject<Block>, RegistryObject<Item>> STRIPPED_TROPICAL_PALM_LOG = registerBlockWithItem("stripped_tropical_palm_log", new LogBlock(MaterialColor.ADOBE, Block.Properties.from(net.minecraft.block.Blocks.OAK_LOG)), 64, CreativeTabs.BUILDING, null);
	public static final BiRegistry<RegistryObject<Block>, RegistryObject<Item>> TROPICAL_PALM_PLANKS = registerBlockWithItem("tropical_palm_planks", new Block(Block.Properties.from(net.minecraft.block.Blocks.OAK_PLANKS)), 64, CreativeTabs.BUILDING, null);
	public static final BiRegistry<RegistryObject<Block>, RegistryObject<Item>> STRIPPED_MAGNOLIA_LOG = registerBlockWithItem("stripped_magnolia_log", new LogBlock(MaterialColor.PINK, Block.Properties.from(net.minecraft.block.Blocks.OAK_PLANKS)), 64, CreativeTabs.BUILDING, null);
	public static final BiRegistry<RegistryObject<Block>, RegistryObject<Item>> TROPICAL_PALM_DOOR = registerBlockWithItem("tropical_palm_door", new DoorBlock(Block.Properties.from(net.minecraft.block.Blocks.OAK_DOOR)), 64, CreativeTabs.BUILDING, null);
	public static final BiRegistry<RegistryObject<Block>, RegistryObject<Item>> TROPICAL_PALM_TRAPDOOR = registerBlockWithItem("tropical_palm_trapdoor", new TrapdoorBlock(Block.Properties.from(net.minecraft.block.Blocks.OAK_TRAPDOOR)), 64, CreativeTabs.BUILDING, null);
	public static final BiRegistry<RegistryObject<Block>, RegistryObject<Item>> TROPICAL_PALM_FENCE = registerBlockWithItem("tropical_palm_fence", new FenceBlock(Block.Properties.from(net.minecraft.block.Blocks.OAK_FENCE)), 64, CreativeTabs.BUILDING, null);
	public static final BiRegistry<RegistryObject<Block>, RegistryObject<Item>> TROPICAL_PALM_FENCE_GATE = registerBlockWithItem("tropical_palm_fence_gate", new NLFenceGate(Block.Properties.from(net.minecraft.block.Blocks.OAK_FENCE_GATE)), 64, CreativeTabs.BUILDING, null);
	public static final BiRegistry<RegistryObject<Block>, RegistryObject<Item>> TROPICAL_PALM_BUTTON = registerBlockWithItem("tropical_palm_button", new NLButton(Block.Properties.from(net.minecraft.block.Blocks.OAK_BUTTON)), 64, CreativeTabs.BUILDING, null);
	public static final BiRegistry<RegistryObject<Block>, RegistryObject<Item>> TROPICAL_PALM_STAIRS = registerBlockWithItem("tropical_palm_stairs", new StairsBlock(() -> TROPICAL_PALM_PLANKS.getObject1().get().getDefaultState(), Block.Properties.from(net.minecraft.block.Blocks.OAK_STAIRS)), 64, CreativeTabs.BUILDING, null);
	public static final BiRegistry<RegistryObject<Block>, RegistryObject<Item>> TROPICAL_PALM_SLAB = registerBlockWithItem("tropical_palm_slab", new SlabBlock(Block.Properties.from(net.minecraft.block.Blocks.OAK_FENCE)), 64, CreativeTabs.BUILDING, null);
	public static final BiRegistry<RegistryObject<Block>, RegistryObject<Item>> TROPICAL_PALM_PRESSURE_PLATE = registerBlockWithItem("tropical_palm_pressure_plate", new NLPressurePlate(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.from(net.minecraft.block.Blocks.OAK_PRESSURE_PLATE)), 64, CreativeTabs.BUILDING, null);
	//MAGNOLIA
	public static final BiRegistry<RegistryObject<Block>, RegistryObject<Item>> MAGNOLIA_PLANKS = registerBlockWithItem("magnolia_planks", new Block(Block.Properties.from(net.minecraft.block.Blocks.OAK_PLANKS)), 64, CreativeTabs.BUILDING, null);
	public static final BiRegistry<RegistryObject<Block>, RegistryObject<Item>> MAGNOLIA_DOOR = registerBlockWithItem("magnolia_door", new DoorBlock(Block.Properties.from(net.minecraft.block.Blocks.OAK_DOOR)), 64, CreativeTabs.BUILDING, null);
	public static final BiRegistry<RegistryObject<Block>, RegistryObject<Item>> MAGNOLIA_TRAPDOOR = registerBlockWithItem("magnolia_trapdoor", new TrapdoorBlock(Block.Properties.from(net.minecraft.block.Blocks.OAK_TRAPDOOR)), 64, CreativeTabs.BUILDING, null);
	public static final BiRegistry<RegistryObject<Block>, RegistryObject<Item>> MAGNOLIA_FENCE = registerBlockWithItem("magnolia_fence", new FenceBlock(Block.Properties.from(net.minecraft.block.Blocks.OAK_TRAPDOOR)), 64, CreativeTabs.BUILDING, null);
	public static final BiRegistry<RegistryObject<Block>, RegistryObject<Item>> MAGNOLIA_FENCE_GATE = registerBlockWithItem("magnolia_fence_gate", new NLFenceGate(Block.Properties.from(net.minecraft.block.Blocks.OAK_TRAPDOOR)), 64, CreativeTabs.BUILDING, null);
	public static final BiRegistry<RegistryObject<Block>, RegistryObject<Item>> MAGNOLIA_STAIRS = registerBlockWithItem("magnolia_stairs", new StairsBlock(() -> MAGNOLIA_PLANKS.getObject1().get().getDefaultState(), Block.Properties.from(net.minecraft.block.Blocks.OAK_STAIRS)), 64, CreativeTabs.BUILDING, null);
	//SHOREROCK
	public static final BiRegistry<RegistryObject<Block>, RegistryObject<Item>> CHISELED_POLISHED_SHOREROCK = registerBlockWithItem("chiseled_polished_shorerock", new Block(Block.Properties.from(net.minecraft.block.Blocks.POLISHED_ANDESITE)), 64, CreativeTabs.BUILDING, null);
	public static final BiRegistry<RegistryObject<Block>, RegistryObject<Item>> POLISHED_SHOREROCK = registerBlockWithItem("polished_shorerock", new Block(Block.Properties.from(net.minecraft.block.Blocks.POLISHED_ANDESITE)), 64, CreativeTabs.BUILDING, null);
	public static final BiRegistry<RegistryObject<Block>, RegistryObject<Item>> POLISHED_SHOREROCK_BRICKS = registerBlockWithItem("polished_shorerock_bricks", new Block(Block.Properties.from(net.minecraft.block.Blocks.STONE_BRICKS)), 64, CreativeTabs.BUILDING, null);
	public static final BiRegistry<RegistryObject<Block>, RegistryObject<Item>> POLISHED_SHOREROCK_BRICKS_SEAWEED = registerBlockWithItem("polished_shorerock_bricks_seaweed", new Block(Block.Properties.from(net.minecraft.block.Blocks.STONE_BRICKS)), 64, CreativeTabs.BUILDING, null);
	
	//WORLD GEN
	//TROPICAL TREE
	public static final BiRegistry<RegistryObject<Block>, RegistryObject<Item>> SAND_LAYER = registerBlockWithItem("sand_layer", new LayerBlock(getSandLayerSupplier()), 64, CreativeTabs.WORLD_GEN, null);
	public static final BiRegistry<RegistryObject<Block>, RegistryObject<Item>> TROPICAL_PALM_LOG = registerBlockWithItem("tropical_palm_log", new StrippableLogBlock(MaterialColor.ADOBE, Block.Properties.from(net.minecraft.block.Blocks.OAK_LOG), () -> STRIPPED_TROPICAL_PALM_LOG.getObject1().get()), 64, CreativeTabs.WORLD_GEN, null);
	public static final BiRegistry<RegistryObject<Block>, RegistryObject<Item>> TROPICAL_PALM_LEAVES = registerBlockWithItem("tropical_palm_leaves", new LeavesBlock(Block.Properties.from(net.minecraft.block.Blocks.OAK_LEAVES)), 64, CreativeTabs.WORLD_GEN, null);
	public static final BiRegistry<RegistryObject<Block>, RegistryObject<Item>> TROPICAL_PALM_SAPLING = registerBlockWithItem("tropical_palm_sapling", new FeatureSapling(new PalmTreeFeature(ProbabilityConfig::deserialize), Block.Properties.from(net.minecraft.block.Blocks.OAK_SAPLING)), 64, CreativeTabs.WORLD_GEN, null);
	//MAGNOLIA TREE
	public static final BiRegistry<RegistryObject<Block>, RegistryObject<Item>> MAGNOLIA_LOG = registerBlockWithItem("magnolia_log", new StrippableLogBlock(MaterialColor.PINK, Block.Properties.from(net.minecraft.block.Blocks.OAK_LOG), () -> STRIPPED_MAGNOLIA_LOG.getObject1().get()), 64, CreativeTabs.WORLD_GEN, null);
	public static final BiRegistry<RegistryObject<Block>, RegistryObject<Item>> MAGNOLIA_LEAVES = registerBlockWithItem("magnolia_leaves", new LeavesBlock(Block.Properties.from(net.minecraft.block.Blocks.OAK_LEAVES)), 64, CreativeTabs.WORLD_GEN, null);
	public static final BiRegistry<RegistryObject<Block>, RegistryObject<Item>> FLOWERING_MAGNOLIA_LEAVES = registerBlockWithItem("flowering_magnolia_leaves", new LeavesBlock(Block.Properties.from(net.minecraft.block.Blocks.OAK_LEAVES)), 64, CreativeTabs.WORLD_GEN, null);
	//TERRAIN
	public static final BiRegistry<RegistryObject<Block>, RegistryObject<Item>> COBBLE_DIRT = registerBlockWithItem("cobble_dirt", new Block(Block.Properties.from(net.minecraft.block.Blocks.COBBLESTONE)), 64, CreativeTabs.WORLD_GEN, null);
	public static final BiRegistry<RegistryObject<Block>, RegistryObject<Item>> COBBLE_SAND = registerBlockWithItem("sandy_cobblestone", new Block(Block.Properties.from(net.minecraft.block.Blocks.COBBLESTONE)), 64, CreativeTabs.WORLD_GEN, null);
	public static final BiRegistry<RegistryObject<Block>, RegistryObject<Item>> SHOREROCK = registerBlockWithItem("shorerock", new Block(Block.Properties.from(net.minecraft.block.Blocks.ANDESITE)), 64, CreativeTabs.WORLD_GEN, null);
	
	public static BiRegistry<RegistryObject<Block>, RegistryObject<Item>> registerBlockWithItem(String name, Block block, int maxStack, @Nullable ItemGroup group, @Nullable Rarity rarity) {
		Item.Properties properties = new Item.Properties();
		if (group != null) properties.group(group);
		if (rarity != null) properties.rarity(rarity);
		properties.maxStackSize(maxStack);
		return new BiRegistry<>(
				BLOCKS.register(name, () -> block),
				Items.ITEMS.register(name, () -> (new BlockItem(block, properties)))
		);
	}
	
	private static Supplier<Item> getSandLayerSupplier() {
		return () -> SAND_LAYER.getObject2().get();
	}
}
