package com.teamabode.scribe.core.fabric;

import com.teamabode.scribe.core.Scribe;
import net.fabricmc.api.ModInitializer;

public class ScribeFabric implements ModInitializer {

    public void onInitialize() {
        Scribe.initialize();
    }
}
