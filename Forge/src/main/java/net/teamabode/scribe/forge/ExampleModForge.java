package net.teamabode.scribe.forge;

import net.teamabode.scribe.ExampleMod;
import net.minecraftforge.fml.common.Mod;

@Mod(ExampleMod.MOD_ID)
public class ExampleModForge {
    public ExampleModForge() {
        ExampleMod.init();
    }
}
