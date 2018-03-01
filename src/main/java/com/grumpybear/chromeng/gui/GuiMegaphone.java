package com.grumpybear.chromeng.gui;

import org.lwjgl.opengl.GL11;

import com.grumpybear.chromeng.lib.LibMain;

import net.minecraft.client.gui.GuiScreen;

public class GuiMegaphone extends GuiScreen{
	
	int guiWidth = 200;
	int guiHeight = 230;
	int x, y;
	
	@Override
	public void initGui() {
		super.initGui();
		
		x = width / 2 - guiWidth / 2;
		y = height / 2 - guiHeight / 2;
		
	}
	
	@Override
	public void drawScreen(int par1, int par2, float par3) {
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		drawRect(x, y, x + guiWidth, y + guiHeight, 0x99000000);
		GL11.glDisable(GL11.GL_BLEND);
		
		GL11.glScalef(2F,  2F, 2F);
		drawCenteredString(fontRendererObj, LibMain.MOD_NAME, (x + guiWidth / 2) / 2, (y + 10) / 2, 0xFF0000);
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		
		super.drawScreen(par1, par2, par3);
	}
	
}
