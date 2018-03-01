package com.grumpybear.chromeng.gui.element;

import com.grumpybear.chromeng.chroma.IChromaStorage;
import com.grumpybear.chromeng.gui.slot.SlotEnergyOutput;
import net.minecraft.client.gui.Gui;

public class CEIO extends Element{

   public IChromaStorage chromaStorage;
   public SlotEnergyOutput ceSlot;

   public CEIO(int width, int height, int xPos, int yPos, Gui gui) {
      super(width, height, xPos, yPos, gui);
   }




}
