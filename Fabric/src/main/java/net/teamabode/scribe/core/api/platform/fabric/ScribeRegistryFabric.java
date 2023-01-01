package net.teamabode.scribe.core.api.platform.fabric;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.block.Block;
import net.teamabode.scribe.core.api.platform.ScribeRegistry;

import java.util.function.Supplier;

public class ScribeRegistryFabric implements ScribeRegistry {
    private final String modId;

    public ScribeRegistryFabric(String modId){
        this.modId = modId;
    }

    @Override
    public <T extends Item> Supplier<T> registerItem(String identifier, Supplier<T> itemSupplier) {
        T item = Registry.register(Registry.ITEM, new ResourceLocation(modId, identifier), itemSupplier.get());
        return () -> item;
    }

    @Override
    public Supplier<SpawnEggItem> registerSpawnEgg(String mobName, EntityType<? extends Mob> entityType, int baseColor, int overlayColor) {
        return registerItem(mobName + "_spawn_egg", () -> new SpawnEggItem(entityType, baseColor, overlayColor, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    }

    @Override
    public <T extends Block> Supplier<T> registerBlock(String identifier, Supplier<T> blockSupplier) {
        T block = Registry.register(Registry.BLOCK, new ResourceLocation(modId, identifier), blockSupplier.get());
        return () -> block;
    }

    @Override
    public <T extends Block> Supplier<T> registerBlockWithItem(String identifier, Supplier<T> blockSupplier, CreativeModeTab tab) {
        Supplier<T> block = registerBlock(identifier, blockSupplier);
        registerItem(identifier, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
        return block;
    }

    @Override
    public <E extends Entity, T extends EntityType<E>> Supplier<T> registerEntityType(String identifier, Supplier<T> entityTypeSupplier) {
        T entityType = Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(modId, identifier), entityTypeSupplier.get());
        return () -> entityType;
    }
}
