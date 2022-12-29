package net.teamabode.scribe;

public class ExampleMod {
    public static final String MOD_ID = "scribe";
    
    public static void init() {
        System.out.println(ExampleExpectPlatform.getConfigDirectory().toAbsolutePath().normalize().toString());
    }
}
