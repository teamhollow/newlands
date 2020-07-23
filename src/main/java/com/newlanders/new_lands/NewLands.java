package com.newlanders.new_lands;

import com.newlanders.new_lands.features.PalmTreeFeature;
import com.newlanders.new_lands.features.SandLayerFeature;
import com.newlanders.new_lands.registry.Blocks;
import com.newlanders.new_lands.registry.Entities;
import com.newlanders.new_lands.registry.Items;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
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

@Mod(NewLands.ModID)
public class NewLands {
    public static final Logger LOGGER = LogManager.getLogger();
    
    public static final String ModID = "new_lands";
    
    public NewLands() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        
        bus.addListener(this::doClientStuff);
        bus.addListener(this::commonSetup);
        Items.ITEMS.register(bus);
        Blocks.BLOCKS.register(bus);
        Entities.ENTITIES.register(bus);
    
        MinecraftForge.EVENT_BUS.register(this);
    }
    
    private void commonSetup(FMLCommonSetupEvent event) {
        for (Biome biome : ForgeRegistries.BIOMES) {
            biome.addFeature(GenerationStage.Decoration.TOP_LAYER_MODIFICATION, new SandLayerFeature(ProbabilityConfig::deserialize).withConfiguration(new ProbabilityConfig(100)));
            biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, new PalmTreeFeature(ProbabilityConfig::deserialize).withConfiguration(new ProbabilityConfig(100)));
        }
    }
    
    private void doClientStuff(final FMLClientSetupEvent event) {
        ClientEventHandler.setup(event);
    }
}
