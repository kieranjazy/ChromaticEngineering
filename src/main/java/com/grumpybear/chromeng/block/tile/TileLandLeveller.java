package com.grumpybear.chromeng.block.tile;

import com.grumpybear.chromeng.chroma.ChromaStorage;
import com.grumpybear.chromeng.chroma.EnumColour;

public class TileLandLeveller extends TileCEStorage {

   public ChromaStorage chromaStorage;
   private final int CHROMA_MAX = 1000;

   public TileLandLeveller() {
      chromaStorage = new ChromaStorage(CHROMA_MAX);
   }






   @Override
   public ChromaStorage getChromaStorage() {
      return chromaStorage;
   }

   @Override
   public int getSizeInventory() {
      return 1;
   }
}
