package com.teamabode.scribe.core.api.misc.forge;

import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

public class ColorRegistryImpl {

    public static void registerBlockProvider(BlockColor blockColor, Block... blocks) {
        Minecraft.getInstance().getBlockColors().register(blockColor, blocks);
    }

    public static void registerItemProvider(ItemColor itemColor, ItemLike... itemLikes) {
        Minecraft.getInstance().getItemColors().register(itemColor, itemLikes);
    }
}
