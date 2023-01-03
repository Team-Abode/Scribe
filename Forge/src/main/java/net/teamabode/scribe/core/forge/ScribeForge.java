package net.teamabode.scribe.core.forge;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.teamabode.scribe.core.Scribe;
import net.teamabode.scribe.core.api.platform.forge.EntityHelperImpl;

@Mod(Scribe.MOD_ID)
public class ScribeForge {
    public ScribeForge() {
        Scribe.initialize();
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        bus.addListener(this::addEntityAttributes);
    }

    private void addEntityAttributes(final EntityAttributeCreationEvent event) {
        EntityHelperImpl.ATTRIBUTE_MAP.forEach((supplier, builder) -> event.put(supplier.get(), builder.build()));
    }
}
