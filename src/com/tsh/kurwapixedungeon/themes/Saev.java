package com.tsh.kurwapixedungeon.themes;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class Saev extends Activity {
	 static SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(null); 
	
	public static void Save(){
	  
	if(Theme.bull == true)
   {
	//SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(null); 
	prefs.edit().putBoolean("bull", Theme.bull).commit();
   }
	if(Theme.bull2 == true)
	   {
		//SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(null); 
		prefs.edit().putBoolean("bull2", Theme.bull2).commit();
	   }
	
	
	
   }
	

   public static void Load(){
	   
	   
	   if(prefs.getBoolean("bull", true))
	   {
		   Theme.bull = true;

		   
		   
		   
	   }
	   if(prefs.getBoolean("bull2", true))
	   {

		  Theme.bull2 = true;
		   
		   
	   }
	   
   }
}