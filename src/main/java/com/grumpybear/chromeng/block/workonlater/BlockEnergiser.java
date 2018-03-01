package com.grumpybear.chromeng.block.workonlater;

import javax.annotation.Nonnull;

import com.grumpybear.chromeng.ChromEng;
import com.grumpybear.chromeng.block.BlockCE;
import com.grumpybear.chromeng.block.tile.TileEnergiser;
import com.grumpybear.chromeng.handler.GuiHandler;
import com.grumpybear.chromeng.lib.LibBlocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockEnergiser extends BlockCE{
	public BlockEnergiser() {
		super(Material.IRON, LibBlocks.ENERGISER);
	}
	
	@Nonnull
	@Override
	public BlockStateContainer createBlockState() {
		return new BlockStateContainer(this);
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
	
	@Nonnull
	@Override
	public TileEntity createTileEntity(@Nonnull World world, @Nonnull IBlockState state) {
		return new TileEnergiser();
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			player.openGui(ChromEng.instance, GuiHandler.ENERGISER, world, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}
}
