package net.teamabode.scribe.api.platform.fabric;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.block.Block;
import net.teamabode.scribe.api.platform.ScribeRegistry;

import java.util.function.Supplier;

public class ScribeRegistryFabric implements ScribeRegistry {
    private final String modId;

    public ScribeRegistryFabric(String modId){
        this.modId = modId;
    }

    @Override
    public <T extends Item> Supplier<T> registerItem(String identifier, Supplier<T> itemSupplier) {
        Registry.register(Registry.ITEM, new ResourceLocation(modId, identifier), itemSupplier.get());
        return itemSupplier;
    }

    @Override
    public Supplier<SpawnEggItem> registerSpawnEgg(String mobName, EntityType<? extends Mob> entityType, int baseColor, int overlayColor) {
        return registerItem(mobName + "_spawn_egg", () -> new SpawnEggItem(entityType, baseColor, overlayColor, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    }

    @Override
    public <T extends Block> Supplier<T> registerBlock(String identifier, Supplier<T> blockSupplier) {
        Registry.register(Registry.BLOCK, new ResourceLocation(modId, identifier), blockSupplier.get());
        return blockSupplier;
    }
}
