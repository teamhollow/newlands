package net.teamhollow.newlands.init;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ComposterBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.teamhollow.newlands.NewLands;
import net.teamhollow.newlands.block.*;
import net.teamhollow.newlands.block.helper.WoodBlocks;
import net.teamhollow.newlands.block.sapling.TropicalPalmSaplingGenerator;

public class NLBlocks {
    //
    // WOOD
    //

    public static final WoodBlocks TROPICAL_PALM = new WoodBlocks.Builder().saplingGenerator(new TropicalPalmSaplingGenerator()).boatType(BoatEntity.Type.BIRCH).build("tropical_palm");

    public static final WoodBlocks MAGNOLIA = new WoodBlocks.Builder().boatType(BoatEntity.Type.ACACIA).build("magnolia");
    public static final Block FLOWERING_MAGNOLIA_LEAVES = register("flowering_magnolia_leaves", new LeavesBlock(FabricBlockSettings.copy(MAGNOLIA.LEAVES)));

    //
    // SHOREROCK
    //

    public static final Block SHOREROCK = register("shorerock", new Block(FabricBlockSettings.copy(Blocks.ANDESITE)));
    public static final Block POLISHED_SHOREROCK = register("polished_shorerock", new Block(FabricBlockSettings.copy(Blocks.POLISHED_ANDESITE)));
    public static final Block POLISHED_SHOREROCK_BRICKS = register("polished_shorerock_bricks", new Block(FabricBlockSettings.copy(Blocks.STONE_BRICKS)));
    public static final Block WEEDED_POLISHED_SHOREROCK_BRICKS = register("weeded_polished_shorerock_bricks", new Block(FabricBlockSettings.copy(Blocks.STONE_BRICKS)));
    public static final Block CHISELED_POLISHED_SHOREROCK = register("chiseled_polished_shorerock", new Block(FabricBlockSettings.copy(Blocks.POLISHED_ANDESITE)));

    //
    // COBBLED BLOCKS
    //

    public static final Block COBBLED_DIRT = register("cobbled_dirt", new Block(FabricBlockSettings.copy(Blocks.COBBLESTONE)));
    public static final Block COBBLED_SAND = register("cobbled_sand", new Block(FabricBlockSettings.copy(Blocks.COBBLESTONE)));

    //
    // STANDALONE BLOCKS
    //

    public static final Block SAND_LAYER = register("sand_layer", new LayerBlock(FabricBlockSettings.of(Material.AGGREGATE, MaterialColor.SAND).strength(0.1F, 0.1F).sounds(BlockSoundGroup.SAND).breakByTool(FabricToolTags.SHOVELS)));

    public NLBlocks() {
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(FLOWERING_MAGNOLIA_LEAVES.asItem(), 0.3F);
    }
    
    public static Block register(String id, Block block, boolean registerItem) {
        Identifier identifier = new Identifier(NewLands.MOD_ID, id);
        Block registeredBlock = Registry.register(Registry.BLOCK, identifier, block);

        if (registerItem) Registry.register(Registry.ITEM, identifier, new BlockItem(registeredBlock, new Item.Settings().maxCount(64).group(NewLands.ITEM_GROUP)));
        return registeredBlock;
    }
    public static Block register(String id, Block block) {
        return register(id, block, true);
    }
}
