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
}