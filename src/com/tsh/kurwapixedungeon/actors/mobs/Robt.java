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
import com.tsh.kurwapixedungeon.actors.mobs.npcs.Ghost;
import com.tsh.kurwapixedungeon.items.armor.Light;
import com.tsh.kurwapixedungeon.items.food.MysteryMeat;
import com.tsh.kurwapixedungeon.items.scrolls.ScrollOfUpgrade;
import com.tsh.kurwapixedungeon.sprites.RobtSprite;
import com.tsh.utils.Random;

public class Robt extends Mob {

	{
		name = "Robt";
		spriteClass = RobtSprite.class;
		
		HP = HT = 95 + (Mob.generation_robot * 5);
		defenseSkill = 20;
		
		maxLvl = 5;
		EXP = 8;
		
		loot = new ScrollOfUpgrade();
		lootChance = 1f;
	}
	
	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 1, 15 );
	}
	
	@Override
	public int attackSkill( Char target ) {
		return 8;
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
			+ "A heavy-armored mob that upgrade every new generation by defeating previous.Version: " + Mob.generation_robot + ".0";
	}
}
