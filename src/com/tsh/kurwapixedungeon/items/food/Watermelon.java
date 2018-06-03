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
import com.tsh.kurwapixedungeon.utils.GLog;
import com.tsh.utils.Random;

public class Watermelon extends Food {

	{
		name = "Watermelon";
		image = AdditionalItems.MELON;
		energy = Hunger.STARV;
	}
	
	@Override
	public String info() {
		return "Food of gods.Restore 750 hunger.";
	}
	
	@Override
	public void execute( Hero hero, String action ) {
		
		super.execute( hero, action );
		
		if (action.equals( AC_EAT )) {
			
				GLog.p( "Watermelons are tasty!" );
				Buff.affect( hero, Barkskin.class ).level( hero.HT / 5 );
				Buff.detach( hero, Poison.class );
				Buff.detach( hero, Cripple.class );
				Buff.detach( hero, Weakness.class );
				Buff.detach( hero, Bleeding.class );
		}}
	
	@Override
	public int price() {
		return 15 * quantity;
	}
	
	
}
