package com.grumpybear.chromeng.gui;

import com.grumpybear.chromeng.gui.container.ContainerBase;
import com.grumpybear.chromeng.gui.element.Element;
import net.minecraft.client.gui.inventory.GuiContainer;

import java.util.ArrayList;

/**
 * Created by Kieran on 6/30/2017.
 */
public class GuiCE extends GuiContainer {

   protected ArrayList<Element> elements = new ArrayList<>();

   public GuiCE(ContainerBase container ) {
      super(container);
   }

   @Override
   public void initGui() {
      super.initGui();
      elements.clear();
   }

   @Override
   protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
      drawElements(mouseX, mouseY);
   }

   public void drawElements(int mouseX, int mouseY) {
      for (Element element : elements) {
         element.drawBackground(mouseX, mouseY);
         element.drawForeground(fontRendererObj);
      }
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      super.drawScreen(mouseX, mouseY, partialTicks);
      func_191948_b(mouseX, mouseY);
   }

   public void addElement(Element element) {
      elements.add(element);
   }

   public void removeElement(Element element) {
      elements.remove(element);
   }

   public ArrayList<Element> getElements(Class<? extends Element> type) {
      ArrayList<Element> elementsoftype = new ArrayList<>();

      for (Element element : elements) {
         if (type.isAssignableFrom(element.getClass())) {
            elementsoftype.add(element);
         }
      }

      return elementsoftype;
   }
}
