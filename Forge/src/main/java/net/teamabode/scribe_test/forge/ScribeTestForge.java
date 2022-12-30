package net.teamabode.scribe_test.forge;

import net.minecraftforge.fml.common.Mod;
import net.teamabode.scribe_test.ScribeTest;

@Mod(ScribeTest.MOD_ID)
public class ScribeTestForge {
    public ScribeTestForge() {
        ScribeTest.initialize();
    }
}
