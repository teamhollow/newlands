package net.teamhollow.newlands.world.gen.foliage;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.UniformIntDistribution;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import net.teamhollow.newlands.init.NLFoliagePlacers;

import java.util.Random;
import java.util.Set;

public class TropicalPalmFoliagePlacer extends FoliagePlacer {
    public static final Codec<TropicalPalmFoliagePlacer> CODEC = RecordCodecBuilder
            .create((instance) -> fillFoliagePlacerFields(instance).apply(instance, TropicalPalmFoliagePlacer::new));

    public TropicalPalmFoliagePlacer(UniformIntDistribution radius, UniformIntDistribution offset) {
		super(radius, offset);
	}

	@Override
	protected FoliagePlacerType<?> getType() {
		return NLFoliagePlacers.TROPICAL_PALM;
	}

	@Override
	protected void generate(ModifiableTestableWorld world, Random random, TreeFeatureConfig config, int trunkHeight, TreeNode treeNode, int foliageHeight, int radius, Set<BlockPos> leaves, int i, BlockBox blockBox) {
		BlockPos center = treeNode.getCenter().toImmutable();
		BlockPos.Mutable pos = new BlockPos.Mutable();
		boolean flipSpiral = random.nextBoolean();

		checkAndSetBlockState(world, random, pos.set(center).move(0, 1, 0), leaves, blockBox, config);
		checkAndSetBlockState(world, random, pos.set(center).move(1, 1, 0), leaves, blockBox, config);
		checkAndSetBlockState(world, random, pos.set(center).move(0, 1, 1), leaves, blockBox, config);
		checkAndSetBlockState(world, random, pos.set(center).move(-1, 1, 0), leaves, blockBox, config);
		checkAndSetBlockState(world, random, pos.set(center).move(0, 1, -1), leaves, blockBox, config);

		for (int dZ = -1; dZ < 2; dZ++) {
			for (int dX = -1; dX < 2; dX++) {
				checkAndSetBlockState(world, random, pos.set(center).move(dZ, 0, dX), leaves, blockBox, config);
			}
		}

		for (int d = 0; d < 4; d++) {
			Direction direction = Direction.fromHorizontal(d);

			pos.set(center).move(direction, 2);
			placeSpiral(world, random, pos, leaves, blockBox, config, direction, !flipSpiral);

			pos.set(center).move(direction, 3);
			placeSpiral(world, random, pos, leaves, blockBox, config, direction, flipSpiral);
		}
	}

	private void placeSpiral(ModifiableTestableWorld world, Random rand, BlockPos.Mutable pos, Set<BlockPos> leaves, BlockBox box, TreeFeatureConfig config, Direction direction, boolean invertLeafSpiral) {
		checkAndSetBlockState(world, rand, pos, leaves, box, config);
		Direction spiral = spiral(direction, invertLeafSpiral);
		checkAndSetBlockState(world, rand, pos.move(spiral), leaves, box, config);

		for (int i = 0; i < 2; i++) {
			checkAndSetBlockState(world, rand, pos.move(Direction.DOWN), leaves, box, config);
		}
	}

	private void checkAndSetBlockState(ModifiableTestableWorld world, Random random, BlockPos.Mutable currentPosition, Set<BlockPos> set, BlockBox blockBox, TreeFeatureConfig config) {
		if (TreeFeature.canReplace(world, currentPosition)) {
			world.setBlockState(currentPosition, config.leavesProvider.getBlockState(random, currentPosition), 19);
			blockBox.encompass(new BlockBox(currentPosition, currentPosition));
			set.add(currentPosition.toImmutable());
		}
	}

	private static Direction spiral(Direction direction, boolean invert) {
		switch (direction) {
			case EAST:
				return invert ? Direction.NORTH : Direction.SOUTH;
			case WEST:
				return invert ? Direction.SOUTH : Direction.NORTH;
			case NORTH:
				return invert ? Direction.WEST : Direction.EAST;
			case SOUTH:
			default:
				return invert ? Direction.EAST : Direction.WEST;
		}
	}

	@Override
	public int getRandomHeight(Random random, int trunkHeight, TreeFeatureConfig config) {
		return 0;
	}

	@Override
	protected boolean isInvalidForLeaves(Random random, int baseHeight, int dx, int dy, int dz, boolean bl) {
		return baseHeight == dz && dy == dz;
	}
}
