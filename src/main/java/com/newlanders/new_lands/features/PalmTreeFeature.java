package com.newlanders.new_lands.features;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.ProbabilityConfig;

import java.util.Random;
import java.util.function.Function;

public class PalmTreeFeature extends Feature<ProbabilityConfig> implements ITreeFeature {
	public PalmTreeFeature(Function<Dynamic<?>, ? extends ProbabilityConfig> configFactoryIn) {
		super(configFactoryIn);
	}
	
	@Override
	public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, ProbabilityConfig config) {
		for (int i = 0; i < rand.nextInt(4); i++) {
			int x = rand.nextInt(16);
			int z = rand.nextInt(16);
			int y = worldIn.getHeight(Heightmap.Type.WORLD_SURFACE, pos.add(x, 0, z)).getY();
			if (worldIn.getBiome(pos.add(x, y, z)).equals(Biomes.BEACH)) {
				if (worldIn.getBlockState(pos.add(x, y, z).down()).equals(Blocks.SAND.getDefaultState())) {
					generate(worldIn, pos, rand, x, y, z);
				}
			}
		}
		return false;
	}
	
	@Override
	public void generate(IWorld worldIn, BlockPos pos, Random rand, int x, int y, int z) {
		int leanX = rand.nextInt(3) - 2;
		int leanZ = rand.nextInt(3) - 2;
		int height = rand.nextInt(3) + 6;
		for (int a = 0; a <= height; a++) {
			if (worldIn.getBlockState(pos.add(x + (a * leanX) / 3, y + a, z + (a * leanZ) / 3)).canBeReplacedByLogs(worldIn, pos.add(x + (a * leanX) / 3, y + a, z + (a * leanZ) / 3))) {
				placeBlock(worldIn, pos.add(x + (a * leanX) / 3, y + a, z + (a * leanZ) / 3), com.newlanders.new_lands.registry.Blocks.TROPICAL_PALM_LOG.getObject1().get().getDefaultState());
				if (a == height) {
					buildLeaves(worldIn, pos.add(x + (a * leanX) / 3, y + a, z + (a * leanZ) / 3));
				}
			}
		}
	}
	
	public void placeBlock(IWorld worldIn, BlockPos pos, BlockState state) {
		if (worldIn instanceof World) {
			((World) worldIn).setBlockState(pos, state);
		} else {
			worldIn.setBlockState(pos, state, 16);
		}
	}
	
	public void buildLeaves(IWorld world, BlockPos pos) {
		//CENTER
		addLeaves(world, pos.up());
		addLeaves(world, pos.up(2));
		addLeaves(world, pos.up(2).north());
		addLeaves(world, pos.up(2).east());
		addLeaves(world, pos.up(2).south());
		addLeaves(world, pos.up(2).west());
		//CORNERS
		addLeaves(world, pos.up().east().north());
		addLeaves(world, pos.up().east().south());
		addLeaves(world, pos.up().west().north());
		addLeaves(world, pos.up().west().south());
		//NORTH
		addLeaves(world, pos.up().north());
		addLeaves(world, pos.up().north(2));
		addLeaves(world, pos.up().north(3));
		addLeaves(world, pos.up().north(3).down());
		addLeaves(world, pos.up().north(4).down());
		//SOUTH
		addLeaves(world, pos.up().south());
		addLeaves(world, pos.up().south(2));
		addLeaves(world, pos.up().south(3));
		addLeaves(world, pos.up().south(3).down());
		addLeaves(world, pos.up().south(4).down());
		//EAST
		addLeaves(world, pos.up().east());
		addLeaves(world, pos.up().east(2));
		addLeaves(world, pos.up().east(3));
		addLeaves(world, pos.up().east(3).down());
		addLeaves(world, pos.up().east(4).down());
		//WEST
		addLeaves(world, pos.up().west());
		addLeaves(world, pos.up().west(2));
		addLeaves(world, pos.up().west(3));
		addLeaves(world, pos.up().west(3).down());
		addLeaves(world, pos.up().west(4).down());
	}
	
	public void addLeaves(IWorld world, BlockPos pos) {
		if (world.getBlockState(pos).canBeReplacedByLeaves(world, pos)) {
			placeBlock(world, pos, com.newlanders.new_lands.registry.Blocks.TROPICAL_PALM_LEAVES.getObject1().get().getDefaultState().with(LeavesBlock.DISTANCE, 1));
		}
	}
}
