package net.teamabode.scribe.core.resource;

import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.teamabode.scribe.core.Scribe;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class ScribeResourceListener extends SimplePreparableReloadListener<String> {
    @Override
    protected String prepare(ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        profilerFiller.startTick();
        profilerFiller.push("Counting to 10000000");

        Scribe.LOGGER.info("Counting to 10000000!");

        for (int i = 0; i < 10000000; i++){

        }

        profilerFiller.pop();
        profilerFiller.endTick();

        return "HEHEHEHA TAKE THAT CSS";
    }

    @Override
    protected void apply(String object, ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        Scribe.LOGGER.info(object);
    }
}
