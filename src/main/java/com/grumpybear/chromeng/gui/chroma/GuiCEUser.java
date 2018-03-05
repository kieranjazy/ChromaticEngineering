package com.grumpybear.chromeng.gui.chroma;

import com.grumpybear.chromeng.block.tile.TileCEStorage;
import com.grumpybear.chromeng.chroma.ChromaUnit;
import com.grumpybear.chromeng.chroma.EnumColour;
import com.grumpybear.chromeng.chroma.IChromaStorage;
import com.grumpybear.chromeng.gui.GuiCE;
import com.grumpybear.chromeng.gui.container.ContainerBase;
import com.grumpybear.chromeng.gui.container.chroma.ContainerChroma;
import com.grumpybear.chromeng.gui.element.CEIO;
import com.grumpybear.chromeng.gui.element.CEItem;
import com.grumpybear.chromeng.gui.element.CEUnit;
import com.grumpybear.chromeng.lib.LibTextures;
import com.grumpybear.chromeng.network.ChromEngPacketHandler;
import com.grumpybear.chromeng.network.MessageSwitchCEFlow;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;

import java.io.IOException;
import java.util.ArrayList;

public class GuiCEUser extends GuiCE {

   private int ceUnitStartLeft;
   private int ceUnitStartTop;
   private boolean hasCEIO;

   private TileCEStorage chromaHandler;

   public GuiCEUser(ContainerChroma guiContainer, TileCEStorage chromaHandler, int ceUnitStartLeft, int ceUnitStartTop) {
      super(guiContainer);
      this.chromaHandler = chromaHandler;
      this.ceUnitStartLeft = ceUnitStartLeft;
      this.ceUnitStartTop = ceUnitStartTop;
   }

   public GuiCEUser(ContainerChroma guiContainer, TileCEStorage chromaHandler, int ceUnitStartLeft, int ceUnitStartTop, boolean hasCEIO) {
      this(guiContainer, chromaHandler, ceUnitStartLeft, ceUnitStartTop);
      this.hasCEIO = hasCEIO;
   }

   @Override
   public void initGui() {
      super.initGui();

      ArrayList<ChromaUnit> chromaUnits = chromaHandler.getChromaStorage().getChromaUnits();
      for (int i = 0; i <= chromaUnits.size() - 1; i++) {
         addElement(new CEUnit(this.guiLeft + ceUnitStartLeft, this.guiTop + ceUnitStartTop, this, chromaUnits.get(i), i + 1));
      }

      addElement(new CEItem(this.guiLeft + ceUnitStartLeft, this.guiTop + LibTextures.CE_UNIT_HEIGHT + 5, this, (ContainerChroma) inventorySlots));

      if (hasCEIO) {
         CEIO ceio = new CEIO(this.guiLeft + ceUnitStartLeft + LibTextures.CE_ITEM_SLOT_WH, this.guiTop + LibTextures.CE_UNIT_HEIGHT + 2, this);
         addElement(ceio);

         addElement(ceio.inputFromMachine);
         addElement(ceio.inputToMachine);
      }
   }

   @Override
   protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
      super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
      for (EnumColour colour : chromaHandler.getChromaStorage().getActualColours())
         chromaHandler.requestCEFromServer(colour);
   }

   @Override
   protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      super.mouseClicked(mouseX, mouseY, mouseButton);

      if (hasCEIO)
         ((CEIO) getElements(CEIO.class).get(0)).mouseClicked(mouseX, mouseY, chromaHandler);
   }

   @Override
   public void onGuiClosed() {
      super.onGuiClosed();

      if (hasCEIO)
         ((CEIO) getElements(CEIO.class).get(0)).onGuiClosed(chromaHandler);
   }
}
