package net.teamabode.scribe;

import net.teamabode.scribe.Scribe;
import net.minecraftforge.fml.common.Mod;

@Mod(Scribe.MOD_ID)
public class ScribeForge {
    public ScribeForge() {
        Scribe.initialize();
    }
}
