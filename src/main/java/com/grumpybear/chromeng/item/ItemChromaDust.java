package com.grumpybear.chromeng.item;

import com.grumpybear.chromeng.handler.ModelHandler;
import com.grumpybear.chromeng.lib.LibItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemChromaDust extends ItemCE{

	public ItemChromaDust() {
		super(LibItems.CHROMA_DUST);
		this.setHasSubtypes(true);
	}

	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "item." + (stack.getItemDamage() == 0 ? "dusteffervescent" : "dustdevitalised");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> list) {
		if (func_194125_a(tab)) {
			list.add(new ItemStack(this, 1, 0));
			list.add(new ItemStack(this, 1, 1));
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels() {
		ModelHandler.registerItemMetas(this, LibItems.DUST_TYPES.length, i -> LibItems.DUST_TYPES[i]);
	}
}
