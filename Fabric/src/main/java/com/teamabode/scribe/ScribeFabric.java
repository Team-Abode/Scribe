package com.teamabode.scribe;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;

public class ScribeFabric implements ModInitializer {

    public void onInitialize() {
        Scribe.init();
        Scribe.LOGGER.info("Hello Fabric World!");

        Registry.register(Registry.ITEM, new ResourceLocation(Scribe.MOD_ID, "test_item"), Scribe.TEST_ITEM.get());
        
        // Fabric implementation of the example hook.
        ItemTooltipCallback.EVENT.register(Scribe::onItemTooltip);
    }
}
