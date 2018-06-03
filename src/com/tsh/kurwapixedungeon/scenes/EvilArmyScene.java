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

import com.tsh.kurwapixedungeon.Assets;
import com.tsh.kurwapixedungeon.Badges;
import com.tsh.kurwapixedungeon.Dungeon;
import com.tsh.kurwapixedungeon.KurwaDungeon;
import com.tsh.kurwapixedungeon.ResultDescriptions;
import com.tsh.kurwapixedungeon.actors.hero.HeroClass;
import com.tsh.kurwapixedungeon.effects.Flare;
import com.tsh.kurwapixedungeon.effects.Speck;
import com.tsh.kurwapixedungeon.scenes.GuideScene.GuideboardItem;
import com.tsh.kurwapixedungeon.scenes.TitleScene.DashboardItem;
import com.tsh.kurwapixedungeon.themes.ThemeLoader;
import com.tsh.kurwapixedungeon.ui.Archs;
import com.tsh.kurwapixedungeon.ui.Icons;
import com.tsh.kurwapixedungeon.ui.RedButton;
import com.tsh.kurwapixedungeon.ui.Specarchs;
import com.tsh.kurwapixedungeon.windows.WndChallenges;
import com.tsh.kurwapixedungeon.windows.WndMessage;
import com.tsh.noosa.BitmapText;
import com.tsh.noosa.BitmapTextMultiline;
import com.tsh.noosa.Camera;
import com.tsh.noosa.Game;
import com.tsh.noosa.Image;
import com.tsh.noosa.audio.Sample;
import com.tsh.noosa.particles.BitmaskEmitter;
import com.tsh.noosa.particles.Emitter;
import com.tsh.noosa.ui.Button;
import com.tsh.utils.Random;

public class EvilArmyScene extends PixelScene {


	
	private static final int WIDTH			= 120;
	private static final int BTN_HEIGHT		= 18;
	private static final float SMALL_GAP	= 2;
	private static final float LARGE_GAP	= 8;
	private Image img1;
	private Image img2;
	private Image img3;
	public static String inftext = "\n\n\nWelcome to Guide Book.\n\n"
			+ "If you can't remember something, just check it.There are a lot of helpful"
			+ " information that will help you on your way.";
	
	public static int pg = 0;
	

	

	@Override
	public void create() {
		super.create();
		int w = Camera.main.width;
		int h = Camera.main.height;
		
		Specarchs archs = new Specarchs();
		archs.setSize( w, h );
		add( archs );
		
		
		
		
		GuideboardItem GUIDE_ARMOR = new GuideboardItem( "Begin", 6 ) {
			@Override
			protected void onClick() {
				//startNewGame();
			}
		};
		add(GUIDE_ARMOR);
	
		fadeIn();
	}
	
	@Override
	protected void onBackPressed() {
		InterlevelScene.mode = InterlevelScene.Mode.CONTINUE;
		Game.switchScene( InterlevelScene.class );
	}
	
	private void startNewGame() {

		Dungeon.hero = null;
		InterlevelScene.mode = InterlevelScene.Mode.DESCEND;
		
		if (KurwaDungeon.intro()) {
			KurwaDungeon.intro( false );
			Game.switchScene( IntroScene.class );
		} else {
			Game.switchScene( InterlevelScene.class );
		}	
	}
	
	
	private static class GameButton extends RedButton {
		
		private static final int SECONDARY_COLOR_N	= 0xCACFC2;
		private static final int SECONDARY_COLOR_H	= 0xFFFF88;
		
		private BitmapText secondary;
		
		public GameButton( String primary ) {
			super( primary );
			
			this.secondary.text( null );
		}
		
		@Override
		protected void createChildren() {
			super.createChildren();
			
			secondary = createText( 6 );
			add( secondary );
		}
		
		@Override
		protected void layout() {
			super.layout();
			
			if (secondary.text().length() > 0) {
				text.y = align( y + (height - text.height() - secondary.baseLine()) / 2 );
				
				secondary.x = align( x + (width - secondary.width()) / 2 );
				secondary.y = align( text.y + text.height() ); 
			} else {
				text.y = align( y + (height - text.baseLine()) / 2 );
			}
		}
		
		public void secondary( String text, boolean highlighted ) {
			secondary.text( text );
			secondary.measure();
			
			secondary.hardlight( highlighted ? SECONDARY_COLOR_H : SECONDARY_COLOR_N );
		}
	}
	
	private class ClassShield extends Button {
		
		private static final float MIN_BRIGHTNESS	= 0.6f;
		
		private static final int BASIC_NORMAL		= 0x444444;
		private static final int BASIC_HIGHLIGHTED	= 0xCACFC2;
		
		private static final int MASTERY_NORMAL		= 0x666644;
		private static final int MASTERY_HIGHLIGHTED= 0xFFFF88;
		
		private static final int WIDTH	= 24;
		private static final int HEIGHT	= 28;
		private static final int SCALE	= 2;
		
		private HeroClass cl;
		
		private Image avatar;
		private BitmapText name;
		private Emitter emitter;
		
		private float brightness;
		
		private int normal;
		private int highlighted;
		
		public ClassShield( HeroClass cl ) {
			super();
		
			this.cl = cl;
			
			avatar.frame( cl.ordinal() * WIDTH, 0, WIDTH, HEIGHT );
			avatar.scale.set( SCALE );
			
			if (Badges.isUnlocked( cl.masteryBadge() )) {
				normal = MASTERY_NORMAL;
				highlighted = MASTERY_HIGHLIGHTED;
			} else {
				normal = BASIC_NORMAL;
				highlighted = BASIC_HIGHLIGHTED;
			}
			
			name.text( cl.name() );
			name.measure();
			name.hardlight( normal );
			
			brightness = MIN_BRIGHTNESS;
			updateBrightness();
		}
		
		@Override
		protected void createChildren() {
			
			super.createChildren();
			
			avatar = new Image( Assets.AVATARS );
			add( avatar );
			
			name = PixelScene.createText( 9 );
			add( name );
			
			emitter = new BitmaskEmitter( avatar );
			add( emitter );
		}
		
		@Override
		protected void layout() {
			
			super.layout();
			
			avatar.x = align( x + (width - avatar.width()) / 2 );
			avatar.y = align( y + (height - avatar.height() - name.height()) / 2 );
			
			name.x = align( x + (width - name.width()) / 2 );
			name.y = avatar.y + avatar.height() + SCALE;
		}
		
		@Override
		protected void onTouchDown() {
			
			emitter.revive();
			emitter.start( Speck.factory( Speck.LIGHT ), 0.05f, 7 );
			
			Sample.INSTANCE.play( Assets.SND_CLICK, 1, 1, 1.2f );
			//updateClass( cl );
		}
		
		@Override
		public void update() {
			super.update();
			
			if (brightness < 1.0f && brightness > MIN_BRIGHTNESS) {
				if ((brightness -= Game.elapsed) <= MIN_BRIGHTNESS) {
					brightness = MIN_BRIGHTNESS;
				}
				updateBrightness();
			}
		}
		
		public void highlight( boolean value ) {
			if (value) {
				brightness = 1.0f;
				name.hardlight( highlighted );
			} else {
				brightness = 0.999f;
				name.hardlight( normal );
			}

			updateBrightness();
		}
		
		private void updateBrightness() {
			avatar.gm = avatar.bm = avatar.rm = avatar.am = brightness;
		}
	}
	
	private class ChallengeButton extends Button {
		
		private Image image;
		
		public ChallengeButton() {
			super();
			
			width = image.width;
			height = image.height;
			
			image.am = Badges.isUnlocked( Badges.Badge.VICTORY ) ? 1.0f : 0.5f;
		}
		
		@Override
		protected void createChildren() {
			
			super.createChildren();
			
			image = Icons.get( KurwaDungeon.challenges() > 0 ? Icons.CHALLENGE_ON :Icons.CHALLENGE_OFF );
			add( image );
		}
		
		@Override
		protected void layout() {
			
			super.layout();
			
			image.x = align( x );
			image.y = align( y  );
		}
		
		@Override
		protected void onClick() {
			if (Badges.isUnlocked( Badges.Badge.VICTORY )) {
				EvilArmyScene.this.add( new WndChallenges( KurwaDungeon.challenges(), true ) {
					public void onBackPressed() {
						super.onBackPressed();
						image.copy( Icons.get( KurwaDungeon.challenges() > 0 ? 
							Icons.CHALLENGE_ON :Icons.CHALLENGE_OFF ) );
					};
				} );
			} else {
				EvilArmyScene.this.add( new WndMessage( "To unlock \"Challenges\", win the game with any character class." ) );
			}
		}
		
		@Override
		protected void onTouchDown() {
			Sample.INSTANCE.play( Assets.SND_CLICK );
		}
	}
}