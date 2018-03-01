package com.grumpybear.chromeng.item;

import com.grumpybear.chromeng.handler.CECreativeTab;
import com.grumpybear.chromeng.lib.LibMain;
import com.grumpybear.chromeng.render.IModelRegister;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCE extends Item implements IModelRegister{

	public ItemCE(String name) {
		setUnlocalizedName(name);
		setCreativeTab(CECreativeTab.INSTANCE);
		setRegistryName(new ResourceLocation(LibMain.MOD_ID, name));
		//GameRegistry.register(this, new ResourceLocation("chromaticengineering", name));
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels() {
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName().toString().toLowerCase()));
	}
}
