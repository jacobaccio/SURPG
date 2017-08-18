package com.reyzerbit.guis;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import com.reyzerbit.Feats;

public class CombatGUI{
	
	//0 is forward, 1 is backward
	static int sliderDirection = 0;
	
	//Input stream
	static InputStream bar = null;
	
	//Images
	static BufferedImage barImage = null;
	
	//Frame
	public static JFrame combatGUI = new JFrame();
	//Buttons
	public static JButton fight = new JButton("Fight");
	public static JButton items = new JButton("Items");
	public static JButton flee = new JButton("Flee");
	public static JButton abilities = new JButton("Abilities");
	public static JButton hit = new JButton("Hit");
	public static JButton cancel = new JButton("Cancel");
	
	//JPanels
	public static JPanel enemyWindow = new JPanel();
	
	//Slider
	public static JSlider combatSlider = new JSlider(0, 100);
	
	//Ints
	
	//This int represents the speed of the slider in milliseconds. 7 is for strong but slow enemies, 5 is for default enemies, 4 is for elite monsters.
	static int speedSlider = 5;
	static int stopSlider = 1;
	static int damageInt = 0;
	public static long enemyHealthVar = 0;
	
	//JLabel
	public static JLabel enemyHealthLabel = new JLabel(" Enemy HP: " + enemyHealthVar);
	
	
	//Moving Slider Thread
	public static Thread threadMove = new Thread(new Runnable(){

	    public void run(){
		
	    	while(true){
	    		int currentValue = combatSlider.getValue();
		
	    		if(currentValue < 100 && sliderDirection == 0 && stopSlider == 0){
			
	    			currentValue = currentValue + 1;
	    			combatSlider.setValue(currentValue);
	    			
	    			try {
						Thread.sleep(speedSlider);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			
	    		}else if(currentValue < 100 && sliderDirection == 1 && currentValue != 0 && stopSlider == 0){
			
	    			currentValue = currentValue - 1;
	    			combatSlider.setValue(currentValue);
	    			
	    			try {
						Thread.sleep(speedSlider);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			
	    		}else if(currentValue == 100 && sliderDirection == 0 && stopSlider == 0){
			
	    			sliderDirection = 1;
	    			currentValue = currentValue - 1;
	    			combatSlider.setValue(currentValue);
	    			
	    			try {
						Thread.sleep(speedSlider);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			
	    		}else if(currentValue == 0 && sliderDirection == 1 && stopSlider == 0){
			
	    			sliderDirection = 0;
	    			currentValue = currentValue + 1;
	    			combatSlider.setValue(currentValue);
	    			
	    			try {
						Thread.sleep(speedSlider);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			
	    		}else{
			
	    			try {
						Thread.sleep(500);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
			
	    		}
		
	    	}
	    	
	    }
	    
	});
	
	public static void initCombat(int speedNumber, int enemyHealth, int enemyStrength){
		
		bar = GUIContent.class.getClassLoader().getResourceAsStream("com/reyzerbit/assets/combatBar.png");
		try {
			barImage = ImageIO.read(bar);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		@SuppressWarnings("serial")
		JPanel combatPanel = new JPanel() {
	        @Override
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            g.drawImage(barImage, 0, 0, null);
	            super.repaint();
	        }
	    };
	    
	    enemyHealthVar = enemyHealth;
	    Feats.resetStat();
		
		speedSlider = speedNumber;
		
		combatPanel.setVisible(false);
		
		combatGUI.getContentPane().setLayout(null);
		combatGUI.setVisible(true);
		combatGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		combatGUI.setSize(300, 400);
		combatGUI.setName("Combat");
		combatGUI.setTitle("Combat");
		combatGUI.setResizable(false);
		combatGUI.setLocationRelativeTo(null);
		
		//Slider Visuals
		combatSlider.setPaintTicks(false);
		combatSlider.setPaintLabels(false);
		combatSlider.setVisible(false);
		combatSlider.putClientProperty( "Slider.paintThumbArrowShape", Boolean.TRUE );
		combatSlider.setValue(0);
		combatSlider.setEnabled(false);
		
		setVisuals(fight, Color.BLACK, 2, Color.LIGHT_GRAY, true);
		setVisuals(flee, Color.BLACK, 2, Color.LIGHT_GRAY, true);
		setVisuals(items, Color.BLACK, 2, Color.LIGHT_GRAY, true);
		setVisuals(abilities, Color.BLACK, 2, Color.LIGHT_GRAY, true);
		setVisuals(hit, Color.BLACK, 2, Color.LIGHT_GRAY, false);
		setVisuals(cancel, Color.BLACK, 2, Color.LIGHT_GRAY, false);
		setVisuals(enemyWindow, Color.BLACK, 2, Color.WHITE, true);
		setVisuals(enemyHealthLabel, Color.BLACK, 2, Color.RED, true);
		
		addComponent(combatGUI, fight, 30, 260, 100, 30);
		addComponent(combatGUI, items, 160, 260, 100, 30);
		addComponent(combatGUI, abilities, 30, 320, 100, 30);
		addComponent(combatGUI, flee, 160, 320, 100, 30);
		addComponent(combatGUI, enemyWindow, 30, 30, 230, 150);
		addComponent(combatGUI, combatSlider, 30, 240, 230, 30);
		addComponent(combatGUI, combatPanel, 30, 270, 230, 30);
		addComponent(combatGUI, hit, 30, 320, 100, 30);
		addComponent(combatGUI, cancel, 160, 320, 100, 30);
		addComponent(combatGUI, enemyHealthLabel, 30, 200, 100, 30);
		
		fight.addActionListener(new ActionListener() {

			@Override			
			public void actionPerformed(ActionEvent e) {
							
				combatSlider.setValue(0);
				hit.setEnabled(true);
				cancel.setEnabled(true);
				
				flee.setVisible(false);
				fight.setVisible(false);
				abilities.setVisible(false);
				items.setVisible(false);
							
				hit.setVisible(true);
				cancel.setVisible(true);
				combatSlider.setVisible(true);
				combatPanel.setVisible(true);

				if(stopSlider == 1){
					
					stopSlider = 0;
						
				}else{
					
					System.out.println("Something went wrong!");
					
				}
				
					
			}
					
		});
		
		hit.addActionListener(new ActionListener() {

			@Override			
			public void actionPerformed(ActionEvent e) {
				
				hit.setEnabled(false);
				
				stopSlider = 1;
				
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				
				enemyHealthVar = enemyHealthVar - calculateDamage(combatSlider.getValue());
				Feats.resetStat();
				
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				
				cancel.doClick();
				
			}
					
		});
		
		cancel.addActionListener(new ActionListener() {

			@Override			
			public void actionPerformed(ActionEvent e) {
				
				flee.setVisible(true);
				fight.setVisible(true);
				abilities.setVisible(true);
				items.setVisible(true);
				
				hit.setVisible(false);
				cancel.setVisible(false);
				combatSlider.setVisible(false);
				combatPanel.setVisible(false);
				
				stopSlider = 1;
				
			}
					
		});
		
	}

	//Method to add component to GUI.
	
	private static void addComponent(JFrame frame, Component component, int posx, int posy, int width, int height){
		
		component.setBounds(posx, posy, width, height);
		frame.getContentPane().add(component);
	
	}
	
	private static void setVisuals(JComponent comp, Color border, int borderWidth, Color background, Boolean visible){
		
		comp.setBorder(BorderFactory.createLineBorder(border, borderWidth, true));
		comp.setOpaque(true);
		comp.setBackground(background);
		comp.setVisible(visible);
		
	}
	
	private static long calculateDamage(int percentage){
		
		long totalDamage = 0;
		
		if(percentage <= 25 || percentage >= 75){
			
			totalDamage = ((int) Math.floor(Feats.physicalStrength * 0));
			
		}else if(percentage > 25 && percentage <= 40 || percentage < 75 && percentage >= 60){
			
			totalDamage = Feats.physicalStrength * 1;
			
		}else if(percentage > 40 && percentage <= 48 || percentage < 60 && percentage >= 52){
			
			totalDamage = ((int) Math.floor(Feats.physicalStrength * 1.5));
			
		}else if(percentage > 48 && percentage < 52){
			
			totalDamage = ((int) Math.floor(Feats.physicalStrength * 2));
			
		}else{
			
			System.out.println("Something went wrong");
			
		}
		
		System.out.println(totalDamage);
		
		return totalDamage;
		
	}
	
}