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
package com.tsh.kurwapixedungeon.items.bags;

import com.tsh.kurwapixedungeon.items.Item;
import com.tsh.kurwapixedungeon.items.keys.Key;
import com.tsh.kurwapixedungeon.items.rings.Ring;
import com.tsh.kurwapixedungeon.sprites.ItemSpriteSheet;

public class Ringbag extends Bag {

	{
		name = "Ring holder";
		image = ItemSpriteSheet.KEYRING;
		
		size = 16;
	}
	
	@Override
	//public boolean grab( Item item ) {
		//return item instanceof Key;
		//return item instanceof Food;
	//}
	public boolean grab( Item item ) {
		return item instanceof Ring;
	}
	
	@Override
	public int price() {
		return 50;
	}
	
	@Override
	public String info() {
		return
			"This is a copper key ring, that lets you keep all your keys " +
			"separately from the rest of your belongings.It can contain rings too!";
	}
}
