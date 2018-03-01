package com.grumpybear.chromeng.gui.element;

import com.grumpybear.chromeng.block.tile.TileCEStorage;
import com.grumpybear.chromeng.chroma.ChromaStorage;
import com.grumpybear.chromeng.chroma.EnumColour;
import com.grumpybear.chromeng.chroma.IChromaStorage;
import com.grumpybear.chromeng.gui.container.chroma.ContainerChroma;
import com.grumpybear.chromeng.gui.slot.SlotEnergyOutput;
import com.grumpybear.chromeng.item.ItemChargeMulti;
import com.grumpybear.chromeng.item.ItemChargeSingle;
import com.grumpybear.chromeng.lib.LibNumbers;
import com.grumpybear.chromeng.lib.LibTextures;
import com.grumpybear.chromeng.util.ItemStackUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.item.ItemStack;

public class CEItem extends Element{

   private ContainerChroma container;
   private SlotEnergyOutput ceSlot;


   public CEItem(int xPos, int yPos, Gui gui, ContainerChroma container) {
      super(LibTextures.CE_ITEM_SLOT_WH, LibTextures.CE_ITEM_SLOT_WH, xPos, yPos, gui);
      this.container = container;
      this.ceSlot = container.getCeSlot();
   }
 /*
   public void tryCharge(int i) { //Try to charge CE Storage item from machine
      ItemStack stack = ceSlot.getStack();

      if (stack.getItem() instanceof ItemChargeSingle) {
         if (container.getChromaStorage().hasColour(((ItemChargeSingle) stack.getItem()).COLOUR_TYPE)) {
            if (container.getChromaStorage().getColour(((ItemChargeSingle) stack.getItem()).getColourType()).canMinusCE(LibNumbers.TRANSFER_RATE) && ItemStackUtil.getChromaUnit(stack).canAddCE(LibNumbers.TRANSFER_RATE)) {
               ChromaStorage.addCEP1toP2(container.getChromaStorage().getColour(((ItemChargeSingle) stack.getItem()).getColourType()), ItemStackUtil.getChromaUnit(stack), LibNumbers.TRANSFER_RATE);
            }
         }
      } else if (stack.getItem() instanceof ItemChargeMulti) {
         ItemChargeMulti chargeItem = (ItemChargeMulti) stack.getItem();

         for (EnumColour colour : ItemStackUtil.getChromaStorage(stack).getActualColours()) {
            if (container.getChromaStorage().hasColour(colour)) {
               if (container.getChromaStorage().getColour(colour).canMinusCE(LibNumbers.TRANSFER_RATE) && ItemStackUtil.getChromaStorage(stack).getColour(colour).canAddCE(LibNumbers.TRANSFER_RATE)) {
                  ChromaStorage.addCEP1toP2(container.getChromaStorage().getColour(colour), ItemStackUtil.getChromaStorage(stack).getColour(colour), LibNumbers.TRANSFER_RATE);
               }
            }
         }
      }
   }
   */


   @Override
   public void drawBackground(int mouseX, int mouseY) {
      super.drawBackground(mouseX, mouseY);

      Minecraft.getMinecraft().getTextureManager().bindTexture(LibTextures.CE_ITEM_SLOT);
      drawRectangle(xPos, yPos, 0, 0, width, height);

   }


}
