package com.grumpybear.chromeng.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

/**
 * Created by Kieran on 7/17/2017.
 */
public class ContainerBase extends Container {

   public ContainerBase(IInventory playerInv) {
      //PlayerInv
      for (int y = 0; y < 3; ++y) {
         for (int x = 0; x < 9; ++x) {
            this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
         }
      }

      for (int x = 0; x < 9; ++x) {
         this.addSlotToContainer(new Slot(playerInv, x, 8 + x * 18, 142));
      }

   }

   public void proxyAddSlot(Slot slot) {
      this.addSlotToContainer(slot);
   }



   @Override
   public boolean canInteractWith(EntityPlayer playerIn) {
      return true;
   }



}
