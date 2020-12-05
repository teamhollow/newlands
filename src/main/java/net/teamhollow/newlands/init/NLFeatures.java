package net.teamhollow.newlands.init;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.teamhollow.newlands.NewLands;
import net.teamhollow.newlands.world.gen.feature.*;

public class NLFeatures {
    public static final Feature<DefaultFeatureConfig> SAND_LAYER = register("sand_layer", new SandLayerFeature(DefaultFeatureConfig.CODEC));
    public static final FloweringMagnoliaTreeFeature TREE_FLOWERING_MAGNOLIA = register("tree_flowering_magnolia", new FloweringMagnoliaTreeFeature(TreeFeatureConfig.CODEC));

    public NLFeatures() {}

    private static <C extends FeatureConfig, F extends Feature<C>> F register(String id, F feature) {
        return Registry.register(Registry.FEATURE, new Identifier(NewLands.MOD_ID, id), feature);
    }
}
