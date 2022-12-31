package net.teamabode.scribe.api.platform;

import net.minecraft.world.entity.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public interface ScribeRegistry {
    <T extends Item>Supplier<T> registerItem(String identifier, Supplier<T> itemSupplier);

    Supplier<SpawnEggItem> registerSpawnEgg(String mobName, EntityType<? extends Mob> entityType, int baseColor, int overlayColor);

    <T extends Block>Supplier<T> registerBlock(String identifier, Supplier<T> blockSupplier);

    <T extends Block>Supplier<T> registerBlockWithItem(String identifier, Supplier<T> blockSupplier, CreativeModeTab tab);

    <E extends Entity, T extends EntityType<E>>Supplier<T> registerEntityType(String identifier, Supplier<T> entityTypeSupplier);
}
