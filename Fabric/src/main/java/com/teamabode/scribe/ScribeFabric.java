package com.teamabode.scribe;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;

public class ScribeFabric implements ModInitializer {

    public void onInitialize() {
        Scribe.init();
        Scribe.LOGGER.info("Hello Fabric World!");
        
        // Fabric implementation of the example hook.
        ItemTooltipCallback.EVENT.register(Scribe::onItemTooltip);
    }
}
