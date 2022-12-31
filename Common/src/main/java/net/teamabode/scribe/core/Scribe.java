package net.teamabode.scribe.core;

import net.teamabode.scribe.core.resource.ScribeResourceListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Scribe {
    public static final String MOD_ID = "scribe";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static ScribeResourceListener resourceListener;

    public static void initialize() {
    }
}
