package net.teamabode.scribe.api.platform;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.teamabode.scribe.api.platform.ScribeRegistry;

public class RegistryHelper {

    @ExpectPlatform
    public static ScribeRegistry getRegistry(String modId) {
        throw new AssertionError("No registry for platform found!");
    }
}
