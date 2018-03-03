package com.grumpybear.chromeng.handler;

import com.grumpybear.chromeng.init.CEKeyBindings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;

public class ClientTickHandler {

   public void clientTickEnd(TickEvent.ClientTickEvent event) {
      if (event.phase == TickEvent.Phase.END) {
         GuiScreen gui = Minecraft.getMinecraft().currentScreen;
         if (gui == null && CEKeyBindings.key.isPressed()) {
            System.out.println("fuck you");
         }
      }
   }


}
