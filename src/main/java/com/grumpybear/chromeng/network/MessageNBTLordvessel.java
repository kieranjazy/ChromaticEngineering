package com.grumpybear.chromeng.network;

import com.grumpybear.chromeng.item.ItemLordvessel;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by Kieran on 7/23/2017.
 */
public class MessageNBTLordvessel implements IMessage {

   public int startIndex;
   public int maxIndex;

   public MessageNBTLordvessel() {
   }

   public MessageNBTLordvessel(int startIndex, int maxIndex) {
      this.startIndex = startIndex;
      this.maxIndex = maxIndex;
   }



   @Override
   public void fromBytes(ByteBuf buf) {
      this.startIndex = buf.readInt();
      this.maxIndex = buf.readInt();
   }

   @Override
   public void toBytes(ByteBuf buf) {
      buf.writeInt(startIndex);
      buf.writeInt(maxIndex);
   }

   public static class MessageNBTLordvesselHandler implements IMessageHandler<MessageNBTLordvessel, IMessage> {

      @Override
      public IMessage onMessage(MessageNBTLordvessel message, MessageContext ctx) {
         ItemLordvessel item = (ItemLordvessel) ctx.getServerHandler().playerEntity.getHeldItemMainhand().getItem();
         item.shiftNBTData(message.startIndex, message.maxIndex, ctx.getServerHandler().playerEntity.getHeldItemMainhand());

         return null;
      }
   }
}
