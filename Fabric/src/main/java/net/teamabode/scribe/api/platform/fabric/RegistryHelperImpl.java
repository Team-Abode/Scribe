package net.teamabode.scribe.api.platform.fabric;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class RegistryHelperImpl {

    public static <T extends Item> Supplier<T> createItem(ResourceLocation resourceLocation, Supplier<T> item) {
        return () -> Registry.register(Registry.ITEM, resourceLocation, item.get());
    }
}
