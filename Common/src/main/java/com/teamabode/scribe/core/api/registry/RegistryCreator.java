package com.teamabode.scribe.core.api.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;

public class RegistryCreator {

    @ExpectPlatform
    public static ScribeRegistry getRegistry(String modId) {
        throw new AssertionError("No registry for platform found!");
    }
}
