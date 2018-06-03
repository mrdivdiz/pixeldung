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
import com.tsh.kurwapixedungeon.effects.BannerSprites;
import com.tsh.kurwapixedungeon.effects.Fireball;
import com.tsh.kurwapixedungeon.themes.Theme;
import com.tsh.kurwapixedungeon.themes.ThemeLoader;
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

public class TitleScene extends PixelScene {

	private static final String TXT_PLAY		= "Start";
	private static final String TXT_HIGHSCORES	= "Rankings";
	private static final String TXT_BADGES		= "Badges";
	private static final String TXT_ABOUT		= "About us";
	private static final String TXT_PRAY		= "Changes & tips";
	private static final String TXT_PRAY2		= "Original music...";
	
	@Override
	public void create() {
	
		super.create();
		//if(Theme.mus = 1){
		Music.INSTANCE.play( Assets.THEME, true );
		Music.INSTANCE.volume( 1f );
		//}
		//if(Theme.mus = 2){
			//Music.INSTANCE.play( Assets.THEME, false );
		//	Music.INSTANCE.play( Assets.THEME2, true );
		//Music.INSTANCE.volume( 1f );
		//}
		
		uiCamera.visible = false;
		
		int w = Camera.main.width;
		int h = Camera.main.height;
		
		Archs archs = new Archs();
		archs.setSize( w, h );
		add( archs );
		
		Image title = ThemeScene.titel;
		add( title );
		
		float height = title.height + 
			(KurwaDungeon.landscape() ? DashboardItem.SIZE : DashboardItem.SIZE * 2);
		
		title.x = (w - title.width()) / 2;
		title.y = (h - height) / 2;
		
		placeTorch( title.x + 10, title.y + 20 );
		placeTorch( title.x + title.width - 12, title.y + 20 );
		
		Image signs = new Image(ThemeScene.sight) {
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
		
		DashboardItem btnBadges = new DashboardItem( TXT_BADGES, 3 ) {
			@Override
			protected void onClick() {
				KurwaDungeon.switchNoFade( BadgesScene.class );
			}
		};
		add( btnBadges );
		DashboardItem btnknowled = new DashboardItem( TXT_PRAY, 4 ) {
			@Override
			protected void onClick() {
				KurwaDungeon.switchNoFade( WelcomeScene.class );
			}
		};
		add( btnknowled );
		
		DashboardItem btnAbout = new DashboardItem( TXT_ABOUT, 1 ) {
			@Override
			protected void onClick() {
				KurwaDungeon.switchNoFade( AboutScene.class );
			}
		};
		add( btnAbout );
		DashboardItem btnmus = new DashboardItem( TXT_PRAY2, 7 ) {
			@Override
			protected void onClick() {
				Theme.generated();
				Theme.Update();
			}
		};
		add( btnmus );
		
		DashboardItem btnPlay = new DashboardItem( TXT_PLAY, 0 ) {
			@Override
			protected void onClick() {
				KurwaDungeon.switchNoFade( SelectScene.class );
			}
		};
		add( btnPlay );
		
		DashboardItem btnHighscores = new DashboardItem( TXT_HIGHSCORES, 2 ) {
			@Override
			protected void onClick() {
				KurwaDungeon.switchNoFade( RankingsScene.class );
			}
		};
		add( btnHighscores );
		
		if (KurwaDungeon.landscape()) {
			float y = (h + height) / 2 - DashboardItem.SIZE;
			btnHighscores	.setPos( w / 2 - btnHighscores.width(), y );
			btnBadges		.setPos( w / 2, y );
			btnPlay			.setPos( btnHighscores.left() - btnPlay.width(), y );
			btnknowled		.setPos( w / 8, 5 );
			btnmus		.setPos( w / 3, 5 );
			btnAbout		.setPos( btnBadges.right(), y );
		} else {
			btnBadges.setPos( w / 2 - btnBadges.width(), (h + height) / 2 - DashboardItem.SIZE );
			btnAbout.setPos( w / 2, (h + height) / 2 - DashboardItem.SIZE );
			btnPlay.setPos( w / 2 - btnPlay.width(), btnAbout.top() - DashboardItem.SIZE );
			btnknowled		.setPos( w / 8, 5 );
			btnmus		.setPos( w / 3, 5 );
			btnHighscores.setPos( w / 2, btnPlay.top() );
		}
		
		BitmapText version = new BitmapText( "ver. " + Game.version, font1x );
		version.measure();
		version.hardlight( 0xFF0000 );
		version.x = w - version.width();
		version.y = h - version.height();
		add( version );
		
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
	
	public static class DashboardItem extends Button {
		
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
