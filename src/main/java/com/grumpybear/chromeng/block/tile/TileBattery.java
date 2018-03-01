package com.grumpybear.chromeng.block.tile;

import com.grumpybear.chromeng.chroma.ChromaStorage;

/**
 * Created by Kieran on 7/25/2017.
 */
public class TileBattery extends TileCEStorage{

   public ChromaStorage chromaStorage;

   private final int CHROMA_MAX = 1000;

   public TileBattery() {
      this.chromaStorage = new ChromaStorage(CHROMA_MAX);
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
