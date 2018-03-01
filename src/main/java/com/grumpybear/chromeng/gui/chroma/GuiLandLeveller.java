package com.grumpybear.chromeng.gui.chroma;

import com.grumpybear.chromeng.block.tile.TileExtractor;
import com.grumpybear.chromeng.block.tile.TileLandLeveller;
import com.grumpybear.chromeng.chroma.IChromaStorage;
import com.grumpybear.chromeng.gui.chroma.GuiCEUser;
import com.grumpybear.chromeng.gui.container.chroma.ContainerChroma;
import com.grumpybear.chromeng.gui.container.chroma.ContainerLandLeveller;
import net.minecraft.inventory.IInventory;

public class GuiLandLeveller extends GuiCEUser{

   private IInventory tileLandLeveller;

   public GuiLandLeveller(IInventory playerInv, TileLandLeveller tile) {
      super(new ContainerLandLeveller(playerInv, tile), tile, 173, 3);

      this.xSize = 176;
      this.ySize = 166;

      this.tileLandLeveller = tile;
   }



}
