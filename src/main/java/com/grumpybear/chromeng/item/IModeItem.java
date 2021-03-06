package com.grumpybear.chromeng.item;

import com.grumpybear.chromeng.network.ChromEngPacketHandler;
import com.grumpybear.chromeng.network.MessageSetMode;
import com.grumpybear.chromeng.network.MessageSwitchMode;
import com.grumpybear.chromeng.util.ItemStackUtil;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import org.apache.commons.lang3.ArrayUtils;

import javax.annotation.Nullable;

/**
 * Created by Kieran on 7/12/2017.
 */
public interface IModeItem {

   default void setMode(ItemStack stack, int i) {
      //setModeClient(stack, i);
      ChromEngPacketHandler.INSTANCE.sendToServer(new MessageSetMode(stack, i));
   }

   default void setModeClient(ItemStack stack, int i) {
      ItemStackUtil.getNBT(stack).setInteger("Mode", i);
   }

   default int getMode(ItemStack stack) {
      return ItemStackUtil.getNBT(stack).getInteger("Mode");
   }

   default void switchMode(ItemStack stack) {
      switchModeClient(stack);
      ChromEngPacketHandler.INSTANCE.sendToServer(new MessageSwitchMode(stack));
   }

   default void switchModeClient(ItemStack stack) {
      int temp = (getMode(stack) + 1) <= getModeLimit() ? (getMode(stack) + 1) : 0;
      setModeClient(stack, temp);
   }

   String[] getModeNames();

   default String getCurrentModeString(ItemStack stack) {
      return getModeNames()[getMode(stack)];
   }

   default int getModeLimit() {
      return getModeNames().length - 1;
   }



}
