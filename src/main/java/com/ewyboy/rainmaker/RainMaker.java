package com.ewyboy.rainmaker;

import com.ewyboy.rainmaker.Proxies.ClientProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

@Mod(modid = "rainmaker", version = "1.4")
public class RainMaker {

    @SidedProxy(modId = "rainmaker", clientSide = "com.ewyboy.rainmaker.Proxies.ClientProxy", serverSide = "com.ewyboy.rainmaker.Proxies.ClientProxy")
    public static ClientProxy proxy;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {}

    public static Block RainMakerBlock;

    @Mod.EventHandler
    public void PreInit (FMLPreInitializationEvent event) {
        Config.init(event.getSuggestedConfigurationFile());
        RainMakerBlock = new RainMakerBlock().setBlockName("RainMaker");
        GameRegistry.registerBlock(RainMakerBlock, "RainMakerMachine");
        proxy.initRenders();
    }
}