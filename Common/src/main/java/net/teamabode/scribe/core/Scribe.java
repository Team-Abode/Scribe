package net.teamabode.scribe.core;

import net.minecraft.client.Minecraft;
import net.minecraft.server.packs.resources.ReloadableResourceManager;
import net.minecraft.server.packs.resources.ResourceManager;
import net.teamabode.scribe.api.resource.ScribeResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Scribe {
    public static final String MOD_ID = "scribe";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final ScribeResourceLoader ANIMATION_LOADER = new ScribeResourceLoader();

    public static void initialize() {
        ResourceManager resourceManager = Minecraft.getInstance().getResourceManager();
        if (resourceManager instanceof ReloadableResourceManager reloadableResourceManager) {
            reloadableResourceManager.registerReloadListener(ANIMATION_LOADER);
        }
    }
}
