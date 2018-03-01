package com.grumpybear.chromeng.chroma;

import com.grumpybear.chromeng.item.ItemChargeMulti;
import com.grumpybear.chromeng.item.ItemChargeSingle;
import com.grumpybear.chromeng.util.ItemStackUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.ArrayList;

public class ChromaStorage {
	
	
	
	public ChromaUnit red;
	public ChromaUnit orange;
	public ChromaUnit yellow;
	public ChromaUnit green;
	public ChromaUnit blue;
	public ChromaUnit indigo;
	public ChromaUnit violet;

	public ArrayList<ChromaUnit> activeColours;
	
	int MAX_CE;

	public ChromaStorage(EnumColour c1, EnumColour c2, EnumColour c3, EnumColour c4, EnumColour c5, EnumColour c6, EnumColour c7, int max) {
		MAX_CE = max;
		activeColours = new ArrayList<>();
		
		EnumColour[] params = new EnumColour[] {c1,	c2,	c3, c4, c5, c6, c7};
		
		for (EnumColour colour : params) {
			if (colour == null) {
				
			} else {
		
				switch(colour) {
					case RED:
						red = new ChromaUnit(colour);
						activeColours.add(red);
						break;
					case ORANGE:
						orange = new ChromaUnit(colour);
						activeColours.add(orange);
						break;
					case YELLOW:
						yellow = new ChromaUnit(colour);
						activeColours.add(yellow);
						break;
					case GREEN:
						green = new ChromaUnit(colour);
						activeColours.add(green);
						break;
					case BLUE:
						blue = new ChromaUnit(colour);
						activeColours.add(blue);
						break;
					case INDIGO:
						indigo = new ChromaUnit(colour);
						activeColours.add(indigo);
						break;
					case VIOLET:
						violet = new ChromaUnit(colour);
						activeColours.add(violet);
						break;
				}
			}
		}
	}
	
	public ChromaStorage(EnumColour c1, EnumColour c2, EnumColour c3, EnumColour c4, EnumColour c5, EnumColour c6, int max) {
		this(c1, c2, c3, c4, c5, c6, null, max);
	}
	
	public ChromaStorage(EnumColour c1, EnumColour c2, EnumColour c3, EnumColour c4, EnumColour c5, int max) {
		this(c1, c2, c3, c4, c5, null, max);
	}
	
	public ChromaStorage(EnumColour c1, EnumColour c2, EnumColour c3, EnumColour c4, int max) {
		this(c1, c2, c3, c4, null, max);
	}
	
	public ChromaStorage(EnumColour c1, EnumColour c2, EnumColour c3, int max) {
		this(c1, c2, c3, null, max);
	}
	
	public ChromaStorage(EnumColour c1, EnumColour c2, int max) {
		this(c1, c2, null, max);
	}
	
	public ChromaStorage(EnumColour c1, int max) {
		this(c1, null, max);
	}
	
	public ChromaStorage(int max) { 
		this(EnumColour.RED, EnumColour.ORANGE, EnumColour.YELLOW, EnumColour.GREEN, EnumColour.BLUE, EnumColour.INDIGO, EnumColour.VIOLET, max);
	}
	
	public ChromaUnit getColour(EnumColour colour) {
		for (ChromaUnit unit : this.getChromaUnits()) {
			if (unit.getChromaType() == colour) {
				return unit;
			}
			System.out.println("Current Iter: " + unit.getChromaType());
			System.out.println("Param Colour: " + colour);
		}
		System.out.println("---------------");
		return null;
	}
	
	public ArrayList<ChromaUnit> getChromaUnits() {
		return activeColours;
	}
	
	public int getMaxCE() {
		return MAX_CE;
	}
	
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		for (ChromaUnit unit : getChromaUnits()) {
			unit.writeToNBT(nbt);
		}
		
		return nbt;
	}
	
	public void readFromNBT(NBTTagCompound nbt) {
		for (ChromaUnit unit : getChromaUnits()) {
			unit.readFromNBT(nbt);
		}
	}

	public static ChromaStorage storageFromNBT(NBTTagCompound nbt) {
		ChromaStorage temp = new ChromaStorage(1000);
		if (!ItemStackUtil.hasChromaTags(nbt)) {
			return temp;
		}


		for (ChromaUnit unit : temp.getChromaUnits()) {
			unit.readFromNBT(nbt);
		}

		return temp; //Returns an accurate ChromaStorage with 10CE
	}

	public static void storageToNBT(ChromaStorage storage, NBTTagCompound nbt) {

		for (ChromaUnit unit : storage.getChromaUnits()) {
			unit.writeToNBT(nbt);
		}
		nbt.setBoolean("Empty", storage.isEmpty());
	}

	public boolean isEmpty() {
		for (ChromaUnit unit : getChromaUnits()) {
			if (!unit.isEmpty()) {
				return false;
			}
		}
		return true;
	}

	public ArrayList<EnumColour> getActualColours() {
		ArrayList<EnumColour> colours = new ArrayList<>();
		for (ChromaUnit unit : getChromaUnits()) {
			colours.add(unit.getChromaType());
		}
		return colours;
	}

	public boolean hasColour(EnumColour colour) {
		for (EnumColour eColour : getActualColours()) {
			if (colour == eColour) {
				return true;
			}
		}
		return false;
	}

	public static void addCEMachinetoItem(ChromaUnit p1, ItemStack stack, int value) {
		p1.minusCurrentCE(value);
		if (stack.getItem() instanceof ItemChargeSingle) {
			((ItemChargeSingle) stack.getItem()).addCE(stack, value);
		} else if (stack.getItem() instanceof ItemChargeMulti) {
			((ItemChargeMulti) stack.getItem()).addCE(stack, value, p1.getChromaType());
		}
	}
}
