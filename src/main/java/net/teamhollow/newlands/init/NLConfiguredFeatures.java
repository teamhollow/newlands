package net.teamhollow.newlands.init;

import net.minecraft.block.BlockState;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.UniformIntDistribution;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.AcaciaFoliagePlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.trunk.ForkingTrunkPlacer;
import net.teamhollow.newlands.NewLands;

public class NLConfiguredFeatures {
    public static final ConfiguredFeature<TreeFeatureConfig, ?> TROPICAL_PALM_TREE = register("tropical_palm_tree", Feature.TREE.configure((new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(States.TROPICAL_PALM_LOG), new SimpleBlockStateProvider(States.TROPICAL_PALM_LEAVES), new AcaciaFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0)), new ForkingTrunkPlacer(6, 2, 2), new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().build()));

    public NLConfiguredFeatures() {}

    private static <FC extends FeatureConfig> ConfiguredFeature<FC, ?> register(String id, ConfiguredFeature<FC, ?> configuredFeature) {
      return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(NewLands.MOD_ID, id), configuredFeature);
   }

    private static class States {
        private static final BlockState TROPICAL_PALM_LOG = NLBlocks.TROPICAL_PALM.LOG.getDefaultState();
        private static final BlockState TROPICAL_PALM_LEAVES = NLBlocks.TROPICAL_PALM.LEAVES.getDefaultState();
    }
}
