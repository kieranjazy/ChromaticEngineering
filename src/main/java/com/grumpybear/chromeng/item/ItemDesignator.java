package com.grumpybear.chromeng.item;

import com.grumpybear.chromeng.lib.LibItems;
import com.grumpybear.chromeng.render.RenderDesignated;
import com.grumpybear.chromeng.util.BlockPosUtil;
import com.grumpybear.chromeng.util.ItemStackUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Set;

public class ItemDesignator extends ItemCE implements IModeItem{

   Set<BlockPos> blocksToVisualise;
   BlockPos prevPos1;
   BlockPos prevPos2;

   private final String[] MODES = {
           "Starting Position",
           "Ending Position",
           "Visualise"
   };

   public ItemDesignator() {
      super(LibItems.DESIGNATOR);
   }
   public ItemDesignator(TileEntity tile, ItemStack stack) {
      this();
      ItemStackUtil.getNBT(stack).setString("BoundTE", tile.toString());
   }

   @Override
   public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      if (getMode(player.getHeldItemMainhand()) == 0) {
         BlockPosUtil.writeToNBT(ItemStackUtil.getNBT(player.getHeldItemMainhand()), pos, 0);
      } else if (getMode(player.getHeldItemMainhand()) == 1) {
         BlockPosUtil.writeToNBT(ItemStackUtil.getNBT(player.getHeldItemMainhand()), pos, 1);
      }

      return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
   }

   @Override
   public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
      if (getMode(playerIn.getHeldItemMainhand()) == 2) {
         BlockPos pos1 = BlockPosUtil.getFromNBT(ItemStackUtil.getNBT(playerIn.getHeldItemMainhand()), 0);
         BlockPos pos2 = BlockPosUtil.getFromNBT(ItemStackUtil.getNBT(playerIn.getHeldItemMainhand()), 1);


         if (prevPos1 != pos1 && prevPos2 != pos2) {
            blocksToVisualise = BlockPosUtil.calculateLandLeveller(pos1, pos2, worldIn);

            prevPos1 = pos1;
            prevPos2 = pos2;
         }


      }

      return super.onItemRightClick(worldIn, playerIn, handIn);
   }

   public Set<BlockPos> getBlocksToVisualise() {
      if (blocksToVisualise != null)
         return blocksToVisualise;

      return null;
   }

   @Override
   public String[] getModeNames() {
      return MODES;
   }
}
