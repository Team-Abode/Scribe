package net.teamabode.scribe.api.platform.forge;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.teamabode.scribe.api.platform.ScribeRegistry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public class ScribeRegistryForge implements ScribeRegistry {
    private final String modId;
    private final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

    private final DeferredRegister<Item> itemRegistry;
    private final DeferredRegister<Block> blockRegistry;
    private final DeferredRegister<EntityType<?>> entityTypeRegistry;

    public static final Map<Supplier<? extends EntityType<? extends LivingEntity>>, Supplier<AttributeSupplier.Builder>> ATTRIBUTE_MAP = new ConcurrentHashMap<>();
    public static final Map<ModelLayerLocation, Supplier<LayerDefinition>> LAYER_DEFINITIONS = new ConcurrentHashMap<>();

    public ScribeRegistryForge(String modId){
        this.modId = modId;

        itemRegistry = DeferredRegister.create(ForgeRegistries.ITEMS, modId);
        itemRegistry.register(bus);

        blockRegistry = DeferredRegister.create(ForgeRegistries.BLOCKS, modId);
        blockRegistry.register(bus);

        entityTypeRegistry = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, modId);
        entityTypeRegistry.register(bus);

//        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
//        bus.addListener(this::addEntityAttributes);
    }

    public Supplier<Item> registerItem(String identifier, Supplier<Item> itemSupplier) {
        return itemRegistry.register(identifier, itemSupplier);
    }

    public Supplier<SpawnEggItem> registerSpawnEgg(String mobName, EntityType<Mob> entityType, int baseColor, int overlayColor) {
        return itemRegistry.register(mobName + "_spawn_egg", () -> new ForgeSpawnEggItem(() -> entityType, baseColor, overlayColor, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    }

    public Supplier<Block> registerBlock(String identifier, Supplier<Block> blockSupplier) {
        itemRegistry.register(identifier, () -> new BlockItem(blockSupplier.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
        return blockRegistry.register(identifier, blockSupplier);
    }

    public Supplier<Block> registerBlockWithItem(String identifier, Supplier<Block> blockSupplier, CreativeModeTab tab) {
        Supplier<Block> block = blockRegistry.register(identifier, blockSupplier);
        itemRegistry.register(identifier, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
        return block;
    }

    @Override
    public Supplier<EntityType> registerEntityType(String identifier, Supplier<EntityType> entityTypeSupplier) {
        return null;
    }

    @Override
    public void registerEntityAttributes(Supplier<EntityType> entityTypeSupplier, Supplier<AttributeSupplier.Builder> map) {

    }

    @Override
    public void registerLayerDefinition(ModelLayerLocation modelLayerLocation, Supplier<LayerDefinition> layerDefinitionSupplier) {

    }

    @Override
    public void registerEntityRenderer(Supplier<EntityType> entityTypeSupplier, EntityRendererProvider entityRendererProvider) {

    }
}
