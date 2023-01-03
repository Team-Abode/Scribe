package net.teamabode.scribe.core.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.teamabode.scribe.core.ScribeClient;

public class ScribeFabricClient implements ClientModInitializer {

    public void onInitializeClient() {
        ScribeClient.clientInit();
    }
}
