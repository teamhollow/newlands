package net.teamhollow.newlands.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.teamhollow.newlands.block.NLSign;

@Mixin(BlockEntityType.class)
public class BlockEntityTypeMixin {
    @SuppressWarnings("all")
    @Inject(method = "supports", at = @At("HEAD"), cancellable = true)
    private void supports(Block block, CallbackInfoReturnable<Boolean> cir) {
        if (BlockEntityType.SIGN.equals(this) && block instanceof NLSign) cir.setReturnValue(true);
    }
}
