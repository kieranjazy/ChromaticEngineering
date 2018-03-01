package com.grumpybear.chromeng.gui.element;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Kieran on 7/5/2017.
 */
public abstract class Element {
   protected ResourceLocation texture;

   public int width;
   public int height;

   public int xPos;
   public int yPos;

   public int texX;
   public int texY;

   protected String name;

   private FontRenderer fontRenderer;
   protected Gui gui;

   public boolean enabled;
   public boolean visible;

   public Element(int width, int height, int xPos, int yPos, Gui gui) {
      this.width = width;
      this.height = height;
      this.xPos = xPos;
      this.yPos = yPos;
      this.gui = gui;
      this.visible = true;
   }

   public Element() {

   }

   public void drawRectangle(int x, int y, int textureX, int textureY, int widthX, int heightY) {
      gui.drawTexturedModalRect(x, y, textureX, textureY, widthX, heightY);
   }

   public Element setName(String name) {
      this.name = name;
      return this;
   }

   public Element setEnabled(boolean enabled) {
      this.enabled = enabled;
      return this;
   }

   public Element setVisible(boolean visible) {
      this.visible = visible;
      return this;
   }

   public Element setGuiPos(int x, int y) {
      this.xPos = x;
      this.yPos = y;
      return this;
   }

   public Element setTexture(String texture, int texX, int texY) {
      this.texture = new ResourceLocation(texture);
      this.texX = texX;
      this.texY = texY;
      return this;
   }

   public boolean intersectsWith(int mouseX, int mouseY) {
      return mouseX >= this.xPos && mouseX <= this.xPos + this.width && mouseY >= this.yPos && mouseY <= this.yPos + this.height;
   }

   public void drawBackground() {

   }

   public void drawBackground(int mouseX, int mouseY) {

   }

   public void drawForeground() {

   }

   public void drawForeground(FontRenderer fontRenderer) {

   }
}
