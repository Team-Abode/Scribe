package com.teamabode.scribe.core.api.registry.forge;

import com.teamabode.scribe.core.api.registry.ScribeRegistry;

public class RegistryCreatorImpl {
    public static ScribeRegistry getRegistry(String modId){
        return new ScribeRegistryForge(modId);
    }
}
