package com.grumpybear.chromeng.gui.element;

import com.grumpybear.chromeng.chroma.ChromaUnit;
import com.grumpybear.chromeng.lib.LibTextures;
import com.grumpybear.chromeng.util.EnumColourUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class CEUnit extends Element{

   public ChromaUnit chromaUnit;
   private int maxCE;

   public CEUnit(int xPos, int yPos, Gui gui, ChromaUnit chromaUnit, int unitNumber) {
      super(LibTextures.CE_UNIT_WIDTH, LibTextures.CE_UNIT_HEIGHT, xPos + ((LibTextures.CE_UNIT_WIDTH) * (unitNumber - 1)), yPos , gui);
      this.chromaUnit = chromaUnit;
      this.maxCE = chromaUnit.getMaxCE();
   }

   @Override
   public void drawBackground(int mouseX, int mouseY) {
      GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
      Minecraft.getMinecraft().getTextureManager().bindTexture(LibTextures.CE_UNIT);
      drawRectangle(xPos, yPos, 0, 0, width, height);

      Minecraft.getMinecraft().getTextureManager().bindTexture(LibTextures.CHROMA_STORAGE);
      LibTextures.Pair colorLoc = LibTextures.COLOR_LOCATIONS[EnumColourUtil.colourToInt(chromaUnit.getChromaType()) - 1];
      drawRectangle(xPos + 4, yPos + 4, colorLoc.getX(), colorLoc.getY(), LibTextures.SIZE_X, LibTextures.SIZE_Y);

      LibTextures.Pair colorFullLoc = LibTextures.COLOR_FULL_LOCATIONS[EnumColourUtil.colourToInt(chromaUnit.getChromaType()) - 1];
      drawRectangle(xPos + 5, yPos + 5 + getYModifier(), colorFullLoc.getX(), colorFullLoc.getY() + getYModifier(), LibTextures.SIZE_FULL_X, LibTextures.SIZE_FULL_Y);

   }



   protected int getYModifier() { //Method for getting modifier for correctly displaying CE levels
      int currCE = chromaUnit.getCurrentCE();

      return currCE == 0 ? 101 : 100 - (currCE / (maxCE / 100));
   }
}
