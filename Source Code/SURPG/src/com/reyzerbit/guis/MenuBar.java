package com.reyzerbit.guis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.reyzerbit.Feats;
import com.reyzerbit.fetchDataClasses.SaveLoad;

public class MenuBar {
	
	//Menu Bar
	static JMenuBar menuBar = new JMenuBar();
	
	//Menus for Bar
	
	//Loading Saving
	static JMenu menuFile = new JMenu("File");
	public static JMenu loadGame = new JMenu("Load Game");
	
	//Game and Stats
	static JMenu gameStuff = new JMenu("Game");
	static JMenu stats = new JMenu("Check Stats");
	static JMenu developerStuff = new JMenu("Developer");
	
	//Menu Items
	
	//Loading Saving
	public static JMenuItem saveGame = new JMenuItem("Save Game");
	static JMenuItem newSave = new JMenuItem("Save New Game");
	public static JMenuItem pickFile = new JMenuItem("Select File to Load");
	public static JMenuItem recents = new JMenuItem("Recents");
	
	//Game and Stats
	public static JMenuItem healthPoints = new JMenuItem("HP: " + Feats.health);
	
	//Developer
	static JMenuItem console = new JMenuItem("Console");
	
	public static void initMenuBar(){
		
		GUIContent.gui.setJMenuBar(menuBar);
		
		//MenuBar Additions
		
		//Add File
		menuBar.add(menuFile);
		//Add Game
		menuBar.add(gameStuff);
		//Add Developer
		menuBar.add(developerStuff);
		
		//Add Items to File
		menuFile.add(saveGame);
		menuFile.add(newSave);
		menuFile.addSeparator();
		menuFile.add(loadGame);
		
		//Add Items to Game
		gameStuff.add(stats);
		stats.add(healthPoints);
		
		//Add Items to Developer
		developerStuff.add(console);
		
		//Load Game Menu
		loadGame.add(pickFile);
		loadGame.addSeparator();
		loadGame.add(recents);
		recents.disable();
		
		//Action Listeners
		saveGame.addActionListener(new ActionListener() {

			@Override
			
			public void actionPerformed(ActionEvent e) {
				
				SaveLoad.saveFile();
				
			}
			
		});
		
		pickFile.addActionListener(new ActionListener() {

			@Override
			
			public void actionPerformed(ActionEvent e) {
				
				SaveLoad.loadGame();
				
			}
			
		});
		
		newSave.addActionListener(new ActionListener() {

			@Override
			
			public void actionPerformed(ActionEvent e) {
				
				SaveLoad.saveNewGame();
				
			}
			
		});
		
		console.addActionListener(new ActionListener() {

			@Override
			
			public void actionPerformed(ActionEvent e) {
				
				ConsoleGUI.initConsole();
				
			}
			
		});
		
	}
	
	public static void makeMenuItem(String name, File file){
		
		JMenuItem menuItem = new JMenuItem(name);
		loadGame.add(menuItem);
		menuItem.addActionListener(new ActionListener() {

			@Override
			
			public void actionPerformed(ActionEvent e) {
				
	            SaveLoad.loadRecent(file);
				
			}
			
		});
		
	}

}
