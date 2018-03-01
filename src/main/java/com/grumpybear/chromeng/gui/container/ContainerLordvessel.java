package com.grumpybear.chromeng.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;

/**
 * Created by Kieran on 6/29/2017.
 */
public class ContainerLordvessel extends ContainerBase {


   public ContainerLordvessel(IInventory playerInv) {
      super(playerInv);
   }



   @Override
   public boolean canInteractWith(EntityPlayer playerIn) {
      return true;
   }
}
