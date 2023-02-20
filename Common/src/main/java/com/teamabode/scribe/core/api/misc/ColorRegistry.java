package com.teamabode.scribe.core.api.misc;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

public class ColorRegistry {

    @ExpectPlatform
    public static void registerBlockProvider(BlockColor blockColor, Block... blocks) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void registerItemProvider(ItemColor itemColor, ItemLike... itemLikes) {
        throw new AssertionError();
    }
}
