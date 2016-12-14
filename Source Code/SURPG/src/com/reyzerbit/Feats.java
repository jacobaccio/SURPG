package com.reyzerbit;

import java.io.File;

import com.reyzerbit.fetchDataClasses.StringsClass;
import com.reyzerbit.guis.GUIContent;
import com.reyzerbit.storyline.PearlBegin;
import com.reyzerbit.storyline.PickingCharacter;

public class Feats {
	
	//Player Features
	public static String gemSpot = new String("undefined");
	public static String gemType = new String("undefined");
	public static int health = 10;
	
	//Separator for Different OS's
	public static String separate = new String(System.getProperty("file.separator"));
	
	//Location in Story
	
	public static long location = 0;
	
	//If Save is a Recent Load or not.
	
	public static int recent = 0;
	
	//Save File Location
	
	public static File saveFile = null;
	
	//Add Text Feature
	
	public static void addText(String text){
		
		GUIContent.outputWindow.append(text);
		
	}
	
	public static void runNextPath(long locator){

		
		if(locator == 0){
			PickingCharacter.run0();
			GUIContent.inputWindow.setText("");
		}
		else if(locator == 1){
			PickingCharacter.run1(GUIContent.inputWindow.getText());
			GUIContent.inputWindow.setText("");
		}
		else if(locator == 2){
			PickingCharacter.run2(GUIContent.inputWindow.getText());
			GUIContent.inputWindow.setText("");
		}
		else if(locator == 3){
			PickingCharacter.run3(GUIContent.inputWindow.getText());
			GUIContent.inputWindow.setText("");
		}
		else if(locator == 4){
			PickingCharacter.run4(GUIContent.inputWindow.getText());
			GUIContent.inputWindow.setText("");
		}
		//Pearl Line is 5
		else if(locator == 51 || locator == 61 || locator == 71){
			PearlBegin.run0();
			GUIContent.inputWindow.setText("");
		}
		//Ruby Line is 6
		
	}
	
	//For Load Files
	
	public static void nameLocation(long loc){
		
		String locNum = "s" + loc;
		String locatedName = StringsClass.readString(locNum);
		
		if(loc == 3){
			
			Feats.addText(locatedName);
			Feats.addText(gemSpot);
			Feats.addText(StringsClass.readString(locNum + ".1"));
			
		}
		else if(loc == 4){
			
			Feats.addText(locatedName);
			Feats.addText(gemType);
			Feats.addText(StringsClass.readString(locNum + ".1"));
			
		}
		else if(loc == 5){
			
			Feats.addText(locatedName);
			Feats.addText(gemType);
			Feats.addText(StringsClass.readString(locNum + ".1"));
			
		}

	}
	
}
