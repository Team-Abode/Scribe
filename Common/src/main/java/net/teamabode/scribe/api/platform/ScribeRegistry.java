package net.teamabode.scribe.api.platform;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public interface ScribeRegistry {
    <T extends Item>Supplier<T> registerItem(String identifier, Supplier<T> itemSupplier);

    Supplier<SpawnEggItem> registerSpawnEgg(String mobName, EntityType<? extends Mob> entityType, int baseColor, int overlayColor);

    <T extends Block>Supplier<T> registerBlock(String identifier, Supplier<T> blockSupplier);
}
