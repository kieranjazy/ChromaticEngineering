package com.grumpybear.chromeng.gui.container;

import com.grumpybear.chromeng.block.tile.TileCE;
import com.grumpybear.chromeng.block.tile.TileDisplacer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.tileentity.TileEntity;

public class ContainerFluidDisplacer extends ContainerCE {
	
	TileDisplacer myTile;

	public ContainerFluidDisplacer(IInventory playerInv, TileEntity tile) {
		super(playerInv, (TileCE) tile);
		
		myTile = (TileDisplacer) tile;
		
		addSlotToContainer(new Slot(myTile, 0, 80, 35));
	}

}
