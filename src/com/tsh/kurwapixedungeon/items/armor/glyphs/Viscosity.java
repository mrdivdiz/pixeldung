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
package com.tsh.kurwapixedungeon.items.armor.glyphs;

import com.tsh.kurwapixedungeon.Badges;
import com.tsh.kurwapixedungeon.Dungeon;
import com.tsh.kurwapixedungeon.ResultDescriptions;
import com.tsh.kurwapixedungeon.actors.Char;
import com.tsh.kurwapixedungeon.actors.buffs.Buff;
import com.tsh.kurwapixedungeon.items.armor.Armor;
import com.tsh.kurwapixedungeon.items.armor.Armor.Glyph;
import com.tsh.kurwapixedungeon.sprites.CharSprite;
import com.tsh.kurwapixedungeon.sprites.ItemSprite;
import com.tsh.kurwapixedungeon.sprites.ItemSprite.Glowing;
import com.tsh.kurwapixedungeon.ui.BuffIndicator;
import com.tsh.kurwapixedungeon.utils.GLog;
import com.tsh.kurwapixedungeon.utils.Utils;
import com.tsh.utils.Bundle;
import com.tsh.utils.Random;

public class Viscosity extends Glyph {

	private static final String TXT_VISCOSITY	= "%s of viscosity";
	
	private static ItemSprite.Glowing PURPLE = new ItemSprite.Glowing( 0x8844CC );
	
	@Override
	public int proc( Armor armor, Char attacker, Char defender, int damage ) {

		if (damage == 0) {
			return 0;
		}
		
		int level = Math.max( 0, armor.effectiveLevel() );
		
		if (Random.Int( level + 7 ) >= 6) {
			
			DeferedDamage debuff = defender.buff( DeferedDamage.class );
			if (debuff == null) {
				debuff = new DeferedDamage();
				debuff.attachTo( defender );
			}
			debuff.prolong( damage );
			
			defender.sprite.showStatus( CharSprite.WARNING, "deferred %d", damage );
			
			return 0;
			
		} else {
			return damage;
		}
	}
	
	@Override
	public String name( String weaponName) {
		return String.format( TXT_VISCOSITY, weaponName );
	}

	@Override
	public Glowing glowing() {
		return PURPLE;
	}
	
	public static class DeferedDamage extends Buff {
		
		protected int damage = 0;
		
		private static final String DAMAGE	= "damage";
		
		@Override
		public void storeInBundle( Bundle bundle ) {
			super.storeInBundle( bundle );
			bundle.put( DAMAGE, damage );
			
		}
		
		@Override
		public void restoreFromBundle( Bundle bundle ) {
			super.restoreFromBundle( bundle );
			damage = bundle.getInt( DAMAGE );
		}
		
		@Override
		public boolean attachTo( Char target ) {
			if (super.attachTo( target )) {
				postpone( TICK );
				return true;
			} else {
				return false;
			}
		}
		
		public void prolong( int damage ) {
			this.damage += damage;
		};
		
		@Override
		public int icon() {
			return BuffIndicator.DEFERRED;
		}
		
		@Override
		public String toString() {
			return Utils.format( "Defered damage (%d)", damage );
		}
		
		@Override
		public boolean act() {
			if (target.isAlive()) {
				
				target.damage( 1, this );
				if (target == Dungeon.hero && !target.isAlive()) {
					// FIXME
					Dungeon.fail( Utils.format( ResultDescriptions.GLYPH, "enchantment of viscosity", Dungeon.depth ) );
					GLog.n( "The enchantment of viscosity killed you..." );
					
					Badges.validateDeathFromGlyph();
				}
				spend( TICK );
				
				if (--damage <= 0) {
					detach();
				}
				
			} else {
				
				detach();
				
			}
			return true;
		}
	}
}
