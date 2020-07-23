package com.newlanders.new_lands.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockReader;

public class NLFenceGate extends FenceGateBlock {
	public NLFenceGate(Properties properties) {
		super(properties);
	}
	
	@Override
	public Vec3d getOffset(BlockState state, IBlockReader worldIn, BlockPos pos) {
		if (state.get(IN_WALL)) {
			return super.getOffset(state, worldIn, pos).add(0,-0.1875f,0);
		}
		return super.getOffset(state, worldIn, pos);
	}
}
