package com.grumpybear.chromeng.network;

import com.grumpybear.chromeng.util.BlockPosUtil;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by Kieran on 7/24/2017.
 */
public class MessageTeleport implements IMessage {

   public BlockPos pos;

   public MessageTeleport() {}

   public MessageTeleport(BlockPos pos) {
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

   public static class MessageTeleportHandler implements IMessageHandler<MessageTeleport, IMessage> {

      @Override
      public IMessage onMessage(MessageTeleport message, MessageContext ctx) {
         BlockPos pos = message.pos;
         EntityPlayer player = ctx.getServerHandler().playerEntity;
         ctx.getServerHandler().playerEntity.getServerWorld().addScheduledTask(() -> player.setPositionAndUpdate(pos.offset(EnumFacing.SOUTH).getX(), pos.offset(EnumFacing.SOUTH).getY(), pos.offset(EnumFacing.SOUTH).getZ() + 1));
         return null;
      }
   }
}
