package net.teamhollow.newlands.init;

import net.fabricmc.fabric.api.biome.v1.OverworldBiomes;
import net.fabricmc.fabric.api.biome.v1.OverworldClimate;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredStructureFeatures;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilders;
import net.teamhollow.newlands.NewLands;

@SuppressWarnings("deprecation")
public class NLBiomes {
    public static final RegistryKey<Biome> MAGNOLIA_FOREST = register("magnolia_forest", createMagnoliaForest());

    public NLBiomes() {
        OverworldBiomes.addContinentalBiome(NLBiomes.MAGNOLIA_FOREST, OverworldClimate.TEMPERATE, 1.0D);
    }

    private static Biome createMagnoliaForest() {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addFarmAnimals(spawnSettings);
        DefaultBiomeFeatures.addBatsAndMonsters(spawnSettings);

        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder().surfaceBuilder(ConfiguredSurfaceBuilders.GRASS);
        DefaultBiomeFeatures.addDefaultUndergroundStructures(generationSettings);
        generationSettings.structureFeature(ConfiguredStructureFeatures.RUINED_PORTAL);
        DefaultBiomeFeatures.addLandCarvers(generationSettings);
        DefaultBiomeFeatures.addDefaultLakes(generationSettings);
        DefaultBiomeFeatures.addDungeons(generationSettings);
        DefaultBiomeFeatures.addForestFlowers(generationSettings);

        DefaultBiomeFeatures.addMineables(generationSettings);
        DefaultBiomeFeatures.addDefaultOres(generationSettings);
        DefaultBiomeFeatures.addDefaultDisks(generationSettings);
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, NLConfiguredFeatures.TREE_MAGNOLIA_FOREST);
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, NLConfiguredFeatures.PATCH_MAGNOLIA_LEAVES);
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, NLConfiguredFeatures.WHITE_ALLIUM);
        DefaultBiomeFeatures.addDefaultFlowers(generationSettings);
        DefaultBiomeFeatures.addForestGrass(generationSettings);

        DefaultBiomeFeatures.addDefaultMushrooms(generationSettings);
        DefaultBiomeFeatures.addDefaultVegetation(generationSettings);
        DefaultBiomeFeatures.addSprings(generationSettings);
        DefaultBiomeFeatures.addFrozenTopLayer(generationSettings);

        return new Biome.Builder().precipitation(Biome.Precipitation.RAIN).category(Biome.Category.FOREST).depth(0.1F).scale(0.4F).temperature(0.65F).downfall(0.9F).effects(new BiomeEffects.Builder().waterColor(0xabffed).waterFogColor(0x8fffe7).fogColor(0xc4ffcd).skyColor(0xe6ffe9).moodSound(BiomeMoodSound.CAVE).build()).spawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    private static RegistryKey<Biome> register(String id, Biome biome) {
        Identifier identifier = new Identifier(NewLands.MOD_ID, id);

        BuiltinRegistries.add(BuiltinRegistries.BIOME, identifier, biome);
        return RegistryKey.of(Registry.BIOME_KEY, identifier);
    }
}
