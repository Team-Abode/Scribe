package net.teamabode.scribe_test;

import net.minecraft.world.item.Item;
import net.teamabode.scribe.registry.Registry;
import net.teamabode.scribe.registry.RegistryHelper;

import java.util.function.Supplier;

public class ScribeTest {
    public static final String MOD_ID = "scribe_test";

    public static final Registry registry = RegistryHelper.getRegistry(MOD_ID);

    public static final Supplier<Item> TEST_ITEM = registry.register("test_item", () -> new Item(new Item.Properties()));
    
    public static void initialize() {
    }
}
