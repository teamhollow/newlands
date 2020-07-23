package com.newlanders.new_lands;

import com.newlanders.new_lands.client.block.colors.LeavesBlockColors;
import com.newlanders.new_lands.client.entities.renderers.FishermanRenderer;
import com.newlanders.new_lands.client.entities.renderers.NLBoatRenderer;
import com.newlanders.new_lands.registry.Blocks;
import com.newlanders.new_lands.registry.Entities;
import com.newlanders.new_lands.utils.BiRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientEventHandler {
	public static void setup(FMLClientSetupEvent event) {
		cutout(Blocks.TROPICAL_PALM_DOOR);
		cutout(Blocks.TROPICAL_PALM_SAPLING);
		cutout(Blocks.TROPICAL_PALM_TRAPDOOR);
		Minecraft.getInstance().getBlockColors().register(new LeavesBlockColors(), Blocks.TROPICAL_PALM_LEAVES.getObject1().get());
		
		//ENTITY RENDERERS
		RenderingRegistry.registerEntityRenderingHandler(Entities.BOAT.get(), NLBoatRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(Entities.FISHERMAN.get(), FishermanRenderer::new);
	}
	
	public static void cutout(BiRegistry<RegistryObject<Block>, RegistryObject<Item>> blockIn) {
		RenderTypeLookup.setRenderLayer(blockIn.getObject1().get(), RenderType.getCutout());
	}
}
