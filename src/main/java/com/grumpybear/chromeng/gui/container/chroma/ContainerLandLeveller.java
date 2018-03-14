package com.grumpybear.chromeng.gui.container.chroma;

import com.grumpybear.chromeng.block.tile.TileLandLeveller;
import com.grumpybear.chromeng.gui.slot.SlotFiltered;
import com.grumpybear.chromeng.lib.LibTextures;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerLandLeveller extends ContainerChroma{

   private TileLandLeveller tile;

   public ContainerLandLeveller(IInventory playerInv, TileEntity chromaHandler) {
      super(playerInv, chromaHandler, LibTextures.SLOT_X_POS_EXTRACTOR, LibTextures.SLOT_Y_POS_EXTRACTOR);

      tile = (TileLandLeveller) chromaHandler;

   }

   @Override
   public void addListener(IContainerListener listener)
   {
      super.addListener(listener);
      listener.sendAllWindowProperties(this, this.tile);
   }

   @SideOnly(Side.CLIENT)
   public void updateProgressBar(int id, int data)
   {
      super.updateProgressBar(id, data);
      this.tile.setField(id, data);
   }
}
