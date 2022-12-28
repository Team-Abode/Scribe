package com.teamabode.scribe;

import com.teamabode.scribe.platform.services.RegistryHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class Registery implements RegistryHelper {
    @Override
    public void setModId(String id) {
        
    }

    @Override
    public Item register(String identifier, Item item) {
        return null;
    }

    @Override
    public Block register(String identifier, Block block) {
        return null;
    }
}
