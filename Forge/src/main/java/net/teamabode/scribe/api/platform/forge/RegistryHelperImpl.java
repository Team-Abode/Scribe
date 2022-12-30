package net.teamabode.scribe.api.platform.forge;

import net.teamabode.scribe.api.platform.ScribeRegistry;

public class RegistryHelperImpl {
    public static ScribeRegistry getRegistry(String modId){
        return new ScribeRegistryForge(modId);
    }
}
