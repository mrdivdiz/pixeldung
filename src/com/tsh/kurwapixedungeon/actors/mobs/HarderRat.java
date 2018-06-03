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

import com.tsh.kurwapixedungeon.Dungeon;
import com.tsh.kurwapixedungeon.actors.Char;
import com.tsh.kurwapixedungeon.actors.mobs.npcs.Ghost;
import com.tsh.kurwapixedungeon.items.Gold;
import com.tsh.kurwapixedungeon.items.food.Burger;
import com.tsh.kurwapixedungeon.items.food.ChargrilledMeat;
import com.tsh.kurwapixedungeon.items.food.FrozenCarpaccio;
import com.tsh.kurwapixedungeon.items.food.MysteryMeat;
import com.tsh.kurwapixedungeon.items.potions.PotionOfHealing;
import com.tsh.kurwapixedungeon.sprites.HarderRatSprite;
import com.tsh.utils.Random;

public class HarderRat extends Mob {

	{
		name = "Harder rat";
		spriteClass = HarderRatSprite.class;
		
		HP = HT = 95;
		defenseSkill = 6;
		
		EXP = 3;
		maxLvl = 5;
		
		
		loot = new Burger();
		lootChance = 0.3f;
	}
	
	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 1, 3 );
	}
	
	@Override
	public int attackSkill( Char target ) {
		return 3;
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
			"Thats a harder rat.Try harder! " +
			"Try to kill me, motherfucka!";
	}
}
