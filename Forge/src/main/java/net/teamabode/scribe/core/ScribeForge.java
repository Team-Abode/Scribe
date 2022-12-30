package net.teamabode.scribe.core;

import net.teamabode.scribe.core.Scribe;
import net.minecraftforge.fml.common.Mod;

@Mod(Scribe.MOD_ID)
public class ScribeForge {
    public ScribeForge() {
        Scribe.init();
    }
}
