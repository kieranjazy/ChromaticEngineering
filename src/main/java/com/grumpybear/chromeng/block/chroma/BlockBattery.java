package com.grumpybear.chromeng.block.chroma;

import com.grumpybear.chromeng.ChromEng;
import com.grumpybear.chromeng.block.BlockCE;
import com.grumpybear.chromeng.block.tile.TileBattery;
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

import javax.annotation.Nonnull;

/**
 * Created by Kieran on 7/25/2017.
 */
public class BlockBattery extends BlockCE {
   public BlockBattery() {
      super(Material.IRON, LibBlocks.BATTERY);
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
      return new TileBattery();
   }

   @Override
   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
                                   EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      if (!worldIn.isRemote) {
         playerIn.openGui(ChromEng.instance, GuiHandler.BATTERY, worldIn, pos.getX(), pos.getY(), pos.getZ());
      }
      return true;
   }

}
