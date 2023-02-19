package com.teamabode.scribe.core;

import com.teamabode.scribe.core.api.animation.AnimationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Scribe {
    public static final String MOD_ID = "scribe";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final AnimationManager ANIMATION_MANAGER = new AnimationManager();

    public static void initialize() {

    }
}
