package net.teamhollow.newlands.world.gen.feature;

import java.util.Random;

import com.mojang.serialization.Codec;

import net.minecraft.block.Blocks;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.teamhollow.newlands.init.NLBlocks;

public class SandLayerFeature extends Feature<DefaultFeatureConfig> {
    public SandLayerFeature(Codec<DefaultFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(StructureWorldAccess structureWorldAccess, ChunkGenerator chunkGenerator, Random random, BlockPos blockPos, DefaultFeatureConfig defaultFeatureConfig) {
        BlockPos.Mutable mutable = new BlockPos.Mutable();

        for (int i = 0; i < 16; ++i) {
            for (int j = 0; j < 16; ++j) {
                int x = blockPos.getX() + i;
                int z = blockPos.getZ() + j;
                int y = structureWorldAccess.getTopY(Heightmap.Type.OCEAN_FLOOR, x, z);
                mutable.set(x, y, z);

                if (structureWorldAccess.getBlockState(mutable).isOf(Blocks.WATER) && structureWorldAccess.getBlockState(mutable.down()).isOf(Blocks.SAND))
                    structureWorldAccess.setBlockState(
                        mutable,
                        NLBlocks.SAND_LAYER.getDefaultState()
                            .with(Properties.LAYERS, 1)
                            .with(Properties.WATERLOGGED, true)
                        , 2
                    );
            }
        }

        return true;
    }
}
