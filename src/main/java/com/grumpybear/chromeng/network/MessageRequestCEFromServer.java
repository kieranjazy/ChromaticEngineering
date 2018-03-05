package com.grumpybear.chromeng.network;

import com.grumpybear.chromeng.block.tile.TileCEStorage;
import com.grumpybear.chromeng.chroma.EnumColour;
import com.grumpybear.chromeng.util.BlockPosUtil;
import com.grumpybear.chromeng.util.EnumColourUtil;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageRequestCEFromServer implements IMessage{

   private BlockPos pos;
   private EnumColour colour;

   public MessageRequestCEFromServer() {
   }

   public MessageRequestCEFromServer(BlockPos pos, EnumColour colour) {
      this.pos = pos;
      this.colour = colour;
   }

   @Override
   public void fromBytes(ByteBuf buf) {
      this.pos = BlockPosUtil.readFromByteBuf(buf);
      this.colour = EnumColourUtil.byteToColour(buf.readByte());
   }

   @Override
   public void toBytes(ByteBuf buf) {
      BlockPosUtil.writeToByteBuf(pos, buf);
      buf.writeByte(EnumColourUtil.colourToByte(colour));
   }

   public static class MessageRequestCEFromServerHandler implements IMessageHandler<MessageRequestCEFromServer, IMessage> {

      @Override
      public IMessage onMessage(MessageRequestCEFromServer message, MessageContext ctx) {
         ctx.getServerHandler().playerEntity.getServerWorld().addScheduledTask(() -> handle(message, ctx));

         return null;
      }

      public void handle(MessageRequestCEFromServer message, MessageContext ctx) {
         if (!ctx.getServerHandler().playerEntity.world.isBlockLoaded(message.pos))
            return;
         TileEntity tile = ctx.getServerHandler().playerEntity.world.getTileEntity(message.pos);
         if (!(tile instanceof TileCEStorage)) {
            return;
         }

         TileCEStorage ceStorage = (TileCEStorage) tile;

         int currentCE = ceStorage.getChromaStorage().getColour(message.colour).getCurrentCE();

         ChromEngPacketHandler.INSTANCE.sendTo(new MessageCEFromServer(message.pos, currentCE, message.colour), ctx.getServerHandler().playerEntity);
      }
   }
}
