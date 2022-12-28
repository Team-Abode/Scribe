package com.teamabode.scribe;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class Registery extends RegisteryBase {
    @Override
    public Supplier<Item> register(String identifier, Supplier<Item> item) {
        if(!hasSetModId) throw new AssertionError("You need to set a mod id before registering!");

        Registry.register(Registry.ITEM, new ResourceLocation(modId, identifier), item.get());

        return item;
    }
}
