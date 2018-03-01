package com.grumpybear.chromeng.block.tile;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

public class TileDisplacer extends TileInventory {

	List<IBlockState> liquids = new ArrayList<IBlockState>();
	
	@Override
	public int getSizeInventory() {
		return 1;
	}
	
	@Override
	public void update() {
		String bucket = null;
		Block liquid = world.getBlockState(pos.add(0, 1, 0)).getBlock();
		Item slot = this.getStackInSlot(0).getItem();
		boolean rsSide = world.isBlockPowered(pos);
		if (slot == Items.WATER_BUCKET)
			bucket = "water";
		if (slot == Items.LAVA_BUCKET)
			bucket = "lava";
		if (slot == Items.BUCKET)
			bucket = "empty";
		
		if (rsSide == true && bucket != null) {
			BlockPos newPos = pos.add(0, 1, 0);
			if ((liquid == Blocks.AIR || liquid == Blocks.FLOWING_WATER) && bucket != "empty") {
				IBlockState state;
				if (bucket == "water") {
					state = Blocks.WATER.getDefaultState();  
				} else if (bucket == "lava") {
					state = Blocks.FLOWING_LAVA.getDefaultState();  
				} else {
					state = Blocks.AIR.getDefaultState();
				}
				
				liquids.add(state);
				world.setBlockState(newPos, state);
				this.setInventorySlotContents(0, new ItemStack(Items.BUCKET));
			}
		} else if (rsSide == false && bucket == "empty") {		
			if (liquid == Blocks.WATER) {
				this.setInventorySlotContents(0, new ItemStack(Items.WATER_BUCKET));
				world.setBlockToAir(pos.add(0, 1, 0));
			} else if (liquid == Blocks.LAVA) {
				this.setInventorySlotContents(0, new ItemStack(Items.LAVA_BUCKET));
				world.setBlockToAir(pos.add(0, 1, 0));
			}
			
			liquids.clear();
		}
	}

	

	
}
