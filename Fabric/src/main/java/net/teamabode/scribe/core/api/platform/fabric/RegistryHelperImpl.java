package net.teamabode.scribe.core.api.platform.fabric;

import net.teamabode.scribe.core.api.platform.ScribeRegistry;

public class RegistryHelperImpl {
    public static ScribeRegistry getRegistry(String modId){
        return new ScribeRegistryFabric(modId);
    }
}
