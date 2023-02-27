package com.teamabode.scribe.core;

import com.teamabode.scribe.core.api.animation.AnimationManager;
import com.teamabode.scribe.core.api.config.Config;
import com.teamabode.scribe.core.api.registry.RegistryCreator;
import com.teamabode.scribe.core.api.registry.ScribeRegistry;
import com.teamabode.scribe.core.registry.ScribeFeatures;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Scribe {
    public static final String MOD_ID = "scribe";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final AnimationManager ANIMATION_MANAGER = new AnimationManager();
    public static final ScribeRegistry REGISTRY = RegistryCreator.getRegistry("scribe");
    public static Config config = Config.define(MOD_ID).property("test", "goodbye").load();

    public static void initialize() {
        ScribeFeatures.init();
    }
}
