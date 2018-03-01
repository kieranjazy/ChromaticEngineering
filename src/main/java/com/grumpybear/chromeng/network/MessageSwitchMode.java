package com.grumpybear.chromeng.network;

import com.grumpybear.chromeng.item.IModeItem;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Kieran on 7/12/2017.
 */
public class MessageSwitchMode implements IMessage{
   private ItemStack stack;

   public MessageSwitchMode(){}

   public MessageSwitchMode(ItemStack stack) {
      this.stack = stack;
   }


   @Override
   public void fromBytes(ByteBuf buf) {
      this.stack = ByteBufUtils.readItemStack(buf);
   }

   @Override
   public void toBytes(ByteBuf buf) {
      ByteBufUtils.writeItemStack(buf, this.stack);
   }

   public static class MessageSwitchModeHandler implements IMessageHandler<MessageSwitchMode, IMessage> {

      @Override
      public IMessage onMessage(MessageSwitchMode message, MessageContext ctx) {
         ((IModeItem) message.stack.getItem()).switchModeClient(message.stack);
         return null;
      }
   }
}
