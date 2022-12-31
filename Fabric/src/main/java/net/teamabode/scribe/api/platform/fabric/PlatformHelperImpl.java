package net.teamabode.scribe.api.platform.fabric;

import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public class PlatformHelperImpl {

    public static boolean isModLoaded(String modid) {
        return FabricLoader.getInstance().isModLoaded(modid);
    }

    public static boolean isDevEnvironment() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    public static Path getGamePath() {
        return FabricLoader.getInstance().getGameDir();
    }

    public static Path getConfigPath() {
        return FabricLoader.getInstance().getConfigDir();
    }
}
