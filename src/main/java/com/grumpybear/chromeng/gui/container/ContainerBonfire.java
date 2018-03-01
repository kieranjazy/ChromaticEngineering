package com.grumpybear.chromeng.gui.container;

import com.grumpybear.chromeng.block.tile.TileBonfire;
import com.grumpybear.chromeng.block.tile.TileCE;
import com.grumpybear.chromeng.gui.slot.ISlotFilter;
import com.grumpybear.chromeng.gui.slot.SlotFiltered;
import com.grumpybear.chromeng.init.ModItems;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.SlotFurnaceOutput;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by Kieran on 7/2/2017.
 */
public class ContainerBonfire extends ContainerCE implements ISlotFilter {

   public TileBonfire bonfire;

   public ContainerBonfire(IInventory playerInv, TileEntity tile) {
      super(playerInv, (TileCE) tile);

      bonfire = (TileBonfire) tile;

      addSlotToContainer(new SlotFiltered(bonfire, 0, 8, 64, this));
      addSlotToContainer(new SlotFurnaceOutput(((InventoryPlayer) playerInv).player, bonfire, 1, 53, 64));
   }

   @Override
   public boolean isItemValid(ItemStack stack) {
      if (stack.getItem() == ModItems.darksign || stack.getItem() == ModItems.lordvessel)
         return true;

      return false;
   }

}
