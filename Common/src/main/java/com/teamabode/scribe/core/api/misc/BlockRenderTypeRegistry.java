package com.teamabode.scribe.core.api.misc;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;

public class BlockRenderTypeRegistry {

    @ExpectPlatform
    public static void putBlock(Block block, RenderType renderType) {
        throw new AssertionError();
    }
}
