package net.teamabode.scribe.api.platform.fabric;

import net.teamabode.scribe.api.platform.Registry;

public class RegistryHelperImpl {
    public static Registry getRegistry(String modId){
        return new RegistryFabric(modId);
    }
}
