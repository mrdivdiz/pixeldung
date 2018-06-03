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
import java.util.Timer;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES20;

import com.tsh.kurwapixedungeon.Assets;
import com.tsh.kurwapixedungeon.KurwaDungeon;
import com.tsh.kurwapixedungeon.effects.BannerSprites;
import com.tsh.kurwapixedungeon.effects.Fireball;
import com.tsh.kurwapixedungeon.effects.SecPres;
import com.tsh.kurwapixedungeon.scenes.TitleScene.DashboardItem;
import com.tsh.kurwapixedungeon.sprites.PresAnim;
import com.tsh.kurwapixedungeon.themes.Theme;
import com.tsh.kurwapixedungeon.themes.ThemeLoader;
import com.tsh.kurwapixedungeon.themes.Timerest;
import com.tsh.kurwapixedungeon.ui.Archs;
import com.tsh.kurwapixedungeon.ui.ExitButton;
import com.tsh.kurwapixedungeon.ui.PrefsButton;
import com.tsh.noosa.BitmapText;
import com.tsh.noosa.Camera;
import com.tsh.noosa.Game;
import com.tsh.noosa.Image;
import com.tsh.noosa.MovieClip;
import com.tsh.noosa.TextureFilm;
import com.tsh.noosa.MovieClip.Animation;
import com.tsh.noosa.audio.Music;
import com.tsh.noosa.audio.Sample;
import java.util.Timer;
import java.util.TimerTask;
import com.tsh.noosa.ui.Button;

public class PresentingScene extends PixelScene {
private Timer mTimer;
public static Image sight = SecPres.get( SecPres.Type.PIXEL_DUNGEON );
	public boolean glog = true;
	@Override
	public void create() {
	
		super.create();	
		int w = Camera.main.width;
		int h = Camera.main.height;
		
		
		Music.INSTANCE.play( Assets.THEME, false );
		Music.INSTANCE.volume( 1f );
	
				
			Image press = new Image(sight);
			
			
			press.x = (w - press.width()) / 2;
			press.y = (h / 3);
			add(press);

		
		uiCamera.visible = false;
		
		/*ClickItem btns = new ClickItem( "", 12 ) {
			@Override
			protected void onClick() {
				KurwaDungeon.switchNoFade( TitleScene.class );
			}
		};
		add( btns );
		btns		.setPos( 10, h / 9);
		
		BitmapText version = new BitmapText( "Original by watabou", font1x );
		version.measure();
		version.hardlight( 0xFF0000 );
		version.x = w - version.width();
		version.y = h - version.height();
		add( version );
		BitmapText version2 = new BitmapText( "Tap on screen center to continue", font1x );
		version2.measure();
		version2.hardlight( 0xFF0000 );
		version2.x = w - version2.width();
		version2.y = version.y - version.height();
		add( version2 );
		//time();
		fadeIn();	
	}*/
	/*public static void time(){
	Timerest tim = new Timerest(5000, 1000);
	tim.onTick(5000);		//FIXME
	tim.start();
	tim.onFinish();{
	 	Game.switchScene(TitleScene.class);
	}}
	//protected void onClick(){
	//	KurwaDungeon.switchNoFade( TitleScene.class );
	//}*/
	/*public static class ClickItem extends Button {
		
		public static final float SIZE	= 192;
		
		private static final int IMAGE_SIZE	= 32;
		
		private Image image;
		private BitmapText label;
		
		public ClickItem( String text, int index ) {
			super();
			
			image.frame( image.texture.uvRect( index * IMAGE_SIZE, 0, (index + 1) * IMAGE_SIZE, IMAGE_SIZE ) );
			this.label.text( text );
			this.label.measure();
			
			setSize( SIZE, SIZE );
		}
		
		@Override
		protected void createChildren() {
			super.createChildren();
			
			image = new Image( Assets.DASHBOARD );
			add( image );
			
			label = createText( 9 );
			add( label );
		}
		
		@Override
		protected void layout() {
			super.layout();
			
			image.x = align( x + (width - image.width()) / 2 );
			image.y = align( y );
			
			label.x = align( x + (width - label.width()) / 2 );
			label.y = align( image.y + image.height() +2 );
		}
		
		@Override
		protected void onTouchDown() {
			image.brightness( 1.5f );
			Sample.INSTANCE.play( Assets.SND_CLICK, 1, 1, 0.8f );
		}
		
		@Override
		protected void onTouchUp() {
			image.resetColor();
		}
	*/
}}

