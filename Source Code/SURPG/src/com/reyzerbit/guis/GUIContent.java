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
import javax.swing.SwingConstants;

import com.reyzerbit.Feats;

public class GUIContent{
	
	//Frame
	public static JFrame gui = new JFrame();
	
	//Labels
	static JLabel inputLabel = new JLabel("");
	
	//Health
	
	public static HealthRectangle healthBar = new HealthRectangle();
	public static JLabel healthPointLabel = new JLabel(Math.round(Feats.health) + "/" + Math.round(Feats.maxHealth));
	
	//Strength
	public static JLabel strengthPoints = new JLabel("Str: " + Feats.strength);
	public static JLabel physicalPoints = new JLabel("PS: " + Feats.physicalStrength);
	public static JLabel willPoints = new JLabel("Will: " + Feats.will);
	public static JLabel endurancePoints = new JLabel("End: " + Feats.endurance);
	
	//Intelligence
	public static JLabel intelPoints = new JLabel("Int: " + Feats.intelligence);
	public static JLabel communicationPoints = new JLabel("Com: " + Feats.communication);
	public static JLabel problemSolvePoints = new JLabel("Prob: " + Feats.problemSolving);
	public static JLabel insightPoints = new JLabel("Ins: " + Feats.insight);
	
	//Agility
	public static JLabel agilPoints = new JLabel("Agl: " + Feats.agility);
	public static JLabel precisionPoints = new JLabel("Prec: " + Feats.precision);
	public static JLabel athleticsPoints = new JLabel("Ath: " + Feats.athletics);
	public static JLabel balancePoints = new JLabel("Bal: " + Feats.balance);
	
	//Panels
	static JPanel stats = new JPanel();
	
	//Hover TextArea
	static JTextArea hover = new JTextArea("Hover over a feature in the box above for a description about it's purpose.");
	
	//Text IO
	public static JTextArea outputWindow = new JTextArea();
	public static JTextField inputWindow = new JTextField();
	public static JScrollPane scroll = new JScrollPane(outputWindow);
	
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
		
		//Health Bar
		@SuppressWarnings("serial")
		JPanel characterPanel = new JPanel() {
	        @Override
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            g.drawImage(characterPic, 0, 0, null);
	            super.repaint();
	        }
	    };

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
		setVisuals(strengthPoints, Color.BLACK, 2, new Color(209, 39, 39), true);
		setVisuals(intelPoints, Color.BLACK, 2, new Color(62, 232, 184), true);
		setVisuals(agilPoints, Color.BLACK, 2, new Color(255, 233, 91), true);
		setVisuals(physicalPoints, Color.BLACK, 2, new Color(219, 89, 89), true);
		setVisuals(willPoints, Color.BLACK, 2, new Color(219, 89, 89), true);
		setVisuals(endurancePoints, Color.BLACK, 2, new Color(219, 89, 89), true);
		setVisuals(communicationPoints, Color.BLACK, 2, new Color(122, 249, 213), true);
		setVisuals(problemSolvePoints, Color.BLACK, 2, new Color(122, 249, 213), true);
		setVisuals(insightPoints, Color.BLACK, 2, new Color(122, 249, 213), true);
		setVisuals(precisionPoints, Color.BLACK, 2, new Color(255, 239, 140), true);
		setVisuals(athleticsPoints, Color.BLACK, 2, new Color(255, 239, 140), true);
		setVisuals(balancePoints, Color.BLACK, 2, new Color(255, 239, 140), true);
		setVisualsPanel(healthBar, Color.BLACK, 2, Color.WHITE, true);
		
		//Add Label to Health Bar
		healthBar.add(healthPointLabel);
		
		//Place Components in the stats/character/etc. panel
		//+50 between each Y space. +75 per X
		addComponentFrame(stats, healthBar, 15, 20, 140, 30);
		
		//Strength
		addComponentFrame(stats, strengthPoints, 15, 100, 65, 30);
		addComponentFrame(stats, physicalPoints, 90, 100, 65, 30);
		addComponentFrame(stats, willPoints, 165, 100, 65, 30);
		addComponentFrame(stats, endurancePoints, 240, 100, 65, 30);
		
		//Intelligence
		addComponentFrame(stats, intelPoints, 15, 150, 65, 30);
		addComponentFrame(stats, communicationPoints, 90, 150, 65, 30);
		addComponentFrame(stats, problemSolvePoints, 165, 150, 65, 30);
		addComponentFrame(stats, insightPoints, 240, 150, 65, 30);
		
		//Agility
		addComponentFrame(stats, agilPoints, 15, 200, 65, 30);
		addComponentFrame(stats, precisionPoints, 90, 200, 65, 30);
		addComponentFrame(stats, athleticsPoints, 165, 200, 65, 30);
		addComponentFrame(stats, balancePoints, 240, 200, 65, 30);
		
		addComponentFrame(stats, characterPanel, 30, 20, 80, 150);
		
		//Place Components
		addComponent(gui, scroll, 20, 20, 280, 440);
		addComponent(gui, inputWindow, 340, 35, 200, 20);
		addComponent(gui, enter, 560, 35, 100, 20);
		addComponent(gui, stats, 340, 80, 320, 250);
		addComponent(gui, hover, 340, 360, 320, 100);
		addComponent(gui, statsButton, 680, 80, 100, 30);
		addComponent(gui, characterButton, 680, 130, 100, 30);
		
		
		//Request Focus for Input Window
		inputWindow.requestFocusInWindow();
		
		//Listener for Hovers
				
		//Health Points Hover Listener
		addHoverListener(healthBar, "Your health starts at ten, and will slowly raise as you level up.");
		
		//Strength Points Hover Listener
		addHoverListener(strengthPoints, "Your strength starts at zero, and will grow as you complete events and level up. "
				+ "It is a total of your Physical Strength (PS), your Will, and your Endurance (End).");
		addHoverListener(physicalPoints, "Your physical strength points indicate how strong you are physically. "
				+ "These values are used to calculate damage done during combat");
		addHoverListener(willPoints, "Your will is your mental health, determining how sane you are, "
				+ "as well how easy it is to corrupt you and your mind.");
		addHoverListener(endurancePoints, "Your endurance is your ability to withstand specific taxing events, both emotionally and physically. "
				+ "It is used in game to calculate how long you can stand up against pressure, be it mental or physical");
		
		//Intelligence Points Hover Listener
		addHoverListener(intelPoints, "Your intelligence starts at zero, and will grow as you complete events and level up.");
		addHoverListener(communicationPoints, "Your communication points determine how efective you are at persuading, "
				+ "bargaining, and communicating with other entities.");
		addHoverListener(problemSolvePoints, "Your problem solving points determine how easy it is to solve puzzles, hack terminals, and pick locks.");
		addHoverListener(insightPoints, "Your insight points determine how observant you are, and how easily you notice things that "
				+ "are out of the ordinary.");
		
		//Agility Points Hover Listener
		addHoverListener(agilPoints, "Your agility starts at zero, and will grow as you complete events and level up.");
		addHoverListener(precisionPoints, "Your precision points determine how accurate you are, including with long range weapons.");
		addHoverListener(athleticsPoints, "Your athletics points determine your ability to out-run and out-maneuver enemies or other obstacles.");
		addHoverListener(balancePoints, "Your balance points determine your ability to stay balanced in combat. Higher balance points make "
				+ "it harder for you to be knocked prone.");
		
		//Character Picture Hover Listener
		addHoverListener(characterPanel, "This is your character picture. It shows your gem location, as well as armor and weapons equiped.");
		
		//Add Listener for Stats Button
		statsButton.addActionListener(new ActionListener() {

			@Override			
			public void actionPerformed(ActionEvent e) {
								
				healthBar.setVisible(true);
				strengthPoints.setVisible(true);
				intelPoints.setVisible(true);
				agilPoints.setVisible(true);
				physicalPoints.setVisible(true);
				willPoints.setVisible(true);
				endurancePoints.setVisible(true);
				communicationPoints.setVisible(true);
				problemSolvePoints.setVisible(true);
				insightPoints.setVisible(true);
				precisionPoints.setVisible(true);
				athleticsPoints.setVisible(true);
				balancePoints.setVisible(true);
								
				characterPanel.setVisible(false);
							
			}
							
		});
					
		//Add Listener for Stats Button
		characterButton.addActionListener(new ActionListener() {

			@Override
									
				public void actionPerformed(ActionEvent e) {
									
					healthBar.setVisible(false);
					strengthPoints.setVisible(false);
					intelPoints.setVisible(false);
					agilPoints.setVisible(false);
					physicalPoints.setVisible(false);
					willPoints.setVisible(false);
					endurancePoints.setVisible(false);
					communicationPoints.setVisible(false);
					problemSolvePoints.setVisible(false);
					insightPoints.setVisible(false);
					precisionPoints.setVisible(false);
					athleticsPoints.setVisible(false);
					balancePoints.setVisible(false);
							
					characterPanel.setVisible(true);
									
				}
									
		});
		
		//Add Listener for Enter Button
		enter.addActionListener(new ActionListener() {

			@Override
			
			public void actionPerformed(ActionEvent e) {
				
				try {
					Feats.runNextPath(Feats.location);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
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
	
	private static void setVisuals(JLabel comp, Color border, int borderWidth, Color background, Boolean visible){
		
		comp.setBorder(BorderFactory.createLineBorder(border, borderWidth, true));
		comp.setOpaque(true);
		comp.setBackground(background);
		comp.setVisible(visible);
		comp.setHorizontalAlignment(SwingConstants.CENTER);
		
	}
	
	private static void setVisualsPanel(JPanel comp, Color border, int borderWidth, Color background, Boolean visible){
		
		comp.setBorder(BorderFactory.createLineBorder(border, borderWidth, true));
		comp.setOpaque(true);
		comp.setBackground(background);
		comp.setVisible(visible);
		
	}
	
}
	
