package com.grumpybear.chromeng.block.tile;


import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class TileReceptacle extends TileTank implements IFluidHandler{
	
	public TileReceptacle() {
		super(1000);
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {
		// TODO Auto-generated method stub
		return super.fill(resource, doFill);
	}
	
	
}
