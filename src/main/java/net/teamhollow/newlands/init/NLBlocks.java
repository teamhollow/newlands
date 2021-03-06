package net.teamhollow.newlands.init;

import me.andante.chord.block.helper.WoodBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ComposterBlock;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.block.VineBlock;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.teamhollow.newlands.NewLands;
import net.teamhollow.newlands.block.*;
import net.teamhollow.newlands.block.sapling.*;

@SuppressWarnings("unused")
public class NLBlocks {
    private static final String MOD_ID = NewLands.MOD_ID;

    //
    // WOOD
    //

    public static final WoodBlocks MAGNOLIA = new WoodBlocks.Builder().leafItemColor(0x6ab04a).saplingGenerator(new MagnoliaSaplingGenerator()).boatType(BoatEntity.Type.ACACIA).build(MOD_ID, "magnolia");
    public static final Block FLOWERING_MAGNOLIA_LEAVES = register("flowering_magnolia_leaves", new FloweringMagnoliaLeavesBlock(FabricBlockSettings.copy(MAGNOLIA.LEAVES)));
    public static final Block FLOWERING_MAGNOLIA_LEAF_CARPET = register("flowering_magnolia_leaf_carpet", new LeafCarpetBlock(FabricBlockSettings.of(Material.REPLACEABLE_PLANT).breakInstantly().sounds(BlockSoundGroup.GRASS).nonOpaque().noCollision()));
    public static final Block MAGNOLIA_VINE = register("magnolia_vine", new VineBlock(FabricBlockSettings.copy(Blocks.VINE).breakInstantly()));

    public static final WoodBlocks TROPICAL_PALM = new WoodBlocks.Builder().leafItemColor(0x8eb04a).saplingGenerator(new TropicalPalmSaplingGenerator()).boatType(BoatEntity.Type.BIRCH).build(MOD_ID, "tropical_palm");

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
    public static final Block WHITE_ALLIUM = register("white_allium", new FlowerBlock(StatusEffects.SLOW_FALLING, 4, FabricBlockSettings.of(Material.PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS)));

    public NLBlocks() {
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(FLOWERING_MAGNOLIA_LEAVES.asItem(), 0.3F);
    }

    public static Block register(String id, Block block) {
        return Registry.register(Registry.BLOCK, new Identifier(MOD_ID, id), block);
    }
}
