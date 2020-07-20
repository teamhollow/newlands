package com.newlanders.new_lands;

import com.newlanders.new_lands.registry.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class CreativeTabs {
	public static final ItemGroup WORLD_GEN = new ItemGroup("newlands_worldgen") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(Blocks.SAND_LAYER.getObject2().get());
		}
	};
	public static final ItemGroup BUILDING = new ItemGroup("newlands_building") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(Blocks.TROPICAL_PALM_PLANKS.getObject2().get());
		}
	};
}