package com.grumpybear.chromeng.world;

import java.util.Random;

import com.grumpybear.chromeng.init.ModBlocks;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.IWorldGenerator;

public class CEWorldGen implements IWorldGenerator{

	private WorldGenMinable chromaGenerator;
	
	public CEWorldGen() {
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		generateWorld(random, chunkX, chunkZ, world);
		
	}
	
	private void generateWorld(Random random, int chunkX, int chunkZ, World world) {
		if (chromaGenerator == null) {
			chromaGenerator = new WorldGenMinable(ModBlocks.chromaOre.getDefaultState(), 6);
		}
		
		int x = chunkX << 4;
		int y = chunkZ << 4;
		
		for (int i = 0; i < 18; i++) {
			int randPosX = x + random.nextInt(16);
			int randPosY = random.nextInt(76) + 16;
			int randPosZ = y + random.nextInt(16);
			chromaGenerator.generate(world, random, new BlockPos(randPosX, randPosY, randPosZ));
		}
	}

}
