package com.grumpybear.chromeng.gui.element;

import com.grumpybear.chromeng.gui.GuiCE;
import com.grumpybear.chromeng.lib.LibTextures;
import net.minecraft.util.math.BlockPos;

/**
 * Created by Kieran on 7/20/2017.
 */
public class LordvesselButton extends IconButton{
   public String name;
   public BlockPos pos;

   public LordvesselButton(int xPos, int yPos, GuiCE gui, LibTextures.Pair iconLoc) {
      super(xPos, yPos, gui, iconLoc);
   }

   public LordvesselButton(int xPos, int yPos, IconReturn icon, GuiCE gui, String name, BlockPos pos) {
      super(xPos, yPos, gui, new LibTextures.Pair(icon.getIconX(), icon.getIconY()));
      this.name = name;
      this.pos = pos;
   }





}
