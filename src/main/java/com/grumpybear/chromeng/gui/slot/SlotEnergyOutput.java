package com.grumpybear.chromeng.gui.slot;

import com.grumpybear.chromeng.chroma.EnumColour;
import com.grumpybear.chromeng.item.ItemChargeMulti;
import com.grumpybear.chromeng.item.ItemChargeSingle;
import com.grumpybear.chromeng.lib.LibNumbers;
import com.grumpybear.chromeng.util.ItemStackUtil;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by Kieran on 6/26/2017.
 */
public class SlotEnergyOutput extends Slot{

   public SlotEnergyOutput(IInventory inventoryIn, int index, int xPosition, int yPosition) {
      super(inventoryIn, index, xPosition, yPosition);
   }

   public void setPos(int x, int y) {
      this.xPos = x;
      this.yPos = y;
   }




   @Override
   public boolean isItemValid(ItemStack stack) {
      if (stack.getItem() instanceof ItemChargeSingle || stack.getItem() instanceof ItemChargeMulti)
         return true;

      return false;
   }
}
