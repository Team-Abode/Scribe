package net.teamabode.scribe.core.api.platform.forge;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class EntityHelperImpl {
    public static final Map<Supplier<? extends EntityType<? extends LivingEntity>>, AttributeSupplier.Builder> ATTRIBUTE_MAP = new HashMap<>();
    public static final Map<ModelLayerLocation, Supplier<LayerDefinition>> LAYER_DEFINITIONS = new HashMap<>();

    public static void registerEntityAttributes(Supplier<? extends EntityType<? extends LivingEntity>> entityTypeSupplier, AttributeSupplier.Builder map) {
        ATTRIBUTE_MAP.put(entityTypeSupplier, map);
    }

    public static void registerLayerDefinition(ModelLayerLocation modelLayerLocation, Supplier<LayerDefinition> layerDefinitionSupplier) {
        LAYER_DEFINITIONS.put(modelLayerLocation, layerDefinitionSupplier);
    }

    public static <T extends Entity> void registerEntityRenderer(Supplier<? extends EntityType<T>> entityTypeSupplier, EntityRendererProvider<T> entityRendererProvider) {
        EntityRenderers.register(entityTypeSupplier.get(), entityRendererProvider);
    }
}
