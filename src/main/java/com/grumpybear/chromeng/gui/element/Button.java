package com.grumpybear.chromeng.gui.element;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;

import java.awt.*;


/**
 * Created by Kieran on 7/5/2017.
 */
public class Button extends Element {

   public String buttonString;

   public int hoverX;
   public int hoverY;

   public Button(int width, int height, int xPos, int yPos, Gui gui, String texture, int texX, int texY, int hoverX, int hoverY) {
      super(width, height, xPos, yPos, gui);

      this.setTexture(texture, texX, texY, hoverX, hoverY);
   }

   public Button(int width, int height, int xPos, int yPos, Gui gui, String texture, int texX, int texY, int hoverX, int hoverY, String buttonString) {
      this(width, height, xPos, yPos, gui, texture, texX, texY, hoverX, hoverY);

      this.buttonString = buttonString;
   }

   public Button setTexture(String texture, int texX, int texY, int hoverX, int hoverY) {
      this.hoverX = hoverX;
      this.hoverY = hoverY;
      super.setTexture(texture, texX, texY);
      return this;
   }

   @Override
   public void drawBackground(int mouseX, int mouseY) {
      if (visible) {
         Minecraft.getMinecraft().getTextureManager().bindTexture(texture);

         if (!intersectsWith(mouseX, mouseY)) {
            drawRectangle(xPos, yPos, texX, texY, width, height);
         } else {
            drawRectangle(xPos, yPos, hoverX, hoverY, width, height);
         }
      }
   }

   @Override
   public void drawForeground(FontRenderer fontRenderer) {
      if (visible) {
         if (this.buttonString != null)
            drawCenteredString(fontRenderer);
      }
   }

   public void drawCenteredString(FontRenderer fontRenderer) {
      //fontRenderer.drawStringWithShadow(text, x - fontRenderer.getStringWidth(text) / 2, y, color);

      gui.drawCenteredString(fontRenderer, this.buttonString, this.xPos + this.width / 2, this.yPos + (this.height - 8) / 2, Color.WHITE.getRGB());
   }
}
