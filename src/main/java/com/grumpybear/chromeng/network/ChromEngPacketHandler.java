package com.grumpybear.chromeng.network;

import com.grumpybear.chromeng.lib.LibMain;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by Kieran on 7/5/2017.
 */
public class ChromEngPacketHandler {
   public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(LibMain.MOD_ID);

   public static void register() {
      INSTANCE.registerMessage(MessageLink.MessageLinkHandler.class, MessageLink.class, 0, Side.SERVER);
      INSTANCE.registerMessage(MessageSwitchMode.MessageSwitchModeHandler.class, MessageSwitchMode.class, 1, Side.SERVER);
      INSTANCE.registerMessage(MessageAddLordvessel.MessageAddLordvesselHandler.class, MessageAddLordvessel.class, 2, Side.SERVER);
      INSTANCE.registerMessage(MessageNBTLordvessel.MessageNBTLordvesselHandler.class, MessageNBTLordvessel.class, 3, Side.SERVER);
      INSTANCE.registerMessage(MessageTeleport.MessageTeleportHandler.class, MessageTeleport.class, 4, Side.SERVER);
      INSTANCE.registerMessage(MessageCEChange.MessageCEChangeHandler.class, MessageCEChange.class, 5, Side.SERVER);
      INSTANCE.registerMessage(MessageSetMode.MessageSetModeHandler.class, MessageSetMode.class, 6, Side.SERVER);
      INSTANCE.registerMessage(MessageRequestCEFromServer.MessageRequestCEFromServerHandler.class, MessageRequestCEFromServer.class, 7, Side.SERVER);

      INSTANCE.registerMessage(MessageCEFromServer.MessageCEFromServerHandler.class, MessageCEFromServer.class, 8, Side.CLIENT);

      INSTANCE.registerMessage(MessageSwitchCEFlow.MessageSwitchCEFlowHandler.class, MessageSwitchCEFlow.class, 9, Side.SERVER);
      INSTANCE.registerMessage(MessageGivePlayerItem.MessageGivePlayerItemHandler.class, MessageGivePlayerItem.class, 10, Side.SERVER);

   }
}
