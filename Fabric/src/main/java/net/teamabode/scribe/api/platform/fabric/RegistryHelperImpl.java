package net.teamabode.scribe.api.platform.fabric;

import net.teamabode.scribe.api.platform.ScribeRegistry;

public class RegistryHelperImpl {
    public static ScribeRegistry getRegistry(String modId){
        return new ScribeRegistryFabric(modId);
    }
}
