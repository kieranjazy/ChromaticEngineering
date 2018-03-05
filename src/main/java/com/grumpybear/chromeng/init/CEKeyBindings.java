package com.grumpybear.chromeng.init;

import com.grumpybear.chromeng.item.IModeItem;
import com.grumpybear.chromeng.item.ItemDesignator;
import com.grumpybear.chromeng.item.ItemExtensionConduit;
import com.grumpybear.chromeng.item.ItemVoidVacuum;
import com.grumpybear.chromeng.network.ChromEngPacketHandler;
import com.grumpybear.chromeng.network.MessageSetMode;
import com.grumpybear.chromeng.network.MessageSwitchMode;
import com.grumpybear.chromeng.util.BlockPosUtil;
import com.grumpybear.chromeng.util.ItemStackUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import static com.grumpybear.chromeng.util.BlockPosUtil.*;

/**
 * Created by Kieran on 7/12/2017.
 */

public class CEKeyBindings {
   public static KeyBinding key;

   public static void init() {
      key = new KeyBinding("Mode Change", Keyboard.KEY_B, "CE");
      ClientRegistry.registerKeyBinding(key);
   }

   @SubscribeEvent
   public void keyDown(TickEvent.ClientTickEvent e) {
      if (e.phase == TickEvent.Phase.END) {
         Minecraft mc = Minecraft.getMinecraft();

         if (mc.world != null) {
            ItemStack stack = mc.player.getHeldItemMainhand();
            if (key.isPressed())
               if (stack != ItemStack.EMPTY && stack.getItem() instanceof IModeItem) {
                  IModeItem item = (IModeItem) stack.getItem();
                  if (item instanceof ItemVoidVacuum) {
                     if (item.getMode(stack) != 0) {
                        int result = item.getMode(stack) == 1 ? 2 : 1;
                        item.setModeClient(stack, result);
                        ChromEngPacketHandler.INSTANCE.sendToServer(new MessageSetMode(stack, result));
                     }
                  } else if (item instanceof ItemDesignator) {
                     NBTTagCompound nbt = ItemStackUtil.getNBT(stack);
                     if (nbtHasPos(nbt, 0) && nbtHasPos(nbt, 1)) {
                        if (!arePosSame(getFromNBT(nbt, 0), getFromNBT(nbt, 1))) {

                        }
                     }

                  } else {
                     item.switchModeClient(stack);
                     ChromEngPacketHandler.INSTANCE.sendToServer(new MessageSwitchMode(stack));
                  }
               }

         }
      }
   }




}
