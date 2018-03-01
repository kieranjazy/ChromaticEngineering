package com.grumpybear.chromeng.block;

import com.grumpybear.chromeng.handler.CECreativeTab;
import com.grumpybear.chromeng.handler.ModelHandler;
import com.grumpybear.chromeng.lib.LibMain;
import com.grumpybear.chromeng.render.IModelRegister;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;



public class BlockCE extends Block implements IModelRegister {

	public BlockCE(Material mat, String name) {
		super(mat);
		setUnlocalizedName(name);
		setDefaultState(blockState.getBaseState());
		setRegistryName(new ResourceLocation(LibMain.MOD_ID, name));
		setHardness(4);
		//GameRegistry.register(this);
		setCreativeTab(CECreativeTab.INSTANCE);
		registerItemForm();
	}
	

	
	public void registerItemForm() {
		//GameRegistry.register(new ItemBlockCE(this), getRegistryName());
	}


	@SideOnly(Side.CLIENT)
	public void registerModels() {
	if (Item.getItemFromBlock(this) != Items.AIR)
		ModelHandler.registerBlockToState(this, 0, getDefaultState());
	}
}
