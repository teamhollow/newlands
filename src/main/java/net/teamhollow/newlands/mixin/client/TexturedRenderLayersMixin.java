package net.teamhollow.newlands.mixin.client;

import java.util.function.Consumer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.util.SpriteIdentifier;
import net.teamhollow.newlands.registry.SpriteIdentifierRegistry;

@Mixin(TexturedRenderLayers.class)
public class TexturedRenderLayersMixin {
    @Inject(method = "addDefaultTextures", at = @At("HEAD"))
    private static void injectCustomTextures(Consumer<SpriteIdentifier> consumer, CallbackInfo info) {
        for (SpriteIdentifier identifier : SpriteIdentifierRegistry.INSTANCE.getIdentifiers()) {
            consumer.accept(identifier);
        }
    }
}
