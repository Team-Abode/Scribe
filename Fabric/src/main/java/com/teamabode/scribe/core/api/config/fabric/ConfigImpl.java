package com.teamabode.scribe.core.api.config.fabric;

import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public class ConfigImpl {
    public static Path getPath() {
        return FabricLoader.getInstance().getConfigDir();
    }
}
