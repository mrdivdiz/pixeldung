/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.tsh.kurwapixedungeon.items;

import java.io.IOException;
import java.util.ArrayList;

import com.tsh.kurwapixedungeon.Base;
import com.tsh.kurwapixedungeon.Dungeon;
import com.tsh.kurwapixedungeon.actors.hero.Hero;
import com.tsh.kurwapixedungeon.effects.Speck;
import com.tsh.kurwapixedungeon.effects.particles.FlameParticle;
import com.tsh.kurwapixedungeon.levels.BaseLevel;
import com.tsh.kurwapixedungeon.levels.Level;
import com.tsh.kurwapixedungeon.scenes.InterlevelScene;
import com.tsh.kurwapixedungeon.sprites.ItemSpriteSheet;
import com.tsh.kurwapixedungeon.utils.GLog;
import com.tsh.kurwapixedungeon.windows.WndCraft;
import com.tsh.noosa.Game;
import com.tsh.noosa.particles.Emitter;
import com.tsh.utils.Bundle;

public class Unlocker extends Item {
	

	public static int teldepth;
	public static int telpos;
	public static int onBase = 0;
	public static int first = 0;
	
	 public static String AC_LOOK = "Unlock it!";
	 public static String AC_DOOK = "To Base";
	 public static String AC_DOOK2 = "To Dungeon";
	
	{
		
		name = "Base teleporter";
		image = ItemSpriteSheet.UNLOCKER;
		defaultAction = AC_LOOK;
		stackable = true;
	}
	
	private static final String TELDEPTH	= "teldepth";
	private static final String TELPOS		= "telpos";
	private static final String ONBASE		= "onbase";
	private static final String FIRST		= "first";
	
	public void onbchg(){
		if(onBase == 0){
			onBase = Dungeon.depth;
		}else{
			onBase = 0;
		}
	}
	
	public static void savetel() {
		Bundle bundle = new Bundle();
		bundle.put(FIRST, first);
		bundle.put(ONBASE, onBase);
		bundle.put( TELDEPTH, teldepth );
		bundle.put( TELPOS, telpos );
	}
	
	public static void loadtel() {
		Bundle bundle = new Bundle();
		onBase = bundle.getInt(ONBASE);
		teldepth = bundle.getInt(TELDEPTH);
		telpos   = bundle.getInt(TELPOS);
	}
	
	
	public ArrayList<String> actions( Hero hero ) {
		ArrayList<String> actions = super.actions( hero );
		if(Base.isUnlocked != true){
		actions.add( AC_LOOK );
		}
		actions.add( AC_DOOK );
		if(teldepth != 0 && telpos != 0){
		actions.add( AC_DOOK2);
		}
		return actions;
	}
	
	public void execute( final Hero hero, String action ){
		if(action.equals(AC_LOOK)){
			Base.isUnlocked = true;
		}
		
		if(action.equals(AC_DOOK)){
			if(Base.isUnlocked == false){
					teldepth = Dungeon.depth;
					telpos = hero.pos;
					
					try {
						Base.sav();
						Dungeon.saveLevel();
					}catch (IOException e1) {
						GLog.n("Blyad.");
					}
				if(first == 0){
					InterlevelScene.mode = InterlevelScene.Mode.RETURN;
					first ++;
					Dungeon.depth = 32;
					Level levelbase = new BaseLevel();
					levelbase.create();
					Dungeon.switchLevel( levelbase, 98 );
				}
				if(first <= 1){
				InterlevelScene.mode = InterlevelScene.Mode.RETURN;
				InterlevelScene.returnDepth = 32;
				InterlevelScene.returnPos = 98;
				}
				savetel();
				Game.switchScene( InterlevelScene.class );
					/*teldepth = Dungeon.depth;
					telpos = hero.pos;
					try {
					Base.sav();
					Dungeon.saveLevel();
					savetel();
					} 
					catch (IOException e1) {
						GLog.n("Blyad.");
					}
					if(first == 0){
					first ++;
					Dungeon.depth = 32;
					Level levelbase = new BaseLevel();
					levelbase.create();
					Dungeon.switchLevel( levelbase, 98 );
					}
					*/
					}//else if(Base.isUnlocked == false){
			//	GLog.n("You won't unlocked base yet!");//Не пишет!Блядь!
			}
					//else	if(onBase == Dungeon.depth){
				//else if(Dungeon.depth == 32){
		if(action.equals(AC_DOOK2)){
			//onBase = 0;
			if(Base.isUnlocked == false){
				//	loadtel();
				InterlevelScene.returnDepth = teldepth;
				InterlevelScene.returnPos = telpos;
				Base.sav();
					/*try {
						Base.sav();
						loadtel();
						Dungeon.saveLevel();
						Dungeon.depth = teldepth;
						
						Level levelr2blyad = Dungeon.loadLevel( Dungeon.hero.heroClass );
						Dungeon.switchLevel( levelr2blyad, telpos );
					}catch (IOException e1){
					GLog.n("Blyad.");
					}
						*/
					}
			InterlevelScene.mode = InterlevelScene.Mode.RETURN;
			Game.switchScene( InterlevelScene.class );
				}
			}
			//else if(Base.isUnlocked == false){
			//	GLog.n("You won't unlocked base yet!");//Не пишет!Блядь!
			//}
	

		
	
	@Override
	public String desc(){
	
		return
				"You finally defeated Goo and now can use the Base." +
				"\n What is it?Basically, its a level where you can store your items," +
				" upgrade it, heal yourself and your pets/companions.\n" +
				"WARNING!\n"
				+ "Base level requires very many gold and some resources to work with:" +
				"\nEnergy, Resources, Clean water and oxygen (as a clean air).";
	}}
	
	
	/*if(Base.isUnlocked == false){
	if(Base.onBase == false   ){
Bundle bundle = new Bundle();

Base.onBase = true;
teldepth = Dungeon.depth;
telpos = hero.pos;
Game.switchScene( InterlevelScene.class );
Base.sav();
try{
	savetel();
	Dungeon.saveLevel();
}catch(IOException e1){
	GLog.n("Unable.");
}

if(first == 0){
	first ++;
	Dungeon.depth = 32;
	Level levelbase = new BaseLevel();
	levelbase.create();
	Dungeon.switchLevel( levelbase, 98 );
}else if( first >= 1){

Dungeon.depth = 32;
Level levelbase = new BaseLevel();
Dungeon.switchLevel( levelbase, 98 );
}
	}else if(Base.onBase == true){
		try {
			loadtel();
			} catch (IOException e1) {
			}
			
		
		
		
		try {
			Level level;
			Dungeon.depth = teldepth;
			level = Dungeon.loadLevel( Dungeon.hero.heroClass );
			Dungeon.switchLevel( level, telpos );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			GLog.n("Fucking ugly error");
		}}}
		
		//Game.switchScene( InterlevelScene.class );
		
	
	if(Base.isUnlocked == false){
		GLog.n("You won't unlocked base yet!");//Не пишет!Блядь!
	}}*/
