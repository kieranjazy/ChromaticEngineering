package com.grumpybear.chromeng.handler;

import com.grumpybear.chromeng.block.tile.*;
import com.grumpybear.chromeng.gui.GuiBonfire;
import com.grumpybear.chromeng.gui.GuiEnergiser;
import com.grumpybear.chromeng.gui.GuiFluidDisplacer;
import com.grumpybear.chromeng.gui.GuiLordvessel;
import com.grumpybear.chromeng.gui.chroma.GuiBattery;
import com.grumpybear.chromeng.gui.chroma.GuiExtractor;
import com.grumpybear.chromeng.gui.chroma.GuiLandLeveller;
import com.grumpybear.chromeng.gui.container.ContainerBonfire;
import com.grumpybear.chromeng.gui.container.ContainerEnergiser;
import com.grumpybear.chromeng.gui.container.ContainerFluidDisplacer;
import com.grumpybear.chromeng.gui.container.ContainerLordvessel;
import com.grumpybear.chromeng.gui.container.chroma.ContainerBattery;
import com.grumpybear.chromeng.gui.container.chroma.ContainerExtractor;
import com.grumpybear.chromeng.gui.container.chroma.ContainerLandLeveller;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
	
	public static final int FLUID_DISPLACER = 0;
	public static final int ENERGISER = 1;
	public static final int EXTRACTOR = 2;
	public static final int LORDVESSEL = 3;
	public static final int BONFIRE = 4;
	public static final int BATTERY = 5;
	public static final int LAND_LEVELLER = 6;

	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);
		TileEntity tile = world.getTileEntity(pos);
		
		if (ID == 0)
			return new ContainerFluidDisplacer(player.inventory, tile);
		if (ID == 1)
			return new ContainerEnergiser(player.inventory, tile);
		if (ID == 2)
			return new ContainerExtractor(player.inventory, tile);
		if (ID == 3)
			return new ContainerLordvessel(player.inventory);
		if (ID == 4)
			return new ContainerBonfire(player.inventory, tile);
		if (ID == 5)
			return new ContainerBattery(player.inventory, tile);
		if (ID == 6)
			return new ContainerLandLeveller(player.inventory, tile);

		return null;
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);
		TileEntity tile = world.getTileEntity(pos);
		
		if (ID == 0)
			return new GuiFluidDisplacer(player.inventory, (TileDisplacer) tile);
		if (ID == 1)
			return new GuiEnergiser(player.inventory, (TileEnergiser) tile);
		if (ID == 2)
			return new GuiExtractor(player.inventory, (TileExtractor) tile);
		if (ID == 3)
			return new GuiLordvessel(player.inventory);
		if (ID == 4)
			return new GuiBonfire(player.inventory, (TileBonfire) tile);
		if (ID == 5)
			return new GuiBattery(player.inventory, (TileBattery) tile);
		if (ID == 6)
			return new GuiLandLeveller(player.inventory, (TileLandLeveller) tile);
		
		
		return null;
	}

}
