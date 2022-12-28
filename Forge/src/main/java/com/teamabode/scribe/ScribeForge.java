package com.teamabode.scribe;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(Scribe.MOD_ID)
public class ScribeForge {
    
    public ScribeForge() {
        Scribe.init();
        Scribe.LOGGER.info("Hello Forge World!");
    
        // Forge implementation of the example hook (1/2)
        MinecraftForge.EVENT_BUS.addListener(this::onItemTooltip);
    }

    // Forge implementation of the example hook (2/2)
    private void onItemTooltip(ItemTooltipEvent event) {
        Scribe.onItemTooltip(event.getItemStack(), event.getFlags(), event.getToolTip());
    }
}