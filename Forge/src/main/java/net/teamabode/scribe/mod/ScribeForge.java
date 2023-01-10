package net.teamabode.scribe.mod;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.teamabode.scribe.Scribe;

@Mod(Scribe.MOD_ID)
public class ScribeForge {
    public ScribeForge() {
        Scribe.initialize();
    }
}
