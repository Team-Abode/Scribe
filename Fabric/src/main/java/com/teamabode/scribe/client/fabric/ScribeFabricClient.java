package com.teamabode.scribe.client.fabric;

import com.teamabode.scribe.core.api.event.ColorRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;

public class ScribeFabricClient implements ClientModInitializer {

    public void onInitializeClient() {
        ColorRegistry.ITEM_COLOR_MAP.forEach(ColorProviderRegistry.ITEM::register);
        ColorRegistry.BLOCK_COLOR_MAP.forEach(ColorProviderRegistry.BLOCK::register);
    }
}
