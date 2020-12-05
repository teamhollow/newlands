package net.teamhollow.newlands.world.gen.trunk;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;
import net.teamhollow.newlands.init.NLTrunkPlacers;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class TropicalPalmTrunkPlacer extends TrunkPlacer {
    public static final Codec<TropicalPalmTrunkPlacer> CODEC = RecordCodecBuilder.create((bentTrunkPlacerInstance) -> method_28904(bentTrunkPlacerInstance).apply(bentTrunkPlacerInstance, TropicalPalmTrunkPlacer::new));

    public TropicalPalmTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
		super(baseHeight, firstRandomHeight, secondRandomHeight);
	}

	@Override
	protected TrunkPlacerType<?> getType() {
		return NLTrunkPlacers.TROPICAL_PALM;
	}

	@Override
	public List<FoliagePlacer.TreeNode> generate(ModifiableTestableWorld world, Random random, int trunkHeight, BlockPos pos, Set<BlockPos> set, BlockBox blockBox, TreeFeatureConfig treeFeatureConfig) {
		Direction bendDirection = Direction.Type.HORIZONTAL.random(random);
		setToDirt(world, pos.down());
		BlockPos.Mutable currentPosition = pos.mutableCopy().move(Direction.DOWN);

		for (int i = 0; i < 4 + random.nextInt(3); i++) {
			getAndSetState(world, random, currentPosition.move(Direction.UP), set, blockBox, treeFeatureConfig);
		}

		currentPosition.move(bendDirection).move(Direction.DOWN);

		for (int i = 0; i < 4 + random.nextInt(1); i++) {
			getAndSetState(world, random, currentPosition.move(Direction.UP), set, blockBox, treeFeatureConfig);
		}

		if (random.nextBoolean()) {
			currentPosition.move(bendDirection).move(Direction.DOWN);

			for (int i = 0; i < 3; i++) {
				getAndSetState(world, random, currentPosition.move(Direction.UP), set, blockBox, treeFeatureConfig);
			}
		}

		return ImmutableList.of(new FoliagePlacer.TreeNode(currentPosition.toImmutable(), 0, false));
	}
}
