package com.grumpybear.chromeng.item;

import com.grumpybear.chromeng.chroma.EnumColour;
import com.grumpybear.chromeng.lib.LibItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemMLE extends ItemChargeSingle{

   private final int CE_COST = 100;

   public ItemMLE(String name, EnumColour colour) {
      super(LibItems.MLE, EnumColour.ORANGE);
   }

   @Override
   public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {


      return super.onItemRightClick(worldIn, playerIn, handIn);
   }

   @Override
   public int getCECost() {
      return CE_COST;
   }
}
