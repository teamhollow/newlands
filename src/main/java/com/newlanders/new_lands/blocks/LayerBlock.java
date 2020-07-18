package com.newlanders.new_lands.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SnowBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class LayerBlock extends SnowBlock {
	public LayerBlock() {
		super(Block.Properties.create(Material.SAND).tickRandomly().hardnessAndResistance(0.1F).sound(SoundType.SAND));
	}
}
