package com.newlanders.new_lands;

import com.newlanders.new_lands.registry.Blocks;
import com.newlanders.new_lands.registry.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(NewLands.ModID)
public class NewLands {
    public static final Logger LOGGER = LogManager.getLogger();
    
    public static final String ModID="new_lands";
    
    public NewLands() {
        IEventBus bus=FMLJavaModLoadingContext.get().getModEventBus();
        
        bus.addListener(this::doClientStuff);
        Items.ITEMS.register(bus);
        Blocks.BLOCKS.register(bus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void doClientStuff(final FMLClientSetupEvent event) {}
}
