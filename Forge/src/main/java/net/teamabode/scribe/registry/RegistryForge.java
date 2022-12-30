package net.teamabode.scribe.registry;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.EventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.teamabode.scribe.registry.Registry;

import java.util.function.Supplier;

public class RegistryForge implements Registry {
    private String modId;

    private DeferredRegister<Item> items;

    RegistryForge(String modId){
        this.modId = modId;

        items = DeferredRegister.create(ForgeRegistries.ITEMS, modId);
        items.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    @Override
    public Supplier<Item> register(String identifier, Supplier<Item> itemSupplier) {
        items.register(identifier, itemSupplier);

        return itemSupplier;
    }
}
