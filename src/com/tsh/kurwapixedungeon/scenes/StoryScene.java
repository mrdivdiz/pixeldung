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
package com.tsh.kurwapixedungeon.scenes;

import java.util.Random;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES20;

import com.tsh.kurwapixedungeon.Assets;
import com.tsh.kurwapixedungeon.KurwaDungeon;
import com.tsh.kurwapixedungeon.effects.Fireball;
import com.tsh.kurwapixedungeon.effects.StorySprite;
import com.tsh.kurwapixedungeon.themes.Theme;
import com.tsh.kurwapixedungeon.themes.ThemeLoader;
import com.tsh.kurwapixedungeon.ui.ExitButton;
import com.tsh.kurwapixedungeon.ui.PrefsButton;
import com.tsh.kurwapixedungeon.ui.RedButton;
import com.tsh.noosa.BitmapText;
import com.tsh.noosa.Camera;
import com.tsh.noosa.Game;
import com.tsh.noosa.Image;
import com.tsh.noosa.audio.Music;
import com.tsh.noosa.audio.Sample;
import com.tsh.noosa.ui.Button;

public class StoryScene extends PixelScene {

	public static String grind = "My story.";
	public static String grind2;
	public static String grind3;
	public static int ai = 0;
	public static Image SCEN1 = new Image(StorySprite.get( StorySprite.Type.PDPRESENTING ));
	
	@Override
	public void create() {
		
		super.create();
		
		
		Music.INSTANCE.play( Assets.THEME, true );
		Music.INSTANCE.volume( 1f );

		
		uiCamera.visible = false;
		
		int w = Camera.main.width;
		int h = Camera.main.height;
		

		
		RedButton btnLoad = new RedButton( "Next" ) {
			@Override
			protected void onClick() {
				ai++;
				if(ai == 0){
					SCEN1 = (StorySprite.get( StorySprite.Type.PDPRESENTING ));
					grind = "My story.";
					grind2 = "";
					grind3 = "";
					Game.switchScene(StoryScene.class);
					
				}
				if(ai == 1){
					SCEN1 = (StorySprite.get( StorySprite.Type.STOR1 ));
					grind = "I lived in a very beauty and known ";
					grind2 = "russian city, Mukhosransk ( Flyshitcity";
					grind3 = " in translate ).But one time...";
					Game.switchScene(StoryScene.class);
				}
				if(ai == 2){
					SCEN1 = (StorySprite.get( StorySprite.Type.STOR2 ));
					grind = "But one time someone start to drop";
					grind2 = "bombs on it and on all the world.";
					grind3 = "Earth dies.";
					Game.switchScene(StoryScene.class);
				}
				if(ai == 3){
					SCEN1 = (StorySprite.get( StorySprite.Type.STOR3 ));
					grind = "Six months later i was one of small";
					grind2 = "pile of survived people.";
					grind3 = "";
					Game.switchScene(StoryScene.class);
				}
				if(ai == 4){
					SCEN1 = (StorySprite.get( StorySprite.Type.STOR4 ));
					grind = "I can't live there anymore because of";
					grind2 = "radiation... But i found ancient";
					grind3 = "Dungeon, my last hope in this world.";
					Game.switchScene(StoryScene.class);
				}
				if(ai == 5){
					SCEN1 = (StorySprite.get( StorySprite.Type.PDPRESENTING ));
					grind = "Shockwawe present:";
					grind2 = "Kurrwa pixel dungeon:";
					grind3 = "A trash-roguelike with many new things.";
					Game.switchScene(StoryScene.class);
				}
				if(ai == 6){
					ai = 0;
					grind = "My story.";
					grind2 = "";
					grind3 = "";
					Game.switchScene(StoryScene.class);
				}
			}
		};
		add(SCEN1);
		btnLoad.setSize( 60, 18);
		add( btnLoad );
		
		float height = SCEN1.height;
		
		SCEN1.x = (w - SCEN1.width()) / 2;
		SCEN1.y = (h - height) / 4;
		
		placeTorch( SCEN1.x + 3, SCEN1.y + 20 );
		placeTorch( SCEN1.x + SCEN1.width - 5, SCEN1.y + 20 );
		btnLoad.setPos( Camera.main.width  / 2,  Camera.main.height / 2 + Camera.main.height / 3 );
		
		
		BitmapText version = new BitmapText( grind, font1x );
		version.measure();
		version.hardlight( 0xFF0000 );
		version.x = 0;
		version.y = h / 2 + 15;
		add( version );
		
		BitmapText version2 = new BitmapText( grind2, font1x );
		version2.measure();
		version2.hardlight( 0xFF0000 );
		version2.x = 0;
		version2.y = h / 2 + 15 + version.height();
		add( version2 );
		
		BitmapText version3 = new BitmapText( grind3, font1x );
		version3.measure();
		version3.hardlight( 0xFF0000 );
		version3.x = 0;
		version3.y = h / 2 + 15 + version.height() + version.height();
		add( version3 );
		
		ExitButton btnExit = new ExitButton();
		btnExit.setPos( w - btnExit.width(), 0 );
		add( btnExit );
		
		fadeIn();
	}
	
	private void placeTorch( float x, float y ) {
		Fireball fb = new Fireball();
		fb.setPos( x, y );
		add( fb );
	}}