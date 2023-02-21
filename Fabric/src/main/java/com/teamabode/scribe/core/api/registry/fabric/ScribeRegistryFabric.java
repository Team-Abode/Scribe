package com.teamabode.scribe.core.api.registry.fabric;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.block.Block;
import com.teamabode.scribe.core.api.registry.ScribeRegistry;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

import java.util.function.Supplier;

public class ScribeRegistryFabric implements ScribeRegistry {
    private final String modId;

    public ScribeRegistryFabric(String modId){
        this.modId = modId;
    }

    public Supplier<Item> registerItem(String identifier, Supplier<Item> itemSupplier) {
        Registry.register(Registry.ITEM, new ResourceLocation(modId, identifier), itemSupplier.get());

        return itemSupplier;
    }

    public Supplier<SpawnEggItem> registerSpawnEgg(String mobName, EntityType<Mob> entityType, int baseColor, int overlayColor) {
        SpawnEggItem item = new SpawnEggItem(entityType, baseColor, overlayColor, new Item.Properties().tab(CreativeModeTab.TAB_MISC));

        Registry.register(Registry.ITEM, new ResourceLocation(modId, mobName + "_spawn_egg"), item);

        return () -> item;
    }

    public Supplier<Block> registerBlock(String identifier, Supplier<Block> blockSupplier) {
        Registry.register(Registry.BLOCK, new ResourceLocation(modId, identifier), blockSupplier.get());

        return blockSupplier;
    }

    public Supplier<Block> registerBlockWithItem(String identifier, Supplier<Block> blockSupplier, CreativeModeTab tab) {
        Block block = Registry.register(Registry.BLOCK, new ResourceLocation(modId, identifier), blockSupplier.get());

        registerItem(identifier, () -> new BlockItem(block, new Item.Properties().tab(tab)));

        return blockSupplier;
    }

    public Supplier<EntityType> registerEntityType(String identifier, Supplier<EntityType> entityTypeSupplier) {
        EntityType entityType = Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(modId, identifier), entityTypeSupplier.get());

        return () -> entityType;
    }

    @Override
    public <FC extends FeatureConfiguration> Supplier<Feature<FC>> registerFeature(String identifier, Supplier<Feature<FC>> featureSupplier) {
        var feature = Registry.register(Registry.FEATURE, new ResourceLocation(modId, identifier), featureSupplier.get());
        return () -> feature;
    }

    public void registerEntityAttributes(Supplier<EntityType> entityTypeSupplier, Supplier<AttributeSupplier.Builder> map) {
        FabricDefaultAttributeRegistry.register((EntityType<? extends LivingEntity>) entityTypeSupplier.get(), map.get());
    }

    public void registerLayerDefinition(ModelLayerLocation modelLayerLocation, Supplier<LayerDefinition> layerDefinitionSupplier) {
        EntityModelLayerRegistry.registerModelLayer(modelLayerLocation, layerDefinitionSupplier::get);
    }

    public void registerEntityRenderer(Supplier<EntityType> entityTypeSupplier, EntityRendererProvider entityRendererProvider) {
        EntityRendererRegistry.register(entityTypeSupplier.get(), entityRendererProvider);
    }
}
