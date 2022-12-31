package net.teamabode.scribe.core.resource;

import net.minecraft.client.resources.sounds.SoundEventRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.profiling.ProfilerFiller;
import net.teamabode.scribe.core.Scribe;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class ScribeResourceListener extends SimplePreparableReloadListener<String> {
    @Override
    protected String prepare(ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        profilerFiller.startTick();

        for(Iterator namespacesIterator = resourceManager.getNamespaces().iterator(); namespacesIterator.hasNext(); profilerFiller.pop()) {
            String namespace = (String)namespacesIterator.next();

            Scribe.LOGGER.info("Reading data on prepare from namespace " + namespace);

//            try {
//                resourceManager.getResourceStack()
//            } catch (IOException exception) {
//                Scribe.LOGGER.warn(String.valueOf(exception));
//            }
        }

        profilerFiller.endTick();

        return "HEHEHEHA TAKE THAT CSS";
    }

    @Override
    protected void apply(String object, ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        Scribe.LOGGER.info(object);
    }
}
