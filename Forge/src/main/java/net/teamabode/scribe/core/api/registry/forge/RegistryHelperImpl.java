package net.teamabode.scribe.core.api.registry.forge;

import net.teamabode.scribe.core.api.registry.ScribeRegistry;

public class RegistryHelperImpl {
    public static ScribeRegistry getRegistry(String modId){
        return new ScribeRegistryForge(modId);
    }
}
