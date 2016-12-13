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
import com.reyzerbit.MenuBar;

public class RecentsLoad {

	public static void addRecent(File file, long exist){
		
		if(exist == 1){
			
			System.out.println("Recent already exists.");
			
		}
		else{
			
	        String filePath = new String(System.getProperty("user.home") + Feats.separate + "Documents" + Feats.separate + "SURPG" + Feats.separate + "RequiredFiles" + Feats.separate + "recents.json");

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
            JSONObject obj = (JSONObject) objLoad;
            
            int numberFile = obj.size();
            String numFileLoc = Integer.toString(numberFile);
            
            String fileLoc = file.getPath();
			
			obj.put(numFileLoc, fileLoc);
	        
	        //Save Data
	        try(FileWriter saveData = new FileWriter(filePath)){
		        saveData.write(obj.toJSONString());
		        
			} catch (IOException e) {
				e.printStackTrace();
			} 
	        
	        reloadRecents();
			
		}
		
	}
	
	public static void loadRecents(){
		
		File recentsJSON = new File(System.getProperty("user.home") + Feats.separate + "Documents" + Feats.separate + "SURPG" + Feats.separate + "RequiredFiles" + Feats.separate + "recents.json");
		
		if(recentsJSON.exists()){
			
			JSONParser parser = new JSONParser();
			JSONObject jsonObject;
			
	        try {
	        	
				Object objLoad = parser.parse(new FileReader(recentsJSON));
	            jsonObject = (JSONObject) objLoad;
		        int recNum = jsonObject.size();
		        
		        for(int i = 0; i < recNum-1; i++){
		        	
		        	String recentsPath = Integer.toString(i+1);
		        	String nameMenu = (String) jsonObject.get(recentsPath);
		        	System.out.println(nameMenu);
		        	File fileMenu = new File(nameMenu);
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
			
			System.out.println("Making recents file.");

			URL download = null;
			try {
				download = new URL("http://jacobaccio.github.io/Text_Files/recents.json");
			} catch (MalformedURLException e2) {
				e2.printStackTrace();
			}
			
			try {
				FileUtils.copyURLToFile(download, recentsJSON, 30000, 30000);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
		
	public static void reloadRecents(){
			
		File recentsJSON = new File(System.getProperty("user.home") + Feats.separate + "Documents" + Feats.separate + "SURPG" + Feats.separate + "RequiredFiles" + Feats.separate + "recents.json");
				
		JSONParser parser = new JSONParser();
		JSONObject jsonObject;
				
		   try {
		        	
			   Object objLoad = parser.parse(new FileReader(recentsJSON));
		       jsonObject = (JSONObject) objLoad;
			   int recNum = jsonObject.size();
			        	
			   String recentsPath = Integer.toString(recNum-1);
			   String nameMenu = (String) jsonObject.get(recentsPath);
			   System.out.println(nameMenu);
			   File fileMenu = new File(nameMenu);
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