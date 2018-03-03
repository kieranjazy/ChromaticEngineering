package com.grumpybear.chromeng.gui.chroma;

import com.grumpybear.chromeng.block.tile.TileExtractor;
import com.grumpybear.chromeng.block.tile.TileLandLeveller;
import com.grumpybear.chromeng.chroma.IChromaStorage;
import com.grumpybear.chromeng.gui.chroma.GuiCEUser;
import com.grumpybear.chromeng.gui.container.chroma.ContainerChroma;
import com.grumpybear.chromeng.gui.container.chroma.ContainerLandLeveller;
import com.grumpybear.chromeng.gui.element.Element;
import com.grumpybear.chromeng.gui.element.IconButton;
import com.grumpybear.chromeng.init.ModItems;
import com.grumpybear.chromeng.lib.LibIcons;
import com.grumpybear.chromeng.lib.LibTextures;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

import java.io.IOException;

public class GuiLandLeveller extends GuiCEUser{

   private EntityPlayer player;
   private IInventory tileLandLeveller;

   public GuiLandLeveller(IInventory playerInv, TileLandLeveller tile) {
      super(new ContainerLandLeveller(playerInv, tile), tile, 173, 3);

      this.xSize = 176;
      this.ySize = 166;

      this.tileLandLeveller = tile;
      this.player = ((InventoryPlayer) playerInv).player;
   }

   @Override
   public void initGui() {
      super.initGui();

      addElement(new IconButton(guiLeft + 5, guiTop + 5, this, LibIcons.HOUSE));
   }

   @Override
   protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      super.mouseClicked(mouseX, mouseY, mouseButton);

      for (Element element : elements) {
         if (element.intersectsWith(mouseX, mouseY)) {
            if (element instanceof IconButton) {
               player.inventory.addItemStackToInventory(new ItemStack(ModItems.locationCard));
            }
         }
      }
   }
}
