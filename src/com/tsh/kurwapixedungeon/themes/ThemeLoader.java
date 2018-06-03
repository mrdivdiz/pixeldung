package com.tsh.kurwapixedungeon.themes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
//import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import com.tsh.kurwapixedungeon.KurwaDungeon;
import com.tsh.kurwapixedungeon.Preferences;
import com.tsh.kurwapixedungeon.scenes.ThemeScene;
import com.tsh.kurwapixedungeon.scenes.TitleScene;
import com.tsh.utils.Bundle;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.Context;

@SuppressWarnings("unused")
public class ThemeLoader extends Activity{
	public static boolean theme1 = Theme.bull;
	public static boolean theme2 = Theme.bull2;
	static boolean loadthem1;
	static boolean loadthem2;
	 public static String baduda = "Baduda";
	 public static String themerec1 = "THEME1";
	 public static String themerec2 = "THEME2";
	 public static SharedPreferences settings = null;
		public static Context context = null;
		
		public static void init( Context cntxt ){
			context = cntxt;
		}
		
		/*public void init2(){
			if(settings == null){
			SharedPreferences settings = getSharedPreferences(baduda, Context.MODE_PRIVATE);
			
		}}*/
		
		public static void addProperty(){
			KurwaDungeon.switchScene(ThemeScene.class);
	    	Bundle bundle = new Bundle();
	    	bundle.put( themerec1, theme1 );
	    	bundle.put( themerec2, theme1  );
			
		}
public static void savv(){
	    	
	    	Bundle bundle = new Bundle();
	    	Theme.bull = bundle.getBoolean(themerec1);
	    	Theme.bull2 = bundle.getBoolean(themerec2);
			
		}
		
	    public static void getProperty(){
	    	KurwaDungeon.switchScene(ThemeScene.class);
	    	Bundle bundle = new Bundle();
	    	Theme.bull = bundle.getBoolean(themerec1);
	    	Theme.bull2 = bundle.getBoolean(themerec2);
			
	    }}
