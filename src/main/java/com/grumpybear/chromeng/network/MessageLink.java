package com.grumpybear.chromeng.network;

import com.grumpybear.chromeng.block.tile.TileBonfire;
import com.grumpybear.chromeng.util.BlockPosUtil;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by Kieran on 7/5/2017.
 */
public class MessageLink implements IMessage {

   private BlockPos pos;
   private String text;

   public MessageLink(){}

   public MessageLink(BlockPos pos, String text) {
      this.pos = pos;
      this.text = text;
   }

   @Override
   public void fromBytes(ByteBuf buf) {
      this.pos = BlockPosUtil.readFromByteBuf(buf);
      this.text = ByteBufUtils.readUTF8String(buf);
   }

   @Override
   public void toBytes(ByteBuf buf) {
      BlockPosUtil.writeToByteBuf(pos, buf);
      ByteBufUtils.writeUTF8String(buf, this.text);
   }

   public static class MessageLinkHandler implements IMessageHandler<MessageLink, IMessage> {


      @Override
      public IMessage onMessage(MessageLink message, MessageContext ctx) {
         World world = ctx.getServerHandler().playerEntity.world;
         if (world.getTileEntity(message.pos) instanceof TileBonfire) {
            TileBonfire bonfire = (TileBonfire) world.getTileEntity(message.pos);
            bonfire.linkDarksign(message.text);
         }

         return null;
      }
   }
}
