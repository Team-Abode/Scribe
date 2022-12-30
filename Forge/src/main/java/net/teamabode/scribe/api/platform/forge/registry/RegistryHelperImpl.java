package net.teamabode.scribe.api.platform.forge.registry;

import net.teamabode.scribe.api.platform.registry.Registry;

public class RegistryHelperImpl {
    public static Registry getRegistry(String modId){
        return new RegistryForge(modId);
    }
}
