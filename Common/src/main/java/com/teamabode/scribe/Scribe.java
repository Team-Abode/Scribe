package com.teamabode.scribe;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

public class Scribe {
    public static final String MOD_ID = "scribe";
    public static final Logger LOGGER = LoggerFactory.getLogger("Scribe");

    public static RegisteryBase registery;

    public static final Supplier<Item> TEST_ITEM = () -> new Item(
            new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(16));

    public static void initialize() {
        Scribe.LOGGER.info("Running initialization for Scribe COMMON...");

        registery.setModId("scribe_test");
        registery.register("test_item", TEST_ITEM);
    }
}
