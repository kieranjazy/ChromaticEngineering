package com.grumpybear.chromeng.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import scala.util.control.Exception;

public class MessageGivePlayerItem implements IMessage {

   private ItemStack stack;

   public MessageGivePlayerItem() {
   }

   public MessageGivePlayerItem(ItemStack stack) {
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

   public static class MessageGivePlayerItemHandler implements IMessageHandler<MessageGivePlayerItem, IMessage> {


      @Override
      public IMessage onMessage(MessageGivePlayerItem message, MessageContext ctx) {
         ctx.getServerHandler().playerEntity.getServerWorld().addScheduledTask(() -> {
            ctx.getServerHandler().playerEntity.inventory.addItemStackToInventory(message.stack);
         });


         return null;
      }
   }
}
