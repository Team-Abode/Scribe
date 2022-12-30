package net.teamabode.scribe.registry;

import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public interface Registry  {
    Supplier<Item> register(String identifier, Supplier<Item> itemSupplier);
}
