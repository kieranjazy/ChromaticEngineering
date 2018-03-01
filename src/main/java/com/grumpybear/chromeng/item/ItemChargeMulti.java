package com.grumpybear.chromeng.item;

import com.grumpybear.chromeng.chroma.ChromaStorage;
import com.grumpybear.chromeng.chroma.EnumColour;
import com.grumpybear.chromeng.util.ItemStackUtil;
import net.minecraft.item.ItemStack;

/**
 * Created by Kieran on 6/27/2017.
 */
public class ItemChargeMulti extends ItemCE {
   public ItemChargeMulti(String name) {
      super(name);
   }

   public void addCE(ItemStack stack, int i, EnumColour colour) {
      ChromaStorage storage = getChromaStorage(stack);

      storage.getColour(colour).addCurrentCE(i);

      ChromaStorage.storageToNBT(storage, ItemStackUtil.getNBT(stack));
   }

   public void minusCE(ItemStack stack, int i, EnumColour colour) {
      ChromaStorage storage = getChromaStorage(stack);

      storage.getColour(colour).minusCurrentCE(i);

      ChromaStorage.storageToNBT(storage, ItemStackUtil.getNBT(stack));
   }



   public ChromaStorage getChromaStorage(ItemStack stack) {
      return ChromaStorage.storageFromNBT(ItemStackUtil.getNBT(stack));
   }


}
