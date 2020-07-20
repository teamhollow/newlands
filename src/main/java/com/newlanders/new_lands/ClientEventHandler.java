package com.newlanders.new_lands;

import com.newlanders.new_lands.client.block_colors.LeavesBlockColors;
import com.newlanders.new_lands.registry.Blocks;
import com.newlanders.new_lands.utils.BiRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientEventHandler {
	public static void setup(FMLClientSetupEvent event) {
		cutout(Blocks.TROPICAL_PALM_DOOR);
		cutout(Blocks.TROPICAL_PALM_SAPLING);
		cutout(Blocks.TROPICAL_PALM_TRAPDOOR);
//		RenderTypeLookup.setRenderLayer(Blocks.TROPICAL_PALM_DOOR.getObject1().get(), RenderType.getCutout());
//		RenderTypeLookup.setRenderLayer(Blocks.TROPICAL_PALM_SAPLING.getObject1().get(), RenderType.getCutout());
//		RenderTypeLookup.setRenderLayer(Blocks.TROPICAL_PALM_TRAPDOOR.getObject1().get(), RenderType.getCutout());
		Minecraft.getInstance().getBlockColors().register(new LeavesBlockColors(), Blocks.TROPICAL_PALM_LEAVES.getObject1().get());
	}
	
	public static void cutout(BiRegistry<RegistryObject<Block>, RegistryObject<Item>> blockIn) {
		RenderTypeLookup.setRenderLayer(blockIn.getObject1().get(), RenderType.getCutout());
	}
}
