package com.grumpybear.chromeng.init;

import com.grumpybear.chromeng.block.BlockBonfire;
import com.grumpybear.chromeng.block.BlockChromaOre;
import com.grumpybear.chromeng.block.BlockLandLeveller;
import com.grumpybear.chromeng.block.BlockShrineReceptacle;
import com.grumpybear.chromeng.block.chroma.BlockBattery;
import com.grumpybear.chromeng.block.chroma.BlockChromaticExtractor;
import com.grumpybear.chromeng.block.tile.*;
import com.grumpybear.chromeng.block.workonlater.BlockEnergiser;
import com.grumpybear.chromeng.block.workonlater.BlockFluidDisplacer;
import com.grumpybear.chromeng.lib.LibBlocks;
import com.grumpybear.chromeng.lib.LibMain;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber
public class ModBlocks {
	public static Block fluidDisplacer = new BlockFluidDisplacer();
	public static Block shrineReceptacle = new BlockShrineReceptacle();
	public static Block energiser = new BlockEnergiser();
	public static Block chromaOre = new BlockChromaOre();
	public static Block extractor = new BlockChromaticExtractor();
	public static Block bonfire = new BlockBonfire();
	public static Block battery = new BlockBattery();
	public static Block landLeveller = new BlockLandLeveller();

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> evt) {
		IForgeRegistry<Block> registry = evt.getRegistry();
		registry.register(fluidDisplacer);
		registry.register(shrineReceptacle);
		registry.register(energiser);
		registry.register(chromaOre);
		registry.register(extractor);
		registry.register(bonfire);
		registry.register(battery);
		registry.register(landLeveller);

		initTileEntities();
	}

	@SubscribeEvent
	public static void registerItemBlocks(RegistryEvent.Register<Item> evt) {
		IForgeRegistry<Item> registry = evt.getRegistry();

		registry.register(new ItemBlock(fluidDisplacer).setRegistryName(fluidDisplacer.getRegistryName()));
		registry.register(new ItemBlock(shrineReceptacle).setRegistryName(shrineReceptacle.getRegistryName()));
		registry.register(new ItemBlock(energiser).setRegistryName(energiser.getRegistryName()));
		registry.register(new ItemBlock(chromaOre).setRegistryName(chromaOre.getRegistryName()));
		registry.register(new ItemBlock(extractor).setRegistryName(extractor.getRegistryName()));
		registry.register(new ItemBlock(bonfire).setRegistryName(bonfire.getRegistryName()));
		registry.register(new ItemBlock(battery).setRegistryName(battery.getRegistryName()));
		registry.register(new ItemBlock(landLeveller).setRegistryName(landLeveller.getRegistryName()));
	}

	
	public static void initTileEntities() {
		registerTile(TileShrine.class, LibBlocks.ESOTERIC_SHRINE);
		registerTile(TileDisplacer.class, LibBlocks.FLUID_DISPLACER);
		registerTile(TileReceptacle.class, LibBlocks.SHRINE_RECEPTACLE);
		registerTile(TileEnergiser.class, LibBlocks.ENERGISER);
		registerTile(TileExtractor.class, LibBlocks.CHROMATIC_EXTRACTOR);
		registerTile(TileBonfire.class, LibBlocks.BONFIRE);
		registerTile(TileBattery.class, LibBlocks.BATTERY);
		registerTile(TileLandLeveller.class, LibBlocks.LAND_LEVELLER);
	}
	
	private static void registerTile(Class<? extends TileEntity> clazz, String key) {
		GameRegistry.registerTileEntity(clazz, LibMain.MOD_ID + ":" + key);
	}
}
