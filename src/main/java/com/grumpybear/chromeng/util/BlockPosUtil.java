package com.grumpybear.chromeng.util;

import io.netty.buffer.ByteBuf;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.LinkedHashSet;
import java.util.Set;

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

	public static Set<BlockPos> calculateLandLeveller(BlockPos pos1, BlockPos pos2, World world) {
		Set<BlockPos> blocks = new LinkedHashSet<>();
		int x1, y1, z1, x2, y2, z2;

		if (pos1.getX() > pos2.getX()) {
			x1 = pos2.getX();
			x2 = pos1.getX();
		} else {
			x1 = pos1.getX();
			x2 = pos2.getX();
		}

		if (pos1.getY() > pos2.getY()) {
			y1 = pos2.getY();
			y2 = pos1.getY();
		} else {
			y1 = pos1.getY();
			y2 = pos2.getY();
		}

		if (pos1.getZ() > pos2.getZ()) {
			z1 = pos2.getZ();
			z2 = pos1.getZ();
		} else {
			z1 = pos1.getZ();
			z2 = pos2.getZ();
		}

		for (int x = x1; x <= x2; x++) {
			for (int y = y1; y <= y2; y++) {
				for (int z = z1; z <= z2; z++) {
					BlockPos temp = new BlockPos(x, y, z);
					if (world.getBlockState(temp).getBlock() == Blocks.DIRT.getBlockState().getBlock() || world.getBlockState(temp) == Blocks.SAND.getBlockState().getBlock())
						blocks.add(new BlockPos(x, y, z));
				}
			}
		}


		return blocks;
	}
}
