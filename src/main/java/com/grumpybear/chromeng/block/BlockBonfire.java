package com.grumpybear.chromeng.block;

import com.grumpybear.chromeng.ChromEng;
import com.grumpybear.chromeng.block.tile.TileBonfire;
import com.grumpybear.chromeng.handler.GuiHandler;
import com.grumpybear.chromeng.lib.LibBlocks;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class BlockBonfire extends BlockCE{
	

	public BlockBonfire() {
		super(Material.ROCK, LibBlocks.BONFIRE);
	}

	
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        return super.canPlaceBlockAt(worldIn, pos) && worldIn.isAirBlock(pos.up());
    }

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
		
		if (playerIn.getHeldItemMainhand().getItem() == Items.FLINT_AND_STEEL) {
			if (ritualSetup(pos, worldIn)) {
				if (worldIn.getTileEntity(pos) instanceof TileBonfire) {
					TileBonfire tile = (TileBonfire) worldIn.getTileEntity(pos);
				
					tile.setBurning(true);
				}
			}
		} else {
			if (!worldIn.isRemote)
				playerIn.openGui(ChromEng.instance, GuiHandler.BONFIRE, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		
		return true;
	}
	
	private boolean ritualSetup(BlockPos pos, World world) {
		BlockPos[] blocks = new BlockPos[16];
		for (int i = 0; i < 3; i++) {
			blocks[i] = pos.add( i - 1, 0, 1);
		}
		
		blocks[3] = pos.add(-1, 0, 0);
		blocks[4] = pos.add(1, 0, 0);
		
		for (int i = 5; i < 8; i++) {
			blocks[i] = pos.add(i - 6, 0, -1);
		}
		
		for (int i = 8; i < 16; i++) {
			blocks[i] = blocks[i - 8].offset(EnumFacing.UP);
		}
		
		for (BlockPos bpos : blocks) {
			if (world.getBlockState(bpos).getBlock() != Blocks.AIR)
				return false;
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
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileBonfire();
	}

}
