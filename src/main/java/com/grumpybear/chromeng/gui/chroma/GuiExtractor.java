package com.grumpybear.chromeng.gui.chroma;

import com.grumpybear.chromeng.block.tile.TileExtractor;
import com.grumpybear.chromeng.chroma.IChromaStorage;
import com.grumpybear.chromeng.gui.container.chroma.ContainerExtractor;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiExtractor extends GuiCEUser{
	
	private IInventory tileExtractor;
	
	public GuiExtractor(IInventory playerInv, TileExtractor tile) {
		super(new ContainerExtractor(playerInv, tile), (IChromaStorage) tile, 173, 3);
		
		this.xSize = 176;
		this.ySize = 166;
		
		tileExtractor = tile;
	}

   @Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

      super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
		
		int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
		
		GlStateManager.color(1.0f,  1.0f,  1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(new ResourceLocation("chromaticengineering:textures/gui/container/chromatic_extractor.png"));
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		
		if (TileExtractor.isBurning(this.tileExtractor))
        {
            int k = this.getBurnLeftScaled(13);
            this.drawTexturedModalRect(i + 56, j + 36 + 12 - k, 176, 12 - k, 14, k + 1);
        }

        int l = this.getCookProgressScaled(24);
        this.drawTexturedModalRect(i + 79, j + 34, 176, 14, l + 1, 16);

	}
	
	private int getCookProgressScaled(int pixels)
    {
        int i = this.tileExtractor.getField(2);
        int j = this.tileExtractor.getField(3);
        return j != 0 && i != 0 ? i * pixels / j : 0;
    }

    private int getBurnLeftScaled(int pixels)
    {
        int i = this.tileExtractor.getField(1);

        if (i == 0)
        {
            i = 200;
        }

        return this.tileExtractor.getField(0) * pixels / i;
    }
	
	
}
