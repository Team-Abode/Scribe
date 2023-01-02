package net.teamabode.scribe.core.api.animation;

import com.google.gson.JsonParser;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;

import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class AnimationManager extends SimplePreparableReloadListener<Map<ResourceLocation, AnimationData>> {
    public static final Map<ResourceLocation, AnimationData> ANIMATIONS = new HashMap<>();

    @Override
    protected Map<ResourceLocation, AnimationData> prepare(ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        Map<ResourceLocation, AnimationData> animations = new HashMap<>();
        resourceManager.getNamespaces().forEach(s -> {
            resourceManager.listResources("scribe_animations", resourceLocation -> resourceLocation.getPath().endsWith(".json")).forEach((resourceLocation, resource) -> {
                try (Reader reader = resource.openAsReader()) {
                    ResourceLocation trueLocation = new ResourceLocation(resourceLocation.getNamespace(), resourceLocation.getPath().substring(18, resourceLocation.getPath().length() - 5));
                    animations.put(trueLocation, new AnimationData(JsonParser.parseReader(reader)));
                } catch (Exception io) {
                    throw new AssertionError();
                }
            });
        });
        return animations;
    }

    @Override
    protected void apply(Map<ResourceLocation, AnimationData> object, ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        ANIMATIONS.clear();
        ANIMATIONS.putAll(object);
    }
}
