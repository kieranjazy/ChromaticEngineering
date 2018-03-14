package com.grumpybear.chromeng.block.tile;

import com.grumpybear.chromeng.ChromEng;
import com.grumpybear.chromeng.chroma.*;
import com.grumpybear.chromeng.item.ItemChargeMulti;
import com.grumpybear.chromeng.item.ItemChargeSingle;
import com.grumpybear.chromeng.lib.LibNumbers;
import com.grumpybear.chromeng.lib.LibTextures;
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
   public EnergyFlow flowDirection;


   public TileCEStorage() {
      this.flowDirection = EnergyFlow.MachineToSlot;
   }

   @Override
   public ChromaStorage getChromaStorage() {
      return chromaStorage;
   }

   public EnergyFlow getFlowDirection() {
      return flowDirection;
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

   @Override
   public void update() {
      super.update();

      if (this.getStackInSlot(this.getSizeInventory() - 1) != ItemStack.EMPTY)
         tryCharge();
   }

   public void tryCharge() { //Try to charge CE Slot
      ItemStack stack = getStackInSlot(getSizeInventory() - 1);

         if (flowDirection == EnergyFlow.MachineToSlot) {
            if (stack.getItem() instanceof ItemChargeSingle) {
               if (chromaStorage.hasColour(((ItemChargeSingle) stack.getItem()).COLOUR_TYPE)) {
                  if (chromaStorage.getColour(((ItemChargeSingle) stack.getItem()).getColourType()).canMinusCE(LibNumbers.TRANSFER_RATE) && ItemStackUtil.getChromaUnit(stack).canAddCE(LibNumbers.TRANSFER_RATE)) {
                     ChromaStorage.addCEMachinetoItem(chromaStorage.getColour(((ItemChargeSingle) stack.getItem()).getColourType()), stack, LibNumbers.TRANSFER_RATE);
                  }
               }
            } else if (stack.getItem() instanceof ItemChargeMulti) {
               ItemChargeMulti chargeItem = (ItemChargeMulti) stack.getItem();

               for (EnumColour colour : ItemStackUtil.getChromaStorage(stack).getActualColours()) {
                  if (chromaStorage.hasColour(colour)) {
                     if (chromaStorage.getColour(colour).canMinusCE(LibNumbers.TRANSFER_RATE) && ItemStackUtil.getChromaStorage(stack).getColour(colour).canAddCE(LibNumbers.TRANSFER_RATE)) {
                  /*
                  chargeItem.addCE(stack, LibNumbers.TRANSFER_RATE, colour);
                  this.getChromaStorage().getColour(colour).minusCurrentCE(LibNumbers.TRANSFER_RATE);
                  */

                        ChromaStorage.addCEMachinetoItem(chromaStorage.getColour(colour), stack, LibNumbers.TRANSFER_RATE);
                     }
                  }
               }

            }
         } else {
            if (stack.getItem() instanceof ItemChargeSingle) {
               if (chromaStorage.hasColour(((ItemChargeSingle) stack.getItem()).COLOUR_TYPE)) {
                  if (chromaStorage.getColour(((ItemChargeSingle) stack.getItem()).getColourType()).canAddCE(LibNumbers.TRANSFER_RATE) && ItemStackUtil.getChromaUnit(stack).canMinusCE(LibNumbers.TRANSFER_RATE)) {
                     ChromaStorage.addCEItemtoMachine(chromaStorage.getColour(((ItemChargeSingle) stack.getItem()).getColourType()), stack, LibNumbers.TRANSFER_RATE);
                  }
               }
            } else if (stack.getItem() instanceof ItemChargeMulti) {
               ItemChargeMulti chargeItem = (ItemChargeMulti) stack.getItem();

               for (EnumColour colour : ItemStackUtil.getChromaStorage(stack).getActualColours()) {
                  if (chromaStorage.hasColour(colour)) {
                     if (chromaStorage.getColour(colour).canAddCE(LibNumbers.TRANSFER_RATE) && ItemStackUtil.getChromaStorage(stack).getColour(colour).canMinusCE(LibNumbers.TRANSFER_RATE)) {
                  /*
                  chargeItem.addCE(stack, LibNumbers.TRANSFER_RATE, colour);
                  this.getChromaStorage().getColour(colour).minusCurrentCE(LibNumbers.TRANSFER_RATE);
                  */

                        ChromaStorage.addCEItemtoMachine(chromaStorage.getColour(colour), stack, LibNumbers.TRANSFER_RATE);
                     }
                  }
               }

            }
      }
   }

   public void requestCEFromServer(EnumColour colour) {
      ChromEngPacketHandler.INSTANCE.sendToServer(new MessageRequestCEFromServer(getPos(),colour));
   }

   public enum EnergyFlow {
      SlotToMachine,
      MachineToSlot
   }

   public void switchFlowDirection() {
      this.flowDirection = flowDirection == EnergyFlow.MachineToSlot ? EnergyFlow.SlotToMachine : EnergyFlow.MachineToSlot;
   }


}
