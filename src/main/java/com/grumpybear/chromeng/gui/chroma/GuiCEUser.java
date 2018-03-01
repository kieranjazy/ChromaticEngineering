package com.grumpybear.chromeng.gui.chroma;

import com.grumpybear.chromeng.chroma.ChromaUnit;
import com.grumpybear.chromeng.chroma.IChromaStorage;
import com.grumpybear.chromeng.gui.GuiCE;
import com.grumpybear.chromeng.gui.container.ContainerBase;
import com.grumpybear.chromeng.gui.container.chroma.ContainerChroma;
import com.grumpybear.chromeng.gui.element.CEItem;
import com.grumpybear.chromeng.gui.element.CEUnit;
import com.grumpybear.chromeng.lib.LibTextures;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;

import java.util.ArrayList;

public class GuiCEUser extends GuiCE {

   public int ceUnitStartLeft;
   public int ceUnitStartTop;

   private IChromaStorage chromaHandler;

   public GuiCEUser(ContainerChroma guiContainer, IChromaStorage chromaHandler, int ceUnitStartLeft, int ceUnitStartTop) {
      super(guiContainer);
      this.chromaHandler = chromaHandler;
      this.ceUnitStartLeft = ceUnitStartLeft;
      this.ceUnitStartTop = ceUnitStartTop;
   }

   @Override
   public void initGui() {
      super.initGui();

      ArrayList<ChromaUnit> chromaUnits = chromaHandler.getChromaStorage().getChromaUnits();
      for (int i = 0; i <= chromaUnits.size() - 1; i++) {
         addElement(new CEUnit(this.guiLeft + ceUnitStartLeft, this.guiTop + ceUnitStartTop, this, chromaUnits.get(i), i + 1));
      }

      addElement(new CEItem(this.guiLeft + ceUnitStartLeft, this.guiTop + LibTextures.CE_UNIT_HEIGHT + 5, this, (ContainerChroma) inventorySlots));

   }

   @Override
   protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
      super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
   }

}
