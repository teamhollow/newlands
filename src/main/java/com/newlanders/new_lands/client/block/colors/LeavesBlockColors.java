package com.newlanders.new_lands.client.block.colors;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ILightReader;

import javax.annotation.Nullable;

public class LeavesBlockColors implements IBlockColor {
	@Override
	public int getColor(BlockState p_getColor_1_, @Nullable ILightReader p_getColor_2_, @Nullable BlockPos p_getColor_3_, int p_getColor_4_) {
		return Minecraft.getInstance().getBlockColors().getColor(Blocks.OAK_LEAVES.getDefaultState(), p_getColor_2_, p_getColor_3_, p_getColor_4_);
	}
}
