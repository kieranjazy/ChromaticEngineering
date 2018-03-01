package com.grumpybear.chromeng.block.tile;

import com.grumpybear.chromeng.gui.element.IconButton;
import com.grumpybear.chromeng.init.ModItems;
import com.grumpybear.chromeng.item.ItemLordvessel;
import com.grumpybear.chromeng.util.BlockPosUtil;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.ArrayList;

import static com.grumpybear.chromeng.util.ItemStackUtil.getNBT;

public class TileBonfire extends TileInventory{

	private boolean isBurning;
	private ArrayList<IconButton> buttons;

	public TileBonfire() {
		this.buttons = new ArrayList<>();
		isBurning = true;
	}

	public boolean isBurning() {
		return isBurning;
	}
	
	public void setBurning(boolean bool) {
		isBurning = bool;
	}

	public NBTTagCompound writeToDarksignNBT(NBTTagCompound nbt) {
		nbt.setBoolean("Burning", isBurning);
		BlockPosUtil.writeToNBT(nbt, this.pos, 0);
		return nbt;
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setBoolean("Burning", isBurning);
		return nbt;
	}

	@Override
	public int getSizeInventory() {
		return 2;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		
		isBurning = nbt.getBoolean("Burning");
	}

	//Only being called client side not server side, cause of errors
	public void linkDarksign(String text) {

		ItemStack stack = this.getStackInSlot(0);
		if (getStackInSlot(1).getItem() == Items.AIR && stack.getItem() == ModItems.darksign) {
			if (linkBonfire(stack, this)) {
				this.setInventorySlotContents(1, stack.copy());
				this.getStackInSlot(0).shrink(1);
				if (!text.isEmpty())
					this.getStackInSlot(1).setStackDisplayName(text);
			}
		}
	}

	public void linkLordvessel(String text, IconButton.IconReturn icon) {
		ItemStack stack  = this.getStackInSlot(0);

		if (getStackInSlot(1).getItem() == Items.AIR && stack.getItem() == ModItems.lordvessel) {
			ItemLordvessel item = (ItemLordvessel) stack.getItem();

			if (item.getNextAvailButton(stack) <= 11 && !item.getBlockPoses(stack).contains(this.pos)) {
				NBTTagCompound nbt = getNBT(stack);
				int num = item.getNextAvailButton(stack);

				BlockPosUtil.writeToNBT(nbt, this.getPos(), num);
				nbt.setString("Name" + num, text);
				nbt.setInteger("IconX" + num, icon.getIconX());
				nbt.setInteger("IconY" + num, icon.getIconY());
				nbt.setBoolean("Set" + num, true);
				item.incrNextButton(stack);

				this.setInventorySlotContents(1, stack.copy());
				this.getStackInSlot(0).shrink(1);

			}
		}
	}


	public boolean linkBonfire(ItemStack stack, TileBonfire bonfire) {
		if (stack.getItem() == ModItems.darksign) {

			if (bonfire.isBurning()) {
				bonfire.writeToDarksignNBT(getNBT(stack));
				return true;
			} else {
				return false;
			}
		} else if (stack.getItem() == ModItems.lordvessel) {
			if (bonfire.isBurning()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
