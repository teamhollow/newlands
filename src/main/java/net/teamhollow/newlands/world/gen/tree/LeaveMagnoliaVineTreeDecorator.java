package net.teamhollow.newlands.world.gen.tree;

import java.util.List;
import java.util.Random;
import java.util.Set;

import com.mojang.serialization.Codec;

import net.minecraft.block.VineBlock;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.ModifiableWorld;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.tree.TreeDecorator;
import net.minecraft.world.gen.tree.TreeDecoratorType;
import net.teamhollow.newlands.init.NLBlocks;
import net.teamhollow.newlands.init.NLTreeDecorators;

public class LeaveMagnoliaVineTreeDecorator extends TreeDecorator {
    public static final LeaveMagnoliaVineTreeDecorator INSTANCE = new LeaveMagnoliaVineTreeDecorator();
    public static final Codec<LeaveMagnoliaVineTreeDecorator> CODEC = Codec.unit(() -> {
        return INSTANCE;
    });

    @Override
    protected TreeDecoratorType<?> getType() {
        return NLTreeDecorators.LEAVE_MAGNOLIA_VINE;
    }

    @Override
    public void generate(StructureWorldAccess world, Random random, List<BlockPos> logPositions, List<BlockPos> leavesPositions, Set<BlockPos> placedStates, BlockBox box) {
        leavesPositions.forEach((pos) -> {
            BlockPos blockPos;
            if (random.nextInt(10) == 0) {
                blockPos = pos.west();
                if (Feature.isAir(world, blockPos)) {
                    this.placeVines(world, blockPos, VineBlock.EAST, placedStates, box);
                }
            }

            if (random.nextInt(10) == 0) {
                blockPos = pos.east();
                if (Feature.isAir(world, blockPos)) {
                    this.placeVines(world, blockPos, VineBlock.WEST, placedStates, box);
                }
            }

            if (random.nextInt(10) == 0) {
                blockPos = pos.north();
                if (Feature.isAir(world, blockPos)) {
                    this.placeVines(world, blockPos, VineBlock.SOUTH, placedStates, box);
                }
            }

            if (random.nextInt(10) == 0) {
                blockPos = pos.south();
                if (Feature.isAir(world, blockPos)) {
                    this.placeVines(world, blockPos, VineBlock.NORTH, placedStates, box);
                }
            }
        });
    }

    private void placeVines(ModifiableTestableWorld world, BlockPos pos, BooleanProperty side, Set<BlockPos> placedStates, BlockBox box) {
        this.placeVine(world, pos, side, placedStates, box);
        int i = 2;

        for (pos = pos.down(); Feature.isAir(world, pos) && i > 0; i--) {
            this.placeVine(world, pos, side, placedStates, box);
            pos = pos.down();
        }
    }

    @Override
    protected void placeVine(ModifiableWorld world, BlockPos pos, BooleanProperty directionProperty, Set<BlockPos> placedStates, BlockBox box) {
        this.setBlockStateAndEncompassPosition(world, pos, NLBlocks.MAGNOLIA_VINE.getDefaultState().with(directionProperty, true), placedStates, box);
    }
}
