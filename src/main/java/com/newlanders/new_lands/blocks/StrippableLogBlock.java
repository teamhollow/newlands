package com.newlanders.new_lands.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LogBlock;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.IProperty;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

public class StrippableLogBlock extends LogBlock {
	private final Supplier<Block> stripped;
	
	public StrippableLogBlock(MaterialColor verticalColorIn, Properties properties, Supplier<Block> strippedVariant) {
		super(verticalColorIn, properties);
		stripped=strippedVariant;
	}
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		if (player.getHeldItem(handIn).getToolTypes().contains(ToolType.AXE)) {
			AtomicReference<BlockState> state1=new AtomicReference<>(stripped.get().getDefaultState());
			state.getProperties().forEach(property->{
				state1.set(applyProperty(state, state1.get(), property));
			});
			worldIn.setBlockState(pos,state1.get());
			worldIn.playSound(pos.getX(),pos.getY(),pos.getZ(), SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS,1,1,false);
			if (!player.isCreative()) {
				player.getHeldItem(handIn).damageItem(1, player, (p_220040_1_) -> p_220040_1_.sendBreakAnimation(handIn));
			}
			player.swingArm(handIn);
			return ActionResultType.PASS;
		}
		return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
	}
	
	public <A extends Comparable<A>> BlockState applyProperty(BlockState sourceState,BlockState newState,IProperty<A> property) {
		return newState.with(property,sourceState.get(property));
	}
}
