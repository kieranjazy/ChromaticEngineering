package com.grumpybear.chromeng.chroma;

import com.grumpybear.chromeng.util.EnumColourUtil;
import net.minecraft.nbt.NBTTagCompound;

public class ChromaUnit {
	int currentCE;
	int maxCE;
	
	EnumColour chromaType;
	
	public ChromaUnit(EnumColour chroma) {
		this.maxCE = 1000;
		this.currentCE = 0;
		this.chromaType = chroma;
	}
	
	public ChromaUnit(EnumColour chroma, int max) {
		this(chroma);
		this.maxCE = max;
	}
	
	public EnumColour getChromaType() {
		return chromaType;
	}
	
	public int getCurrentCE() {
		return currentCE;
	}
	
	public int getMaxCE() {
		return maxCE;
	}
	
	public void addCurrentCE(int currentCE) {
		if (this.currentCE + currentCE <= this.maxCE)
			this.currentCE += currentCE;
	}

	public void minusCurrentCE(int currentCE) {
		if (this.currentCE - currentCE >= 0)
			this.currentCE -= currentCE;
	}

	public boolean canMinusCE(int value) {
		if (this.currentCE - value >= 0)
			return true;

		return false;
	}

	public boolean canAddCE(int value) {
		if (this.currentCE + value <= maxCE)
			return true;

		return false;
	}
	
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		String temp = EnumColourUtil.colourToString(chromaType);
		
		nbt.setInteger("Current" + temp, currentCE);
		nbt.setInteger("Max" + temp, maxCE);
		nbt.setByte("Colour" + temp, EnumColourUtil.colourToByte(chromaType));
		
		return nbt;
	}
	
	public void readFromNBT(NBTTagCompound nbt) {
		String temp = EnumColourUtil.colourToString(chromaType);
		
		this.currentCE = nbt.getInteger("Current" + temp);
		this.maxCE = nbt.getInteger("Max" + temp);
		this.chromaType = EnumColourUtil.byteToColour(nbt.getByte("Colour" + temp));
	}

	public boolean isEmpty() {
		if (this.currentCE == 0) {
			return true;
		}

		return false;
	}
	
	public void setCurrentCE(int currentCE) {
		this.currentCE = currentCE;
	}
}
