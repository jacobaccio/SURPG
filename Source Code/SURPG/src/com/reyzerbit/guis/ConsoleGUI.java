package com.reyzerbit.guis;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;

public class ConsoleGUI {
	
	//Frame
	public static JFrame consoleGUI = new JFrame();
	
	//Text Area and Scroll
	public static JTextArea inputWindow = new JTextArea();
	static JScrollPane scroll = new JScrollPane(inputWindow);
	
	//Buttons
	static JButton enter = new JButton("Enter");
		
	public static void initConsole(){
			
		consoleGUI.getContentPane().setLayout(null);
		consoleGUI.setVisible(true);
		consoleGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		consoleGUI.setSize(300, 400);
		consoleGUI.setName("Console");
		consoleGUI.setTitle("Console");
		consoleGUI.setResizable(false);
		consoleGUI.setLocationRelativeTo(null);
		
		enter.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
		enter.setOpaque(true);
		enter.setBackground(Color.LIGHT_GRAY);
		
		addComponent(consoleGUI, scroll, 10, 10, 280, 320);
		addComponent(consoleGUI, enter, 120, 335, 60, 30);
			
		//Add Listener for Enter Button
		enter.addActionListener(new ActionListener() {

			@Override
					
			public void actionPerformed(ActionEvent e) {
						
				int last  = inputWindow.getLineCount() - 1;
				int start = 0;
				try {
					start = inputWindow.getLineStartOffset(last);
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}
				int end = 0;
				try {
					end = inputWindow.getLineEndOffset(last);
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}

				String lastLine = inputWindow.getText().substring(start, end);
				
				if(lastLine.equals("startCombat")){
					
					inputWindow.append("\n\nOpening Combat GUI\n\n");
					CombatGUI.initCombat();
					
				}
				else{
					
					inputWindow.append("\n\nUnknown Command\n\n");
					
				}
						
			}
					
		});
				
	}
	
	//Method to add component to GUI.
	
	private static void addComponent(JFrame frame, Component component, int posx, int posy, int width, int height){
		
		component.setBounds(posx, posy, width, height);
		frame.getContentPane().add(component);
	
	}

}