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
import com.tsh.kurwapixedungeon.Dungeon;
import com.tsh.kurwapixedungeon.KurwaDungeon;
import com.tsh.kurwapixedungeon.ResultDescriptions;
import com.tsh.kurwapixedungeon.effects.Flare;
import com.tsh.kurwapixedungeon.effects.Speck;
import com.tsh.kurwapixedungeon.scenes.TitleScene.DashboardItem;
import com.tsh.kurwapixedungeon.themes.ThemeLoader;
import com.tsh.kurwapixedungeon.ui.Archs;
import com.tsh.kurwapixedungeon.ui.RedButton;
import com.tsh.kurwapixedungeon.ui.Specarchs;
import com.tsh.noosa.BitmapText;
import com.tsh.noosa.BitmapTextMultiline;
import com.tsh.noosa.Camera;
import com.tsh.noosa.Game;
import com.tsh.noosa.Image;
import com.tsh.noosa.audio.Sample;
import com.tsh.noosa.ui.Button;
import com.tsh.utils.Random;

public class GuideScene extends PixelScene {


	
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
		
		
		
		if(pg <= -1){
			pg = 0;
		}
		if(pg == 0){
			inftext = "\n\n\nWelcome to Guide Book.\n\n"
					+ "If you can't remember something, just check it.There are a lot of helpful"
					+ " information that will help you on your way.";
				img1 = new Image(Assets.G1);
				img1.x = w / 3;
				img1.y = h / 2 + 10;
				add(img1);
				img2 = null;
				img3 = null;
				new Flare( 7, 64 ).color( 0x112233, true ).show( img1, 0 ).angularSpeed = +20;
		}
		if(pg == 1){
			inftext = "There are some buttons on the left that nill help you with selection what you need.";
				img1 = null;
				img2 = null;
				img3 = null;
		}
		if(pg == 2){
			inftext = "Armor.\n\n\n"
					+ "Armor are required for everyone who want to get less damage from enemys."
					+ "Upgrade it to get less damage, but it will decrease your durability."
					+ "Be careful: some armor may cause...Problems.";
				img1 = new Image(Assets.G2);
				img1.x = w / 3;
				img1.y = h / 2 + 10;
				add(img1);
				new Flare( 7, 64 ).color( 0x112233, true ).show( img1, 0 ).angularSpeed = +20;
				img2 = null;
				img3 = null;
		}
		if(pg == 3){
			inftext = "Common armor.\n\n\n"
					+ "Common armor are common.\n"
					+ "It have no skills, and change it states (if +0) from tier to tier."
					+ "Upgraded tier 1 defend you less upgraded tier 2, e.t.c., but you may loke low tiers"
					+ " because of its low strength requirements.";
				img1 = new Image(Assets.G3);
				img1.x = w / 3;
				img1.y = h / 2 + 10;
				add(img1);
				new Flare( 7, 64 ).color( 0x112233, true ).show( img1, 0 ).angularSpeed = +20;
				img2 = null;
				img3 = null;
		}
		if(pg == 4){
			inftext = "Legendary armor.\n\n\n"
					+ "Every game class have its own armor, to get it"
					+ " you have to defeat third boss and smelt one of your"
					+ " armor to it."
					+ "Legenday armor have its own skills, as a teleportation, bomb blows and wand regeneration.";
				img1 = new Image(Assets.G2);
				img1.x = w / 3;
				img1.y = h / 2 + 10;
				add(img1);
				new Flare( 7, 64 ).color( 0x112233, true ).show( img1, 0 ).angularSpeed = +20;
				img2 = null;
				img3 = null;
			
		}
		if(pg == 6){
			inftext = "Dew.\n\n\n"
					+ "Dew - one of most important resources that you will use on your way."
					+ "\nWith it you can heal yourself, give youself inviciblity or get some XP.Sometimes it is"
					+ " hard to get some dew drops.Where you can get it?";
			img1 = null;
			img2 = null;
			img3 = null;
		}
		if(pg == 7){
			inftext = "Dew.\n\n\n"
					+ "First place where you can get some dew is a regular room, where with a some chance spawning dewdrops "
					+ "with variable values:\n"
					+ "Regular Dewdrop gives you 1 dew point;\n"
					+ "Rew Dewdrop gives you 5 dew points;\n"
					+ "Orange Dewdrop gives you 15 dew points;\n"
					+ "Lime Dewdrop gives you 7 dew points;";
			img1 = null;
			img2 = null;
			img3 = null;
			
		}
		if(pg == 8){
			 			inftext = "Dew.\n\n\n"
+ "Your dew vial contains dew.Maximum amount of dew is 12000."
+ "If you reached maximum, colored dew will be wasted, and regular dewdrop"
+ " will heal you by 1 HP.";
    	img1 = null;
			img2 = null;
			img3 = null;
			
		}
		if(pg == 9){
			pg = 0;
			
		}
		if(pg == 10){
			pg = 0;
			
		}
		
		
		
		GuideboardItem GUIDE_ARMOR = new GuideboardItem( "Armor", 0 ) {
			@Override
			protected void onClick() {
				pg = 2;
				Game.switchScene(GuideScene.class);
			}
		};
		add(GUIDE_ARMOR);
		GuideboardItem GUIDE_DEW = new GuideboardItem( "Dew", 1 ) {
			@Override
			protected void onClick() {
				pg = 6;
				Game.switchScene(GuideScene.class);
			}
		};
		add(GUIDE_DEW);

		RedButton btnnxt = new RedButton( "Next" ) {
			@Override
			protected void onClick() {
				pg++;
				Game.switchScene(GuideScene.class);
			}
		};
		RedButton btnprv = new RedButton( "Prev" ) {
			@Override
			protected void onClick() {
					pg--;
				Game.switchScene(GuideScene.class);
			}
		};
		btnnxt.setSize( WIDTH /2, BTN_HEIGHT);
		add( btnnxt );
		btnprv.setSize( WIDTH /2, BTN_HEIGHT);
		add( btnprv );
		
		
		
		
		
		
		
		if (KurwaDungeon.landscape()) {
			btnnxt.setPos(w / 3 + 30, 200);
			btnprv.setPos(w / 3 - 40, 200);
			GUIDE_ARMOR.setPos(0, 0);
			GUIDE_DEW.setPos(GUIDE_ARMOR.right() + 6, 0);
		} else {
			btnnxt.setPos(w / 3 + 30, 200);
			btnprv.setPos(w / 3 - 40, 200);
			GUIDE_ARMOR.setPos(0, 0);
			GUIDE_DEW.setPos(0, GUIDE_ARMOR.bottom() + 6);
		}
		
		BitmapTextMultiline text = null;
		{
			text = createMultiline( inftext, 8 );
			text.maxWidth = 94;
			text.measure();
			text.x = GuideboardItem.SIZE + 10;
			text.y = 20;
			
		}
		add( text );
		BitmapTextMultiline text2 = null;
		{
			text2 = createMultiline( "page:" + pg, 8 );
			text2.maxWidth = 94;
			text2.measure();
			text2.x = GuideboardItem.SIZE + 10;
			text2.y = h - text2.height();
			
		}
		add( text2 );
		fadeIn();
	}
	
	@Override
	protected void onBackPressed() {
		InterlevelScene.mode = InterlevelScene.Mode.CONTINUE;
		Game.switchScene( InterlevelScene.class );
	}

public static class GuideboardItem extends Button {
		
		public static final float SIZE	= 16;
		
		private static final int IMAGE_SIZE	= 16;
		
		private Image image;
		private BitmapText label;
		
		public GuideboardItem( String text, int index ) {
			super();
			
			image.frame( image.texture.uvRect( index * IMAGE_SIZE, 0, (index + 1) * IMAGE_SIZE, IMAGE_SIZE ) );
			this.label.text( text );
			this.label.measure();
			
			setSize( SIZE, SIZE );
		}
		
		@Override
		protected void createChildren() {
			super.createChildren();
			
			image = new Image( Assets.GUIDEBOARD );
			add( image );
			
			label = createText( 6 );
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
