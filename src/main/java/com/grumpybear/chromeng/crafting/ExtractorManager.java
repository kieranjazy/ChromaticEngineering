package com.grumpybear.chromeng.crafting;

import com.grumpybear.chromeng.init.ModItems;

import net.minecraft.item.ItemStack;

public class ExtractorManager {
	public static boolean recipeExists(ItemStack stack) {
		if (stack.getItem() == ModItems.chromaDust) {
			if (stack.getItemDamage() == 0) {
				return true;
			}	
		}
		
		return false;
	}
}
