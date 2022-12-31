package net.teamabode.scribe.core;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.teamabode.scribe.api.platform.RegistryHelper;
import net.teamabode.scribe.api.platform.ScribeRegistry;
import net.teamabode.scribe.core.resource.ScribeReloadableResourceListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

public class Scribe {
    public static final String MOD_ID = "scribe";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final ScribeReloadableResourceListener RELOAD_LISTENER = new ScribeReloadableResourceListener();

    public static final ScribeRegistry REGISTRY = RegistryHelper.getRegistry(MOD_ID);
    public static final Supplier<Block> TEST_BLOCK = REGISTRY.registerBlockWithItem("test_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).destroyTime(1.0F)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static void initialize() {
    }
}
