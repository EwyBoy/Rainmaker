package com.ewyboy.rainmaker.Proxies;

import com.ewyboy.rainmaker.RainMakerRender;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
    
    public int rainmakerRenderID;

    //Registering the rendering of the block to the clientsided proxy. Since rendering is done only on the client.
    public void initRenders() {
        RainMakerRender rainMakerRender = new RainMakerRender();
        RenderingRegistry.registerBlockHandler(rainMakerRender);
        rainmakerRenderID = rainMakerRender.getRenderId();
    }
}
