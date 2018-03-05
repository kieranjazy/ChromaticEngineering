package com.grumpybear.chromeng.network;

import com.grumpybear.chromeng.block.tile.TileCEStorage;
import com.grumpybear.chromeng.util.BlockPosUtil;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageSwitchCEFlow implements IMessage {

   private BlockPos pos;

   public MessageSwitchCEFlow() {
   }

   public MessageSwitchCEFlow(BlockPos pos) {
      this.pos = pos;
   }

   @Override
   public void fromBytes(ByteBuf buf) {
      this.pos = BlockPosUtil.readFromByteBuf(buf);
   }

   @Override
   public void toBytes(ByteBuf buf) {
      BlockPosUtil.writeToByteBuf(pos, buf);
   }


   public static class MessageSwitchCEFlowHandler implements IMessageHandler<MessageSwitchCEFlow, IMessage> {

      @Override
      public IMessage onMessage(MessageSwitchCEFlow message, MessageContext ctx) {
         ctx.getServerHandler().playerEntity.getServerWorld().addScheduledTask(() -> {
            World world = ctx.getServerHandler().playerEntity.world;
            if (world.getTileEntity(message.pos) instanceof TileCEStorage)
               ((TileCEStorage) world.getTileEntity(message.pos)).switchFlowDirection();
         });

         return null;
      }
   }
}
