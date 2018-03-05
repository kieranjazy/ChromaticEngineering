package com.grumpybear.chromeng.gui.element;

import com.grumpybear.chromeng.block.tile.TileCE;
import com.grumpybear.chromeng.block.tile.TileCEStorage;
import com.grumpybear.chromeng.chroma.IChromaStorage;
import com.grumpybear.chromeng.gui.GuiCE;
import com.grumpybear.chromeng.gui.slot.SlotEnergyOutput;
import com.grumpybear.chromeng.lib.LibIcons;
import com.grumpybear.chromeng.lib.LibTextures;
import com.grumpybear.chromeng.network.ChromEngPacketHandler;
import com.grumpybear.chromeng.network.MessageSwitchCEFlow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public class CEIO extends Element{

   public IconRadioButton inputToMachine;
   public IconRadioButton inputFromMachine;

   public CEIO(int xPos, int yPos, GuiCE gui) {
      super(LibTextures.CE_IO_WIDTH, LibTextures.CE_IO_HEIGHT, xPos, yPos, gui);
      inputToMachine = new IconRadioButton(xPos + 6, yPos + 6, gui, LibIcons.UP_ARROW, false);
      inputFromMachine = new IconRadioButton(xPos + 31, yPos + 6, gui, LibIcons.DOWN_ARROW, true);

      inputToMachine.setOtherButton(inputFromMachine);
      inputFromMachine.setOtherButton(inputToMachine);
   }

   @Override
   public void drawBackground(int mouseX, int mouseY) {
      Minecraft.getMinecraft().getTextureManager().bindTexture(LibTextures.CE_IO);
      drawRectangle(xPos, yPos, 0, 0, width, height);
   }

   public void switchFlowDirection(TileCEStorage chromaHandler) {
      chromaHandler.switchFlowDirection();
      ChromEngPacketHandler.INSTANCE.sendToServer(new MessageSwitchCEFlow(chromaHandler.getPos()));
   }

   public void mouseClicked(int mouseX, int mouseY, TileCEStorage chromaStorage) {
      if (inputFromMachine.intersectsWith(mouseX, mouseY) && !inputFromMachine.isActive) {
         inputFromMachine.mouseClicked();
         switchFlowDirection(chromaStorage);
      } else if (inputToMachine.intersectsWith(mouseX, mouseY) && !inputToMachine.isActive) {
         inputToMachine.mouseClicked();
         switchFlowDirection(chromaStorage);
      }

   }

   public void onGuiClosed(TileCEStorage chromaHandler) {
      switchFlowDirection(chromaHandler);
   }
}
