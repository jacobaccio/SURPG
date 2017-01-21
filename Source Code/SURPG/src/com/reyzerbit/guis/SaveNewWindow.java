package com.reyzerbit.guis;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.reyzerbit.Feats;
import com.reyzerbit.MenuBar;
import com.reyzerbit.fetchDataClasses.SaveLoad;

public class SaveNewWindow {
	
	static String newSaveFile;
	static File newFile;
	static JFrame newSaveFrame;

	public static void saveWindow(){
		
		JTextField nameNewSave = new JTextField();
		
		JLabel nameIt = new JLabel("Enter a name for your save.");
		
		JButton enter = new JButton("Enter");
		
		newSaveFrame = new JFrame();
		
		newSaveFrame.setLayout(null);
		newSaveFrame.setVisible(true);
		newSaveFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		newSaveFrame.setSize(300, 100);
		newSaveFrame.setName("Save New Game");
		newSaveFrame.setTitle("Save New Game");
		newSaveFrame.setResizable(false);
		newSaveFrame.setLocationRelativeTo(null);
		newSaveFrame.getRootPane().setDefaultButton(enter);
		
		addComponent(newSaveFrame, nameNewSave, 25, 35, 250, 25);
		addComponent(newSaveFrame, nameIt, 25, 5, 250, 25);
		addComponent(newSaveFrame, enter, 200, 5, 80, 25);
		
		nameNewSave.requestFocusInWindow();
		
		nameNewSave.setText("");
		
		enter.addActionListener(new ActionListener() {

			@Override
			
			public void actionPerformed(ActionEvent e) {
				
				if(nameNewSave.getText().trim().isEmpty()){
					
					nameIt.setText("Please enter a valid name.");
					
				}
				else{
					
					//New Save Prep
					newSaveFile = nameNewSave.getText();
					newFile = new File(System.getProperty("user.home") + Feats.separate + "Documents" + Feats.separate + "SURPG" + Feats.separate + newSaveFile + ".json");
					
					//Check for Overwrite
					if(newFile.exists()){
						
						int overwrite = JOptionPane.showConfirmDialog(null, "This file already exists. Overwrite?", "ERROR", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
						System.out.print(overwrite);
						if(overwrite==0){
							
							saveAction();
							
						}
						else if(overwrite==1){
							
							nameNewSave.setText("");
							
						}
						else if(overwrite==2){
							
							newSaveFrame.dispose();
							
						}else{
							
							System.out.println("Something went wrong...");
							
						}
						
					}else{
						
						saveAction();
						
					}
					
				}
				
			}
			
		});
		
	}
	
	private static void saveAction(){
		
		newFile.getParentFile().mkdirs();
		
		//Save New File
		try {
			newFile.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	
		Feats.saveFile = newFile;
		SaveLoad.saveFile();
	
		System.out.println("New files created and saved!");
		Feats.addText("New files created and saved!\n\n");
	
		newSaveFrame.dispose();
		MenuBar.saveGame.enable();
		
	}
	
	private static void addComponent(JFrame frame, Component component, int posx, int posy, int width, int height){
		
		component.setBounds(posx, posy, width, height);
		frame.add(component);
	
	}
	
}
