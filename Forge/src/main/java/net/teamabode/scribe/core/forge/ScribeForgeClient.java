package net.teamabode.scribe.core.forge;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.teamabode.scribe.core.Scribe;
import net.teamabode.scribe.core.ScribeClient;

@Mod.EventBusSubscriber(modid = Scribe.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ScribeForgeClient {

    @SubscribeEvent
    public static void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(ScribeClient::clientInit);
    }
}
