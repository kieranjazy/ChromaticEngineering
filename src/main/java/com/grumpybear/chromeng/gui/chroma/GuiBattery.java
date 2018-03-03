package com.grumpybear.chromeng.gui.chroma;

import com.grumpybear.chromeng.block.tile.TileBattery;
import com.grumpybear.chromeng.chroma.IChromaStorage;
import com.grumpybear.chromeng.gui.container.chroma.ContainerBattery;
import net.minecraft.inventory.IInventory;

/**
 * Created by Kieran on 7/25/2017.
 */
public class GuiBattery extends GuiCEUser{

   private IInventory tileBattery;

   public GuiBattery(IInventory playerInv, TileBattery tile) {
      super(new ContainerBattery(playerInv, tile), tile, 173, 3);

      this.xSize = 176;
      this.ySize = 166;

      tileBattery = tile;
   }




}
