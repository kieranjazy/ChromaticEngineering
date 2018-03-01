package com.grumpybear.chromeng.handler;

import com.grumpybear.chromeng.init.ModItems;
import com.grumpybear.chromeng.item.workonlater.ItemBlockDesignator;
import com.grumpybear.chromeng.render.RenderDesignated;
import com.grumpybear.chromeng.util.BlockPosUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import static com.grumpybear.chromeng.util.BlockPosUtil.getFromNBT;
import static com.grumpybear.chromeng.util.ItemStackUtil.getNBT;
@SideOnly(Side.CLIENT)
public class RenderWorldLastEventHandler {

   private static final ResourceLocation glow = new ResourceLocation("chromaticengineering:textures/blocks/designated.png");
   private static final ResourceLocation boundGlow = new ResourceLocation("chromaticengineering:textures/blocks/boundglow.png");
   private static final ResourceLocation validGlow = new ResourceLocation("chromaticengineering:textures/blocks/validglow.png");

	public static void tick(RenderWorldLastEvent event) {
		renderDesignatedBlocks(event);
	}
	
	private static void renderDesignatedBlocks(RenderWorldLastEvent event) {
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayerSP p = mc.player;
		ItemStack held = p.getHeldItem(EnumHand.MAIN_HAND);
		if (held.isEmpty()) {
			return;
		}
		
		if (held.getItem() == ModItems.blockDesignator) {
			Set<BlockPos> blocks = new HashSet<BlockPos>();
			for (String key : ItemBlockDesignator.getNBT(held).getKeySet()) {
				blocks.add(BlockPosUtil.longToPos(ItemBlockDesignator.getNBT(held).getLong(key)));
			}
			if (!blocks.isEmpty()) {
				renderHighlightedBlocks(event, p, blocks);
			}
		}

		 if (held.getItem() == ModItems.extensionConduit) {
		   if (BlockPosUtil.nbtHasPos(getNBT(held), 0)) {
              World world = mc.world;
              BlockPos pos = p.getPosition();
              BlockPos tempPos = getFromNBT(getNBT(held), 0);

              if((pos.getY() <= tempPos.getY() + 16 && pos.getY() >= tempPos.getY() - 16) && (pos.getZ() <= tempPos.getZ() + 16 && pos.getZ() >= tempPos.getZ() - 16) && (pos.getX() <= tempPos.getX() + 16 && pos.getX() >= tempPos.getX() - 16)) {

                 Map<BlockPos, ResourceLocation> map = new LinkedHashMap<>();
                 if (!world.isAirBlock(BlockPosUtil.getFromNBT(getNBT(held), 0))) {
                    map.put(tempPos, glow);
                 }

                 if (BlockPosUtil.nbtHasPos(getNBT(held), 1) && !world.isAirBlock(BlockPosUtil.getFromNBT(getNBT(held), 1))) {
                    map.put(BlockPosUtil.getFromNBT(getNBT(held), 1), boundGlow);
                 }

                 if (!map.isEmpty())
                    renderExtensionBlocks(event, p, map);

              }
           }
        }
	}
	
	private static void renderHighlightedBlocks(RenderWorldLastEvent event, EntityPlayerSP p, Set<BlockPos> blocks) {
		double doubleX = p.lastTickPosX + (p.posX - p.lastTickPosX) * event.getPartialTicks();
        double doubleY = p.lastTickPosY + (p.posY - p.lastTickPosY) * event.getPartialTicks();
        double doubleZ = p.lastTickPosZ + (p.posZ - p.lastTickPosZ) * event.getPartialTicks();

        GlStateManager.pushMatrix();
        GlStateManager.translate(-doubleX, -doubleY, -doubleZ);

        GlStateManager.disableDepth();
        GlStateManager.enableTexture2D();

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();

        Minecraft.getMinecraft().getTextureManager().bindTexture(glow);

        for (BlockPos coordinate : blocks) {

            RenderDesignated.renderGlow(tessellator, coordinate.getX(), coordinate.getY(), coordinate.getZ());
        }

        GlStateManager.disableBlend();


        GlStateManager.enableTexture2D();
        GlStateManager.popMatrix();
	}

	private static void renderExtensionBlocks(RenderWorldLastEvent event, EntityPlayerSP p, Map<BlockPos, ResourceLocation> blocks) {
       double doubleX = p.lastTickPosX + (p.posX - p.lastTickPosX) * event.getPartialTicks();
       double doubleY = p.lastTickPosY + (p.posY - p.lastTickPosY) * event.getPartialTicks();
       double doubleZ = p.lastTickPosZ + (p.posZ - p.lastTickPosZ) * event.getPartialTicks();

       GlStateManager.pushMatrix();
       GlStateManager.translate(-doubleX, -doubleY, -doubleZ);

       GlStateManager.disableDepth();
       GlStateManager.enableTexture2D();

       Tessellator tessellator = Tessellator.getInstance();
       BufferBuilder buffer = tessellator.getBuffer();


       for (BlockPos coordinate : blocks.keySet()) {
          Minecraft.getMinecraft().getTextureManager().bindTexture(blocks.get(coordinate));
          RenderDesignated.renderGlow(tessellator, coordinate.getX(), coordinate.getY(), coordinate.getZ());
       }

       GlStateManager.disableBlend();


       GlStateManager.enableTexture2D();
       GlStateManager.popMatrix();
    }
	
	
}
