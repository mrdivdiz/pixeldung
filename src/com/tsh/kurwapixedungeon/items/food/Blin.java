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
package com.tsh.kurwapixedungeon.items.food;

import com.tsh.kurwapixedungeon.actors.buffs.Hunger;
import com.tsh.kurwapixedungeon.sprites.ItemSpriteSheet;

public class Blin extends Food {

	{
		name = "Blin";
		image = ItemSpriteSheet.BLIN;
		energy = Hunger.HUNGRY;
	}
	
	@Override
	public String info() {
		return "Traditional Russian food.Restore 500 points.";
	}
	
	@Override
	public int price() {
		return 15 * quantity;
	}
	
	
}
