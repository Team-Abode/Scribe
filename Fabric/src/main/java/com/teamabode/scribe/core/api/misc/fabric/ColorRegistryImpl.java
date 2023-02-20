package com.teamabode.scribe.core.api.misc.fabric;

import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

public class ColorRegistryImpl {

    public static void registerBlockProvider(BlockColor blockColor, Block... blocks) {
        ColorProviderRegistry.BLOCK.register(blockColor, blocks);
    }

    public static void registerItemProvider(ItemColor itemColor, ItemLike... itemLikes) {
        ColorProviderRegistry.ITEM.register(itemColor, itemLikes);
    }
}
