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
import com.tsh.kurwapixedungeon.sprites.RatSprite;
import com.tsh.utils.Random;

public class Rat extends Mob {

	{
		name = "marsupial rat";
		spriteClass = RatSprite.class;
		
		HP = HT = 45 + Hero.defskill + Hero.attackSkill;
		defenseSkill = 4;
		
		EXP = 4;
		maxLvl = 5;
		
		loot = new MysteryMeat();
		lootChance = 0.500f;
	}
	
	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 1, 9 );
	}
	
	@Override
	public int attackSkill( Char target ) {
		return 4;
	}
	
	@Override
	public int dr() {
		return 1;
	}
	
	@Override
	public void die( Object cause ) {
		Ghost.Quest.processSewersKill( pos );
		
		super.die( cause );
	}
	
	@Override
	public String description() {
		return
			"Marsupial rats are aggressive, but rather weak denizens " +
			"of the sewers. They can be dangerous only in big numbers.";
	}
}
