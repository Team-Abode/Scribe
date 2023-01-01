package net.teamabode.scribe.core.api.platform.forge;

import net.teamabode.scribe.core.api.platform.ScribeRegistry;

public class RegistryHelperImpl {
    public static ScribeRegistry getRegistry(String modId){
        return new ScribeRegistryForge(modId);
    }
}
