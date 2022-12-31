package net.teamabode.scribe.core.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.server.packs.resources.ReloadableResourceManager;
import net.teamabode.scribe.api.animation.AnimationManager;
import net.teamabode.scribe.core.Scribe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {
    private final Minecraft mc = Minecraft.class.cast(this);

    @Inject(method = "<init>", at = @At("TAIL"))
    private void constructor(CallbackInfo callbackInfo){
        ReloadableResourceManager reloadableResourceManager = (ReloadableResourceManager) mc.getResourceManager();
        Scribe.LOGGER.info("Got resource manager:");
        Scribe.LOGGER.info(String.valueOf(reloadableResourceManager));
        reloadableResourceManager.registerReloadListener(new AnimationManager());
    }
}
