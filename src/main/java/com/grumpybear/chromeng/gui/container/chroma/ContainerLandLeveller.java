package com.grumpybear.chromeng.gui.container.chroma;

import com.grumpybear.chromeng.block.tile.TileLandLeveller;
import com.grumpybear.chromeng.lib.LibTextures;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;

public class ContainerLandLeveller extends ContainerChroma{

   TileLandLeveller tile;

   public ContainerLandLeveller(IInventory playerInv, TileEntity chromaHandler) {
      super(playerInv, chromaHandler, LibTextures.SLOT_X_POS_EXTRACTOR, LibTextures.SLOT_Y_POS_EXTRACTOR);

      tile = (TileLandLeveller) chromaHandler;
   }
}
