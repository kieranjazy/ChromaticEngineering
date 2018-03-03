package com.grumpybear.chromeng.network;

import com.grumpybear.chromeng.block.tile.TileBonfire;
import com.grumpybear.chromeng.gui.element.IconButton;
import com.grumpybear.chromeng.util.BlockPosUtil;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by Kieran on 7/20/2017.
 */
public class MessageAddLordvessel implements IMessage {
   private BlockPos pos;
   private String text;
   private IconButton.IconReturn icon;

   public MessageAddLordvessel(){}

   public MessageAddLordvessel(BlockPos pos, String text, IconButton.IconReturn icon) {
      this.pos = pos;
      this.text = text;
      this.icon = icon;
   }

   @Override
   public void fromBytes(ByteBuf buf) {
      this.pos = BlockPosUtil.readFromByteBuf(buf);
      this.text = ByteBufUtils.readUTF8String(buf);
      this.icon = new IconButton.IconReturn(buf.readInt(), buf.readInt());
   }

   @Override
   public void toBytes(ByteBuf buf) {
      BlockPosUtil.writeToByteBuf(pos, buf);
      ByteBufUtils.writeUTF8String(buf, this.text);
      buf.writeInt(icon.getIconX());
      buf.writeInt(icon.getIconY());
   }

   public static class MessageAddLordvesselHandler implements IMessageHandler<MessageAddLordvessel, IMessage> {

      @Override
      public IMessage onMessage(MessageAddLordvessel message, MessageContext ctx) {

         World world = ctx.getServerHandler().playerEntity.world;
         ctx.getServerHandler().playerEntity.getServerWorld().addScheduledTask(() -> {
            if (world.getTileEntity(message.pos) instanceof TileBonfire) {
               TileBonfire bonfire = (TileBonfire) world.getTileEntity(message.pos);
               bonfire.linkLordvessel(message.text, message.icon);
            }
         });

         return null;
      }
   }
}
