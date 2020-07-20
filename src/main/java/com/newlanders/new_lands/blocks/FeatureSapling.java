package com.newlanders.new_lands.blocks;

import com.newlanders.new_lands.features.ITreeFeature;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.trees.OakTree;
import net.minecraft.block.trees.Tree;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.Tags;

import java.util.Random;

public class FeatureSapling extends SaplingBlock {
	ITreeFeature tree;
	
	public FeatureSapling(ITreeFeature treeIn, Properties properties) {
		super(new OakTree(), properties);
		this.tree=treeIn;
	}
	
	@Override
	public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
		this.onGrow(worldIn, pos, state, rand);
	}
	
	@Override
	public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
		return super.canGrow(worldIn, pos, state, isClient);
	}
	
	public void onGrow(ServerWorld p_226942_1_, BlockPos p_226942_2_, BlockState p_226942_3_, Random p_226942_4_) {
		if (p_226942_3_.get(STAGE) == 0) {
			p_226942_1_.setBlockState(p_226942_2_, p_226942_3_.cycle(STAGE), 4);
		} else {
			if (!net.minecraftforge.event.ForgeEventFactory.saplingGrowTree(p_226942_1_, p_226942_4_, p_226942_2_)) return;
			p_226942_1_.setBlockState(p_226942_2_, Blocks.AIR.getDefaultState());
			this.tree.generate(p_226942_1_,p_226942_2_,p_226942_1_.getRandom(),0,0,0);
		}
	}
	
	@Override
	protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
		Block block = state.getBlock();
		return super.isValidGround(state, worldIn, pos)||block.isIn(Tags.Blocks.SAND);
	}
}
