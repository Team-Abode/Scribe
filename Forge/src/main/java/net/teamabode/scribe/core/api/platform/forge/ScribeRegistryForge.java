package net.teamabode.scribe.core.api.platform.forge;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.teamabode.scribe.core.api.platform.ScribeRegistry;

import java.util.function.Supplier;

public class ScribeRegistryForge implements ScribeRegistry {
    private final String modId;
    private final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

    private final DeferredRegister<Item> itemRegistry;
    private final DeferredRegister<Block> blockRegistry;
    private final DeferredRegister<EntityType<?>> entityTypeRegistry;

    public ScribeRegistryForge(String modId){
        this.modId = modId;
        // Items
        itemRegistry = DeferredRegister.create(ForgeRegistries.ITEMS, modId);
        itemRegistry.register(bus);
        // Blocks
        blockRegistry = DeferredRegister.create(ForgeRegistries.BLOCKS, modId);
        blockRegistry.register(bus);
        // Entity Types
        entityTypeRegistry = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, modId);
        entityTypeRegistry.register(bus);
    }

    @Override
    public <T extends Item> Supplier<T> registerItem(String identifier, Supplier<T> itemSupplier) {
        return itemRegistry.register(identifier, itemSupplier);
    }

    @Override
    public Supplier<SpawnEggItem> registerSpawnEgg(String mobName, EntityType<? extends Mob> entityType, int baseColor, int overlayColor) {
        return itemRegistry.register(mobName + "_spawn_egg", () -> new ForgeSpawnEggItem(() -> entityType, baseColor, overlayColor, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    }

    @Override
    public <T extends Block> Supplier<T> registerBlock(String identifier, Supplier<T> blockSupplier) {
        itemRegistry.register(identifier, () -> new BlockItem(blockSupplier.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
        return blockRegistry.register(identifier, blockSupplier);
    }

    @Override
    public <T extends Block> Supplier<T> registerBlockWithItem(String identifier, Supplier<T> blockSupplier, CreativeModeTab tab) {
        Supplier<T> block = blockRegistry.register(identifier, blockSupplier);
        itemRegistry.register(identifier, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
        return block;
    }

    @Override
    public <E extends Entity, T extends EntityType<E>> Supplier<T> registerEntityType(String identifier, Supplier<T> entityTypeSupplier) {
        return entityTypeRegistry.register(identifier, entityTypeSupplier);
    }
}
