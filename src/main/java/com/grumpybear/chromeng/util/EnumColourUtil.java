package com.grumpybear.chromeng.util;

import com.grumpybear.chromeng.chroma.EnumColour;
import com.grumpybear.chromeng.lib.LibTextures;
import com.grumpybear.chromeng.lib.LibTextures.Pair;
import net.minecraft.util.text.TextFormatting;

import java.awt.*;

public class EnumColourUtil {

	public static final int RED_RGB = -65536;
	public static final int ORANGE_RGB = -23296;
	public static final int YELLOW_RGB = -256;
	public static final int GREEN_RGB = -16711936;
	public static final int BLUE_RGB = -16776961;
	public static final int INDIGO_RGB = -11861886;
	public static final int VIOLET_RGB = -7077677;

	public static final int[] ENUMCOLOUR_RGB_ARRAY = {RED_RGB, ORANGE_RGB, YELLOW_RGB, GREEN_RGB, BLUE_RGB, INDIGO_RGB, VIOLET_RGB};


	
	public static EnumColour byteToColour(byte i) {

		switch(i) {
			case 1:		return EnumColour.RED;
			case 2:		return EnumColour.ORANGE;
			case 3: 	return EnumColour.YELLOW;
			case 4: 	return EnumColour.GREEN;
			case 5: 	return EnumColour.BLUE;
			case 6: 	return EnumColour.INDIGO;
			case 7: 	return EnumColour.VIOLET;
		}
		
		return null;

		/*
		return EnumColour.values()[(int) i];
		*/
	}
	
	public static byte colourToByte(EnumColour colour) {

		switch(colour) {
			case RED:		return 1;
			case ORANGE:	return 2;
			case YELLOW:	return 3;
			case GREEN:		return 4;
			case BLUE:		return 5;
			case INDIGO:	return 6;
			case VIOLET: 	return 7;
		}
		
		return 0;


		/*

		return (byte) colourToInt(colour);

		*/
	}

	public static int colourToInt(EnumColour colour) {

		switch(colour) {
			case RED:		return 1;
			case ORANGE:	return 2;
			case YELLOW:	return 3;
			case GREEN:		return 4;
			case BLUE:		return 5;
			case INDIGO:	return 6;
			case VIOLET: 	return 7;
		}

		return 0;


		/*
		for (int i = 0; i < EnumColour.values().length; i++) {
			if (colour == EnumColour.values()[i]) {
				return i;
			}
		}

		return 0;
		*/
	}
	
	public static String colourToString(EnumColour colour) {
		switch(colour) {
			case RED:		return "red";
			case ORANGE:	return "orange";
			case YELLOW:	return "yellow";
			case GREEN:		return "green";
			case BLUE:		return "blue";
			case INDIGO:	return "indigo";
			case VIOLET: 	return "violet";
		}
	
		return null;
	}

	public static String colourToStringCaps(EnumColour colour) {
		switch(colour) {
			case RED:		return "Red";
			case ORANGE:	return "Orange";
			case YELLOW:	return "Yellow";
			case GREEN:		return "Green";
			case BLUE:		return "Blue";
			case INDIGO:	return "Indigo";
			case VIOLET: 	return "Violet";
		}

		return null;
	}
	
	public static EnumColour pairToColour(Pair pair) {

		if (pair == LibTextures.RED_FULL_LOCATION) {
			return EnumColour.RED;
		} else if (pair == LibTextures.ORANGE_FULL_LOCATION) {
			return EnumColour.ORANGE;
		} else if (pair == LibTextures.YELLOW_FULL_LOCATION) {
			return EnumColour.YELLOW;
		} else if (pair == LibTextures.BLUE_FULL_LOCATION) {
			return EnumColour.BLUE;
		} else if (pair == LibTextures.INDIGO_FULL_LOCATION) {
			return EnumColour.INDIGO;
		} else if (pair == LibTextures.VIOLET_FULL_LOCATION) {
			return EnumColour.VIOLET;
		} else {
			return EnumColour.BLUE;
		}
	}

	public static TextFormatting colourToFormatting(EnumColour colour) {
		if (colour == EnumColour.RED) {
			return TextFormatting.RED;
		} else if (colour == EnumColour.ORANGE) {
			return TextFormatting.GOLD;
		} else if (colour == EnumColour.YELLOW) {
			return TextFormatting.YELLOW;
		} else if (colour == EnumColour.GREEN) {
			return TextFormatting.GREEN;
		} else if (colour == EnumColour.BLUE) {
			return TextFormatting.BLUE;
		} else if (colour == EnumColour.INDIGO) {
			return TextFormatting.DARK_BLUE;
		} else if (colour == EnumColour.VIOLET) {
			return TextFormatting.DARK_PURPLE;
		} else {
			return TextFormatting.OBFUSCATED;
		}
	}

	public static int colourToRGB(EnumColour colour) {
		if (colour == EnumColour.RED) {
			return RED_RGB;
		} else if (colour == EnumColour.ORANGE) {
			return ORANGE_RGB;
		} else if (colour == EnumColour.YELLOW) {
			return YELLOW_RGB;
		} else if (colour == EnumColour.GREEN) {
			return GREEN_RGB;
		} else if (colour == EnumColour.BLUE) {
			return BLUE_RGB;
		} else if (colour == EnumColour.INDIGO) {
			return INDIGO_RGB;
		} else if (colour == EnumColour.VIOLET) {
			return VIOLET_RGB;
		} else {
			return 0;
		}


		/*
		for (int i = 0; i < EnumColour.values().length; i++) {
			if (colour == EnumColour.values()[i]) {
				return ENUMCOLOUR_RGB_ARRAY[i];
			}
		}

		return 0;
		*/
	}

	public static EnumColour RGBtoColour(int rgb) {
		for (int i = 0; i < ENUMCOLOUR_RGB_ARRAY.length; i++) {
			if (rgb == ENUMCOLOUR_RGB_ARRAY[i]) {
				return EnumColour.values()[i];
			}
		}

		return EnumColour.RED;
	}




}
