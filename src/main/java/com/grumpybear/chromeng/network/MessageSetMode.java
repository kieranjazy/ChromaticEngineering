package com.grumpybear.chromeng.network;

import com.grumpybear.chromeng.ChromEng;
import com.grumpybear.chromeng.item.IModeItem;
import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageSetMode  implements IMessage{

   private ItemStack stack;
   private int modeNo;

   public MessageSetMode() {
   }

   public MessageSetMode(ItemStack stack, int modeNo) {
      this.stack = stack;
      this.modeNo = modeNo;
   }

   @Override
   public void fromBytes(ByteBuf buf) {
      this.stack = ByteBufUtils.readItemStack(buf);
      this.modeNo = buf.readInt();
   }

   @Override
   public void toBytes(ByteBuf buf) {
      ByteBufUtils.writeItemStack(buf, this.stack);
      buf.writeInt(modeNo);
   }

   public static class MessageSetModeHandler implements IMessageHandler<MessageSetMode, IMessage> {

      @Override
      public IMessage onMessage(MessageSetMode message, MessageContext ctx) {
         ctx.getServerHandler().playerEntity.getServerWorld().addScheduledTask(() ->  {
            ((IModeItem) message.stack.getItem()).setModeClient(ctx.getServerHandler().playerEntity.getHeldItemMainhand(), message.modeNo);
         });

         return null;
      }
   }
}
