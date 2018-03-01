package com.grumpybear.chromeng.gui.container;


import com.grumpybear.chromeng.block.tile.TileCE;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;


public class ContainerCE extends ContainerBase {

	TileCE baseTile;

	public ContainerCE(IInventory playerInv, TileCE base) {
		super(playerInv);
		baseTile = base;
	}


	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex) {

		if (!supportsShiftClick(player, slotIndex)) {
			return ItemStack.EMPTY;
		}

		ItemStack stack = ItemStack.EMPTY;
		Slot slot = inventorySlots.get(slotIndex);

		if (slot != null && slot.getHasStack()) {
			ItemStack stackInSlot = slot.getStack();
			stack = stackInSlot.copy();

			if (!performMerge(player, slotIndex, stackInSlot)) {
				return ItemStack.EMPTY;
			}

			slot.onSlotChange(stackInSlot, stack);

			if (stackInSlot.getCount() <= 0) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.putStack(stackInSlot);
			}

			if (stackInSlot.getCount() == stack.getCount()) {
				return ItemStack.EMPTY;
			}
			slot.onTake(player, stackInSlot);
		}
		return stack;
	}
	
	

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}

	protected boolean supportsShiftClick(EntityPlayer player, int slotIndex) {

		return supportsShiftClick(slotIndex);
	}

	protected boolean supportsShiftClick(int slotIndex) {

		return true;
	}


	protected boolean performMerge(int slotIndex, ItemStack stack) {

		int invBase = getSizeInventory();
		int invFull = inventorySlots.size();

		if (slotIndex < invBase) {
			return mergeItemStack(stack, invBase, invFull, true);
		}
		return mergeItemStack(stack, 0, invBase, false);
	}

	protected boolean performMerge(EntityPlayer player, int slotIndex, ItemStack stack) {

		return performMerge(slotIndex, stack);
	}

	protected int getSizeInventory() {

		if (baseTile instanceof IInventory) {
			return ((IInventory) baseTile).getSizeInventory();
		}
		return 0;
	}
	
	
}
