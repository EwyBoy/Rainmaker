package com.ewyboy.rainmaker;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class Config {

    public static int rainTime;
    public static boolean debugMode;

    public static void init (File file) {

        Configuration config = new Configuration(file);

        config.load();
            rainTime = config.getInt("Rain time", "Rainmaker", 300, 0, 999999999 ,"Sets how long the rain is going to last after block is activated (seconds)");
            debugMode = config.getBoolean("Debug Mode", "Rainmaker", false,"Set to true to enable debug mode with extra ingame and console info");
        config.save();
    }
}
