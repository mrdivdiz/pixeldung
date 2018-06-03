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
import com.tsh.kurwapixedungeon.Dungeon;
import com.tsh.kurwapixedungeon.actors.buffs.Invisibility;
import com.tsh.kurwapixedungeon.actors.hero.Belongings;
import com.tsh.kurwapixedungeon.actors.hero.Hero;
import com.tsh.kurwapixedungeon.effects.particles.BloodParticle;
import com.tsh.kurwapixedungeon.effects.particles.FlameParticle;
import com.tsh.kurwapixedungeon.effects.particles.SnowParticle;
import com.tsh.kurwapixedungeon.items.Encrypt;
import com.tsh.kurwapixedungeon.items.Fireball;
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
import com.tsh.kurwapixedungeon.levels.Level;
import com.tsh.kurwapixedungeon.items.weapon.missiles.Dart;
import com.tsh.kurwapixedungeon.items.weapon.rare.Inch;
import com.tsh.kurwapixedungeon.items.weapon.rare.Saw;
import com.tsh.kurwapixedungeon.items.weapon.rare.Vodka;
import com.tsh.kurwapixedungeon.plants.Firebloom;
import com.tsh.kurwapixedungeon.scenes.GameScene;
import com.tsh.kurwapixedungeon.sprites.RelicSprite;
import com.tsh.kurwapixedungeon.ui.HighlightedText;
import com.tsh.kurwapixedungeon.ui.Icons;
import com.tsh.kurwapixedungeon.ui.RedButton;
import com.tsh.kurwapixedungeon.ui.Window;
import com.tsh.kurwapixedungeon.utils.GLog;
import com.tsh.noosa.BitmapText;
import com.tsh.noosa.Camera;
import com.tsh.noosa.Image;
import com.tsh.noosa.audio.Sample;
import com.tsh.noosa.particles.Emitter;

public class WndEnrcyptGiver extends Window {

	protected WndBag.Mode mode = WndBag.Mode.ALL;
	private Belongings belongings;
	private static final int WIDTH		= 112;
	private static final int BTN_HEIGHT	= 20;
	private static int craftpoints = 0;
	private RedButton butt1;
	private RedButton butt2;
	private RedButton butt3;
	private RedButton butt4;
	private RedButton butt5;
	private RedButton butt6;
	private RedButton butt7;
	private RedButton butt8;
	private RedButton butt9;
	private RedButton butt10;
	private RedButton butt11;
	private RedButton butt12;
	private String crafted = "";
	private RedButton craftit;
	private String craftit2 = "Craft!";
	public static BitmapText.Font font3x;
	private String item1 = "";
	private static Hero hero;
	 static Random rand = new Random();
	private int gap = 4;
	private String txt1 = "You hold a strange book in your hands...";
	private final String inventoryTitle = "Select item to work with...";
	private static final String TXT_TITLE =  "Craft items...";
	
		
	
	
		
	public WndEnrcyptGiver() {

		
		
		
		
		super( 112, 152, Chrome.get(Chrome.Type.WINDOW));
	
		HighlightedText text1 = new HighlightedText( 6 );
		text1.text( txt1, width);
		text1.setPos( 0, 0 );
		add( text1 );
		
		butt1 = new RedButton("A"){@Override
			protected void onClick() { item1 = item1 + "A";check();}};
		butt2 = new RedButton("B"){@Override
			protected void onClick() { item1 = item1 + "B";check();}};
		butt3 = new RedButton("C"){@Override
			protected void onClick() { item1 = item1 + "C";check();}};
		butt4 = new RedButton("D"){@Override
			protected void onClick() { item1 = item1 + "D";check();}};
		butt5 = new RedButton("S"){@Override
			protected void onClick() { item1 = item1 + "S";check();}};
		butt6 = new RedButton("V"){@Override
			protected void onClick() { item1 = item1 + "V";check();}};
		butt7 = new RedButton("E"){@Override
			protected void onClick() { item1 = item1 + "E";check();}};
		butt8 = new RedButton("H"){@Override
			protected void onClick() { item1 = item1 + "H";check();}};
		butt9 = new RedButton("O"){@Override
			protected void onClick() { item1 = item1 + "O";check();}};
		butt10 = new RedButton("J"){@Override
			protected void onClick() { item1 = item1 + "J";check();}};
		butt11 = new RedButton("R"){@Override
			protected void onClick() { item1 = item1 + "R";check();}};
		butt12 = new RedButton("L"){@Override
			protected void onClick() { item1 = item1 + "L";check();}};
		
		
		
	}
	
	public void check(){
		if(item1 == "OBSERVE"){
			Hero hero = new Hero();
			Dungeon.level.drop(new Fireball(), hero.pos);
			Dungeon.level.drop(new Fireball(), hero.pos);
			Dungeon.level.drop(new Fireball(), hero.pos);
			Dungeon.level.drop(new Fireball(), hero.pos);
			Dungeon.level.drop(new Raise(), hero.pos);
			Dungeon.level.drop(new Raise(), hero.pos);
			Dungeon.level.drop(new Raise(), hero.pos);
			Dungeon.level.drop(new Raise(), hero.pos);
			item1 = "";
		}
	}
}
