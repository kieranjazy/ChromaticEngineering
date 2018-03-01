package com.grumpybear.chromeng.util;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;

public class BlockPosUtil {
	

	// Changes BlockPos to unique long
	public static long posToLong(BlockPos pos) {
		return pos.toLong();
	}
	
	// Gets BlockPos from unique long
	public static BlockPos longToPos(long l) {
		if (l == 0) {
			
		}
		return BlockPos.fromLong(l);
	}

	public static void writeToNBT(NBTTagCompound nbt, BlockPos pos, int posID) {
		nbt.setInteger("x" + posID, pos.getX());
		nbt.setInteger("y" + posID, pos.getY());
		nbt.setInteger("z" + posID, pos.getZ());
	}

	public static BlockPos getFromNBT(NBTTagCompound nbt, int posID) {
		return new BlockPos(nbt.getInteger("x" + posID), nbt.getInteger("y" + posID), nbt.getInteger("z" + posID));
	}

	public static boolean nbtHasPos(NBTTagCompound nbt, int posID) {
		if (nbt.hasKey("x" + posID) && nbt.hasKey("y" + posID) && nbt.hasKey("z" + posID))
			return true;

		return false;
	}

	public static void emptyNBT(NBTTagCompound nbt, int posID) {
		nbt.removeTag("x" + posID);
		nbt.removeTag("y" + posID);
		nbt.removeTag("z" + posID);
	}

	public static boolean arePosSame(BlockPos p1, BlockPos p2) {
		if (p1.getX() == p2.getX() && p1.getY() == p2.getY() && p1.getZ() == p2.getZ())
			return true;

		return false;
	}

	public static void writeToByteBuf(BlockPos pos, ByteBuf buf) {
		buf.writeInt(pos.getX());
		buf.writeInt(pos.getY());
		buf.writeInt(pos.getZ());
	}

	public static BlockPos readFromByteBuf(ByteBuf buf) {
		return new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
	}
}
