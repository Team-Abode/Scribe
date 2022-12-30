package net.teamabode.scribe.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import java.util.function.Supplier;

public class RegistryFabric implements Registry {
    private String modId;

    RegistryFabric(String modId){
        this.modId = modId;
    }

    @Override
    public Supplier<Item> register(String identifier, Supplier<Item> itemSupplier) {
        net.minecraft.core.Registry.register(net.minecraft.core.Registry.ITEM, new ResourceLocation(modId, identifier), itemSupplier.get());

        return itemSupplier;
    }
}
