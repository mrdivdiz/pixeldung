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
import com.tsh.kurwapixedungeon.ui.HighlightedText;
import com.tsh.kurwapixedungeon.ui.Icons;
import com.tsh.kurwapixedungeon.ui.RedButton;
import com.tsh.kurwapixedungeon.ui.Window;
import com.tsh.kurwapixedungeon.utils.GLog;
import com.tsh.noosa.BitmapText;
import com.tsh.noosa.audio.Sample;
import com.tsh.noosa.particles.Emitter;

public class WndCraft extends Window {

	protected WndBag.Mode mode = WndBag.Mode.ALL;
	private Belongings belongings;
	private static final int WIDTH		= 112;
	private static final int BTN_HEIGHT	= 20;
	private static int craftpoints = 0;
	private RedButton butt1;
	private RedButton butt2;
	private RedButton butt3;
	private RedButton butt4;
	private String crafted = "";
	private RedButton craftit;
	private String craftit2 = "Craft!";
	public static BitmapText.Font font3x;
	private String item1 = "Select items";
	private String item2 = "Second";
	private String item3 = "Third";
	private String item4 = "Forth";
	private static Hero hero;
	 static Random rand = new Random();
	private int gap = 4;
	private String txt1 = "Select some items to work with;     \n CraftPoints: " + craftpoints;
	private String txt2 = "To craft something, select one of items of TRASH class in your inventory and press CRAFT.";
	private final String inventoryTitle = "Select item to work with...";
	private static final String TXT_TITLE =  "Craft items...";
	
		
	
	
		
	public WndCraft() {

		
		
		
		
		super( 112, 112, Chrome.get(Chrome.Type.WINDOW));
	
		HighlightedText text1 = new HighlightedText( 6 );
		text1.text( txt1, width);
		text1.setPos( 0, 0 );
		add( text1 );
		
		HighlightedText text2 = new HighlightedText( 6 );
		text2.text( txt2, width);
		text2.setPos( 0, text1.bottom() );
		add( text2 );
		HighlightedText text3 = new HighlightedText( 6 );
		text3.text( "Now you can craft rare items.Warning: that items are very strange and unstable...", width);
		text3.setPos( 0, text2.bottom() );
		if(craftpoints > 25){
		add( text3 );
		}
		
	
	
	
	craftit = new RedButton( craftit2 ){
		@Override
		protected void onClick(){
			int i;
if(craftpoints > 0 && craftpoints <= 10){
	craftpoints = 0;
	int s;
	i = rand.nextInt(6);
	switch (rand.nextInt(6)){
	case 1:
		new Food().identify().collect();
		crafted = "Ration of food";
		break;
	case 2:
		new ScrollOfTerror().collect();
		crafted = "Scroll Of Terror";
		break;
	case 3:
		new PotionOfFrost().collect();
		crafted = "Potion of Frost";
		break;
	case 4:
		new Dagger().identify().collect();
		crafted = "Dagger";
		break;
	case 5:
		new Dart( s = rand.nextInt(6)).identify().collect();
		crafted = "Darts(" + s + ")";
		break;
	}
	
	GLog.p("You crafted:" + crafted + ", check your inventory.")
	;}
if(craftpoints > 10 && craftpoints <= 24){
	i = rand.nextInt(8);
	craftpoints = 0;
	switch (i){
	case 1:
		new Raise().collect();
		crafted = "Nail bag";
		break;
	case 2:
		new ScrollOfLullaby().collect();
		crafted = "Scroll of Lullaby";
		break;
	case 3:
		new PotionOfMending().collect();
		crafted = "Potion of Mending";
		break;
	case 4:
		new Beer().collect();
		crafted = "Beer";
		break;
	case 5:
		new PotionOfExperience().collect();
		crafted = "Potion of Experience";
		break;
	case 6:
		new PistolGun().collect();
		crafted = "Pistol gun";
		break;
	case 7:
		new AWP().collect();
		crafted = "AWP";
		break;
	}
	
	GLog.p("You crafted:" + crafted + ", check your inventory.")
	;}
if(craftpoints > 24 ){
	i = rand.nextInt(4);
	craftpoints = craftpoints -25;
	switch (i){
	case 1:
		new Saw().identify().collect();
		crafted = "Saw";
		break;
	case 2:
		new Inch().identify().collect();
		crafted = "7-inch wrench";
		break;
	case 3:
		new Vodka().identify().collect();
		crafted = "Vodka";
		break;
	
	}
	
	GLog.p("You crafted something rare:" + crafted + ", check your inventory.")
	;}
		}};
		craftit.setRect( 56 / 2, 112 - 12, WIDTH / 2 , BTN_HEIGHT);
	add(craftit);
	
	
	
	
	
	
	}
	
	
	
		
		
		
	public static void craftits(){
	GLog.p("You added something to your CraftPoints.");
		craftpoints = craftpoints + rand.nextInt(11);
	}
	
}
