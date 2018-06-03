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

import java.util.Random;

import com.tsh.kurwapixedungeon.Assets;
import com.tsh.kurwapixedungeon.Chrome;
import com.tsh.kurwapixedungeon.actors.buffs.Invisibility;
import com.tsh.kurwapixedungeon.actors.hero.Belongings;
import com.tsh.kurwapixedungeon.actors.hero.Hero;
import com.tsh.kurwapixedungeon.effects.particles.BloodParticle;
import com.tsh.kurwapixedungeon.effects.particles.FlameParticle;
import com.tsh.kurwapixedungeon.effects.particles.SnowParticle;
import com.tsh.kurwapixedungeon.items.Generator;
import com.tsh.kurwapixedungeon.items.Item;
import com.tsh.kurwapixedungeon.items.Raise;
import com.tsh.kurwapixedungeon.items.armor.DirtyArmor;
import com.tsh.kurwapixedungeon.items.food.Beer;
import com.tsh.kurwapixedungeon.items.food.Food;
import com.tsh.kurwapixedungeon.items.govno.Trash;
import com.tsh.kurwapixedungeon.items.potions.Potion;
import com.tsh.kurwapixedungeon.items.potions.PotionOfExperience;
import com.tsh.kurwapixedungeon.items.potions.PotionOfFrost;
import com.tsh.kurwapixedungeon.items.potions.PotionOfMending;
import com.tsh.kurwapixedungeon.items.scrolls.InventoryScroll;
import com.tsh.kurwapixedungeon.items.scrolls.ScrollOfLullaby;
import com.tsh.kurwapixedungeon.items.scrolls.ScrollOfTerror;
import com.tsh.kurwapixedungeon.items.weapon.melee.Dagger;
import com.tsh.kurwapixedungeon.items.weapon.melee.Knuckles;
import com.tsh.kurwapixedungeon.items.weapon.shooting.*;
import com.tsh.kurwapixedungeon.items.weapon.missiles.Dart;
import com.tsh.kurwapixedungeon.items.weapon.rare.Inch;
import com.tsh.kurwapixedungeon.items.weapon.rare.Saw;
import com.tsh.kurwapixedungeon.items.weapon.rare.Vodka;
import com.tsh.kurwapixedungeon.plants.Firebloom;
import com.tsh.kurwapixedungeon.scenes.GameScene;
import com.tsh.kurwapixedungeon.scenes.GuideScene;
import com.tsh.kurwapixedungeon.scenes.GuideScene.GuideboardItem;
import com.tsh.kurwapixedungeon.ui.HighlightedText;
import com.tsh.kurwapixedungeon.ui.Icons;
import com.tsh.kurwapixedungeon.ui.RedButton;
import com.tsh.kurwapixedungeon.ui.Window;
import com.tsh.kurwapixedungeon.utils.GLog;
import com.tsh.noosa.BitmapText;
import com.tsh.noosa.Game;
import com.tsh.noosa.audio.Sample;
import com.tsh.noosa.particles.Emitter;

public class WndSkills extends Window {

	private static final int WIDTH		= 112;
	private static final int BTN_HEIGHT	= 20;
	public static BitmapText.Font font3x;
	 static Random rand = new Random();
	
		
	
	
		
	public WndSkills() {

		
		
		
		
		super( 112, 112, Chrome.get(Chrome.Type.WINDOW));
	
		HighlightedText text1 = new HighlightedText( 6 );
		text1.text( "Here you can improve your skills with skillpoints.Gain skillpoints by level-ups \n skillpoints: " + Hero.sp, width);
		text1.setPos( 0, 0 );
		add( text1 );
		
		GuideboardItem GUIDE_ARMOR = new GuideboardItem( "Attack", 11 ) {
			@Override
			protected void onClick() {
				GameScene.show(new WndSkillHP());
			}
		};
		add(GUIDE_ARMOR);
		GUIDE_ARMOR.setPos(8, text1.bottom() + 8);
		
		GuideboardItem GUIDE_ARMOR2 = new GuideboardItem( "Defence", 12 ) {
			@Override
			protected void onClick() {
				GameScene.show(new WndSkillDEF());
			}
		};
		add(GUIDE_ARMOR2);
		GUIDE_ARMOR2.setPos(8, GUIDE_ARMOR.bottom() + 20);
		
		if(Hero.sp < 0){
			Hero.sp = 0;
		} 
		
		HighlightedText text12 = new HighlightedText( 6 );
		text12.text( "Now:" + Hero.skillhp + "/10", width);
		text12.setPos( 25, text1.bottom() + 11 );
		add( text12 );
		
		HighlightedText text11 = new HighlightedText( 6 );
		text11.text( "Now:" + Hero.defskill + "/10", width);
		text11.setPos( 25, GUIDE_ARMOR.bottom() + 15 );
		add( text11 );
	
}}
