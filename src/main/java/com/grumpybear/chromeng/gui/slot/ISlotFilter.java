package com.grumpybear.chromeng.gui.slot;

import net.minecraft.item.ItemStack;

/**
 * Created by Kieran on 7/9/2017.
 */
public interface ISlotFilter {
   public boolean isItemValid(ItemStack stack);
}
