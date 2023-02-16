package net.teamabode.scribe.core.api.registry.fabric;

import net.teamabode.scribe.core.api.registry.ScribeRegistry;

public class RegistryHelperImpl {
    public static ScribeRegistry getRegistry(String modId){
        return new ScribeRegistryFabric(modId);
    }
}
