package net.teamabode.scribe.api.platform.forge;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.teamabode.scribe.core.Scribe;

import java.util.function.Supplier;

public class RegistryHelperImpl {

    public static final DeferredRegister<Item> ITEM_HELPER = DeferredRegister.create(ForgeRegistries.ITEMS, Scribe.MOD_ID);

    public static <T extends Item> Supplier<T> createItem(String id, Supplier<T> item) {
        return ITEM_HELPER.register(id, item);
    }
}
