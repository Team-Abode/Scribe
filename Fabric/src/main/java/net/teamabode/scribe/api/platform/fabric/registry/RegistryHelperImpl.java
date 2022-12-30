package net.teamabode.scribe.api.platform.fabric.registry;

import net.teamabode.scribe.api.platform.registry.Registry;

public class RegistryHelperImpl {
    public static Registry getRegistry(String modId){
        return new RegistryFabric(modId);
    }
}
