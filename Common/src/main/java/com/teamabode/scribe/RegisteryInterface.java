package com.teamabode.scribe;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public interface RegisteryInterface {
    void setModId(String id);
    Item register(String identifier, Item item);
    Block register(String identifier, Block block);
}
