package com.newlanders.new_lands.features;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

import java.util.Random;

public interface ITreeFeature {
	void generate(IWorld worldIn, BlockPos pos, Random rand, int x, int y, int z);
}
