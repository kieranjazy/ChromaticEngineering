package com.grumpybear.chromeng.network;

import com.grumpybear.chromeng.block.tile.TileCEStorage;
import com.grumpybear.chromeng.chroma.EnumColour;
import com.grumpybear.chromeng.util.BlockPosUtil;
import com.grumpybear.chromeng.util.EnumColourUtil;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageCEFromServer implements IMessage{

   private BlockPos pos;
   private int currentCE;
   private EnumColour colour;

   public MessageCEFromServer() {
   }

   public MessageCEFromServer(BlockPos pos, int currentCE, EnumColour colour) {
      this.pos = pos;
      this.currentCE = currentCE;
      this.colour = colour;
   }

   @Override
   public void fromBytes(ByteBuf buf) {
      this.pos = BlockPosUtil.readFromByteBuf(buf);
      this.currentCE = buf.readInt();
      this.colour = EnumColourUtil.byteToColour(buf.readByte());
   }

   @Override
   public void toBytes(ByteBuf buf) {
      BlockPosUtil.writeToByteBuf(pos, buf);
      buf.writeInt(currentCE);
      buf.writeByte(EnumColourUtil.colourToByte(colour));
   }

   public static class MessageCEFromServerHandler implements IMessageHandler<MessageCEFromServer, IMessage> {

      @Override
      public IMessage onMessage(MessageCEFromServer message, MessageContext ctx) {
         Minecraft.getMinecraft().addScheduledTask(() -> handle(message, ctx));

         return null;
      }

      public void handle(MessageCEFromServer message, MessageContext ctx) {
         TileEntity tile = Minecraft.getMinecraft().world.getTileEntity(message.pos);
         if (!(tile instanceof TileCEStorage)) {
            return;
         }
         TileCEStorage ceStorage = (TileCEStorage) tile;

         ceStorage.getChromaStorage().getColour(message.colour).setCurrentCE(message.currentCE);
      }
   }
}
