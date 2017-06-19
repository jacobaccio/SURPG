package com.reyzerbit.fetchDataClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.reyzerbit.Feats;
import com.reyzerbit.guis.MenuBar;

public class RecentsLoad {

	//Add recent method.
	public static void addRecent(File file, long exist){
		
		//If the file says it exists, and the recents file wasn't just made, tell console that it already exists.
		if(exist == 1 && Feats.recMade == 0){
			
			System.out.println("Recent already exists.");
			
		}
		//Otherwise, add save file to recents.
		else{
			
			//Get recents file.
	        String filePath = new String(System.getProperty("user.home") + Feats.separate + "Documents" + Feats.separate + "SURPG" + Feats.separate + "RequiredFiles" + Feats.separate + "recents.json");

	        //Attempt to read recents file.
            JSONParser parser = new JSONParser();
			Object objLoad = null;
			try {
				objLoad = parser.parse(new FileReader(filePath));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			
			//Look at this section later
			
            JSONObject obj = (JSONObject) objLoad;
            
            int numberFile = obj.size();
            
            String numFileLoc = Integer.toString(numberFile);
            
            String fileLoc = file.getPath();
			
			obj.put(numFileLoc, fileLoc);
			
			//^^^^^^^^^^^^^^^^^^^^^^
	        
	        //Save Data
	        try(FileWriter saveData = new FileWriter(filePath)){
		        saveData.write(obj.toJSONString());
		        
			} catch (IOException e) {
				e.printStackTrace();
			} 
	        
	        //Relod recents method.
	        reloadRecents();
			
		}
		
	}
	
	public static void loadRecents(){
		
		//Recetns.json file
		File recentsJSON = new File(System.getProperty("user.home") + Feats.separate + "Documents" + Feats.separate + "SURPG" + Feats.separate + "RequiredFiles" + Feats.separate + "recents.json");
		
		if(recentsJSON.exists()){
			
			//If file exists
			JSONParser parser = new JSONParser();
			JSONObject jsonObject;
			
	        try {
	        	
	        	//Read file
				Object objLoad = parser.parse(new FileReader(recentsJSON));
	            jsonObject = (JSONObject) objLoad;
	            //Get size of recents file.
		        int recNum = jsonObject.size();
		        
		        //Loop to make all recents.
		        for(int i = 0; i < recNum-1; i++){
		        	
		        	//Get recent by taking current loop number (i) and adding 1 to it.
		        	String recentsPath = Integer.toString(i+1);
		        	//Make the a string that is the name of the recent save file, received from the JSON file.
		        	String nameMenu = (String) jsonObject.get(recentsPath);
		        	//Print the name of the file.
		        	System.out.println(nameMenu);
		        	//Make a file from the received save file.
		        	File fileMenu = new File(nameMenu);
		        	//Add said file (with name) to menu bar.
		        	MenuBar.makeMenuItem(nameMenu, fileMenu);
		        	
		        }
				
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			
		}
		else{
			
			//Make the recents file.
			System.out.println("Making recents file.");

			//Tell system that file is made.
			Feats.recMade = 1;
			
			//Set download URL for later use.
			URL download = null;
			try {
				download = new URL("http://jacobaccio.github.io/Text_Files/recents.json");
			} catch (MalformedURLException e2) {
				e2.printStackTrace();
				System.out.println("Failed to set URL for recents.json");
			}
			
			//Try to download said file
			try {
				FileUtils.copyURLToFile(download, recentsJSON, 30000, 30000);
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Failed to download recents.json");
			}
			
		}
		
	}
		
	//Reload recents
	public static void reloadRecents(){
			
		//Get recents file.
		File recentsJSON = new File(System.getProperty("user.home") + Feats.separate + "Documents" + Feats.separate + "SURPG" + Feats.separate + "RequiredFiles" + Feats.separate + "recents.json");
				
		//Load and parse file.
		JSONParser parser = new JSONParser();
		JSONObject jsonObject;
				
		   try {
		        	
			   //Load and check file size.
			   Object objLoad = parser.parse(new FileReader(recentsJSON));
		       jsonObject = (JSONObject) objLoad;
			   int recNum = jsonObject.size();
			   
			   //Set recents path.
			   String recentsPath = Integer.toString(recNum-1);
			   //Get recent save path.
			   String nameMenu = (String) jsonObject.get(recentsPath);
			   //Print said save file.
			   System.out.println(nameMenu);
			   //Make file path out of save file.
			   File fileMenu = new File(nameMenu);
			   //Add that to menu bar.
			   MenuBar.makeMenuItem(nameMenu, fileMenu);
					
		   } catch (FileNotFoundException e1) {
					e1.printStackTrace();
		   } catch (IOException e1) {
					e1.printStackTrace();
		   } catch (ParseException e1) {
					e1.printStackTrace();
		   }
		
	}
	
}
