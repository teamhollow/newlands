package net.teamhollow.newlands.init;

import com.mojang.serialization.Codec;

import net.minecraft.util.Identifier;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;
import net.teamhollow.newlands.NewLands;
import net.teamhollow.newlands.mixin.TrunkPlacerTypeMixin;
import net.teamhollow.newlands.world.gen.trunk.*;

public class NLTrunkPlacers {
    public static final TrunkPlacerType<TropicalPalmTrunkPlacer> TROPICAL_PALM = register("tropical_palm", TropicalPalmTrunkPlacer.CODEC);

    public NLTrunkPlacers() {}

    @SuppressWarnings("deprecation")
    public static <P extends TrunkPlacer> TrunkPlacerType<P> register(String id, Codec<P> codec) {
        return TrunkPlacerTypeMixin.callRegister(new Identifier(NewLands.MOD_ID, id).toString(), codec);
    }
}
