package com.grumpybear.chromeng.gui.element;

import com.grumpybear.chromeng.gui.GuiCE;
import com.grumpybear.chromeng.lib.LibTextures;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Kieran on 7/20/2017.
 */
public class RadioButton2 extends Element{
   private final ResourceLocation ICON_LOCATION = new ResourceLocation("chromaticengineering:textures/icons.png");

   public RadioButton button1;
   public RadioButton button2;

   public RadioButton2(int xPos1, int yPos1, int xPos2, int yPos2, GuiCE gui) {
      button1 = new RadioButton(xPos1, yPos1, gui, LibTextures.UNACTIVE_GREEN_RADIO);
      button2 = new RadioButton(xPos2, yPos2, gui, LibTextures.UNACTIVE_RED_RADIO);

      gui.addElement(button1);
      gui.addElement(button2);
   }

   @Override
   public void drawBackground(int mouseX, int mouseY) {
      button1.drawBackground(mouseX, mouseY);
      button2.drawBackground(mouseX, mouseY);
   }

   private static class RadioButton extends Button {

      public int state;

      public RadioButton(int xPos, int yPos, GuiCE gui, LibTextures.Pair radio) {
         super(3, 3, xPos, yPos, gui, "chromaticengineering:textures/icons.png", radio.getX(), radio.getY(), 0, 0);
         state = 0;
      }

      @Override
      public void drawBackground(int mouseX, int mouseY) {
         if (visible) {
            Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
            if (state == 1) {
               drawRectangle(xPos, yPos, LibTextures.ACTIVE_GREEN_RADIO.getX(), LibTextures.ACTIVE_GREEN_RADIO.getY(), width, height);
            } else {
               drawRectangle(xPos, yPos, texX, texY, width, height);
            }
         }
      }

      public void changeState() {
         state = state == 1 ? 0 : 1;
      }
   }


}
