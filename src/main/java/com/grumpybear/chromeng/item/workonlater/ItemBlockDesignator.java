package com.grumpybear.chromeng.item.workonlater;

import com.grumpybear.chromeng.item.ItemCE;
import com.grumpybear.chromeng.lib.LibItems;
import com.grumpybear.chromeng.util.BlockPosUtil;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemBlockDesignator extends ItemCE{

	public ItemBlockDesignator() {
		super(LibItems.BLOCK_DESIGNATOR);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
		
		BlockPos newPos = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
		
		
		if (getNBT(player.getHeldItemMainhand()).getLong(newPos.toString()) == 0) {
			addToNBT(newPos.toString(), BlockPosUtil.posToLong(newPos), player.getHeldItemMainhand());
		} else {
			removeFromNBT(newPos.toString(), player.getHeldItemMainhand());
		}
		
		return EnumActionResult.SUCCESS;
	}
	
	
	
	
	
	public static NBTTagCompound getNBT(ItemStack stack) {
		if(!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());
		return stack.getTagCompound();
	}
	
	public void addToNBT(String key, long l, ItemStack stack) {
		getNBT(stack).setLong(key, l);
	}
	
	public void removeFromNBT(String key, ItemStack stack) {
		getNBT(stack).removeTag(key);
	}

	
	
}
