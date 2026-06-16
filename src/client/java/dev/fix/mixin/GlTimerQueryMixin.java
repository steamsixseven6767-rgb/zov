package dev.fix.mixin;

import net.minecraft.client.gl.GlTimerQuery;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GlTimerQuery.class)
public abstract class GlTimerQueryMixin {

    @Shadow
    private boolean closed;

    @Inject(method = "getValue", at = @At("HEAD"), cancellable = true)
    private void fixClosedQuery(CallbackInfoReturnable<Long> cir) {
        if (this.closed) {
            cir.setReturnValue(0L);
        }
    }
}
