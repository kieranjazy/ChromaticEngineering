package com.grumpybear.chromeng.item;

import com.grumpybear.chromeng.chroma.EnumColour;
import com.grumpybear.chromeng.lib.LibItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemInspector extends ItemChargeSingle{

   private final int CE_COST = 10;

   public ItemInspector() {
      super(LibItems.INSPECTOR, EnumColour.GREEN);
   }

   @Override
   public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

      return EnumActionResult.SUCCESS;
   }

   @Override
   public int getCECost() {
      return CE_COST;
   }
}
