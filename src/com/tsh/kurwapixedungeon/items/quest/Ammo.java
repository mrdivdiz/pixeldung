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
package com.tsh.kurwapixedungeon.items.quest;

import com.tsh.kurwapixedungeon.items.Item;
import com.tsh.kurwapixedungeon.sprites.ItemSpriteSheet;

public class Ammo extends Item {
	
	{
		name = "7.92 ammo pack";
		image = ItemSpriteSheet.AMMO;
		
		cursed = true;
		cursedKnown = true;
		
		unique = true;
	}
	
	@Override
	public boolean isUpgradable() {
		return false;
	}
	
	@Override
	public boolean isIdentified() {
		return true;
	}
	
	@Override
	public String info() {
		return
			"It looks wasted, but if someone needs it, why you can call it useless?" +
			"You know that it's better than nothing.";
	}
}
