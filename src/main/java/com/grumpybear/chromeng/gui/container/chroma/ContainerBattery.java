package com.grumpybear.chromeng.gui.container.chroma;

import com.grumpybear.chromeng.block.tile.TileBattery;
import com.grumpybear.chromeng.lib.LibTextures;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by Kieran on 7/25/2017.
 */
public class ContainerBattery extends ContainerChroma{

   TileBattery myTile;

   public ContainerBattery(IInventory playerInv, TileEntity tile) {
      super(playerInv, tile, LibTextures.SLOT_X_POS_EXTRACTOR, LibTextures.SLOT_Y_POS_EXTRACTOR);

      myTile = (TileBattery) tile;
   }


}
