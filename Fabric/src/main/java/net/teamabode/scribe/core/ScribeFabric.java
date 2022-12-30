package net.teamabode.scribe.core;

import net.teamabode.scribe.core.Scribe;
import net.fabricmc.api.ModInitializer;

public class ScribeFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Scribe.init();
    }
}
