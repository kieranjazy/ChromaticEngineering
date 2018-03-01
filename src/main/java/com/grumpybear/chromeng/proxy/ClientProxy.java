package com.grumpybear.chromeng.proxy;

import com.grumpybear.chromeng.handler.RenderWorldLastEventHandler;
import com.grumpybear.chromeng.init.CEKeyBindings;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy{
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		CEKeyBindings.init();
	}
	
	@Override
	public void init(FMLInitializationEvent e) {
	}
	
	@SubscribeEvent
	public static void renderWorldLastEvent(RenderWorldLastEvent event) {
		RenderWorldLastEventHandler.tick(event);
	}
}
