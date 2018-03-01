package com.grumpybear.chromeng.item.workonlater;

import java.util.ArrayList;

import com.grumpybear.chromeng.item.ItemCE;
import com.grumpybear.chromeng.lib.LibItems;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class ItemLocationCard extends ItemCE{
	
	public static final String BLOCK_1 = "blockpos1";
	public static final String BLOCK_2 = "blockpos2";
	
	public ArrayList<BlockPos> blockList;
	
			
	public ItemLocationCard() {
		super(LibItems.LOCATION_CARD);
		blockList = new ArrayList<BlockPos>();
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!player.isSneaking()) {
			addLocation(posToLong(pos), BLOCK_1, player.getHeldItemMainhand());
			addToBlockList(player.getHeldItemMainhand());
			if (!worldIn.isRemote) {
				player.sendMessage(new TextComponentString("Location 1 set to X:" + pos.getX() + " Y:" + pos.getY() + " Z:" + pos.getZ()));
			}
		} else {
			addLocation(posToLong(pos), BLOCK_2, player.getHeldItemMainhand());
			addToBlockList(player.getHeldItemMainhand());
			if (!worldIn.isRemote) {
				player.sendMessage(new TextComponentString("Location 2 set to X:" + pos.getX() + " Y:" + pos.getY() + " Z:" + pos.getZ()));
			}
		}
		return EnumActionResult.SUCCESS;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if (!playerIn.isSneaking()) {
			if (!worldIn.isRemote) {
				BlockPos pos = retPos(playerIn, BLOCK_1);
				playerIn.sendMessage(new TextComponentString("Location 1 is X:" + pos.getX() + " Y:" + pos.getY() + " Z:" + pos.getZ()));
			}
		} else {
			if (!worldIn.isRemote) {
				BlockPos pos = retPos(playerIn, BLOCK_2);
				playerIn.sendMessage(new TextComponentString("Location 2 is X:" + pos.getX() + " Y:" + pos.getY() + " Z:" + pos.getZ()));
			}
		}
		return ActionResult.newResult(EnumActionResult.SUCCESS, playerIn.getHeldItemMainhand());
	}

	public boolean addToBlockList(ItemStack stack) {
		BlockPos bpos1;
		BlockPos bpos2;
		
		if (getNBT(stack).getLong(BLOCK_1) == 0 || getNBT(stack).getLong(BLOCK_2) == 0) {
			return false;
		}
		
		BlockPos pos1 = longToPos(getNBT(stack).getLong(BLOCK_1));
		BlockPos pos2 = longToPos(getNBT(stack).getLong(BLOCK_2));
		
		if (Math.max(pos1.getX(), pos2.getX()) == pos1.getX()) {
			bpos2 = pos1;
			bpos1 = pos2;
		} else {
			bpos2 = pos2;
			bpos1 = pos1;
		}
		
		for (int j = 0; j < ((bpos2.getX() - bpos1.getX()) + 1); j++) {
			for (int i = 0; i < ((bpos2.getZ() - bpos1.getZ()) + 1); i++) {
				BlockPos temp = new BlockPos(bpos1.getX() + j, 0, bpos1.getZ() + i);
				blockList.add(temp);
			}
		}
		
		for (int i = 1; i < blockList.size() - 2; i++) {
			addLocation(posToLong(blockList.get(i)), Integer.toString(i), stack);
		}
		
		return true;
	}
	
	// Adds BlockPos converted to unique long to the NBT
	public void addLocation(long value, String key, ItemStack stack) {
		getNBT(stack).setLong(key, value);
	}
	

	// Changes BlockPos to unique long
	public long posToLong(BlockPos pos) {
		return pos.toLong();
	}
	
	// Gets BlockPos from unique long
	public BlockPos longToPos(long l) {
		if (l == 0) {
			
		}
		return BlockPos.fromLong(l);
	}
	
	public BlockPos retPos(EntityPlayer player, String block) {
		return longToPos(getNBT(player.getHeldItemMainhand()).getLong(block));
	}
	
	public static NBTTagCompound getNBT(ItemStack stack) {
		if(!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());
		return stack.getTagCompound();
	}

}
