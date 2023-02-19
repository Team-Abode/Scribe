package com.teamabode.scribe.core.forge;

import net.minecraftforge.fml.common.Mod;
import com.teamabode.scribe.core.Scribe;

@Mod(Scribe.MOD_ID)
public class ScribeForge {
    public ScribeForge() {
        Scribe.initialize();
    }
}
