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

import com.tsh.kurwapixedungeon.actors.buffs.Barkskin;
import com.tsh.kurwapixedungeon.actors.buffs.Bleeding;
import com.tsh.kurwapixedungeon.actors.buffs.Buff;
import com.tsh.kurwapixedungeon.actors.buffs.Cripple;
import com.tsh.kurwapixedungeon.actors.buffs.Hunger;
import com.tsh.kurwapixedungeon.actors.buffs.Invisibility;
import com.tsh.kurwapixedungeon.actors.buffs.Poison;
import com.tsh.kurwapixedungeon.actors.buffs.Weakness;
import com.tsh.kurwapixedungeon.actors.hero.Hero;
import com.tsh.kurwapixedungeon.effects.Speck;
import com.tsh.kurwapixedungeon.sprites.AdditionalItems;
import com.tsh.kurwapixedungeon.sprites.ItemSpriteSheet;
import com.tsh.kurwapixedungeon.utils.GLog;
import com.tsh.utils.Random;

public class Vine extends Food {

	{
		name = "Vine";
		image = AdditionalItems.VINE;
		energy = Hunger.HUNGRY;
	}
	
	@Override
	public void execute( Hero hero, String action ) {
		
		super.execute( hero, action );
		
		if (action.equals( AC_EAT )) {
			
			
				GLog.i( "You feel your skin hardens!" );
				Buff.affect( hero, Barkskin.class ).level( hero.HT);
				
				GLog.p( "You feel drunk!Yolo!" );
				
		}
	}
	
	@Override
	public String info() {
		return "That's vine.Restore 500 hunger.";
	}
	
	@Override
	public int price() {
		return 15 * quantity;
	}
	
	
}
