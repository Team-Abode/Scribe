package net.teamabode.scribe.core;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.teamabode.scribe.core.api.animation.AnimationManager;
import net.teamabode.scribe.core.api.platform.EntityHelper;
import net.teamabode.scribe.core.api.platform.RegistryHelper;
import net.teamabode.scribe.core.api.platform.ScribeRegistry;
import net.teamabode.scribe.core.registry.quebe.Quebe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

public class Scribe {
    public static final String MOD_ID = "scribe";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final AnimationManager ANIMATION_MANAGER = new AnimationManager();

    public static final ScribeRegistry REGISTRY = RegistryHelper.getRegistry(MOD_ID);
    public static final Supplier<Block> TEST_BLOCK = REGISTRY.registerBlockWithItem("test_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).destroyTime(1.0F)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final Supplier<EntityType<Quebe>> QUEBE = REGISTRY.registerEntityType("quebe", () -> EntityType.Builder.of(Quebe::new, MobCategory.CREATURE).sized(1.0F, 1.0F).clientTrackingRange(10).build("quebe"));

    public static void initialize() {
        EntityHelper.registerEntityAttributes(QUEBE, Quebe::createQuebeAttributes);
    }
}
