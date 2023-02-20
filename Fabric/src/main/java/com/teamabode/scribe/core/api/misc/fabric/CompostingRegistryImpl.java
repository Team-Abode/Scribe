package com.teamabode.scribe.core.api.misc.fabric;

import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.world.level.ItemLike;

public class CompostingRegistryImpl {

    public static void addCompostableItem(ItemLike item, float chance) {
        CompostingChanceRegistry.INSTANCE.add(item, chance);
    }
}
