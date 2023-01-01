package net.teamabode.scribe.core.api.platform.forge;

import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

public class PlatformHelperImpl {

    public static boolean isModLoaded(String modid) {
        return ModList.get().isLoaded(modid);
    }

    public static boolean isDevEnvironment() {
        return FMLLoader.isProduction();
    }

    public static Path getGamePath() {
        return FMLPaths.GAMEDIR.get();
    }

    public static Path getConfigPath() {
        return FMLPaths.CONFIGDIR.get();
    }
}
