package com.teamabode.scribe;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod(Scribe.MOD_ID)
public class ScribeForge {
    private void preInitialize(){
        Scribe.LOGGER.info("Running pre-initialization for Scribe FORGE...");

        Scribe.registery = new Registery();
    }

    private void postInitialize(){
        Scribe.LOGGER.info("Running post-initialization for Scribe FORGE...");

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ((Registery)Scribe.registery).registerEventBus(eventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    public ScribeForge() {
        preInitialize();
        Scribe.initialize();
        postInitialize();
    }
}