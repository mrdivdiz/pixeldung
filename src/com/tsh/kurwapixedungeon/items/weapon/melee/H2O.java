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
package com.tsh.kurwapixedungeon.items.weapon.melee;

import com.tsh.kurwapixedungeon.sprites.AdditionalItems;

public class H2O extends MeleeWeapon {

	{
		name = "H2O(OH)2";
		image = AdditionalItems.H2O;
	}
	
	public H2O() {
		super( 2, 0.9f, 2f );
	}
	
	@Override
	public String desc() {
		return "Water hydroxid.WHAT THE FUCK IS GOING ON?H 2 O O H x 2!(Looks like a little pile of blue shards of water.Water, NOT ice.Shards.Of water.Shards.Water.Water shards.)";
	}
}
