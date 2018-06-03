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
package com.tsh.kurwapixedungeon.items;

import java.util.HashMap;

import com.tsh.kurwapixedungeon.Dungeon;
import com.tsh.kurwapixedungeon.actors.hero.Hero;
import com.tsh.kurwapixedungeon.items.armor.*;
import com.tsh.kurwapixedungeon.items.bags.*;
import com.tsh.kurwapixedungeon.items.food.*;
import com.tsh.kurwapixedungeon.items.govno.*;
import com.tsh.kurwapixedungeon.items.potions.*;
import com.tsh.kurwapixedungeon.items.resources.*;
import com.tsh.kurwapixedungeon.items.rings.*;
import com.tsh.kurwapixedungeon.items.scrolls.*;
import com.tsh.kurwapixedungeon.items.wands.*;
import com.tsh.kurwapixedungeon.items.weapon.*;
import com.tsh.kurwapixedungeon.items.weapon.melee.*;
import com.tsh.kurwapixedungeon.items.weapon.missiles.*;
import com.tsh.kurwapixedungeon.items.weapon.shooting.*;
import com.tsh.kurwapixedungeon.plants.*;
import com.tsh.utils.Random;

public class Generator {

	public static enum Category {
		RESOURCE	( 15,	Resource.class ),
		WEAPON		( 15,	Weapon.class ),
		ARMOR		( 10,	Armor.class ),
		POTION		( 50,	Potion.class ),
		SCROLL		( 40,	Scroll.class ),
		WAND		( 4,	Wand.class ),
		RING		( 2,	Ring.class ),
		SEED		( 10,	Plant.Seed.class ),
		FOOD		( 0,	Food.class ),
		GOLD		( 50,	Gold.class ),
		MISC		( 5,	Item.class ),
		TRASH		( 5,	Item.class );
		
		public Class<?>[] classes;
		public float[] probs;
		
		public float prob;
		public Class<? extends Item> superClass;
		
		private Category( float prob, Class<? extends Item> superClass ) {
			this.prob = prob;
			this.superClass = superClass;
		}
		
		public static int order( Item item ) {
			for (int i=0; i < values().length; i++) {
				if (values()[i].superClass.isInstance( item )) {
					return i;
				}
			}
			
			return item instanceof Bag ? Integer.MAX_VALUE : Integer.MAX_VALUE - 1;
		}
	};
	
	private static HashMap<Category,Float> categoryProbs = new HashMap<Generator.Category, Float>();
	
	static {
		
		Category.GOLD.classes = new Class<?>[]{ 
			Gold.class };
		Category.GOLD.probs = new float[]{ 1 };
		
		Category.SCROLL.classes = new Class<?>[]{ 
			ScrollOfIdentify.class, 
			ScrollOfTeleportation.class, 
			ScrollOfRemoveCurse.class, 
			ScrollOfRecharging.class,
			ScrollOfMagicMapping.class,
			ScrollOfChallenge.class,
			ScrollOfTerror.class,
			ScrollOfLullaby.class,
			ScrollOfPsionicBlast.class,
			ScrollOfMirrorImage.class,
			ScrollOfUpgrade.class,
			ScrollOfEnchantment.class };
		Category.SCROLL.probs = new float[]{ 30, 10, 15, 10, 15, 12, 8, 8, 4, 6, 0, 1 };
		
		Category.TRASH.classes = new Class <?> []{
			Gayporn.class,
			Can.class,
			InsulatingTape.class,
			OldBox.class,
			Boot.class};
		Category.TRASH.probs = new float[]{2, 2, 1, 4, 2};
		
		Category.RESOURCE.classes = new Class <?> []{
			Resource.class,
			Electricy.class};
		Category.RESOURCE.probs = new float[]{90, 70}; //of 90%
		
		Category.POTION.classes = new Class<?>[]{ 
			PotionOfHealing.class, 
			PotionOfMending.class,
			PotionOfExperience.class,
			PotionOfToxicGas.class, 
			PotionOfParalyticGas.class,
			PotionOfLiquidFlame.class,
			PotionOfLevitation.class,
			PotionOfStrength.class,
			PotionOfMindVision.class,
			PotionOfPurity.class,
			PotionOfInvisibility.class,
			PotionOfMight.class,
			PotionOfFrost.class };
		Category.POTION.probs = new float[]{ 25, 20, 5, 15, 10, 25, 10, 2, 25, 22, 15, 5, 10 };
		
		Category.WAND.classes = new Class<?>[]{ 
			WandOfTeleportation.class, 
			WandOfSlowness.class,
			WandOfFirebolt.class,
			WandOfRegrowth.class,
			WandOfPoison.class,
			WandOfBlink.class,
			WandOfLightning.class,
			WandOfAmok.class,
			WandOfReach.class,
			WandOfFlock.class,
			WandOfMagicMissile.class,
			WandOfDisintegration.class,
			WandOfAvalanche.class };
		Category.WAND.probs = new float[]{ 10, 10, 15, 6, 10, 11, 15, 10, 6, 10, 0, 5, 5 };
		
		Category.WEAPON.classes = new Class<?>[]{ 
			Bite.class,
			H2O.class,
			DevNull.class,
			Shovel.class,
			SharpKnuckles.class,
			RiceBall.class,
			DumplingBomb.class,
			AWP.class,
			PPSH.class,
			PistolGun.class,
			Dagger.class, 
			Knuckles.class,
			Quarterstaff.class, 
			Spear.class, 
			Mace.class, 
			Sword.class, 
			Longsword.class,
			BattleAxe.class,
			WarHammer.class, 
			Glaive.class,
			ShortSword.class,
			Dart.class,
			Javelin.class,
			IncendiaryDart.class,
			CurareDart.class,
			Shuriken.class,
			Boomerang.class,
			Tamahawk.class,
			Fireball.class};
		Category.WEAPON.probs = new float[]{1, 0.7f, 0.4f, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0.2f };
		
		Category.ARMOR.classes = new Class<?>[]{ 
			Light.class,
			DirtyArmor.class,
			ClothArmor.class, 
			LeatherArmor.class, 
			MailArmor.class, 
			ScaleArmor.class, 
			PlateArmor.class };
		Category.ARMOR.probs = new float[]{1, 1, 1, 1, 1, 1, 1 };
		
		Category.FOOD.classes = new Class<?>[]{ 
			Watermelon.class,
			Vine.class,
			Blin.class,
			Semka.class,
			Food.class, 
			Pasty.class,
			Burger.class,
			Pizza.class,
			FrozenCarpaccio.class,
			CookedBurger.class,
			Beer.class,
			Free.class,
			Microphone.class,
			MysteryMeat.class };
		Category.FOOD.probs = new float[]{1, 1, 4, 4, 1, 5, 4, 5, 2, 1, 4, 2, 3, 8 };
			
		Category.RING.classes = new Class<?>[]{ 
			RingOfMending.class,
			RingOfDetection.class,
			RingOfShadows.class,
			RingOfPower.class,
			RingOfHerbalism.class,
			RingOfAccuracy.class,
			RingOfEvasion.class,
			RingOfSatiety.class,
			RingOfHaste.class,
			RingOfElements.class,
			RingOfHaggler.class,
			RingOfThorns.class };
		Category.RING.probs = new float[]{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0 };
		
		Category.SEED.classes = new Class<?>[]{ 
			Weed.Seed.class,
			Dewcatcher.Seed.class,
			Firebloom.Seed.class,
			Icecap.Seed.class,
			Sorrowmoss.Seed.class,
			Dreamweed.Seed.class,
			Sungrass.Seed.class,
			Earthroot.Seed.class,
			Fadeleaf.Seed.class,
			Rotberry.Seed.class };
		Category.SEED.probs = new float[]{1, 2, 1, 1, 1, 1, 1, 1, 1, 0 };
		
		Category.MISC.classes = new Class<?>[]{ 
			RedDewdrop.class,
			Raise.class,
			OrangeDewdrop.class,
			LimeDewdrop.class,
			Bomb.class,
			Honeypot.class,
			Manapot.class,
			Ankh.class,
			Grenade.class};
		Category.MISC.probs = new float[]{ 5, 5, 5, 2, 5, 5, 30, 1, 5 };
	}
	
	public static void reset() {
		for (Category cat : Category.values()) {
			categoryProbs.put( cat, cat.prob );
		}
	}
	
	public static Item random() {
		return random( Random.chances( categoryProbs ) );
	}
	
	public static Item random( Category cat ) {
		try {
			
			categoryProbs.put( cat, categoryProbs.get( cat ) / 2 );
			
			switch (cat) {
			case ARMOR:
				return randomArmor();
			case WEAPON:
				return randomWeapon();
			default:
				return ((Item)cat.classes[Random.chances( cat.probs )].newInstance()).random();
			}
			
		} catch (Exception e) {

			return null;
			
		}
	}
	
	public static Item random( Class<? extends Item> cl ) {
		try {
			
			return ((Item)cl.newInstance()).random();
			
		} catch (Exception e) {

			return null;
			
		}
	}
	
	public static Armor randomArmor() throws Exception {
		
		int curStr = Hero.STARTING_STR + Dungeon.potionOfStrength;
		
		Category cat = Category.ARMOR;
		
		Armor a1 = (Armor)cat.classes[Random.chances( cat.probs )].newInstance();
		Armor a2 = (Armor)cat.classes[Random.chances( cat.probs )].newInstance();
		
		a1.random();
		a2.random();
		
		return Math.abs( curStr - a1.STR ) < Math.abs( curStr - a2.STR ) ? a1 : a2;
	}
	
	public static Weapon randomWeapon() throws Exception {
		
		int curStr = Hero.STARTING_STR + Dungeon.potionOfStrength;
		
		Category cat = Category.WEAPON;
		
		Weapon w1 = (Weapon)cat.classes[Random.chances( cat.probs )].newInstance();
		Weapon w2 = (Weapon)cat.classes[Random.chances( cat.probs )].newInstance();
		
		w1.random();
		w2.random();
		
		return Math.abs( curStr - w1.STR ) < Math.abs( curStr - w2.STR ) ? w1 : w2;
	}
}
