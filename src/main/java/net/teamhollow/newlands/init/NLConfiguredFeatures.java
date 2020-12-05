package net.teamhollow.newlands.init;

import java.util.OptionalInt;

import com.google.common.collect.ImmutableList;

import net.minecraft.block.BlockState;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.UniformIntDistribution;
import net.minecraft.world.gen.decorator.CountExtraDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.RandomFeatureConfig;
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.LargeOakFoliagePlacer;
import net.minecraft.world.gen.placer.SimpleBlockPlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.trunk.LargeOakTrunkPlacer;
import net.teamhollow.newlands.NewLands;
import net.teamhollow.newlands.world.gen.foliage.*;
import net.teamhollow.newlands.world.gen.tree.LeaveMagnoliaVineTreeDecorator;
import net.teamhollow.newlands.world.gen.trunk.*;

public class NLConfiguredFeatures {
    // tropical palm
    public static final ConfiguredFeature<TreeFeatureConfig, ?> TREE_TROPICAL_PALM = register("tree_tropical_palm", Feature.TREE.configure(new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(States.TROPICAL_PALM_LOG), new SimpleBlockStateProvider(States.TROPICAL_PALM_LEAVES), new TropicalPalmFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0)), new TropicalPalmTrunkPlacer(6, 2, 2), new TwoLayersFeatureSize(1, 0, 2)).ignoreVines().build()));
    public static final ConfiguredFeature<?, ?> TREE_TROPICAL_PALM_BEACH = register("tree_tropical_palm_beach", TREE_TROPICAL_PALM.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP).decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(9, 0.1F, 1))));

    // magnolia
    public static final ConfiguredFeature<?, ?> PATCH_MAGNOLIA_LEAVES = register("patch_magnolia_leaves", Feature.RANDOM_PATCH.configure(new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(States.FLOWERING_MAGNOLIA_LEAF_CARPET), SimpleBlockPlacer.INSTANCE).tries(32).build()).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP).repeat(2));
    public static final ConfiguredFeature<?, ?> WHITE_ALLIUM = register("white_allium", Feature.FLOWER.configure(new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(States.WHITE_ALLIUM), SimpleBlockPlacer.INSTANCE).tries(64).build()).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP).repeat(2));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> TREE_MAGNOLIA = register("tree_magnolia", Feature.TREE.configure(new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(States.MAGNOLIA_LOG), new SimpleBlockStateProvider(States.MAGNOLIA_LEAVES), new LargeOakFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(4), 4), new LargeOakTrunkPlacer(7, 11, 0), new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4))).ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build()));
    public static final ConfiguredFeature<TreeFeatureConfig, ?> TREE_MAGNOLIA_FLOWERING = register("tree_magnolia_flowering", Feature.TREE.configure(new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(States.MAGNOLIA_LOG), new WeightedBlockStateProvider().addState(States.MAGNOLIA_LEAVES, 4).addState(States.FLOWERING_MAGNOLIA_LEAVES, 1), new LargeOakFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(4), 4), new LargeOakTrunkPlacer(9, 11, 0), new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4))).heightmap(Heightmap.Type.MOTION_BLOCKING).build()));
    public static final ConfiguredFeature<TreeFeatureConfig, ?> TREE_MAGNOLIA_FLOWERING_VINES = register("tree_magnolia_flowering_vines", NLFeatures.TREE_FLOWERING_MAGNOLIA_VINES.configure(new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(States.MAGNOLIA_LOG), new SimpleBlockStateProvider(States.FLOWERING_MAGNOLIA_LEAVES), new LargeOakFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(4), 4), new LargeOakTrunkPlacer(9, 11, 0), new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4))).heightmap(Heightmap.Type.MOTION_BLOCKING).decorators(ImmutableList.of(LeaveMagnoliaVineTreeDecorator.INSTANCE)).ignoreVines().build()));

    public static final ConfiguredFeature<?, ?> TREE_MAGNOLIA_FOREST = register("tree_magnolia_forest", Feature.RANDOM_SELECTOR.configure(new RandomFeatureConfig(ImmutableList.of(TREE_MAGNOLIA_FLOWERING_VINES.withChance(0.07F), TREE_MAGNOLIA_FLOWERING.withChance(0.14F)), TREE_MAGNOLIA)).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP).decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(7, 0.1F, 1))));

    // standalone
    public static final ConfiguredFeature<?, ?> SAND_LAYER = register("sand_layer", NLFeatures.SAND_LAYER.configure(FeatureConfig.DEFAULT));

    public NLConfiguredFeatures() {}

    private static <FC extends FeatureConfig> ConfiguredFeature<FC, ?> register(String id, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(NewLands.MOD_ID, id), configuredFeature);
    }

    private static class States {
        private static final BlockState TROPICAL_PALM_LOG = NLBlocks.TROPICAL_PALM.LOG.getDefaultState();
        private static final BlockState TROPICAL_PALM_LEAVES = NLBlocks.TROPICAL_PALM.LEAVES.getDefaultState();
        private static final BlockState MAGNOLIA_LOG = NLBlocks.MAGNOLIA.LOG.getDefaultState();
        private static final BlockState MAGNOLIA_LEAVES = NLBlocks.MAGNOLIA.LEAVES.getDefaultState();
        private static final BlockState FLOWERING_MAGNOLIA_LEAVES = NLBlocks.FLOWERING_MAGNOLIA_LEAVES.getDefaultState();
        private static final BlockState FLOWERING_MAGNOLIA_LEAF_CARPET = NLBlocks.FLOWERING_MAGNOLIA_LEAF_CARPET.getDefaultState();
        private static final BlockState WHITE_ALLIUM = NLBlocks.WHITE_ALLIUM.getDefaultState();
    }
}
