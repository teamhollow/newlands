package com.newlanders.new_lands;

import com.newlanders.new_lands.features.SandLayerFeature;
import com.newlanders.new_lands.registry.Blocks;
import com.newlanders.new_lands.registry.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;

@Mod(NewLands.ModID)
public class NewLands {
    public static final Logger LOGGER = LogManager.getLogger();
    
    public static final String ModID="new_lands";
    
    public NewLands() {
        IEventBus bus=FMLJavaModLoadingContext.get().getModEventBus();
        
        bus.addListener(this::doClientStuff);
        bus.addListener(this::commonSetup);
        Items.ITEMS.register(bus);
        Blocks.BLOCKS.register(bus);

        MinecraftForge.EVENT_BUS.register(this);
    }
    
    private void commonSetup(FMLCommonSetupEvent event) {
//        Biome beach=ForgeRegistries.BIOMES.getValue(new ResourceLocation("minecraft:beach"));
//        beach.addFeature(GenerationStage.Decoration.TOP_LAYER_MODIFICATION,new SandLayerFeature(ProbabilityConfig::deserialize).withConfiguration(new ProbabilityConfig(100)));
//
//        Biome[] allOceans=new Biome[]{
//                Biomes.OCEAN,
//                Biomes.COLD_OCEAN,
//                Biomes.DEEP_FROZEN_OCEAN,
//                Biomes.WARM_OCEAN,
//                Biomes.LUKEWARM_OCEAN,
//                Biomes.FROZEN_OCEAN,
//                Biomes.DEEP_WARM_OCEAN,
//                Biomes.DEEP_OCEAN,
//                Biomes.DEEP_COLD_OCEAN
//        };
//
//        for (int i=0;i<allOceans.length;i++) {
//            allOceans[i].addFeature(GenerationStage.Decoration.TOP_LAYER_MODIFICATION,new SandLayerFeature(ProbabilityConfig::deserialize).withConfiguration(new ProbabilityConfig(100)));
//        }
    
        for (Biome biome : ForgeRegistries.BIOMES) {
            biome.addFeature(GenerationStage.Decoration.TOP_LAYER_MODIFICATION, new SandLayerFeature(ProbabilityConfig::deserialize).withConfiguration(new ProbabilityConfig(100)));
        }
    }
    
    private void doClientStuff(final FMLClientSetupEvent event) {
        ClientEventHandler.setup(event);
    }
}
