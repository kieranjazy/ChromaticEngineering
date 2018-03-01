package com.grumpybear.chromeng.init;

import com.grumpybear.chromeng.item.*;
import com.grumpybear.chromeng.item.workonlater.ItemBlockDesignator;
import com.grumpybear.chromeng.item.workonlater.ItemLocationCard;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber
public class ModItems {
	public static Item locationCard = new ItemLocationCard();
	public static Item blockDesignator = new ItemBlockDesignator();
	public static Item chromaDust = new ItemChromaDust();
	public static Item darksign = new ItemDarksign();
	public static Item palette = new ItemPalette();
	public static Item lordvessel = new ItemLordvessel();
	public static Item extensionConduit = new ItemExtensionConduit();
	public static Item inspector = new ItemInspector();
	public static Item voidVacuum = new ItemVoidVacuum();
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> evt) {
		IForgeRegistry<Item> registry = evt.getRegistry();
		registry.register(locationCard);
		registry.register(blockDesignator);
		registry.register(chromaDust);
		registry.register(darksign);
		registry.register(palette);
		registry.register(lordvessel);
		registry.register(extensionConduit);
		registry.register(inspector);
		registry.register(voidVacuum);
	}
}
