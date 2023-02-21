package com.teamabode.scribe.core.registry;

import com.teamabode.scribe.common.feature.SequenceFeature;
import com.teamabode.scribe.common.feature.SequenceFeatureConfiguration;
import com.teamabode.scribe.core.Scribe;
import net.minecraft.world.level.levelgen.feature.Feature;

import java.util.function.Supplier;

public class ScribeFeatures {

    public static final Supplier<Feature<SequenceFeatureConfiguration>> SEQUENCE = Scribe.REGISTRY.registerFeature("sequence", SequenceFeature::new);

    public static void init() {

    }
}
