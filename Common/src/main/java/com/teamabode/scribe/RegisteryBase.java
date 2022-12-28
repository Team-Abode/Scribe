package com.teamabode.scribe;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public abstract class RegisteryBase {
    public String modId;
    public boolean hasSetModId = false;

    public void setModId(String id) {
        modId = id;

        hasSetModId = true;
    }

    public abstract Supplier<Item> register(String identifier, Supplier<Item> item);
}
