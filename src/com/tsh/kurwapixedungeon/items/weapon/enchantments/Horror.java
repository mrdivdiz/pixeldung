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
package com.tsh.kurwapixedungeon.items.weapon.enchantments;

import com.tsh.kurwapixedungeon.Dungeon;
import com.tsh.kurwapixedungeon.actors.Char;
import com.tsh.kurwapixedungeon.actors.buffs.Buff;
import com.tsh.kurwapixedungeon.actors.buffs.Terror;
import com.tsh.kurwapixedungeon.actors.buffs.Vertigo;
import com.tsh.kurwapixedungeon.items.weapon.Weapon;
import com.tsh.kurwapixedungeon.sprites.ItemSprite;
import com.tsh.kurwapixedungeon.sprites.ItemSprite.Glowing;
import com.tsh.utils.Random;

public class Horror extends Weapon.Enchantment {

	private static final String TXT_ELDRITCH	= "eldritch %s";
	
	private static ItemSprite.Glowing GREY = new ItemSprite.Glowing( 0x222222 );
	
	@Override
	public boolean proc( Weapon weapon, Char attacker, Char defender, int damage ) {
		// lvl 0 - 20%
		// lvl 1 - 33%
		// lvl 2 - 43%
		int level = Math.max( 0, weapon.effectiveLevel() );
		
		if (Random.Int( level + 5 ) >= 4) {
			
			if (defender == Dungeon.hero) {
				Buff.affect( defender, Vertigo.class, Vertigo.duration( defender ) );
			} else {
				Buff.affect( defender, Terror.class, Terror.DURATION ).object = attacker.id();
			}
			
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public Glowing glowing() {
		return GREY;
	}
	
	@Override
	public String name( String weaponName) {
		return String.format( TXT_ELDRITCH, weaponName );
	}

}
