package com.teamabode.scribe.core.api.registry;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

import java.util.function.Supplier;

public interface ScribeRegistry {

    Supplier<Item> registerItem(String identifier, Supplier<Item> itemSupplier);

    Supplier<SpawnEggItem> registerSpawnEgg(String mobName, EntityType<Mob> entityType, int baseColor, int overlayColor);

    Supplier<Block> registerBlock(String identifier, Supplier<Block> blockSupplier);

    Supplier<Block> registerBlockWithItem(String identifier, Supplier<Block> blockSupplier, CreativeModeTab tab);

    Supplier<EntityType> registerEntityType(String identifier, Supplier<EntityType> entityTypeSupplier);

    <FC extends FeatureConfiguration> Supplier<Feature<FC>> registerFeature(String identifier, Supplier<Feature<FC>> featureSupplier);

    void registerEntityAttributes(Supplier<EntityType> entityTypeSupplier, Supplier<AttributeSupplier.Builder> map);

    void registerLayerDefinition(ModelLayerLocation modelLayerLocation, Supplier<LayerDefinition> layerDefinitionSupplier);

    void registerEntityRenderer(Supplier<EntityType> entityTypeSupplier, EntityRendererProvider entityRendererProvider);

}
