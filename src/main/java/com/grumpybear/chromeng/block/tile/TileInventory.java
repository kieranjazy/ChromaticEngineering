package com.grumpybear.chromeng.block.tile;

import java.util.Arrays;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public abstract class TileInventory extends TileCE implements IInventory{
	
	public ItemStack[] inventory;

	public TileInventory() {
		inventory = new ItemStack[this.getSizeInventory()];
		for (int i = 0; i < inventory.length; i++) {
			inventory[i] = ItemStack.EMPTY;
		}
	}
	
	
	public abstract int getSizeInventory();
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {

		super.readFromNBT(nbt);
		readInventoryFromNBT(nbt);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {

		super.writeToNBT(nbt);
		writeInventoryToNBT(nbt);
		return nbt;
	}

	public void readInventoryFromNBT(NBTTagCompound nbt) {

		NBTTagList list = nbt.getTagList("Inventory", 10);
		inventory = new ItemStack[inventory.length];
		Arrays.fill(inventory, ItemStack.EMPTY);
		for (int i = 0; i < list.tagCount(); i++) {
			NBTTagCompound tag = list.getCompoundTagAt(i);
			int slot = tag.getInteger("Slot");

			if (slot >= 0 && slot < inventory.length) {
				inventory[slot] = new ItemStack(tag);
			}
		}
	}

	public void writeInventoryToNBT(NBTTagCompound nbt) {

		if (inventory.length <= 0) {
			return;
		}
		NBTTagList list = new NBTTagList();
		for (int i = 0; i < inventory.length; i++) {
			if (inventory[i] != null) {
				NBTTagCompound tag = new NBTTagCompound();
				tag.setInteger("Slot", i);
				inventory[i].writeToNBT(tag);
				list.appendTag(tag);
			}
		}
		if (list.tagCount() > 0) {
			nbt.setTag("Inventory", list);
		}
	}
	
	
	
	
	@Override
	public boolean isEmpty() {
		for (ItemStack stack : inventory) {
			if (!stack.isEmpty()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return inventory[index];
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		if (inventory[index].isEmpty()) {
			return ItemStack.EMPTY;
		}
		if (inventory[index].getCount() <= count) {
			count = inventory[index].getCount();
		}
		ItemStack stack = inventory[index].splitStack(count);

		if (inventory[index].getCount() <= 0) {
			inventory[index] = ItemStack.EMPTY;
		}
		return stack;
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		if (inventory[index].isEmpty()) 
			return ItemStack.EMPTY;
		
		ItemStack stack = inventory[index];
		inventory[index] = ItemStack.EMPTY;
		return stack;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		inventory[index] = stack;
		
		if (!stack.isEmpty() && stack.getCount() > getInventoryStackLimit())
			stack.setCount(getInventoryStackLimit());
		
		markDirty();
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void openInventory(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeInventory(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getField(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setField(int id, int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getFieldCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasCustomName() {
		// TODO Auto-generated method stub
		return false;
	}

}
