package net.teamabode.scribe_test;

import net.fabricmc.api.ModInitializer;

public class ScribeTestFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        ScribeTest.initialize();
    }
}
