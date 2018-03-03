package com.grumpybear.chromeng.network;

import com.grumpybear.chromeng.item.ItemChargeSingle;
import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;


/**
 * Created by Kieran on 7/24/2017.
 */
public class MessageCEChange implements IMessage {

   public enum OPS {
      ADD,
      MINUS,
      CHANGE
   }

   public OPS op;
   public int i;

   public MessageCEChange() {}

   public MessageCEChange(OPS op, int i) {
      this.op = op;
      this.i = i;
   }

   @Override
   public void fromBytes(ByteBuf buf) {
      switch(buf.readInt()) {
         case 0:
            this.op = OPS.ADD;
            break;
         case 1:
            this.op = OPS.MINUS;
            break;
         case 2:
            this.op = OPS.CHANGE;
            break;
      }

      this.i = buf.readInt();
   }

   @Override
   public void toBytes(ByteBuf buf) {
      switch(op) {
         case ADD:
            buf.writeInt(0);
            break;
         case MINUS:
            buf.writeInt(1);
            break;
         case CHANGE:
            buf.writeInt(2);
            break;
      }

      buf.writeInt(i);
   }

   public static class MessageCEChangeHandler implements IMessageHandler<MessageCEChange, IMessage> {

      @Override
      public IMessage onMessage(MessageCEChange message, MessageContext ctx) {
         ItemStack stack = ctx.getServerHandler().playerEntity.getHeldItemMainhand();
         if (stack.getItem() instanceof ItemChargeSingle) {
            if (message.op == OPS.ADD) {
               ctx.getServerHandler().playerEntity.getServerWorld().addScheduledTask(() -> ((ItemChargeSingle) stack.getItem()).addCE(stack, message.i));
            } else if (message.op == OPS.MINUS) {
               ctx.getServerHandler().playerEntity.getServerWorld().addScheduledTask(() -> ((ItemChargeSingle) stack.getItem()).minusCE(stack, message.i));
            } else {
               ctx.getServerHandler().playerEntity.getServerWorld().addScheduledTask(() -> ((ItemChargeSingle) stack.getItem()).setCE(stack, message.i));
            }
         }


         return null;
      }
   }
}
