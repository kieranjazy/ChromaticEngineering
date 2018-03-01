package com.grumpybear.chromeng.item;

import com.grumpybear.chromeng.block.tile.TileBonfire;
import com.grumpybear.chromeng.lib.LibItemTypes;
import com.grumpybear.chromeng.lib.LibItems;
import com.grumpybear.chromeng.util.BlockPosUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

import static com.grumpybear.chromeng.util.ItemStackUtil.getNBT;

public class ItemDarksign extends ItemChargeSingle {
	public final int CE_COST = 500;
	
	public ItemDarksign() {
		super(LibItems.DARKSIGN, LibItemTypes.DARKSIGN);
	}

	public static boolean isLinked(ItemStack stack) {
		if (getNBT(stack).hasKey("Burning"))
			return true;

		return false;
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
		if (entityLiving instanceof EntityPlayer) {
			EntityPlayer playerIn = (EntityPlayer) entityLiving;
			NBTTagCompound tag = getNBT(stack);
			if (this.minusCE(stack, CE_COST)) {
				BlockPos pos = BlockPosUtil.getFromNBT(tag, 0);
				particleLogic(playerIn, worldIn);
				playerIn.setPositionAndUpdate(pos.offset(EnumFacing.SOUTH).getX() + 1, pos.offset(EnumFacing.SOUTH).getY(), pos.offset(EnumFacing.SOUTH).getZ() + 1);
				particleLogic(playerIn, worldIn);
			} else {

			}
		}

		return stack;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 28;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BOW;
	}

	/*
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack stack = playerIn.getHeldItemMainhand();
		NBTTagCompound tag = getNBT(stack);
		if (this.getChromaUnit(stack).getCurrentCE() - CE_COST >= 0) {
			if (!tag.hasNoTags()) {
				if (isLinked(stack)) {
					BlockPos pos = new BlockPos(tag.getInteger("x"), tag.getInteger("y"), tag.getInteger("z"));

					particleLogic(playerIn, worldIn);

					playerIn.setPositionAndUpdate(pos.offset(EnumFacing.SOUTH).getX() + 1, pos.offset(EnumFacing.SOUTH).getY(), pos.offset(EnumFacing.SOUTH).getZ() + 1);
					this.minusCE(stack, CE_COST);

					particleLogic(playerIn, worldIn);
				}
			}
		}
		
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItemMainhand());
	}
	*/

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack stack = playerIn.getHeldItem(handIn);

		if (this.isLinked(stack) && worldIn.getTileEntity(BlockPosUtil.getFromNBT(getNBT(stack), 0)) instanceof TileBonfire) {
			if (((TileBonfire) worldIn.getTileEntity(BlockPosUtil.getFromNBT(getNBT(stack), 0))).isBurning()) {
				playerIn.setActiveHand(handIn);
				return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
			}
		}
		else
		{
		}

		return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);
	}

	private void particleLogic(EntityPlayer player, World worldIn) {
		if (worldIn.isRemote) {
			Random rand = new Random();
			BlockPos pos = player.getPosition();
			double d0 = (double)pos.getX() + 0.5D;
			double d1 = (double)pos.getY() + rand.nextDouble() * 6.0D / 8.0D;
			double d2 = (double)pos.getZ() + 0.5D;

			double motionX = rand.nextGaussian() * 0.02D;
			double motionY = rand.nextGaussian() * 0.02D;
			double motionZ = rand.nextGaussian() * 0.02D;

			for (int i = 0; i < 100; i++)
				worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 - 0.52D, d1, d2 + (rand.nextDouble() * 0.6D - 0.3D), 0.0D, 0.0D, 0.0D);
		}
	}


	@Override
	public int getCECost() {
		return CE_COST;
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World playerIn, List<String> tooltip, ITooltipFlag advanced) {
	   super.addInformation(stack, playerIn, tooltip, advanced);

	   if (isLinked(stack)) {
		   	BlockPos pos = BlockPosUtil.getFromNBT(getNBT(stack), 0);
			tooltip.add("Linked Coords: " + "X:" + pos.getX() + " Y:" + pos.getY() + " Z:" + pos.getZ());
	   }
	}

}
