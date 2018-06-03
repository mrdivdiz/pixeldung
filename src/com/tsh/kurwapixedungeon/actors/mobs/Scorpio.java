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

import java.util.HashSet;

import com.tsh.kurwapixedungeon.Dungeon;
import com.tsh.kurwapixedungeon.actors.Char;
import com.tsh.kurwapixedungeon.actors.buffs.Buff;
import com.tsh.kurwapixedungeon.actors.buffs.Cripple;
import com.tsh.kurwapixedungeon.actors.buffs.Light;
import com.tsh.kurwapixedungeon.actors.buffs.Poison;
import com.tsh.kurwapixedungeon.items.food.MysteryMeat;
import com.tsh.kurwapixedungeon.items.potions.PotionOfHealing;
import com.tsh.kurwapixedungeon.items.weapon.enchantments.Leech;
import com.tsh.kurwapixedungeon.levels.Level;
import com.tsh.kurwapixedungeon.mechanics.Ballistica;
import com.tsh.kurwapixedungeon.sprites.ScorpioSprite;
import com.tsh.utils.Random;

public class Scorpio extends Mob {
	
	{
		name = "scorpio";
		spriteClass = ScorpioSprite.class;
		
		HP = HT = 195;
		defenseSkill = 24;
		viewDistance = Light.DISTANCE;
		
		EXP = 14;
		maxLvl = 25;
		
		loot = new PotionOfHealing();
		lootChance = 0.125f;
	}
	
	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 20, 32 );
	}
	
	@Override
	public int attackSkill( Char target ) {
		return 36;
	}
	
	@Override
	public int dr() {
		return 16;
	}
	
	@Override
	protected boolean canAttack( Char enemy ) {
		return !Level.adjacent( pos, enemy.pos ) && Ballistica.cast( pos, enemy.pos, false, true ) == enemy.pos;
	}
	
	@Override
	public int attackProc( Char enemy, int damage ) {
		if (Random.Int( 2 ) == 0) {
			Buff.prolong( enemy, Cripple.class, Cripple.DURATION );
		}
		
		return damage;
	}
	
	@Override
	protected boolean getCloser( int target ) {
		if (state == HUNTING) {
			return enemySeen && getFurther( target );
		} else {
			return super.getCloser( target );
		}
	}
	
	@Override
	protected void dropLoot() {
		if (Random.Int( 8 ) == 0) {
			Dungeon.level.drop( new PotionOfHealing(), pos ).sprite.drop();
		} else if (Random.Int( 6 ) == 0) {
			Dungeon.level.drop( new MysteryMeat(), pos ).sprite.drop();
		}
	}
	
	@Override
	public String description() {
		return
			"These huge arachnid-like demonic creatures avoid close combat by all means, " +
			"firing crippling serrated spikes from long distances.";
	}
	
	private static final HashSet<Class<?>> RESISTANCES = new HashSet<Class<?>>();
	static {
		RESISTANCES.add( Leech.class );
		RESISTANCES.add( Poison.class );
	}
	
	@Override
	public HashSet<Class<?>> resistances() {
		return RESISTANCES;
	}
}
