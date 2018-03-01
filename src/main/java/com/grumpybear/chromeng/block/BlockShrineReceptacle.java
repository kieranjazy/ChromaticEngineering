package com.grumpybear.chromeng.block;

import com.grumpybear.chromeng.block.tile.TileReceptacle;
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
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler;

import javax.annotation.Nonnull;

public class BlockShrineReceptacle extends BlockCE {

	public BlockShrineReceptacle() {
		super(Material.ROCK, LibBlocks.SHRINE_RECEPTACLE);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {	
		TileEntity tile = worldIn.getTileEntity(pos);
		
		if (tile instanceof TileReceptacle) {
			tile = (TileReceptacle) tile;
			
			FluidUtil.interactWithFluidHandler(playerIn, hand, (IFluidHandler) tile);
		}
		
		return true;
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
		return new TileReceptacle();
	}
	
}
