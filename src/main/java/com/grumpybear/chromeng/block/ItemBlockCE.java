package com.grumpybear.chromeng.block;

import javax.annotation.Nonnull;

import com.grumpybear.chromeng.lib.LibMain;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockCE extends ItemBlock{

	
	public ItemBlockCE(Block block) {
		super(block);
	}

	@Nonnull
	@Override
	public String getUnlocalizedNameInefficiently(@Nonnull ItemStack par1ItemStack) {
		return getUnlocalizedNameInefficiently_(par1ItemStack).replaceAll("tile.", "tile." + LibMain.MOD_PREFIX);
	}

	public String getUnlocalizedNameInefficiently_(ItemStack stack) {
		return super.getUnlocalizedNameInefficiently(stack);
	}
}
