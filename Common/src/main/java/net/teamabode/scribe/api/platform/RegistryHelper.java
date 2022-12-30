package net.teamabode.scribe.api.platform;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.nio.file.Path;
import java.util.function.Supplier;

public class RegistryHelper {

    @ExpectPlatform
    public <T extends Item>Supplier<T> createItem(String id, Supplier<T> item) {
        throw new AssertionError();
    }
}
