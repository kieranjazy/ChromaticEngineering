package com.grumpybear.chromeng.gui.element;

import com.grumpybear.chromeng.lib.LibTextures;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;

import static com.grumpybear.chromeng.lib.LibTextures.ICON_LOCATION;

/**
 * Created by Kieran on 7/17/2017.
 */
public class IconButton extends Button {

   public int iconX;
   public int iconY;

   public IconButton(int xPos, int yPos, Gui gui, LibTextures.Pair iconLoc) {
      this(xPos, yPos, gui);
      setIcon(iconLoc);
   }

   public IconButton(int xPos, int yPos, Gui gui) {
      super(20, 21, xPos, yPos, gui, "chromaticengineering:textures/icons.png", 1, 213, 1, 234);
   }

   public void setIcon(int iconX, int iconY) {
      this.iconX = iconX;
      this.iconY = iconY;
   }

   public void setIcon(LibTextures.Pair pair) {
      setIcon(pair.getX(), pair.getY());
   }


   @Override
   public void drawBackground(int mouseX, int mouseY) {
      super.drawBackground(mouseX, mouseY);
   }

   @Override
   public void drawForeground(FontRenderer fontRenderer) {
      if (visible) {
         Minecraft.getMinecraft().getTextureManager().bindTexture(LibTextures.ICON_LOCATION);

         drawRectangle(xPos + 2, yPos + 2, iconX, iconY, 16, 16);
      }
   }

   public boolean hasIcon() {
      if (this.iconX  == 0 || this.iconY == 0)
         return false;

      return true;
   }

   public IconReturn getIcon() {
      return new IconReturn(this.iconX, this.iconY);
   }

   public static class IconReturn {
      public int iconX;
      public int iconY;
      public final String ICON_STR = "chromaticengineering:textures/icons.png";

      public IconReturn(int iconX, int iconY) {
         this.iconX = iconX;
         this.iconY = iconY;
      }

      public int getIconX() {
         return iconX;
      }

      public int getIconY() {
         return iconY;
      }
   }

}
