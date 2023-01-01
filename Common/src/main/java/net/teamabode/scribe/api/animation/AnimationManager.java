package net.teamabode.scribe.api.animation;

import com.google.gson.JsonParser;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.entity.AnimationState;
import net.teamabode.scribe.core.Scribe;

import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class AnimationManager extends SimplePreparableReloadListener<Map<ResourceLocation, AnimationData>> {

    public AnimationManager() {}

    @Override
    protected Map<ResourceLocation, AnimationData> prepare(ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        Map<ResourceLocation, AnimationData> animations = new HashMap<>();
        resourceManager.getNamespaces().forEach(s -> {
            resourceManager.listResources("scribe_animations", resourceLocation -> resourceLocation.getPath().endsWith(".json")).forEach((resourceLocation, resource) -> {
                try (Reader reader = resource.openAsReader()) {
                    animations.put(resourceLocation, new AnimationData(JsonParser.parseReader(reader)));
                } catch (Exception io) {
                    throw new AssertionError();
                }
            });
        });
        return animations;
    }

    @Override
    protected void apply(Map<ResourceLocation, AnimationData> object, ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        AnimationData.ANIMATIONS.clear();
        AnimationData.ANIMATIONS.putAll(object);
        AtomicInteger animationCount = new AtomicInteger();
        AnimationData.ANIMATIONS.forEach((resourceLocation, animationData) -> {
            animationCount.getAndIncrement();
            Scribe.LOGGER.info(resourceLocation.toString());
            AnimationDefinition animation = AnimationParser.getAnimation(resourceLocation);
            Scribe.LOGGER.info(String.valueOf(animation.lengthInSeconds()));
            Scribe.LOGGER.info(String.valueOf(animation.looping()));
        });
        Scribe.LOGGER.info("Found: " + animationCount.get() + " animations");
    }
}
