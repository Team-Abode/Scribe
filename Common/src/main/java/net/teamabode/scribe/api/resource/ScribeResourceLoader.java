package net.teamabode.scribe.api.resource;

import com.google.gson.JsonParser;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
import net.teamabode.scribe.api.animation.AnimationData;
import net.teamabode.scribe.core.Scribe;
import org.jetbrains.annotations.Nullable;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class ScribeResourceLoader implements PreparableReloadListener {
    private final Map<ResourceLocation, AnimationData> animationRegistry = new HashMap<>();

    public AnimationData getAnimationData(ResourceLocation resourceLocation) {
        return animationRegistry.get(resourceLocation);
    }

    @Override
    public CompletableFuture<Void> reload(PreparationBarrier preparationBarrier, ResourceManager resourceManager, ProfilerFiller profilerFiller, ProfilerFiller profilerFiller2, Executor executor, Executor executor2) {
        return CompletableFuture.allOf(
                loadResources(executor, resourceManager, "scribe_animations", resource -> loadAnimation(resourceManager, resource), animationRegistry::put)
        );
    }

    private static <T> CompletableFuture<Void> loadResources(Executor executor, ResourceManager resourceManager, String type, Function<ResourceLocation, T> loader, BiConsumer<ResourceLocation, T> map) {
        return CompletableFuture.supplyAsync(
                () -> resourceManager.listResources(type, fileName -> fileName.toString().endsWith(".json")), executor
        ).thenApplyAsync(resources -> {
            Map<ResourceLocation, CompletableFuture<T>> tasks = new HashMap<>();
            for (ResourceLocation resource : resources.keySet()) {
                CompletableFuture<T> existing = tasks.put(resource, CompletableFuture.supplyAsync(() -> loader.apply(resource), executor));
                Scribe.LOGGER.info("Registering " + resource);
                if (existing != null) {
                    Scribe.LOGGER.error("Duplicate resource for " + resource);
                    existing.cancel(false);
                }
            }
            return tasks;
        }, executor).thenAcceptAsync(tasks -> {
            for (Map.Entry<ResourceLocation, CompletableFuture<T>> entry : tasks.entrySet()) {
                map.accept(entry.getKey(), entry.getValue().join());
            }
        });
    }

    private static AnimationData loadAnimation(ResourceManager resourceManager, ResourceLocation resourceLocation) {
        return new AnimationData(JsonParser.parseString(getResourceAsString(resourceManager, resourceLocation)));
    }

    @Nullable
    private static String getResourceAsString(ResourceManager resourceManager, ResourceLocation resourceLocation) {
        try {
            InputStream inputStream = resourceManager.getResourceOrThrow(resourceLocation).open();

            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            Scribe.LOGGER.error("There was an error loading resource file " + resourceLocation.toString());
            Scribe.LOGGER.error(String.valueOf(e));

            return null;
        }
    }
}
