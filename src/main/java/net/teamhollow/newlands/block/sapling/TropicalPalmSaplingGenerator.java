package net.teamhollow.newlands.block.sapling;

import java.util.Random;

import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.teamhollow.newlands.init.NLConfiguredFeatures;

public class TropicalPalmSaplingGenerator extends SaplingGenerator {
    protected ConfiguredFeature<TreeFeatureConfig, ?> createTreeFeature(Random random, boolean bl) {
        return NLConfiguredFeatures.TROPICAL_PALM_TREE;
    }
}
