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

import java.nio.FloatBuffer;

import com.tsh.gltextures.Gradient;
import com.tsh.gltextures.SmartTexture;
import com.tsh.glwrap.Quad;
import com.tsh.kurwapixedungeon.Assets;
import com.tsh.kurwapixedungeon.Dungeon;
import com.tsh.kurwapixedungeon.KurwaDungeon;
import com.tsh.kurwapixedungeon.effects.BannerSprites;
import com.tsh.kurwapixedungeon.effects.Flare;
import com.tsh.kurwapixedungeon.effects.Speck;
import com.tsh.kurwapixedungeon.themes.Theme;
import com.tsh.kurwapixedungeon.themes.ThemeLoader;
import com.tsh.kurwapixedungeon.ui.RedButton;
import com.tsh.noosa.BitmapTextMultiline;
import com.tsh.noosa.Camera;
import com.tsh.noosa.Game;
import com.tsh.noosa.Group;
import com.tsh.noosa.Image;
import com.tsh.noosa.NoosaScript;
import com.tsh.noosa.Visual;
import com.tsh.utils.Bundle;
import com.tsh.utils.Random;

import android.content.Context;
import android.content.SharedPreferences;


public class ThemeScene extends PixelScene {

	private static final String TXT_EXIT	= "Standart";
	private static final String TXT_STAY	= "Pepsi - skillful thief";
	private static final String TXT_STAY2	= "Golden style";
	private static final String TXT_STAY3	= "Save unlocked themes";
	private static final String TXT_STAY4	= "Load unlocked themes";
	private static String bulle = "locked";
	private static String bulle2 = "locked";
	private static final int WIDTH			= 120;
	private static final int BTN_HEIGHT		= 18;
	private static final float SMALL_GAP	= 2;
	private static final float LARGE_GAP	= 8;
	int w = Camera.main.width;
	int h = Camera.main.height;
	private Camera viewport;
	private static final String TXT = 
		"Select music & interface theme (its can be unlocked by getting badges.)"
		+ "If it not unlocked, button does nothing.";
	
	public static boolean noText = false;
	public static Image titel = BannerSprites.get( BannerSprites.Type.PIXEL_DUNGEON );
	public static Image sight = BannerSprites.get( BannerSprites.Type.PIXEL_DUNGEON_SIGNS );
	
	private Image amulet = new Image( Assets.STANDARTTHEM );
	public static Bundle bundle = new Bundle();
	@Override
	public void create() {
		if(Theme.bull != false){
			bulle = "unlocked";
		}
		if(Theme.bull2 != false){
			bulle2 = "unlocked";
		}
		if(Theme.bull != true){
			bulle = "locked";
		}
		if(Theme.bull2 != true){
			bulle2 = "locked";
		}
		super.create();
		
		
		boolean dayTime = !Dungeon.nightMode;
		
		Group window = new Group();
		window.camera = viewport;
		add( window );
		
		Sky sky = new Sky( dayTime );
		sky.scale.set( w, h );
		window.add( sky );
		
		BitmapTextMultiline text = null;
		if (!noText) {
			text = createMultiline( TXT, 8 );
			text.maxWidth = WIDTH;
			text.measure();
			add( text );
		}
		
		amulet = new Image( Assets.STANDARTTHEM );
		add( amulet );
		RedButton btnSave = new RedButton( TXT_STAY3 ) {
			@Override
			protected void onClick() {
				
				ThemeLoader.addProperty();
				
			}
		};
		btnSave.setSize( WIDTH /2, BTN_HEIGHT);
		add( btnSave );
		
		
		RedButton btnLoad = new RedButton( TXT_STAY4 ) {
			@Override
			protected void onClick() {
				ThemeLoader.getProperty();
			}
		};
		btnLoad.setSize( WIDTH / 2, BTN_HEIGHT);
		add( btnLoad );
		
		
		
		RedButton btnExit = new RedButton( TXT_EXIT  ) {
			@Override
			protected void onClick() {
				Assets.FIREBALL = "fireball.png";
				Assets.ARCS_BG = "arcs1.png";
				Assets.ARCS_FG = "arcs2.png";
				Assets.STATUS = "status_pane.png";
				Assets.TOOLBAR = "toolbar.png";
				Assets.CHROME = "chrome.png";
				KurwaDungeon.switchScene(TitleScene.class);
				//Assets.STANDARTTHEM = "standartthem2.png";
				titel = BannerSprites.get( BannerSprites.Type.PIXEL_DUNGEON );
				sight = BannerSprites.get( BannerSprites.Type.PIXEL_DUNGEON_SIGNS );
				
			}
		};
		btnExit.setSize( WIDTH, BTN_HEIGHT );
		add( btnExit );
		
		RedButton btnStay = new RedButton( TXT_STAY + " is " + bulle) {
			@Override
			protected void onClick() {
				
				if(Theme.bull != false){
					Assets.FIREBALL = "fireball2.png";

				Assets.ARCS_BG = "arcs3.png";
				Assets.ARCS_FG = "arcs4.png";
				Assets.STATUS = "status_pane2.png";
				Assets.TOOLBAR = "toolbar2.png";
				Assets.CHROME = "chrome2.png";
				Assets.STANDARTTHEM = "standartthem2.png";
				KurwaDungeon.switchScene(TitleScene.class);
				titel = BannerSprites.get( BannerSprites.Type.PIXEL_DUNGEON );
				sight = BannerSprites.get( BannerSprites.Type.PEPSI_SIGHS );
				
			}else{
				KurwaDungeon.switchScene(TitleScene.class);
			}
				}
		};
		btnStay.setSize( WIDTH, BTN_HEIGHT );
		add( btnStay );
		
		
		RedButton btnStay2 = new RedButton( TXT_STAY2 + " is " + bulle2) {
			@Override
			protected void onClick() {
				
				if(Theme.bull2 != false){
					Assets.FIREBALL = "fireball4.png";

					
				
				
				Assets.ARCS_BG = "arcs5.png";
				Assets.ARCS_FG = "arcs6.png";
				Assets.STATUS = "status_pane3.png";
				Assets.TOOLBAR = "toolbar3.png";
				Assets.CHROME = "chrome3.png";
				Assets.STANDARTTHEM = "standartthem3.png";
				KurwaDungeon.switchScene(TitleScene.class);
				titel = BannerSprites.get( BannerSprites.Type.PIXEL_DUNGEON );
				sight = BannerSprites.get( BannerSprites.Type.PEPSI_SIGHS );
				
				
			}else{
				KurwaDungeon.switchScene(TitleScene.class);
				
			}
				
			
				}
		};
		btnStay2.setSize( WIDTH, BTN_HEIGHT );
		add( btnStay2 );
		
		
		float height;
		if (noText) {
			height = amulet.height + LARGE_GAP + btnExit.height() + SMALL_GAP + btnStay.height();
			
			amulet.x = align( (Camera.main.width - amulet.width) / 2 );
			amulet.y = align( (Camera.main.height - height) / 2 );
			btnExit.setPos( (Camera.main.width - btnExit.width()) / 2, amulet.y + amulet.height + LARGE_GAP );
			btnStay.setPos( btnExit.left(), btnExit.bottom() + SMALL_GAP );
			
		} else {
			height = amulet.height + LARGE_GAP + text.height() + LARGE_GAP + btnExit.height() + SMALL_GAP + btnStay.height();
			
			amulet.x = align( (Camera.main.width - amulet.width) / 2 );
			amulet.y = align( (Camera.main.height - height) / 2 );
			
			text.x =  align( (Camera.main.width - text.width()) / 2 );
			text.y = amulet.y + amulet.height + LARGE_GAP;
			
			btnExit.setPos( (Camera.main.width - btnExit.width()) / 2, text.y + text.height() + LARGE_GAP );
			btnStay.setPos( btnExit.left(), btnExit.bottom() + SMALL_GAP );
			btnStay2.setPos( btnStay.left(), btnStay.bottom() + SMALL_GAP );
			btnSave.setPos( Camera.main.width  / 10, 10 );
			btnLoad.setPos( Camera.main.width  / 10,  30 );
		}

		new Flare( 8, 48 ).color( 0xFFDDBB, true ).show( amulet, 0 ).angularSpeed = +30;
		
		fadeIn();
	}
	
	@Override
	protected void onBackPressed() {
	//	InterlevelScene.mode = InterlevelScene.Mode.CONTINUE;
		KurwaDungeon.switchScene( TitleScene.class );
	}
	
	private float timer = 0;
	
	@Override
	public void update() {
		super.update();
		
		if ((timer -= Game.elapsed) < 0) {
			timer = Random.Float( 0.5f, 10f );
			
			Speck star = (Speck)recycle( Speck.class );
			star.reset( 0, amulet.x + 10.5f, amulet.y + 5.5f, Speck.DISCOVER );
			add( star );
		}
	}
private static class Sky extends Visual {
		
		private static final int[] day		= {0xFF4488FF, 0xFFCCEEFF};
		private static final int[] night	= {0xFF001155, 0xFF335980};
		
		private SmartTexture texture;
		private FloatBuffer verticesBuffer;
		
		public Sky( boolean dayTime ) {
			super( 0, 0, 1, 1 );

			texture = new Gradient( dayTime ? day : night );
			
			float[] vertices = new float[16];
			verticesBuffer = Quad.create();
			
			vertices[2]		= 0.25f;
			vertices[6]		= 0.25f;
			vertices[10]	= 0.75f;
			vertices[14]	= 0.75f;
			
			vertices[3]		= 0;
			vertices[7]		= 1;
			vertices[11]	= 1;
			vertices[15]	= 0;
			
			
			vertices[0] 	= 0;
			vertices[1] 	= 0;
			
			vertices[4] 	= 1;
			vertices[5] 	= 0;
			
			vertices[8] 	= 1;
			vertices[9] 	= 1;
			
			vertices[12]	= 0;
			vertices[13]	= 1;
			
			verticesBuffer.position( 0 );
			verticesBuffer.put( vertices );
		}
		
		@Override
		public void draw() {
			
			super.draw();

			NoosaScript script = NoosaScript.get();
			
			texture.bind();
			
			script.camera( camera() );
			
			script.uModel.valueM4( matrix );
			script.lighting( 
				rm, gm, bm, am, 
				ra, ga, ba, aa );
			
			script.drawQuad( verticesBuffer );
		}
	}
}
