package com.grumpybear.chromeng.gui;

import com.grumpybear.chromeng.block.tile.TileEnergiser;
import com.grumpybear.chromeng.gui.container.ContainerEnergiser;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiEnergiser extends GuiContainer{
	public GuiEnergiser(IInventory playerInv, TileEnergiser tile) {
		super(new ContainerEnergiser(playerInv, tile));
		
		this.xSize = 176;
		this.ySize = 166;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0f,  1.0f,  1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(new ResourceLocation("chromaticengineering:textures/gui/container/fluid_displacer.png"));
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}
}
