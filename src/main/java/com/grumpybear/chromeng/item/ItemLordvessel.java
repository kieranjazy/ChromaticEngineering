package com.grumpybear.chromeng.item;

import com.grumpybear.chromeng.ChromEng;
import com.grumpybear.chromeng.handler.GuiHandler;
import com.grumpybear.chromeng.lib.LibItemTypes;
import com.grumpybear.chromeng.lib.LibItems;
import com.grumpybear.chromeng.util.BlockPosUtil;
import com.grumpybear.chromeng.util.ItemStackUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kieran on 6/28/2017.
 */
public class ItemLordvessel extends ItemChargeSingle{

   public final int CE_COST = 100;

   public ItemLordvessel() {
      super(LibItems.LORDVESSEL, LibItemTypes.LORDVESSEL);
   }

   @Override
   public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
      if (!worldIn.isRemote)
          playerIn.openGui(ChromEng.instance, GuiHandler.LORDVESSEL, worldIn, playerIn.getPosition().getX(), playerIn.getPosition().getY(), playerIn.getPosition().getZ());

      return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItemMainhand());
   }

   public int getNextAvailButton(ItemStack stack) {
      return ItemStackUtil.getNBT(stack).getInteger("currButton");
   }

   public void incrNextButton(ItemStack stack) {
      ItemStackUtil.getNBT(stack).setInteger("currButton", (ItemStackUtil.getNBT(stack).getInteger("currButton") + 1));
   }

   public void decrNextButton(ItemStack stack) {
      ItemStackUtil.getNBT(stack).setInteger("currButton", ItemStackUtil.getNBT(stack).getInteger("currButton") - 1);
   }


   public List<BlockPos> getBlockPoses(ItemStack stack) {
      int count = 0;
      List<BlockPos> list = new ArrayList<>();
      while (BlockPosUtil.nbtHasPos(ItemStackUtil.getNBT(stack), count)) {
         list.add(BlockPosUtil.getFromNBT(ItemStackUtil.getNBT(stack), count));
         count++;
      }

      return list;
   }

   public void shiftNBTData(int startIndex, int maxIndex, ItemStack stack) {
      NBTTagCompound tag = ItemStackUtil.getNBT(stack);

      for (int i = startIndex; i <= maxIndex; i++) {
         if (i == maxIndex) {
            BlockPosUtil.emptyNBT(tag, i);
            tag.removeTag("Name" + i);
            tag.removeTag("IconX" + i);
            tag.removeTag("IconY" + i);
            tag.removeTag("Set" + i);
            break; //Remove from elements
         }

         tag.setString("Name" + i, tag.getString("Name" + (i + 1)));
         tag.setInteger("IconX" + i, tag.getInteger("IconX" + (i + 1)));
         tag.setInteger("IconY" + i, tag.getInteger("IconY" + (i + 1)));
         tag.setBoolean("Set" + i, tag.getBoolean("Set" + (i + 1)));
         BlockPosUtil.writeToNBT(tag, BlockPosUtil.getFromNBT(tag, i + 1), i);
      }

      decrNextButton(stack);
   }


   @Override
   public int getCECost() {
      return CE_COST;
   }
}
