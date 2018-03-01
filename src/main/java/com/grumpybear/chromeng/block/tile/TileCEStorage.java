package com.grumpybear.chromeng.block.tile;

import com.grumpybear.chromeng.chroma.*;
import com.grumpybear.chromeng.item.ItemChargeMulti;
import com.grumpybear.chromeng.item.ItemChargeSingle;
import com.grumpybear.chromeng.lib.LibNumbers;
import com.grumpybear.chromeng.util.EnumColourUtil;
import com.grumpybear.chromeng.util.ItemStackUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Kieran on 7/25/2017.
 */
public abstract class TileCEStorage extends TileInventory implements IChromaStorage{
   public final List<EnumColour> COLOURS = new ArrayList<>(Arrays.asList(EnumColour.values()));
   public ChromaStorage chromaStorage;

   @Override
   public ChromaStorage getChromaStorage() {
      return chromaStorage;
   }


   public void addCE(ItemStack stack, int ce) { //Adds CE to a chargeable item and takes that CE from this chroma storage
      if (stack.getItem() instanceof ItemChargeSingle) {
         ItemChargeSingle item = (ItemChargeSingle) stack.getItem();

         if (this.getChromaStorage().getColour(item.getColourType()).getCurrentCE() - LibNumbers.TRANSFER_RATE >= 0 && item.getChromaUnit(stack).getCurrentCE() + LibNumbers.TRANSFER_RATE <= item.getChromaUnit(stack).getMaxCE()) {
            item.addCE(stack, LibNumbers.TRANSFER_RATE);
            minusCE(stack, LibNumbers.TRANSFER_RATE, item.getColourType());
         }
      }

      if (stack.getItem() instanceof ItemChargeMulti) {
         ItemChargeMulti item = (ItemChargeMulti) stack.getItem();
         for (EnumColour colour : COLOURS) {
            if (this.getChromaStorage().getColour(colour).getCurrentCE() - LibNumbers.TRANSFER_RATE >= 0 && item.getChromaStorage(stack).getColour(colour).getCurrentCE() + LibNumbers.TRANSFER_RATE <= item.getChromaStorage(stack).getMaxCE()) {
               item.addCE(stack, LibNumbers.TRANSFER_RATE, colour);
               minusCE(stack, ce, colour);
            }
         }
      }
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

   public int getField(int id)
   {
      /*
      return this.chromaStorage.getColour(EnumColourUtil.RGBtoColour(id)).getCurrentCE();
      */


      switch (id)
      {
         case EnumColourUtil.RED_RGB:
            return this.chromaStorage.getColour(EnumColour.RED).getCurrentCE();
         case EnumColourUtil.ORANGE_RGB:
            return this.chromaStorage.getColour(EnumColour.ORANGE).getCurrentCE();
         case EnumColourUtil.YELLOW_RGB:
            return this.chromaStorage.getColour(EnumColour.YELLOW).getCurrentCE();
         case EnumColourUtil.GREEN_RGB:
            return this.chromaStorage.getColour(EnumColour.GREEN).getCurrentCE();
         case EnumColourUtil.BLUE_RGB:
            return this.chromaStorage.getColour(EnumColour.BLUE).getCurrentCE();
         case EnumColourUtil.INDIGO_RGB:
            return this.chromaStorage.getColour(EnumColour.INDIGO).getCurrentCE();
         case EnumColourUtil.VIOLET_RGB:
            return this.chromaStorage.getColour(EnumColour.VIOLET).getCurrentCE();
         default:
            return 0;
      }

   }

   @Override
   public void setField(int id, int value) {

      /*
      this.chromaStorage.getColour(EnumColourUtil.RGBtoColour(id)).setCurrentCE(value);
      */


      switch (id)
      {
         case EnumColourUtil.RED_RGB:
            this.chromaStorage.getColour(EnumColour.RED).setCurrentCE(value);
            break;
         case EnumColourUtil.ORANGE_RGB:
            this.chromaStorage.getColour(EnumColour.ORANGE).setCurrentCE(value);
            break;
         case EnumColourUtil.YELLOW_RGB:
            this.chromaStorage.getColour(EnumColour.YELLOW).setCurrentCE(value);
            break;
         case EnumColourUtil.GREEN_RGB:
            this.chromaStorage.getColour(EnumColour.GREEN).setCurrentCE(value);
            break;
         case EnumColourUtil.BLUE_RGB:
            this.chromaStorage.getColour(EnumColour.BLUE).setCurrentCE(value);
            break;
         case EnumColourUtil.INDIGO_RGB:
            this.chromaStorage.getColour(EnumColour.INDIGO).setCurrentCE(value);
            break;
         case EnumColourUtil.VIOLET_RGB:
            this.chromaStorage.getColour(EnumColour.VIOLET).setCurrentCE(value);
            break;
      }

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
                  ChromaStorage.addCEMachinetoItem(getChromaStorage().getColour(colour), stack, LibNumbers.TRANSFER_RATE);
                  System.out.println("fuck off");
               }
            }
         }
      }
   }
}
