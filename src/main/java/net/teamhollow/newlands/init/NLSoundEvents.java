package net.teamhollow.newlands.init;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.teamhollow.newlands.NewLands;

public class NLSoundEvents {
    public static final SoundEvent ENTITY_HERMIT_CRAB_AMBIENT = register("entity.hermit_crab.ambient");
    public static final SoundEvent ENTITY_HERMIT_CRAB_HURT = register("entity.hermit_crab.hurt");
    public static final SoundEvent ENTITY_HERMIT_CRAB_DEATH = register("entity.hermit_crab.death");
    public static final SoundEvent ENTITY_HERMIT_CRAB_STEP = register("entity.hermit_crab.step");

    public NLSoundEvents() {}

    private static SoundEvent register(String id) {
        Identifier identifier = new Identifier(NewLands.MOD_ID, id);
        return Registry.register(Registry.SOUND_EVENT, identifier, new SoundEvent(identifier));
    }
}
