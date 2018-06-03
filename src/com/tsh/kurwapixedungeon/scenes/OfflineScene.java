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

import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES20;

import com.tsh.kurwapixedungeon.Assets;
import com.tsh.kurwapixedungeon.KurwaDungeon;
import com.tsh.kurwapixedungeon.effects.BannerSprites;
import com.tsh.kurwapixedungeon.effects.Fireball;
import com.tsh.kurwapixedungeon.themes.Theme;
import com.tsh.kurwapixedungeon.ui.Archs;
import com.tsh.kurwapixedungeon.ui.ExitButton;
import com.tsh.kurwapixedungeon.ui.PrefsButton;
import com.tsh.noosa.BitmapText;
import com.tsh.noosa.Camera;
import com.tsh.noosa.Game;
import com.tsh.noosa.Image;
import com.tsh.noosa.audio.Music;
import com.tsh.noosa.audio.Sample;
import com.tsh.noosa.ui.Button;

public class OfflineScene extends PixelScene {

	private static final String TXT_PLAY		= "Classical";
	private static final String TXT_HIGHSCORES	= "Cheats";
	//private static final String TXT_BADGES		= "Campagne";
	private static final String TXT_ABOUT		= "Customs...";
	//private static final String TXT_PRAY		= "Changes & tips";
	
	@Override
	public void create() {
		
		super.create();
		
		Music.INSTANCE.play( Assets.THEME, true );
		Music.INSTANCE.volume( 1f );
		
		uiCamera.visible = false;
		
		int w = Camera.main.width;
		int h = Camera.main.height;
		
		Archs archs = new Archs();
		archs.setSize( w, h );
		add( archs );
		
		Image title = BannerSprites.get( BannerSprites.Type.PIXEL_DUNGEON );
		add( title );
		
		float height = title.height + 
			(KurwaDungeon.landscape() ? DashboardItem.SIZE : DashboardItem.SIZE * 2);
		
		title.x = (w - title.width()) / 2;
		title.y = (h - height) / 2;
		
		placeTorch( title.x + 10, title.y + 20 );
		placeTorch( title.x + title.width - 12, title.y + 20 );
		
		Image signs = new Image( BannerSprites.get( BannerSprites.Type.PIXEL_DUNGEON_SIGNS ) ) {
			private float time = 0;
			@Override
			public void update() {
				super.update();
				am = (float)Math.sin( -(time += Game.elapsed) );
			}
			@Override
			public void draw() {
				GLES20.glBlendFunc( GL10.GL_SRC_ALPHA, GL10.GL_ONE );
				super.draw();
				GLES20.glBlendFunc( GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA );
			}
		};
		signs.x = title.x;
		signs.y = title.y;
		add( signs );
		
		/*DashboardItem btnBadges = new DashboardItem( TXT_BADGES, 0 ) {
			@Override
			protected void onClick() {
				PixelDungeon.switchNoFade( InProgressScene.class );
			}
		};
		add( btnBadges );*/
		
		DashboardItem btnAbout = new DashboardItem( TXT_ABOUT, 8 ) {
			@Override
			protected void onClick() {
				KurwaDungeon.switchNoFade( InProgressScene.class );
			}
		};
		add( btnAbout );
		
		DashboardItem btnPlay = new DashboardItem( TXT_PLAY, 6 ) {
			@Override
			protected void onClick() {
				KurwaDungeon.switchNoFade( StartScene.class );
			}
		};
		add( btnPlay );
		
		DashboardItem btnHighscores = new DashboardItem( TXT_HIGHSCORES, 7 ) {
			@Override
			protected void onClick() {
				KurwaDungeon.switchNoFade( InProgressScene.class );
			}
		};
		add( btnHighscores );
		
		if (KurwaDungeon.landscape()) {
			float y = (h + height) / 2 - DashboardItem.SIZE;
			btnHighscores	.setPos( w / 2 - btnHighscores.width(), y );
			
			btnPlay			.setPos( btnHighscores.left() - btnPlay.width(), y );
			btnAbout		.setPos( w / 2 + 10, y );
		} else {
			
			btnAbout.setPos( w / 2 - DashboardItem.SIZE / 2, h / 2  );
			btnPlay.setPos( w / 2 - btnPlay.width(), btnAbout.top() + DashboardItem.SIZE );
			btnHighscores.setPos( w / 2, btnPlay.top() );
		}
		
	/*	BitmapText version = new BitmapText( "Debug mode", font1x );
		version.measure();
		version.hardlight( 0xFF00FF );
		version.x = w - version.width();
		version.y = h - version.height();
		add( version );
		BitmapText versio = new BitmapText( "DEBUG " + Theme.olded, font1x );
		versio.measure();
		versio.hardlight( 0xFF00FF );
		versio.x = w - versio.width();
		versio.y = h - versio.height() - 10;
		add( versio );*/
		
		PrefsButton btnPrefs = new PrefsButton();
		btnPrefs.setPos( 0, 0 );
		add( btnPrefs );
		
		ExitButton btnExit = new ExitButton();
		btnExit.setPos( w - btnExit.width(), 0 );
		add( btnExit );
		
		fadeIn();
	}
	
	private void placeTorch( float x, float y ) {
		Fireball fb = new Fireball();
		fb.setPos( x, y );
		add( fb );
	}
	
	private static class DashboardItem extends Button {
		
		public static final float SIZE	= 48;
		
		private static final int IMAGE_SIZE	= 32;
		
		private Image image;
		private BitmapText label;
		
		public DashboardItem( String text, int index ) {
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
	}
}
