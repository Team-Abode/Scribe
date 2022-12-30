package net.teamabode.scribe.registry;

public class RegistryHelperImpl {
    public static Registry getRegistry(String modId){
        return new RegistryFabric(modId);
    }
}
