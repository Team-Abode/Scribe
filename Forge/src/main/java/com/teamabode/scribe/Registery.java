package com.teamabode.scribe;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Supplier;

public class Registery extends RegisteryBase {
    private DeferredRegister<Item> ITEMS;

    @Override
    public void setModId(String id) {
        super.setModId(id);

        ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, id);
    }

    @Override
    public Supplier<Item> register(String identifier, Supplier<Item> item) {
        Scribe.LOGGER.info("Registering item!");

        if(!hasSetModId) throw new AssertionError("You need to set a mod id before registering!");

        ITEMS.register(identifier, item);

        return item;
    }

    public void registerEventBus(IEventBus eventBus){
        if(!hasSetModId) return;

        ITEMS.register(eventBus);
    }
}
