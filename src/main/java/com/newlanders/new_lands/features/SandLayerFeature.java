package com.newlanders.new_lands.features;

import com.mojang.datafixers.Dynamic;
import com.newlanders.new_lands.blocks.LayerBlock;
import com.newlanders.new_lands.registry.Blocks;
import net.minecraft.block.Block;
import net.minecraft.block.SnowBlock;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.ProbabilityConfig;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;
import java.util.function.Function;

public class SandLayerFeature extends Feature<ProbabilityConfig> {
	public SandLayerFeature(Function<Dynamic<?>, ? extends ProbabilityConfig> configFactoryIn) {
		super(configFactoryIn);
	}
	
	@Override
	@ParametersAreNonnullByDefault
	public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, ProbabilityConfig config) {
		for (int x=0;x<16;x++) {
			for (int z = 0; z < 16; z++) {
				BlockPos pos1 = new BlockPos(pos.getX() + x, worldIn.getHeight(Heightmap.Type.OCEAN_FLOOR_WG, pos.add(x, 0, z)).getY(), pos.getZ() + z);
				ResourceLocation biome = worldIn.getBiome(pos1).getRegistryName();
				if (
						biome != null && (
								worldIn.getBiome(pos1).equals(Biomes.BEACH) ||
								biome.getPath().contains("ocean")
						)) {
					if (!worldIn.getBlockState(pos1.down()).getBlock().equals(Blocks.SAND_LAYER.getObject1().get())) {
						if (!worldIn.getBlockState(pos1).getBlock().equals(Blocks.SAND_LAYER.getObject1().get())) {
							if (worldIn.getBlockState(pos1).getRaytraceShape(worldIn, pos).isEmpty()&&worldIn.getBlockState(pos1).getCollisionShape(worldIn, pos).isEmpty()&&worldIn.getBlockState(pos1).getShape(worldIn, pos).isEmpty()) {
								int height = getLayerHeight(worldIn, pos1);
								if (height != 0) {
									if (
											worldIn.getBlockState(pos1.down()).equals(net.minecraft.block.Blocks.ICE.getDefaultState())||
											worldIn.getBlockState(pos1.down()).equals(net.minecraft.block.Blocks.PACKED_ICE.getDefaultState())||
											worldIn.getBlockState(pos1.down()).equals(net.minecraft.block.Blocks.SNOW_BLOCK.getDefaultState())||
											worldIn.getBlockState(pos1.down()).equals(net.minecraft.block.Blocks.BLUE_ICE.getDefaultState())
									) {
									} else {
										if (height == 8) {
											worldIn.setBlockState(pos1, net.minecraft.block.Blocks.SAND.getDefaultState(), 16);
										} else {
											worldIn.setBlockState(pos1, Blocks.SAND_LAYER.getObject1().get().getDefaultState().with(SnowBlock.LAYERS, height).with(LayerBlock.WATERLOGGED, worldIn.getBlockState(pos1).getFluidState().getFluid().equals(Fluids.WATER)), 16);
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return true;
	}
	
	public int getLayerHeight(IWorld world,BlockPos pos) {
		int y=world.getHeight(Heightmap.Type.OCEAN_FLOOR_WG,pos).getY();
		double height=0;
//		for (int x2=-3;x2<=3;x2++) {
//			for (int z2=-3;z2<=3;z2++) {
//				int y2=world.getHeight(Heightmap.Type.OCEAN_FLOOR_WG,pos.add(x2,0,z2)).getY();
//				if (!world.getBlockState(world.getHeight(Heightmap.Type.OCEAN_FLOOR_WG,pos.add(x2,0,z2))).getBlock().equals(Blocks.SAND_LAYER.getObject1().get())) {
//					if (y2>y) {
//						height+=2;
//					}
//				}
//				height/=1.2f;
//			}
//		}
		height=Math.max(height,getQuadrant(1,1,0,0,pos.getX(),pos.getZ(),world));
		height=Math.max(height,getQuadrant(0,1,1,0,pos.getX(),pos.getZ(),world));
		height=Math.max(height,getQuadrant(0,0,1,1,pos.getX(),pos.getZ(),world));
		height=Math.max(height,getQuadrant(1,0,0,1,pos.getX(),pos.getZ(),world));
		return (int)Math.min(8,Math.max(0,height));
	}
	
	public double getQuadrant(int minusX, int minusZ, int plusX, int plusZ, int x, int z, IWorld world) {
		int minX=x-(3*minusX);
		int minZ=z-(3*minusZ);
		int maxX=x+(3*plusX);
		int maxZ=z+(3*plusZ);
		
		BlockPos pos=new BlockPos(x,0,z);
		double val=0;
		int y=world.getHeight(Heightmap.Type.OCEAN_FLOOR_WG,pos).getY();
		for (int xPos=minX;xPos<maxX;xPos++) {
			for (int zPos=minZ;zPos<maxZ;zPos++) {
				int y2=world.getHeight(Heightmap.Type.OCEAN_FLOOR_WG,new BlockPos(xPos,0,zPos)).getY();
				if (!world.getBlockState(world.getHeight(Heightmap.Type.OCEAN_FLOOR_WG,new BlockPos(xPos,0,zPos))).getBlock().equals(Blocks.SAND_LAYER.getObject1().get())) {
					if (y2>y) {
						val+=2;
					}
				}
				val/=1.2f;
			}
		}
		return val;
	}
}
