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
import com.tsh.kurwapixedungeon.items.Fireball;
import com.tsh.kurwapixedungeon.items.armor.Light;
import com.tsh.kurwapixedungeon.items.food.MysteryMeat;
import com.tsh.kurwapixedungeon.sprites.RobtSprite;
import com.tsh.utils.Random;

public class AngryRobt extends Mob {

	{
		name = "Andgry Robt";
		spriteClass = RobtSprite.class;
		
		HP = HT = 295 + (Mob.generation_robot * 10);
		defenseSkill = Hero.defenseSkill * 2;
		
		baseSpeed = 2f;
		
		maxLvl = 5;
		EXP = 30;
		loot = new Fireball();
		lootChance = 0.8f;
	}
	
	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 1, Hero.attackSkill * 2 );
	}
	
	@Override
	public int attackSkill( Char target ) {
		return 12;
	}
	
	@Override
	public int dr() {
		return 1;
	}
	
	@Override
	public void die( Object cause ) {
		super.die( cause );
		Mob.generation_robot++;
	}
	
	@Override
	public String description() {
		return
			"Very bad if you found me.\n " +
			"Robt - one of most dangerous machines at start.Recommend to run for any newbie."
			+ "This is upgraded machine with another two chainsaws and red case.Version " + Mob.generation_robot + " of generation." ;
	}
}
