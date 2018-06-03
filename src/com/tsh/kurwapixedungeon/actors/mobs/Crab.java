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
package com.tsh.kurwapixedungeon.actors.mobs;

import com.tsh.kurwapixedungeon.actors.Char;
import com.tsh.kurwapixedungeon.actors.hero.Hero;
import com.tsh.kurwapixedungeon.actors.mobs.npcs.Ghost;
import com.tsh.kurwapixedungeon.items.food.MysteryMeat;
import com.tsh.kurwapixedungeon.sprites.CrabSprite;
import com.tsh.utils.Random;

public class Crab extends Mob {

	{
		name = "sewer crab";
		spriteClass = CrabSprite.class;
		
		HP = HT = 120;
		defenseSkill = 30;
		baseSpeed = 3f;
		
		EXP = 10;
		maxLvl = 9;
		
		loot = new MysteryMeat();
		lootChance = 1.0f;
	}
	
	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 3, 15 );
	}
	
	@Override
	public int attackSkill( Char target ) {
		return (int)Hero.attackSkill / 2;
	}
	
	@Override
	public int dr() {
		return 4;
	}
	
	@Override
	public String defenseVerb() {
		return "parried";
	}
	
	@Override
	public void die( Object cause ) {
		Ghost.Quest.processSewersKill( pos );
		super.die( cause );
	}
	
	@Override
	public String description() {
		return
			"These huge crabs are at the top of the food chain in the sewers. " +
			"They are extremely fast and their thick exoskeleton can withstand " +
			"heavy blows.";
	}
}
