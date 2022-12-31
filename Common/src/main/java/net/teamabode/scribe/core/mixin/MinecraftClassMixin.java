package net.teamabode.scribe.core.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.server.packs.resources.ReloadableResourceManager;
import net.teamabode.scribe.core.Scribe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftClassMixin {
    @Inject(method = "<init>", at = @At("TAIL"))
    private void constructor(CallbackInfo callbackInfo){
        ReloadableResourceManager reloadableResourceManager = (ReloadableResourceManager)((Minecraft)(Object)this).getResourceManager();

        Scribe.LOGGER.info("Got resource manager:");
        Scribe.LOGGER.info(String.valueOf(reloadableResourceManager));

        reloadableResourceManager.registerReloadListener(Scribe.RELOAD_LISTENER);
    }
}
