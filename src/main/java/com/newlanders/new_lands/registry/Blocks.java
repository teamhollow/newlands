package com.newlanders.new_lands.registry;

import com.newlanders.new_lands.CreativeTabs;
import com.newlanders.new_lands.NewLands;
import com.newlanders.new_lands.blocks.*;
import com.newlanders.new_lands.features.PalmTreeFeature;
import com.newlanders.new_lands.utils.BiRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.LogBlock;
import net.minecraft.block.TrapDoorBlock;
import net.minecraft.block.material.Material;
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
	public static final BiRegistry<RegistryObject<Block>, RegistryObject<Item>> STRIPPED_TROPICAL_PALM_LOG = registerBlockWithItem("stripped_tropical_palm_log", new LogBlock(MaterialColor.ADOBE, Block.Properties.from(net.minecraft.block.Blocks.OAK_LOG)), 64, CreativeTabs.BUILDING,null);
	public static final BiRegistry<RegistryObject<Block>, RegistryObject<Item>> TROPICAL_PALM_PLANKS = registerBlockWithItem("tropical_palm_planks", new Block(Block.Properties.from(net.minecraft.block.Blocks.OAK_PLANKS)), 64, CreativeTabs.BUILDING,null);
	public static final BiRegistry<RegistryObject<Block>, RegistryObject<Item>> TROPICAL_PALM_DOOR = registerBlockWithItem("tropical_palm_door", new DoorBlock(Block.Properties.from(net.minecraft.block.Blocks.OAK_DOOR)), 64, CreativeTabs.BUILDING,null);
	public static final BiRegistry<RegistryObject<Block>, RegistryObject<Item>> TROPICAL_PALM_TRAPDOOR = registerBlockWithItem("tropical_palm_trapdoor", new TrapdoorBlock(Block.Properties.from(net.minecraft.block.Blocks.OAK_TRAPDOOR)), 64, CreativeTabs.BUILDING,null);
	
	//WORLD GEN
	public static final BiRegistry<RegistryObject<Block>, RegistryObject<Item>> SAND_LAYER = registerBlockWithItem("sand_layer", new LayerBlock(getSandLayerSupplier()), 64, CreativeTabs.WORLD_GEN,null);
	public static final BiRegistry<RegistryObject<Block>, RegistryObject<Item>> TROPICAL_PALM_LOG = registerBlockWithItem("tropical_palm_log", new StrippableLogBlock(MaterialColor.ADOBE, Block.Properties.from(net.minecraft.block.Blocks.OAK_LOG),()->STRIPPED_TROPICAL_PALM_LOG.getObject1().get()), 64, CreativeTabs.WORLD_GEN,null);
	public static final BiRegistry<RegistryObject<Block>, RegistryObject<Item>> TROPICAL_PALM_LEAVES = registerBlockWithItem("tropical_palm_leaves", new LeavesBlock(Block.Properties.from(net.minecraft.block.Blocks.OAK_LEAVES)), 64, CreativeTabs.WORLD_GEN,null);
	public static final BiRegistry<RegistryObject<Block>, RegistryObject<Item>> TROPICAL_PALM_SAPLING = registerBlockWithItem("tropical_palm_sapling", new FeatureSapling(new PalmTreeFeature(ProbabilityConfig::deserialize),Block.Properties.from(net.minecraft.block.Blocks.OAK_SAPLING)), 64, CreativeTabs.WORLD_GEN,null);
	
	public static BiRegistry<RegistryObject<Block>, RegistryObject<Item>> registerBlockWithItem(String name, Block block, int maxStack, @Nullable ItemGroup group, @Nullable Rarity rarity) {
		Item.Properties properties = new Item.Properties();
		if (group!=null) properties.group(group);
		if (rarity!=null) properties.rarity(rarity);
		properties.maxStackSize(maxStack);
		return new BiRegistry<>(
				BLOCKS.register(name,()->block),
				Items.ITEMS.register(name,()->(new BlockItem(block, properties)))
		);
	}
	
	private static Supplier<Item> getSandLayerSupplier() {
		return ()->SAND_LAYER.getObject2().get();
	}
}
