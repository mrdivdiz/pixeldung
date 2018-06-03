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
import com.tsh.kurwapixedungeon.sprites.ItemSpriteSheet;

public class Shovel extends MeleeWeapon {
	
	{
		name = "HARDCORE SHOVEL";
		image = AdditionalItems.SHOVEL;
	}
	
	public Shovel() {
		super( 3, 0.7f, 2f );
	}
	
	@Override
	public String desc() {
		return "Really powerful _banana_.Use as melee weapon, pls.Not accurate as a dagger but kinda faster.";
	}
}
