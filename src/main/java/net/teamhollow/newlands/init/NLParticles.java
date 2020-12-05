package net.teamhollow.newlands.init;

import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.teamhollow.newlands.NewLands;
import net.teamhollow.newlands.particle.vanilla.PublicDefaultParticleType;

public class NLParticles {
    public static final DefaultParticleType WHITE_ALLIUM = register("white_allium", false);

    public NLParticles() {
    }

    private static DefaultParticleType register(String id, boolean alwaysShow) {
        return Registry.register(Registry.PARTICLE_TYPE, new Identifier(NewLands.MOD_ID, id), new PublicDefaultParticleType(alwaysShow));
    }
}
