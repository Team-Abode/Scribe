package net.teamabode.scribe.core.forge;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.teamabode.scribe.core.api.platform.forge.EntityHelperImpl;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ScribeForgeClient {

    @SubscribeEvent
    private static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        EntityHelperImpl.LAYER_DEFINITIONS.forEach(event::registerLayerDefinition);
    }
}
