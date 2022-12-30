package net.teamabode.scribe.api.platform.forge;

import net.teamabode.scribe.api.platform.Registry;

public class RegistryHelperImpl {
    public static Registry getRegistry(String modId){
        return new RegistryForge(modId);
    }
}
