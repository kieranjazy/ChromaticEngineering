package com.grumpybear.chromeng.gui.container.chroma;

import com.grumpybear.chromeng.block.tile.TileCE;
import com.grumpybear.chromeng.block.tile.TileCEStorage;
import com.grumpybear.chromeng.block.tile.TileExtractor;
import com.grumpybear.chromeng.chroma.ChromaStorage;
import com.grumpybear.chromeng.chroma.ChromaUnit;
import com.grumpybear.chromeng.chroma.EnumColour;
import com.grumpybear.chromeng.chroma.IChromaStorage;
import com.grumpybear.chromeng.gui.container.ContainerCE;
import com.grumpybear.chromeng.gui.slot.SlotEnergyOutput;
import com.grumpybear.chromeng.util.EnumColourUtil;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;

public class ContainerChroma extends ContainerCE{
	
	IChromaStorage chromaHandler;
    TileCEStorage tile;
	ArrayList<ChromaUnit> ceValues;
	SlotEnergyOutput ceSlot;

	public ContainerChroma(IInventory playerInv, TileEntity chromaHandler, int xPos, int yPos) {
		super(playerInv, (TileCE) chromaHandler);
		this.chromaHandler = (IChromaStorage) chromaHandler;
        this.tile = (TileCEStorage) this.chromaHandler;
        this.ceValues = new ArrayList<>();
        for (ChromaUnit unit : this.chromaHandler.getChromaStorage().getChromaUnits()) {
           ChromaUnit tempUnit = new ChromaUnit(unit.getChromaType(), unit.getMaxCE());
           tempUnit.setCurrentCE(-1);
           ceValues.add(tempUnit);
        }

        this.ceSlot = new SlotEnergyOutput(tile, tile.getSizeInventory() - 1, xPos, yPos);
        addSlotToContainer(ceSlot);
	}
	
	@Override
	public void addListener(IContainerListener listener)
    {
        super.addListener(listener);
        listener.sendAllWindowProperties(this, (IInventory) this.chromaHandler);
    }


    public ChromaStorage getChromaStorage() {
	   return chromaHandler.getChromaStorage();
    }

    public TileCEStorage getTile() {
	   return tile;
    }

   public SlotEnergyOutput getCeSlot() {
      return ceSlot;
   }
}
