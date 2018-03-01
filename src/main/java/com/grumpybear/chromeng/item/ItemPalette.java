package com.grumpybear.chromeng.item;

import com.grumpybear.chromeng.chroma.EnumColour;
import com.grumpybear.chromeng.init.CEKeyBindings;
import com.grumpybear.chromeng.lib.LibItems;
import com.grumpybear.chromeng.network.ChromEngPacketHandler;
import com.grumpybear.chromeng.network.MessageSwitchMode;
import com.grumpybear.chromeng.util.EnumColourUtil;
import com.grumpybear.chromeng.util.ItemStackUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Kieran on 6/26/2017.
 */
public class ItemPalette extends ItemChargeMulti implements IModeItem{
   public final int TRANSFER_RATE = 5;

   private final String[] MODE_NAMES = {
           "Retain",
           "Charge"
   };

   public ItemPalette() {
      super(LibItems.PALETTE);
   }

   @Override
   public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
      if (isSelected && !worldIn.isRemote && stack.getItem() instanceof IModeItem) {
         if (CEKeyBindings.key.isPressed()) {
            switchMode(stack, new MessageSwitchMode(stack));
         }
      }

      if (getMode(stack) == 1 && entityIn != null) {
         for (ItemStack item : ((EntityPlayer) entityIn).inventory.mainInventory) {
            if (item.getItem() instanceof ItemChargeSingle) {
               if (this.getChromaStorage(stack).getColour(((ItemChargeSingle) item.getItem()).getColourType()).getCurrentCE() - TRANSFER_RATE >= 0) {
                  ItemChargeSingle charge = (ItemChargeSingle) item.getItem();
                  if (charge.addCE(item, TRANSFER_RATE))
                     this.minusCE(stack, TRANSFER_RATE, charge.getColourType());
               }
            }
         }
      }
   }

   @Override
   public String[] getModeNames() {
      return MODE_NAMES;
   }

   @Override
   public void addInformation(ItemStack stack, @Nullable World playerIn, List<String> tooltip, ITooltipFlag advanced) {
      for (EnumColour colour : EnumColour.values()) {
         tooltip.add(EnumColourUtil.colourToFormatting(colour) + EnumColourUtil.colourToStringCaps(colour) + " CE: " + TextFormatting.WHITE + ItemStackUtil.getChromaStorage(stack).getColour(colour).getCurrentCE());
      }
   }
}
