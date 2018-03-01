package com.grumpybear.chromeng.block.tile;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileCE extends TileEntity implements ITickable {

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, @Nonnull IBlockState oldState, @Nonnull IBlockState newState) {
		return oldState.getBlock() != newState.getBlock();
	}

	@Nonnull
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound par1nbtTagCompound) {
		super.writeToNBT(par1nbtTagCompound);
		writePacketNBT(par1nbtTagCompound);
		return par1nbtTagCompound;
	}

	@Nonnull
	@Override
	public final NBTTagCompound getUpdateTag() {
		NBTTagCompound updateTag = super.getUpdateTag();
		writeClientDataToNBT(updateTag);
		return updateTag;
	}

	@Override
	public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
		super.readFromNBT(par1nbtTagCompound);
		readPacketNBT(par1nbtTagCompound);
	}

	public void writePacketNBT(NBTTagCompound cmp) {}

	public void readPacketNBT(NBTTagCompound cmp) {}

	@Nullable
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound nbtTag = new NBTTagCompound();
		this.writeClientDataToNBT(nbtTag);
		return new SPacketUpdateTileEntity(pos, 1, nbtTag);
	}

	public void writeClientDataToNBT(NBTTagCompound tagCompound) {
		writeToNBT(tagCompound);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
		readClientDataFromNBT(packet.getNbtCompound());
	}


	public void readClientDataFromNBT(NBTTagCompound tagCompound) {
		readFromNBT(tagCompound);
	}

	@Override
	public void update() {}

}
