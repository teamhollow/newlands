package com.newlanders.new_lands;

import com.newlanders.new_lands.client.block_colors.LeavesBlockColors;
import com.newlanders.new_lands.registry.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientEventHandler {
	public static void setup(FMLClientSetupEvent event) {
		Minecraft.getInstance().getBlockColors().register(new LeavesBlockColors(), Blocks.TROPICAL_PALM_LEAVES.getObject1().get());
	}
}
