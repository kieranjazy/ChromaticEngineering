package com.grumpybear.chromeng.item;

import com.grumpybear.chromeng.chroma.EnumColour;
import com.grumpybear.chromeng.init.CEKeyBindings;
import com.grumpybear.chromeng.lib.LibItems;
import com.grumpybear.chromeng.network.ChromEngPacketHandler;
import com.grumpybear.chromeng.network.MessageSwitchMode;
import com.grumpybear.chromeng.util.ItemStackUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.play.server.SPacketMultiBlockChange;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemVoidVacuum extends ItemChargeSingle implements IModeItem { //Suck/Place and Void

   private final int CE_COST = 5;
   private int prefMode;

   public final String[] MODE_NAMES = {
           "Suck", //0
           "Place", //1
           "Void" //2
   };

   public ItemVoidVacuum() {
      super(LibItems.VOID_VACUUM, EnumColour.VIOLET);
      this.prefMode = 1;
   }

   @Override
   public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      NBTTagCompound voidNBT = ItemStackUtil.getNBT(player.getHeldItemMainhand());

      /*
      if (player.getHeldItemMainhand().getItem() instanceof ItemVoidVacuum) {
         if (minusCE(player.getHeldItemMainhand())) {
            if (!voidNBT.getBoolean("hasBlock")) {
               NBTUtil.writeBlockState(voidNBT, worldIn.getBlockState(pos));
               voidNBT.setBoolean("hasBlock", true);
               worldIn.setBlockToAir(pos);
               setMode(player.getHeldItemMainhand(), prefMode);
            } else {
               if (getMode(player.getHeldItemMainhand()) == 1) {
                  worldIn.setBlockState(pos.offset(facing), NBTUtil.readBlockState(voidNBT));
                  worldIn.spawnParticle(EnumParticleTypes.END_ROD, pos.offset(facing).getX(), pos.offset(facing).getY(), pos.offset(facing).getZ(), 5, 5, 5);
                  prefMode = 1;
               } else if (getMode(player.getHeldItemMainhand()) == 2) {
                  ItemStackUtil.getNBT(player.getHeldItemMainhand()).removeTag("hasBlock");
                  prefMode = 2;
               }

               voidNBT.setBoolean("hasBlock", false);
               setMode(player.getHeldItemMainhand(), 0);
            }
         }
      } */ //If held item is vacuum and has enough CE, if it doesn't have a block then add target block and say it has a block, delete target, if has a block then perform mode func...

      if (player.getHeldItemMainhand().getItem() instanceof ItemVoidVacuum) {
         if (minusCE(player.getHeldItemMainhand())) {
            if (getMode(player.getHeldItemMainhand()) == 0) {
               NBTUtil.writeBlockState(voidNBT, worldIn.getBlockState(pos));
               worldIn.setBlockToAir(pos);
               setModeClient(player.getHeldItemMainhand(), prefMode);
            } else if (getMode(player.getHeldItemMainhand()) == 1) {
               worldIn.setBlockState(pos.offset(facing), NBTUtil.readBlockState(voidNBT));
               worldIn.spawnParticle(EnumParticleTypes.END_ROD, pos.offset(facing).getX(), pos.offset(facing).getY(), pos.offset(facing).getZ(), 5, 5, 5);
               setModeClient(player.getHeldItemMainhand(), 0);
               prefMode = 1;
            } else if (getMode(player.getHeldItemMainhand()) == 2) {
               NBTUtil.writeBlockState(voidNBT, Blocks.AIR.getDefaultState());
               setModeClient(player.getHeldItemMainhand(), 0);
               prefMode = 2;
            }
         }
      }





      return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
   }

   /*
   @Override
   public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
      if (playerIn.getHeldItemMainhand().getItem() instanceof ItemVoidVacuum && ItemStackUtil.getNBT(playerIn.getHeldItemMainhand()).getBoolean("hasBlock")) {
         if (getMode(playerIn.getHeldItemMainhand()) == 1 && minusCE(playerIn.getHeldItemMainhand())) {
            ItemStackUtil.getNBT(playerIn.getHeldItemMainhand()).removeTag("hasBlock");
         }
      }

      return super.onItemRightClick(worldIn, playerIn, handIn);
   }
*/
   @Override
   public int getCECost() {
      return CE_COST;
   }


   @Override
   public String[] getModeNames() {
      return MODE_NAMES;
   }

   @Override
   public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
      /*
      if (isSelected && !worldIn.isRemote) {
         if (CEKeyBindings.key.isPressed()) {
            if (getMode(stack) != 0) {
               setMode(stack, getMode(stack) == 1 ? 2 : 1);
            }
         }
      }
      */
   }

   @Override
   public boolean updateItemStackNBT(NBTTagCompound nbt) {
      return true;
   }
}
