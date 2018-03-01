package com.grumpybear.chromeng.item;

import com.grumpybear.chromeng.chroma.ChromaUnit;
import com.grumpybear.chromeng.chroma.EnumColour;
import com.grumpybear.chromeng.util.EnumColourUtil;
import com.grumpybear.chromeng.util.ItemStackUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static com.grumpybear.chromeng.util.ItemStackUtil.getNBT;

/**
 * Created by Kieran on 6/28/2017.
 */
public abstract class ItemChargeSingle extends ItemCE{

   public final EnumColour COLOUR_TYPE;

   public abstract int getCECost();

   public ItemChargeSingle(String name, EnumColour colour) {
      super(name);
      this.COLOUR_TYPE = colour;
   }

   @Override
   public int getItemStackLimit(ItemStack stack) {
      return 1;
   }

   public boolean addCE(ItemStack stack, int i) {
      if (getChromaUnit(stack).getCurrentCE() + i <= getChromaUnit(stack).getMaxCE()) {
         ChromaUnit temp = getChromaUnit(stack);
         temp.addCurrentCE(i);
         temp.writeToNBT(getNBT(stack));
         return true;
      }

      return false;
   }

   public boolean addCE(ItemStack stack) {
      return addCE(stack, getCECost());
   }

   public boolean minusCE(ItemStack stack, int i) {
      if (getChromaUnit(stack).getCurrentCE() - i >= 0) {
         ChromaUnit temp = getChromaUnit(stack);
         temp.minusCurrentCE(i);
         temp.writeToNBT(getNBT(stack));
         return true;
      }

      return false;
   }

   public boolean minusCE(ItemStack stack) {
      return minusCE(stack, getCECost());
   }

   public void setCE(ItemStack stack, int i) {
      ChromaUnit temp = getChromaUnit(stack);
      temp.setCurrentCE(i);
      temp.writeToNBT(getNBT(stack));
   }

   public ChromaUnit getChromaUnit(ItemStack stack) {
      ChromaUnit temp = new ChromaUnit(this.getColourType(), 1000);

      if (!ItemStackUtil.hasChromaTags(getNBT(stack))) {
         temp.writeToNBT(getNBT(stack));
         return temp;
      }

      temp.readFromNBT(getNBT(stack));
      return temp;
   }

   public EnumColour getColourType() {
      return this.COLOUR_TYPE;
   }

   @Override
   public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
      getChromaUnit(stack);

      super.onCreated(stack, worldIn, playerIn);
   }

   @Override
   public boolean showDurabilityBar(ItemStack stack) {
      return true;
   }

   @Override
   public double getDurabilityForDisplay(ItemStack stack) {
      return 1 - ((double) this.getChromaUnit(stack).getCurrentCE() / (double)this.getChromaUnit(stack).getMaxCE());
   }

   @Override
   public int getRGBDurabilityForDisplay(ItemStack stack) {
      return EnumColourUtil.colourToRGB(getColourType());
   }

   @Override
   public void addInformation(ItemStack stack, @Nullable World playerIn, List<String> tooltip, ITooltipFlag advanced) {
      tooltip.add(EnumColourUtil.colourToFormatting(getColourType()) + EnumColourUtil.colourToStringCaps(getColourType()) + " CE: "  + TextFormatting.WHITE + getChromaUnit(stack).getCurrentCE());
   }

}
