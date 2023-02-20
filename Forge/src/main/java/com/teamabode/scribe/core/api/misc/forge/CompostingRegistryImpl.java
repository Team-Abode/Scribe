package com.teamabode.scribe.core.api.misc.forge;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.ComposterBlock;

public class CompostingRegistryImpl {

    public static void addCompostableItem(ItemLike item, float chance) {
        ComposterBlock.COMPOSTABLES.put(item, chance);
    }
}
