package com.teamabode.scribe;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;

public class ScribeFabric implements ModInitializer {

    private void preInitialize(){
        Scribe.LOGGER.info("Running pre-initialization for Scribe FABRIC...");

        Scribe.registery = new Registery();
    }

    private void postInitialize(){
        Scribe.LOGGER.info("Running post-initialization for Scribe FABRIC...");
    }

    public void onInitialize() {
        preInitialize();
        Scribe.initialize();
        postInitialize();
    }
}
