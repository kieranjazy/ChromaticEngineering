package com.grumpybear.chromeng.gui.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by Kieran on 7/9/2017.
 */
public class SlotFiltered extends Slot {

   private ISlotFilter filter;

   public SlotFiltered(IInventory inventoryIn, int index, int xPosition, int yPosition, ISlotFilter filter) {
      super(inventoryIn, index, xPosition, yPosition);
      this.filter = filter;

   }

   @Override
   public boolean isItemValid(ItemStack stack) {
      return filter.isItemValid(stack);
   }
}
