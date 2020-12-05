package net.teamhollow.newlands.init;

import com.mojang.serialization.Codec;

import net.minecraft.util.Identifier;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import net.teamhollow.newlands.NewLands;
import net.teamhollow.newlands.mixin.FoliagePlacerTypeMixin;
import net.teamhollow.newlands.world.gen.foliage.*;

public class NLFoliagePlacers {
    public static final FoliagePlacerType<TropicalPalmFoliagePlacer> TROPICAL_PALM = register("tropical_palm", TropicalPalmFoliagePlacer.CODEC);

    public NLFoliagePlacers() {}

    @SuppressWarnings("deprecation")
    public static <P extends FoliagePlacer> FoliagePlacerType<P> register(String id, Codec<P> codec) {
        return FoliagePlacerTypeMixin.callRegister(new Identifier(NewLands.MOD_ID, id).toString(), codec);
    }
}
