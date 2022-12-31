package net.teamabode.scribe.core.resource;

import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.teamabode.scribe.core.Scribe;

import java.util.stream.Stream;

public class ScribeReloadableResourceListener implements ResourceManagerReloadListener {
    @Override
    public void onResourceManagerReload(ResourceManager resourceManager) {
        Stream<PackResources> stream = resourceManager.listPacks();

        stream.forEach((packResources) -> {
            try {
                Scribe.LOGGER.info("Reading pack... " + packResources.getName() + " " + packResources.getNamespaces(PackType.CLIENT_RESOURCES) + " " + packResources.getNamespaces(PackType.SERVER_DATA));
            } catch (RuntimeException exception) {
                Scribe.LOGGER.warn("An error occurred on the scribe reloadable resource listener: {}", packResources.getName(), exception);
            }

        });
    }
}
