package net.teamabode.scribe.api.platform;

import dev.architectury.injectables.annotations.ExpectPlatform;

public class RegistryHelper {
    @ExpectPlatform
    public static Registry getRegistry(String modId) {
        throw new AssertionError("No registry for platform found!");
    }
}
