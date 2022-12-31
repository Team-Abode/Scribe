package net.teamabode.scribe.core.fabric;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.Minecraft;
import net.minecraft.server.packs.resources.ResourceManager;
import net.teamabode.scribe.core.Scribe;

public class ScribeFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Scribe.initialize();
    }
}
