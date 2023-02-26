package com.teamabode.scribe.core.api.config.forge;

import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

public class ConfigImpl {
    public static Path getPath() {
        return FMLPaths.CONFIGDIR.get();
    }
}
