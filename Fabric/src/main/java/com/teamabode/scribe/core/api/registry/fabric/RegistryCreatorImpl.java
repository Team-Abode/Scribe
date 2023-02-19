package com.teamabode.scribe.core.api.registry.fabric;

import com.teamabode.scribe.core.api.registry.ScribeRegistry;

public class RegistryCreatorImpl {
    public static ScribeRegistry getRegistry(String modId){
        return new ScribeRegistryFabric(modId);
    }
}
