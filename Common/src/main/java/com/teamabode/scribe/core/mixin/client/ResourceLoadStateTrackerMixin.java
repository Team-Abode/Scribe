package com.teamabode.scribe.core.mixin.client;

import com.teamabode.scribe.core.Scribe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.ResourceLoadStateTracker;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.resources.ReloadableResourceManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ResourceLoadStateTracker.class)
public abstract class ResourceLoadStateTrackerMixin {

    @Inject(method = "startReload", at = @At("HEAD"))
    private void constructor(ResourceLoadStateTracker.ReloadReason reloadReason, List<PackResources> list, CallbackInfo callbackInfo) {
        if (reloadReason == ResourceLoadStateTracker.ReloadReason.INITIAL) {
            Minecraft minecraft = Minecraft.getInstance();
            ReloadableResourceManager resourceManager = (ReloadableResourceManager) minecraft.getResourceManager();
            resourceManager.registerReloadListener(Scribe.ANIMATION_MANAGER);
        }
    }
}
