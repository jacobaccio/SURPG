package com.reyzerbit.guis;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.reyzerbit.Feats;

public class GUIContent{
	
	//Frame
	public static JFrame gui = new JFrame();
	
	//Labels
	static JLabel inputLabel = new JLabel("");
	public static JLabel healthPoints = new JLabel("  HP: " + Feats.health);
	public static JLabel strengthPoints = new JLabel("  Str: " + Feats.strength);
	public static JLabel intelPoints = new JLabel("  Int: " + Feats.intelligence);
	public static JLabel agilPoints = new JLabel("  Agl: " + Feats.agility);
	
	//Panels
	static JPanel stats = new JPanel();
	
	//Hover TextArea
	static JTextArea hover = new JTextArea("Hover over a feature in the box above for a description about it's purpose.");
	
	//Text IO
	public static JTextArea outputWindow = new JTextArea();
	public static JTextField inputWindow = new JTextField();
	static JScrollPane scroll = new JScrollPane(outputWindow);
	
	//Input Streams
	static InputStream in = null;
	static InputStream in2 = null;
	
	//Images
	static BufferedImage background = null;
	static BufferedImage characterPic = null;
	
	//Buttons
	static JButton enter = new JButton("Enter");
	static JButton statsButton = new JButton("Stats");
	static JButton characterButton = new JButton("Character");
	
	static JPanel characterObject = new JPanel();
	
	public static void init() throws IOException, URISyntaxException{
		
		//Initiate Game GUI

		//Background
		in = GUIContent.class.getClassLoader().getResourceAsStream("com/reyzerbit/assets/background.jpg");
		
		//Character Shape
		in2 = GUIContent.class.getClassLoader().getResourceAsStream("com/reyzerbit/assets/CharacterPic.png");
		
		background = ImageIO.read(in);
		
		//Remove comments once Lauryn draws the picture.
		
		/*
		@SuppressWarnings("serial")
		JPanel backPic = new JPanel() {
	        @Override
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            g.drawImage(background, 0, 0, null);
	        }
	    };
	    
	    */
		
		in2 = GUIContent.class.getClassLoader().getResourceAsStream("com/reyzerbit/assets/CharacterPic.png");
		try {
			characterPic = ImageIO.read(in2);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		@SuppressWarnings("serial")
		JPanel characterPanel = new JPanel() {
	        @Override
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            g.drawImage(characterPic, 0, 0, null);
	            super.repaint();
	        }
	    };
		
		//GUI Constraints
		
		//Also remove comments once picture is drawn.
		/*
	    gui.setContentPane(backPic);
	    */
		
		gui.getContentPane().setLayout(null);
		gui.setVisible(true);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setSize(800, 500);
		gui.setName("Steven Universe Text Based RPG");
		gui.setTitle("Steven Universe Text Based RPG");
		gui.setResizable(false);
		gui.setLocationRelativeTo(null);
		gui.getRootPane().setDefaultButton(enter);

		//Hover Window Constraints
		hover.setWrapStyleWord(true);
		hover.setLineWrap(true);
		hover.setEditable(false);
		
		//Input Window Constraints
		inputWindow.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
		
		//Output Windows Constraints
		outputWindow.setWrapStyleWord(true);
		outputWindow.setLineWrap(true);
		outputWindow.setEditable(false);
		outputWindow.setMargin(new Insets(5,5,5,5));
		
		//Scroll Constraints
		scroll.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true));
		
		//Enter Button Constraints
		enter.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
		enter.setOpaque(true);
		enter.setBackground(Color.LIGHT_GRAY);
		
		//Stats Button Constraints
		statsButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
		statsButton.setOpaque(true);
		Color characterColor = new Color(239, 156, 21);
		statsButton.setBackground(characterColor);
		
		//Stats Button Constraints
		characterButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
		characterButton.setOpaque(true);
		Color statsColor = new Color(34, 229, 196);
		characterButton.setBackground(statsColor);
		
		//Character Pic Constraints
		characterPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
		characterPanel.setOpaque(true);
		characterPanel.setVisible(false);
		
		//Stats Panel Constraints
		stats.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true));
		stats.setLayout(null);
		stats.setBackground(Color.WHITE);
		
		//Hover Constraints
		hover.setMargin(new Insets(5,5,5,5));
		hover.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true));
		
		//Stats JPanel Item Constraints
		healthPoints.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		healthPoints.setOpaque(true);
		healthPoints.setBackground(new Color(255, 74, 90));
		
		strengthPoints.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		strengthPoints.setOpaque(true);
		strengthPoints.setBackground(Color.ORANGE);
		
		intelPoints.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		intelPoints.setOpaque(true);
		intelPoints.setBackground(Color.BLUE);
		
		agilPoints.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		agilPoints.setOpaque(true);
		agilPoints.setBackground(Color.GREEN);
		
		//Place JPanel Components
		addComponentFrame(stats, healthPoints, 30, 20, 65, 30);
		addComponentFrame(stats, strengthPoints, 125, 20, 65, 30);
		addComponentFrame(stats, intelPoints, 220, 20, 65, 30);
		addComponentFrame(stats, agilPoints, 30, 70, 65, 30);
		addComponentFrame(stats, characterPanel, 30, 20, 80, 150);
		
		//Place Components
		addComponent(gui, scroll, 20, 20, 280, 440);
		addComponent(gui, inputWindow, 340, 35, 200, 20);
		addComponent(gui, enter, 560, 35, 100, 20);
		addComponent(gui, stats, 340, 80, 320, 250);
		addComponent(gui, hover, 340, 380, 320, 80);
		addComponent(gui, statsButton, 680, 80, 100, 30);
		addComponent(gui, characterButton, 680, 130, 100, 30);
		
		
		//Request Focus for Input Window
		inputWindow.requestFocusInWindow();
		
		//Listener for Hovers
				
		//Health Points Hover Listener
		addHoverListener(healthPoints, "Your health starts at ten, and will slowly raise as you level up.");
		
		//Strength Points Hover Listener
		addHoverListener(strengthPoints, "Your strength starts at zero, and will grow as you complete events and level up.");
		
		//Intelligence Points Hover Listener
		addHoverListener(intelPoints, "Your intelligence starts at zero, and will grow as you complete events and level up.");
		
		//Agility Points Hover Listener
		addHoverListener(agilPoints, "Your agility starts at zero, and will grow as you complete events and level up.");
		
		//Character Pic Hover Listsner
		addHoverListener(characterPanel, "This is your character picture. It shows your gem location, as well as armor and weapons equiped.");
		
		//Add Listener for Stats Button
		statsButton.addActionListener(new ActionListener() {

			@Override			
			public void actionPerformed(ActionEvent e) {
								
				healthPoints.setVisible(true);
				strengthPoints.setVisible(true);
				intelPoints.setVisible(true);
				agilPoints.setVisible(true);
								
				characterPanel.setVisible(false);
							
			}
							
		});
					
		//Add Listener for Stats Button
		characterButton.addActionListener(new ActionListener() {

			@Override
									
				public void actionPerformed(ActionEvent e) {
									
					healthPoints.setVisible(false);
					strengthPoints.setVisible(false);
					intelPoints.setVisible(false);
					agilPoints.setVisible(false);
							
					characterPanel.setVisible(true);
									
				}
									
		});
		
		//Add Listener for Enter Button
		enter.addActionListener(new ActionListener() {

			@Override
			
			public void actionPerformed(ActionEvent e) {
				
				Feats.runNextPath(Feats.location);
				
			}
			
		});
		
	}

	//Method to add component to GUI.
	
	private static void addComponent(JFrame frame, Component component, int posx, int posy, int width, int height){
		
		component.setBounds(posx, posy, width, height);
		frame.getContentPane().add(component);
	
	}
	
	//Method to add a component to a another panel of some sort.
	
	private static void addComponentFrame(JPanel panel, Component component, int posx, int posy, int width, int height){
		
		component.setSize(width, height);
		component.setLocation(posx, posy);
		panel.add(component);
	
	}
	
	//Method to add hover event
	private static void addHoverListener(JComponent label, String text1){
		
		label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent event) {
                hover.setText(text1);
            }

            @Override
            public void mouseExited(MouseEvent event) {
                hover.setText("Hover over a stat for details.");
            }
        });
		
	}
	
}
	

