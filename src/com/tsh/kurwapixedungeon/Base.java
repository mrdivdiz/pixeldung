package com.tsh.kurwapixedungeon;

import com.tsh.kurwapixedungeon.actors.Char;
import com.tsh.kurwapixedungeon.actors.hero.Hero;
import com.tsh.kurwapixedungeon.levels.BaseLevel;
import com.tsh.kurwapixedungeon.levels.Level;
import com.tsh.kurwapixedungeon.windows.WndBase;
import com.tsh.utils.Bundle;

public class Base extends Char{
	public static int energy = 0;
	public static int resources = 0;
	public static int water = 0;
	public static int oxygen = 0;
	public static int latestmoves = 0;
	public static boolean isUnlocked = false;
	public static boolean isCreated = false;
	public static boolean onBase;

	public static final String f1a = "energy";
	public static final String f1b = "resources";
	public static final String f1c = "water";
	public static final String f1d = "oxygen";
	public static final String f1e = "teldepth";
	public static final String f1f = "telpos";
	public static final String f1g = "telcount";
	public static final String f1h = "onbase";
	public static final String f1i = "created";
	public static final String f1j = "unlocked";
	public static final String f1k = "latestmoves";
	
	/*public static void onBaseSav(boolean b){
		Bundle bundle = new Bundle();
		bundle.put("onbase", b);
	}
	public static void onBaseLoad(boolean b){
		Bundle bundle = new Bundle();
		onBase = bundle.getBoolean("onbase");
		
	}*/
	
	public static void sav(){
		Bundle bundle = new Bundle();
		bundle.put(f1g, WndBase.first);
		
		bundle.put(f1a, energy);
		bundle.put(f1b, resources);
		bundle.put(f1c, water);
		bundle.put(f1d, oxygen);
		bundle.put(f1h, onBase);
		bundle.put(f1j, isUnlocked);
		bundle.put(f1i, isCreated);
		bundle.put(f1k, latestmoves);
	}
	public static void load(){
		Bundle bundle = new Bundle();
		WndBase.first = bundle.getInt( f1g );
		
		energy = bundle.getInt(f1a);
		resources = bundle.getInt(f1b);
		water = bundle.getInt(f1c);
		oxygen = bundle.getInt(f1d);
		onBase = bundle.getBoolean(f1h);
		isCreated = bundle.getBoolean(f1i);
		isUnlocked = bundle.getBoolean(f1j);
		latestmoves = bundle.getInt(f1k);
	}
	
	
	public static void CreateBase(){
		Hero hero = new Hero();
		energy = 0;
		resources = 0;
		water = 0;
		oxygen = 0;
		latestmoves = (int)Statistics.duration;
		if(Dungeon.depth == 32){
			Level levelbase = new BaseLevel();
			levelbase.create();
		}
		
		
	}
	
	
}
