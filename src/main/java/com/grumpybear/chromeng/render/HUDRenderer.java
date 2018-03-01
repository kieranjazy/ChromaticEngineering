package com.grumpybear.chromeng.render;

import com.grumpybear.chromeng.chroma.EnumColour;
import com.grumpybear.chromeng.item.IModeItem;
import com.grumpybear.chromeng.item.ItemChargeSingle;
import com.grumpybear.chromeng.item.ItemExtensionConduit;
import com.grumpybear.chromeng.item.ItemInspector;
import com.grumpybear.chromeng.util.BlockPosUtil;
import com.grumpybear.chromeng.util.EnumColourUtil;
import com.grumpybear.chromeng.util.ItemStackUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

/**
 * Created by Kieran on 7/12/2017.
 */
@Mod.EventBusSubscriber(Side.CLIENT)
public class HUDRenderer{
   private HUDRenderer(){}


   @SubscribeEvent(priority = EventPriority.HIGHEST)
   public static void renderMode(RenderGameOverlayEvent.Post event) {
      Minecraft mc = Minecraft.getMinecraft();
      EntityPlayer player = mc.player;
      FontRenderer font = mc.fontRendererObj;
      ScaledResolution res = event.getResolution();

      int sx = res.getScaledWidth(); //196.5 - 223
      int sy = res.getScaledHeight(); //122 - 129.5

      if (player.getHeldItemMainhand().getItem() instanceof IModeItem) {
         IModeItem modeItem = (IModeItem) player.getHeldItemMainhand().getItem();
         font.drawString("Mode: " + modeItem.getCurrentModeString(player.getHeldItemMainhand()), sx - 120, sy - 10, modeItem instanceof ItemChargeSingle ? EnumColourUtil.colourToRGB(((ItemChargeSingle) modeItem).getColourType()) : 0xFF4500);
      }

      if (player.getHeldItemMainhand().getItem() instanceof ItemExtensionConduit) {
         ItemExtensionConduit item = (ItemExtensionConduit) player.getHeldItemMainhand().getItem();
         NBTTagCompound tag = ItemStackUtil.getNBT(player.getHeldItemMainhand());
         if (item.getMode(player.getHeldItemMainhand()) == 2 && BlockPosUtil.nbtHasPos(tag, 0) && BlockPosUtil.nbtHasPos(tag, 1)) {
            IBlockState blockstate = mc.world.getBlockState(BlockPosUtil.getFromNBT(tag, 0));
            IItemHandler inv = player.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
            int total = 0;

            for (int i = inv.getSlots() - 1; i >= 0; i--) {
               ItemStack stack = inv.getStackInSlot(i);

               if (stack.isEmpty())
                  continue;

               Item items = stack.getItem();
               if (items == Item.getItemFromBlock(blockstate.getBlock()) && stack.getItemDamage() == blockstate.getBlock().getMetaFromState(blockstate)) {
                  total += stack.getCount();
               }
            }

            int required = 0;

            for (BlockPos pos : item.getFinalBlockList(BlockPosUtil.getFromNBT(tag, 0), BlockPosUtil.getFromNBT(tag, 1))) {
               if (mc.world.getBlockState(pos) != mc.world.getBlockState(BlockPosUtil.getFromNBT(tag, 0)))
                  required++;
            }

            font.drawString((total <= required ? String.valueOf(total) : String.valueOf(required)) + "/" + String.valueOf(required) + " | " + (required - total > 0 ? required - total : "No more") + " needed", sx - 120, sy - 20, EnumColourUtil.colourToRGB(EnumColour.YELLOW));
            mc.getRenderItem().renderItemIntoGUI(new ItemStack(Item.getItemFromBlock(blockstate.getBlock()), 1, blockstate.getBlock().getMetaFromState(blockstate)), sx - 120, sy - 40);
         }
      }

      if (player.getHeldItemMainhand().getItem() instanceof ItemInspector) {
         NBTTagCompound tag = ItemStackUtil.getNBT(player.getHeldItemMainhand());
         ItemInspector item = (ItemInspector) player.getHeldItemMainhand().getItem();
         IBlockState blockstate = mc.world.getBlockState(BlockPosUtil.getFromNBT(tag, 0));
         mc.getRenderItem().renderItemIntoGUI(new ItemStack(Item.getItemFromBlock(blockstate.getBlock()), 1, blockstate.getBlock().getMetaFromState(blockstate)), sx - 120, sy - 65);

      }
   }
}
