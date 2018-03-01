package com.grumpybear.chromeng.block;

import com.grumpybear.chromeng.init.ModItems;
import com.grumpybear.chromeng.lib.LibBlocks;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Random;

public class BlockChromaOre extends BlockCE{

	public BlockChromaOre() {
		super(Material.IRON, LibBlocks.CHROMA_ORE);
		OreDictionary.registerOre(LibBlocks.CHROMA_ORE, this);
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return ModItems.chromaDust;
	}
	
	@Override
	public int quantityDropped(Random random) {
		return 3 + random.nextInt(2);
	}

}
