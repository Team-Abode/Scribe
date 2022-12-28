package com.teamabode.scribe;

import net.minecraft.world.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod(Scribe.MOD_ID)
public class ScribeForge {

    public static final DeferredRegister<Item> DEF_REG = DeferredRegister.create(ForgeRegistries.ITEMS, Scribe.MOD_ID);
    
    public ScribeForge() {
        Scribe.init();
        Scribe.LOGGER.info("Hello Forge World!");

        DEF_REG.register("test_item", Scribe.TEST_ITEM);
    
        // Forge implementation of the example hook (1/2)
        MinecraftForge.EVENT_BUS.addListener(this::onItemTooltip);
    }

    // Forge implementation of the example hook (2/2)
    private void onItemTooltip(ItemTooltipEvent event) {
        Scribe.onItemTooltip(event.getItemStack(), event.getFlags(), event.getToolTip());
    }
}