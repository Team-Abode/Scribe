package net.teamabode.scribe.core.api.platform;

import dev.architectury.injectables.annotations.ExpectPlatform;




public class RegistryHelper {

    @ExpectPlatform
    public static ScribeRegistry getRegistry(String modId) {
        throw new AssertionError("No registry for platform found!");
    }
}
