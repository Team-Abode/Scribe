package net.teamabode.scribe.core.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.ResourceLoadStateTracker;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.resources.ReloadableResourceManager;
import net.teamabode.scribe.api.animation.AnimationManager;
import net.teamabode.scribe.core.Scribe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ResourceLoadStateTracker.class)
public abstract class ResourceLoadStateTrackerMixin {

    @Inject(method = "startReload", at = @At("HEAD"))
    private void constructor(ResourceLoadStateTracker.ReloadReason reloadReason, List<PackResources> list, CallbackInfo callbackInfo) {
        if(reloadReason == ResourceLoadStateTracker.ReloadReason.INITIAL) {
            Minecraft minecraft = Minecraft.getInstance();
            ReloadableResourceManager resourceManager = (ReloadableResourceManager) minecraft.getResourceManager();
            Scribe.animationManager = new AnimationManager();
            resourceManager.registerReloadListener(Scribe.animationManager);
        }
    }
}
