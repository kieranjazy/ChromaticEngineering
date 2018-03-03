package com.grumpybear.chromeng.block.tile;

import com.grumpybear.chromeng.ChromEng;
import com.grumpybear.chromeng.chroma.*;
import com.grumpybear.chromeng.item.ItemChargeMulti;
import com.grumpybear.chromeng.item.ItemChargeSingle;
import com.grumpybear.chromeng.lib.LibNumbers;
import com.grumpybear.chromeng.network.ChromEngPacketHandler;
import com.grumpybear.chromeng.network.MessageRequestCEFromServer;
import com.grumpybear.chromeng.util.EnumColourUtil;
import com.grumpybear.chromeng.util.ItemStackUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Kieran on 7/25/2017.
 */
public abstract class TileCEStorage extends TileInventory implements IChromaStorage{
   public ChromaStorage chromaStorage;

   @Override
   public ChromaStorage getChromaStorage() {
      return chromaStorage;
   }


   public void minusCE(ItemStack stack, int ce, EnumColour colour) {
      this.getChromaStorage().getColour(colour).minusCurrentCE(ce);
   }

   @Override
   public int getSizeInventory() {
      return 1;
   }

   public void readFromNBT(NBTTagCompound compound)
   {
      super.readFromNBT(compound);
      getChromaStorage().readFromNBT(compound);
   }

   public NBTTagCompound writeToNBT(NBTTagCompound compound)
   {
      super.writeToNBT(compound);

      getChromaStorage().writeToNBT(compound);

      return compound;
   }

   public void tryCharge() { //Try to charge CE Slot
      ItemStack stack = getStackInSlot(getSizeInventory() - 1);

      if (stack.getItem() instanceof ItemChargeSingle) {
         if (getChromaStorage().hasColour(((ItemChargeSingle) stack.getItem()).COLOUR_TYPE)) {
            if (getChromaStorage().getColour(((ItemChargeSingle) stack.getItem()).getColourType()).canMinusCE(LibNumbers.TRANSFER_RATE) && ItemStackUtil.getChromaUnit(stack).canAddCE(LibNumbers.TRANSFER_RATE)) {
               ChromaStorage.addCEMachinetoItem(getChromaStorage().getColour(((ItemChargeSingle) stack.getItem()).getColourType()), stack, LibNumbers.TRANSFER_RATE);
            }
         }
      } else if (stack.getItem() instanceof ItemChargeMulti) {
         ItemChargeMulti chargeItem = (ItemChargeMulti) stack.getItem();

         for (EnumColour colour : ItemStackUtil.getChromaStorage(stack).getActualColours()) {
            if (getChromaStorage().hasColour(colour)) {
               if (getChromaStorage().getColour(colour).canMinusCE(LibNumbers.TRANSFER_RATE) && ItemStackUtil.getChromaStorage(stack).getColour(colour).canAddCE(LibNumbers.TRANSFER_RATE)) {
                  /*
                  chargeItem.addCE(stack, LibNumbers.TRANSFER_RATE, colour);
                  this.getChromaStorage().getColour(colour).minusCurrentCE(LibNumbers.TRANSFER_RATE);
                  */

                  chargeItem.addCE(stack, LibNumbers.TRANSFER_RATE, colour);
                  minusCE(stack, LibNumbers.TRANSFER_RATE, colour);
               }
            }
         }

      }
   }

   public void requestCEFromServer(EnumColour colour) {
      ChromEngPacketHandler.INSTANCE.sendToServer(new MessageRequestCEFromServer(getPos(),colour));
   }


}
