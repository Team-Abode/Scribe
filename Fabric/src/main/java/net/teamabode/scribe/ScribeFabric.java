package net.teamabode.scribe;

import net.fabricmc.api.ModInitializer;

public class ScribeFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Scribe.initialize();
    }
}
