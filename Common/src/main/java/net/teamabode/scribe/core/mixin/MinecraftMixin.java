package net.teamabode.scribe.core.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.server.packs.resources.ReloadableResourceManager;
import net.teamabode.scribe.api.animation.AnimationManager;
import net.teamabode.scribe.core.Scribe;
import net.teamabode.scribe.core.resource.ScribeResourceListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {

    @Inject(method = "<init>", at = @At("TAIL"))
    private void constructor(CallbackInfo callbackInfo) {
        Minecraft minecraft = ((Minecraft) (Object) this);
        ReloadableResourceManager resourceManager = (ReloadableResourceManager) minecraft.getResourceManager();
        Scribe.resourceListener = new ScribeResourceListener();
        Scribe.animationManager = new AnimationManager();
        resourceManager.registerReloadListener(Scribe.animationManager);
        minecraft.reloadResourcePacks();
    }
}
