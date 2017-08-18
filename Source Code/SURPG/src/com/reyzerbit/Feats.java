package com.reyzerbit;

import java.io.File;

import com.reyzerbit.fetchDataClasses.StringsClass;
import com.reyzerbit.guis.CombatGUI;
import com.reyzerbit.guis.GUIContent;
import com.reyzerbit.guis.HealthRectangle;
import com.reyzerbit.storyline.PickingCharacter;
import com.reyzerbit.storyline.Test;

public class Feats {
	
	//Player Features
	//Gem spot and type.
	public static String gemSpot = new String("undefined");
	public static String gemType = new String("undefined");
	
	//Stats
	public static int physicalStrength = 0;
	public static int will = 0;
	public static int endurance = 0;
	public static int strength = 0;
	
	
	public static int agility = 0;
	public static int precision = 0;
	public static int athletics = 0;
	public static int balance = 0;
	
	public static int intelligence = 0;
	public static int insight = 0;
	public static int communication = 0;
	public static int problemSolving = 0;
	
	public static float health = 10;
	public static float maxHealth = 10;
	
	//Separator for Different OS's
	public static String separate = new String(System.getProperty("file.separator"));
	
	//If recents file was already made
	
	public static int recMade = 0;
	
	//Location in Story
	
	public static long location = 0;
	
	//If Save is a Recent Load or not.
	
	public static int recent = 0;
	
	//Save File Location
	
	public static File saveFile = null;
	
	//Add Text Feature
	
	public static void addText(String text){
		
		GUIContent.outputWindow.append(text);
		System.out.println("Text added.");
		
	}
	
	//Path Run Method
	
	public static void runNextPath(long locator){

		
		if(locator == 0){
			PickingCharacter.run0();
			clearInput();
		}
		else if(locator == 1){
			PickingCharacter.run1(getInput());
			clearInput();
		}
		else if(locator == 2){
			PickingCharacter.run2(getInput());
			clearInput();
		}
		else if(locator == 3){
			PickingCharacter.run3(getInput());
			clearInput();
		}
		else if(locator == 4){
			PickingCharacter.run4(getInput());
			clearInput();
		}
		else if(locator == 5){
			Test.run0();
			clearInput();
		}
		
		System.out.println("Next path run.");
		
	}
	
	//Clear input window.
	
	public static void clearInput(){
		
		GUIContent.inputWindow.setText("");
		
	}
	
	//Get input window text
	public static String getInput(){
		
		return GUIContent.inputWindow.getText();
		
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
	
	//Reset Stats GUI
	
	public static void resetStat(){

		strength = physicalStrength + will + endurance;
		intelligence = insight + communication + problemSolving;
		agility = precision + balance + athletics;
		
		GUIContent.agilPoints.setText("  Agl: " + agility);
		GUIContent.strengthPoints.setText("  Str: " + strength);
		GUIContent.intelPoints.setText("  Int: " + intelligence);
		CombatGUI.enemyHealthLabel.setText(" Enemy HP: " + CombatGUI.enemyHealthVar);
		
		//Calculate health bar amount.
		HealthRectangle.healthPercent = Math.ceil((health/maxHealth)*140);
		GUIContent.healthBar.repaint();
		GUIContent.healthPointLabel.setText(Math.round(health) + "/" + Math.round(maxHealth));
		
		System.out.println("Stats reloaded.");
		
	}
	
}
