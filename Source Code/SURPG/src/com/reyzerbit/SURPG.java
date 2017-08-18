package com.reyzerbit;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.reyzerbit.fetchDataClasses.RecentsSystem;
import com.reyzerbit.guis.CombatGUI;
import com.reyzerbit.guis.GUIContent;
import com.reyzerbit.guis.MenuBar;
import com.reyzerbit.storyline.PickingCharacter;

public class SURPG {
	
	public static void main(String[] args) throws IOException, UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		
		//Menu Bar for MacOS
		
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		//Initialize GUI
		
		try {
			GUIContent.init();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		MenuBar.initMenuBar();
		
		//Disable save game feature and file is loaded.
		MenuBar.saveGame.disable();
		
		//Start Combat Thread
		CombatGUI.threadMove.start();
		
		//Begin Game
		PickingCharacter.run0();
		
		//Load Recents
		RecentsSystem.loadRecents();

	}

}
