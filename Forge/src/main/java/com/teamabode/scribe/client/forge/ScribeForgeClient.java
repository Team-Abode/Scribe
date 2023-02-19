package com.teamabode.scribe.client.forge;

import com.teamabode.scribe.core.Scribe;
import com.teamabode.scribe.core.api.event.ColorRegistry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Scribe.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ScribeForgeClient {

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        ColorRegistry.BLOCK_COLOR_MAP.forEach(event::register);
    }

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        ColorRegistry.ITEM_COLOR_MAP.forEach(event::register);
    }
}
