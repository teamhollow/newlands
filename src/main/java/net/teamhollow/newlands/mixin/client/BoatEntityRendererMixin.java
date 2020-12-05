package net.teamhollow.newlands.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.client.render.entity.BoatEntityRenderer;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.util.Identifier;
import net.teamhollow.newlands.entity.boat.NLBoatEntity;

@Mixin(BoatEntityRenderer.class)
public class BoatEntityRendererMixin {
    @Inject(method = "getTexture", at = @At("HEAD"), cancellable = true)
    private void injectCustomTexture(BoatEntity boat, CallbackInfoReturnable<Identifier> cir) {
        if (boat instanceof NLBoatEntity) cir.setReturnValue(((NLBoatEntity) boat).getBoatSkin());
    }
}
