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
import com.tsh.kurwapixedungeon.actors.mobs.Wraith;
import com.tsh.kurwapixedungeon.actors.mobs.Zashkvar;
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
import com.tsh.kurwapixedungeon.ui.HighlightedText;
import com.tsh.kurwapixedungeon.ui.Icons;
import com.tsh.kurwapixedungeon.ui.RedButton;
import com.tsh.kurwapixedungeon.ui.Window;
import com.tsh.kurwapixedungeon.utils.GLog;
import com.tsh.noosa.BitmapText;
import com.tsh.noosa.audio.Sample;
import com.tsh.noosa.particles.Emitter;

public class WndStuli extends Window {

	protected WndBag.Mode mode = WndBag.Mode.ALL;
	private static final int WIDTH		= 112;
	private static final int BTN_HEIGHT	= 20;
	private static int craftpoints = 0;
	private RedButton craftit;
	private RedButton craftit2;
	private RedButton craftit3;
	public static BitmapText.Font font3x;
	 static Random rand = new Random();
	
	public WndStuli() {
		super( 112, 112, Chrome.get(Chrome.Type.WINDOW));
	
		HighlightedText text1 = new HighlightedText( 6 );
		text1.text( "There are two chairs: on the one are sharped peaks, "
				+ "on the other one are erected dicks. Which one would you sit on"
				+ " and which one would you seat your mother?", width);
		text1.setPos( 0, 0 );
		add( text1 );
		
	
		craftit = new RedButton( "Sharped peaks" ){
			@Override
			protected void onClick(){
				Hero hero = new Hero();
				hero.damage( hero.HP/2, this );
			}};
		craftit.setRect( 14, 112 - 45, WIDTH / 2 , BTN_HEIGHT);
	add(craftit);
	
	craftit2 = new RedButton( "Erected dicks" ){
		@Override
		protected void onClick(){
			Hero hero = new Hero();
			Zashkvar.spawnAround( hero.pos );
		}};
	craftit2.setRect( BTN_HEIGHT + 14, 112 - 45, WIDTH / 2 , BTN_HEIGHT);
	add(craftit2);
	
	craftit3 = new RedButton( "Take peaks and erase dicks" ){
		@Override
		protected void onClick(){
		GLog.p("Heh.");
		}};
	craftit3.setRect( 56 / 2, 112 - 20, WIDTH / 2 , BTN_HEIGHT);
	add(craftit3);
	
	
	
	
	
	
	}
	
	
	
	
}
