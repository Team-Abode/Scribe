package net.teamabode.scribe.api.platform;

import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public interface Registry  {
    Supplier<Item> register(String identifier, Supplier<Item> itemSupplier);
}
