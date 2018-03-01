package com.grumpybear.chromeng.init;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

/**
 * Created by Kieran on 7/12/2017.
 */
public class CEKeyBindings {
   public static KeyBinding key;

   public static void init() {
      key = new KeyBinding("Mode Change", Keyboard.KEY_B, "CE");
      ClientRegistry.registerKeyBinding(key);
   }
}
