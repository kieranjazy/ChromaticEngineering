package com.grumpybear.chromeng.item;

import com.grumpybear.chromeng.chroma.EnumColour;
import com.grumpybear.chromeng.init.CEKeyBindings;
import com.grumpybear.chromeng.lib.LibItems;
import com.grumpybear.chromeng.network.ChromEngPacketHandler;
import com.grumpybear.chromeng.network.MessageSwitchMode;
import com.grumpybear.chromeng.util.BlockPosUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import java.util.LinkedHashSet;
import java.util.Set;

import static com.grumpybear.chromeng.util.BlockPosUtil.*;
import static com.grumpybear.chromeng.util.ItemStackUtil.getNBT;

/**
 * Created by Kieran on 7/12/2017.
 */
public class ItemExtensionConduit extends ItemChargeSingle implements IModeItem{

   private final int CE_COST = 4;

   public final String[] MODE_NAMES = {
           "Base Selection",
           "Bound Selection",
           "Activation"
   };

   public ItemExtensionConduit() {
      super(LibItems.EXTENSION_CONDUIT, EnumColour.YELLOW);
   }

   @Override
   public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
         NBTTagCompound temp = getNBT(player.getHeldItem(hand));

         if (getMode(player.getHeldItemMainhand()) == 0) {

            if (nbtHasPos(temp, 0)) {
               if (player.isSneaking()) {
                  if (arePosSame(pos, BlockPosUtil.getFromNBT(temp, 0))) {
                     emptyNBT(temp, 0);
                  }
               } else {
                  emptyNBT(temp, 0);
                  emptyNBT(temp, 1);
                  writeToNBT(temp, pos, 0);
               }
            } else {
               if (worldIn.getTileEntity(pos) == null) {
                  writeToNBT(temp, pos, 0);
               }
            }
         } else if (getMode(player.getHeldItemMainhand()) == 1 && nbtHasPos(temp, 0)) { //                       X == East/West    Y == Up/Down    Z == North/South
            BlockPos tempPos = getFromNBT(temp, 0);
            if (player.isSneaking()) {
               if (nbtHasPos(temp, 1)) {
                  emptyNBT(temp, 1);
               }
            } else {
               if (worldIn.getBlockState(pos).getBlock() == worldIn.getBlockState(tempPos).getBlock()) {
                  if (pos.getX() == tempPos.getX() || pos.getY() == tempPos.getY() || pos.getZ() == tempPos.getZ()) {
                     if (pos.getX() == tempPos.getX()) {
                        if ((pos.getY() <= tempPos.getY() + 16 && pos.getY() >= tempPos.getY() - 16) && (pos.getZ() <= tempPos.getZ() + 16 && pos.getZ() >= tempPos.getZ() - 16)) {
                           writeToNBT(temp, pos, 1);
                        }
                     } else if (pos.getY() == tempPos.getY()) {
                        if ((pos.getX() <= tempPos.getX() + 16 && pos.getX() >= tempPos.getX() - 16) && (pos.getZ() <= tempPos.getZ() + 16 && pos.getZ() >= tempPos.getZ() - 16)) {
                           writeToNBT(temp, pos, 1);
                        }
                     } else {
                        if ((pos.getY() <= tempPos.getY() + 16 && pos.getY() >= tempPos.getY() - 16) && (pos.getX() <= tempPos.getX() + 16 && pos.getX() >= tempPos.getX() - 16)) {
                           writeToNBT(temp, pos, 1);
                        }
                     }
                  }
               }
            }
         } else if (getMode(player.getHeldItemMainhand()) == 2 && ((nbtHasPos(temp,0) && nbtHasPos(temp, 1)) && (arePosSame(pos, getFromNBT(temp, 0)) || (arePosSame(pos, getFromNBT(temp, 1)))))) {
            BlockPos pos1 = getFromNBT(temp, 0);
            BlockPos pos2 = getFromNBT(temp, 1);

            Set<BlockPos> blocks = getFinalBlockList(pos1, pos2);

               for (BlockPos blockPos : blocks) {
                  Block block = worldIn.getBlockState(pos1).getBlock();

                  IItemHandler inv = player.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

                  for (int i = inv.getSlots() - 1; i >= 0; i--) {
                     ItemStack stack = inv.getStackInSlot(i);

                     if (stack.isEmpty())
                        continue;

                     Item item = stack.getItem();

                     if (item == Item.getItemFromBlock(block) && stack.getItemDamage() == block.getMetaFromState(worldIn.getBlockState(pos1)) && (worldIn.getBlockState(blockPos) != worldIn.getBlockState(pos1) && worldIn.getTileEntity(blockPos) == null)) { //Not being called
                        if (minusCE(player.getHeldItemMainhand(), CE_COST) || player.capabilities.isCreativeMode) {
                           inv.extractItem(i, 1, false);
                           worldIn.setBlockState(blockPos, worldIn.getBlockState(pos1));
                        }
                     }
                  }
               }

               emptyNBT(temp, 0);
               emptyNBT(temp, 1);

               if (player.isSneaking()) {
                  setMode(player.getHeldItemMainhand(), 0);
               } else {
                  setMode(player.getHeldItemMainhand(), 1);
                  writeToNBT(temp, pos, 0);
               }
         }
      return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
   }

   @Override
   public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
      if (isSelected && !worldIn.isRemote) {
          if (CEKeyBindings.key.isPressed()) {
             switchMode(stack, new MessageSwitchMode(stack));
         }
     }
   }

   public Set<BlockPos> getFinalBlockList(BlockPos pos1, BlockPos pos2) {
      Set<BlockPos> blocks = new LinkedHashSet<BlockPos>() {{
         add(pos1);
         add(pos2);
      }};

      for (int j = 0; j < ((Math.max(pos1.getX(), pos2.getX()) - Math.min(pos1.getX(), pos2.getX())) + 1); j++) {
         for (int i = 0; i < ((Math.max(pos1.getZ(), pos2.getZ()) - Math.min(pos1.getZ(), pos2.getZ())) + 1); i++) {
            for (int k = 0; k < ((Math.max(pos1.getY(), pos2.getY()) - Math.min(pos1.getY(), pos2.getY())) + 1); k++) {
               BlockPos tempPos = new BlockPos(Math.min(pos1.getX(), pos2.getX()) + j, Math.min(pos1.getY(), pos2.getY()) + k, Math.min(pos1.getZ(), pos2.getZ()) + i);        //+X -Y && -X +Y     These directions aren't being detected
               blocks.add(tempPos);
            }
         }
      }

      return blocks;
   }

   @Override
   public String[] getModeNames() {
      return this.MODE_NAMES;
   }

   @Override
   public int getCECost() {
      return CE_COST;
   }
}
