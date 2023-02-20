package com.teamabode.scribe.core.api.misc;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

public class CompostingRegistry {

    @ExpectPlatform
    public static void addCompostableItem(ItemLike item, float chance) {
        throw new AssertionError();
    }
}
