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
package com.tsh.kurwapixedungeon.windows;

import com.tsh.kurwapixedungeon.Chrome;
import com.tsh.kurwapixedungeon.Dungeon;
import com.tsh.kurwapixedungeon.actors.Actor;
import com.tsh.kurwapixedungeon.actors.hero.Hero;
import com.tsh.kurwapixedungeon.items.LloydsBeacon;
import com.tsh.kurwapixedungeon.items.Unlocker;
import com.tsh.kurwapixedungeon.levels.BaseLevel;
import com.tsh.kurwapixedungeon.levels.Level;
import com.tsh.kurwapixedungeon.levels.SewerLevel;
import com.tsh.kurwapixedungeon.scenes.InterlevelScene;
import com.tsh.kurwapixedungeon.ui.HighlightedText;
import com.tsh.kurwapixedungeon.ui.RedButton;
import com.tsh.kurwapixedungeon.ui.Window;
import com.tsh.kurwapixedungeon.utils.GLog;
import com.tsh.noosa.Game;
import com.tsh.noosa.audio.Sample;
import com.tsh.utils.Bundle;

import java.io.IOException;

import com.tsh.kurwapixedungeon.Assets;
import com.tsh.kurwapixedungeon.Base;

public class WndBase extends Window {

	public  RedButton teleport;
	public static int teldepth;
	public static int telpos;
	public static int first = 0;
		

	public static void savpos(Bundle bundle){
		//Bundle bundle = new Bundle();
		Hero hero = new Hero();
		bundle.put(Base.f1f , hero.pos);
		bundle.put(Base.f1e , Dungeon.depth);
	}
	public static void loadpos(Bundle bundle){
		//Bundle bundle = new Bundle();
		teldepth = bundle.getInt( Base.f1e );
		telpos	= bundle.getInt( Base.f1f );
	}
	public static void loadandsetpos(){
		Bundle bundle = new Bundle();
		Hero hero = new Hero();
		bundle.put(Base.f1f , 	hero.pos);
		bundle.put(Base.f1e , Dungeon.depth);
		teldepth = bundle.getInt( Base.f1e );
		telpos	= bundle.getInt( Base.f1f );
	}
	
	public WndBase() {
		
		super( 80, 100, Chrome.get(Chrome.Type.WINDOW));
		Unlocker.loadtel();
		HighlightedText txt1 = new HighlightedText(6);
		txt1.setPos(2, 5);
		txt1.text("Your base stats:"
				+ "\n\n Energy:" + Base.energy
				+ "\n Resources:" + Base.resources
				+ "\n Clean water:" + Base.water
				+ "\n Oxygen:" + Base.oxygen + " teldepth & telpos + onbase" + Unlocker.teldepth + " " + Unlocker.telpos + " " + Unlocker.onBase, 80);
		add(txt1);
		
		
		
		teleport = new RedButton( "Base teleport" ){
			@Override
			protected void onClick(){
				if(Base.isUnlocked = false){
				if(Base.onBase = false   ){
					Game.switchScene( InterlevelScene.class );
					
					Base.onBase = true;
					
					//savpos();
						try {
							
							Base.sav();
							
							Dungeon.saveLevel();
							
						} catch (IOException e1) {}
						if(first == 0){
						first ++;
						Dungeon.depth = 32;
						Level levelbase = new BaseLevel();
						levelbase.create();
						Dungeon.switchLevel( levelbase, 98 );
				}
						
						
						
			}
				else	if(Base.onBase = true){
					
				/*	Game.switchScene( InterlevelScene.class );
				loadpos();
					Dungeon.depth = teldepth;
					try {
						Base.sav();
						Dungeon.saveLevel();
						Level level;
						level = Dungeon.loadLevel( Dungeon.hero.heroClass );
						Dungeon.switchLevel( level, telpos );
						
						} catch (IOException e1) {}
						Base.onBase = false;
				*/
				//}
				
				//unlocked//}//Больше скобочек и комментариев!!
				//else if(Base.isUnlocked == false){
					//GLog.n("You won't unlocked base yet!");//Не пишет!Блядь!
				//}
			}}
			
		

	}};
	teleport.setRect(15, 80, 50, 15);
	add(teleport);
	}
}
