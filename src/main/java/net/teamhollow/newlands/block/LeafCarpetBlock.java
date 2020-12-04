package net.teamhollow.newlands.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CarpetBlock;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;

public class LeafCarpetBlock extends CarpetBlock {
    public LeafCarpetBlock(Settings settings) {
        super(DyeColor.WHITE, settings);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return world.getBlockState(pos.down()).isOf(Blocks.WATER) || hasTopRim(world, pos.down());
    }
}
