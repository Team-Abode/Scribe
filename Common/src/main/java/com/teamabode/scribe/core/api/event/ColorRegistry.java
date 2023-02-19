package com.teamabode.scribe.core.api.event;

import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;

public class ColorRegistry {

    public static final Map<BlockColor, Block[]> BLOCK_COLOR_MAP = new HashMap<>();
    public static final Map<ItemColor, ItemLike[]> ITEM_COLOR_MAP = new HashMap<>();

    public static void registerBlockProvider(BlockColor blockColor, Block... blocks) {
        BLOCK_COLOR_MAP.put(blockColor, blocks);
    }

    public static void registerItemProvider(ItemColor itemColor, ItemLike... itemLikes) {
        ITEM_COLOR_MAP.put(itemColor, itemLikes);
    }
}
