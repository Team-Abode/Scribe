package net.teamabode.scribe;

import net.fabricmc.api.ModInitializer;
import net.teamabode.scribe.Scribe;

public class ScribeFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Scribe.initialize();
    }
}
