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
package com.tsh.kurwapixedungeon.actors.hero;

import com.tsh.kurwapixedungeon.Assets;
import com.tsh.kurwapixedungeon.Badges;
import com.tsh.kurwapixedungeon.items.DewVial;
import com.tsh.kurwapixedungeon.items.Grenade;
import com.tsh.kurwapixedungeon.items.TomeOfMastery;
import com.tsh.kurwapixedungeon.items.Torch;
import com.tsh.kurwapixedungeon.items.Unlocker;
import com.tsh.kurwapixedungeon.items.armor.ClothArmor;
import com.tsh.kurwapixedungeon.items.bags.EatBag;
import com.tsh.kurwapixedungeon.items.bags.Keyring;
import com.tsh.kurwapixedungeon.items.bags.Ringbag;
import com.tsh.kurwapixedungeon.items.bags.SeedPouch;
import com.tsh.kurwapixedungeon.items.bags.TrashBag;
import com.tsh.kurwapixedungeon.items.food.Food;
import com.tsh.kurwapixedungeon.items.potions.PotionOfStrength;
import com.tsh.kurwapixedungeon.items.rings.RingOfShadows;
import com.tsh.kurwapixedungeon.items.scrolls.ScrollOfIdentify;
import com.tsh.kurwapixedungeon.items.scrolls.ScrollOfMagicMapping;
import com.tsh.kurwapixedungeon.items.wands.WandOfMagicMissile;
import com.tsh.kurwapixedungeon.items.weapon.melee.Dagger;
import com.tsh.kurwapixedungeon.items.weapon.melee.Knuckles;
import com.tsh.kurwapixedungeon.items.weapon.melee.ShortSword;
import com.tsh.kurwapixedungeon.items.weapon.melee.Shovel;
import com.tsh.kurwapixedungeon.items.weapon.missiles.Boomerang;
import com.tsh.kurwapixedungeon.items.weapon.missiles.Dart;
import com.tsh.kurwapixedungeon.ui.QuickSlot;
import com.tsh.utils.Bundle;

public enum HeroClass {

	WARRIOR( "Dovakin" ), MAGE( "Dambldore" ), ROGUE( "Aiden Pearce" ), HUNTRESS( "Asuna" );
	
	private String title;
	
	private HeroClass( String title ) {
		this.title = title;
	}
	
	public static final String[] WAR_PERKS = {
		"Warriors start with  much more HP.",
		"Warriors start with a unique short sword. This sword can be later \"reforged\" to upgrade another melee weapon.",
		"Warriors are less proficient with missile weapons.",
		"Any piece of food restores some health when eaten.",
		"Potions of Strength are identified from the beginning.",
	};
	
	public static final String[] MAG_PERKS = {
		"Mages start with a unique Wand of Magic Missile. This wand can be later \"disenchanted\" to upgrade another wand.",
		"Mages recharge their wands faster.",
		"When eaten, any piece of food restores 1 charge for all wands in the inventory.",
		"Mages can use wands as a melee weapon.",
		"Scrolls of Identify are identified from the beginning."
	};
	
	public static final String[] ROG_PERKS = {
		"Rogues start with a Ring of Shadows+1.",
		"Rogues identify a type of a ring on equipping it.",
		"Rogues are proficient with light armor, dodging better while wearing one.",
		"Rogues are proficient in detecting hidden doors and traps.",
		"Rogues can go without food longer.",
		"Scrolls of Magic Mapping are identified from the beginning."
	};
	
	public static final String[] HUN_PERKS = {
		"Huntresses start with 15 points of Health.",
		"Huntresses start with a unique upgradeable boomerang.",
		"Huntresses are proficient with missile weapons and get a damage bonus for excessive strength when using them.",
		"Huntresses gain more health from dewdrops.",
		"Huntresses sense neighbouring monsters even if they are hidden behind obstacles."
	};
	
	public void initHero( Hero hero ) {
		
		hero.heroClass = this;
		
		initCommon( hero );
		
		switch (this) {
		case WARRIOR:
			initWarrior( hero );
			break;
			
		case MAGE:
			initMage( hero );
			break;
			
		case ROGUE:
			initRogue( hero );
			break;
			
		case HUNTRESS:
			initHuntress( hero );
			break;
		}
		
		if (Badges.isUnlocked( masteryBadge() )) {
			new TomeOfMastery().collect();
		}
		
		hero.updateAwareness();
	}
	
	private static void initCommon( Hero hero ) {
		(hero.belongings.armor = new ClothArmor()).identify();
		new Food().identify().collect();
		new Keyring().collect();
		new Ringbag().collect();
		new TrashBag().collect();
		new Unlocker().collect();
	}
	
	public Badges.Badge masteryBadge() {
		switch (this) {
		case WARRIOR:
			return Badges.Badge.MASTERY_WARRIOR;
		case MAGE:
			return Badges.Badge.MASTERY_MAGE;
		case ROGUE:
			return Badges.Badge.MASTERY_ROGUE;
		case HUNTRESS:
			return Badges.Badge.MASTERY_HUNTRESS;
		}
		return null;
	}
	
	private static void initWarrior( Hero hero ) {
		hero.HP = (hero.HT += 20);
		(hero.belongings.weapon = new ShortSword()).identify();
		new Dart( 16 ).identify().collect();
		
		QuickSlot.primaryValue = Dart.class;
		new DewVial().identify().collect();
		new PotionOfStrength().setKnown();
		new SeedPouch().identify().collect();
		new EatBag().identify().collect();
		//new Isolation().identify().collect();
	}
	
	private static void initMage( Hero hero ) {	
		(hero.belongings.weapon = new Knuckles()).identify();
		
		WandOfMagicMissile wand = new WandOfMagicMissile();
		wand.identify().collect();
		new DewVial().identify().collect();
		new SeedPouch().identify().collect();
		new EatBag().identify().collect();
		
		QuickSlot.primaryValue = wand;
		
		new ScrollOfIdentify().setKnown();
	}
	
	private static void initRogue( Hero hero ) {
		(hero.belongings.weapon = new Dagger()).identify();
		(hero.belongings.ring1 = new RingOfShadows()).upgrade().identify();
		new Dart( 8 ).identify().collect();
		
		hero.belongings.ring1.activate( hero );
		new DewVial().identify().collect();
		QuickSlot.primaryValue = Dart.class;
		new SeedPouch().identify().collect();
		new EatBag().identify().collect();
		
		new ScrollOfMagicMapping().setKnown();
	}
	
	private static void initHuntress( Hero hero ) {
		
		hero.HP = (hero.HT -= 5);
		
		(hero.belongings.weapon = new Dagger()).identify();
		Boomerang boomerang = new Boomerang();
		boomerang.identify().collect();
		new DewVial().identify().collect();
		QuickSlot.primaryValue = boomerang;
		new SeedPouch().identify().collect();
		new EatBag().identify().collect();
	}
	
	public String title() {
		return title;
	}
	
	public String spritesheet() {
		
		switch (this) {
		case WARRIOR:
			return Assets.WARRIOR;
		case MAGE:
			return Assets.MAGE;
		case ROGUE:
			return Assets.ROGUE;
		case HUNTRESS:
			return Assets.HUNTRESS;
		}
		
		return null;
	}
	
	public String[] perks() {
		
		switch (this) {
		case WARRIOR:
			return WAR_PERKS;
		case MAGE:
			return MAG_PERKS;
		case ROGUE:
			return ROG_PERKS;
		case HUNTRESS:
			return HUN_PERKS;
		}
		
		return null;
	}

	private static final String CLASS	= "class";
	
	public void storeInBundle( Bundle bundle ) {
		bundle.put( CLASS, toString() );
	}
	
	public static HeroClass restoreInBundle( Bundle bundle ) {
		String value = bundle.getString( CLASS );
		return value.length() > 0 ? valueOf( value ) : ROGUE;
	}
}
