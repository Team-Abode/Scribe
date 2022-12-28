package com.teamabode.scribe;

import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.Supplier;

public class Scribe {
    public static final String MOD_ID = "scribe";
    public static final String MOD_NAME = "Scribe";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public static final Supplier<Item> TEST_ITEM = () -> new Item(new Item.Properties());

    public static void init() {
        Scribe.LOGGER.info("Loading up Scribe!");
    }

    public static void onItemTooltip(ItemStack stack, TooltipFlag context, List<Component> tooltip) {
        if (!stack.isEmpty()) {
            final FoodProperties food = stack.getItem().getFoodProperties();

            if (food != null) {
                tooltip.add(Component.literal("Nutrition: " + food.getNutrition()));
                tooltip.add(Component.literal("Saturation: " + food.getSaturationModifier()));
            }
        }
    }
}
