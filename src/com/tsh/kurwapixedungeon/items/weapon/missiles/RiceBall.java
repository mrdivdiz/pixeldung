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
package com.tsh.kurwapixedungeon.items.weapon.missiles;

import com.tsh.kurwapixedungeon.Dungeon;
import com.tsh.kurwapixedungeon.actors.Char;
import com.tsh.kurwapixedungeon.actors.buffs.Bleeding;
import com.tsh.kurwapixedungeon.actors.buffs.Buff;
import com.tsh.kurwapixedungeon.actors.mobs.Golem;
import com.tsh.kurwapixedungeon.actors.mobs.Skeleton;
import com.tsh.kurwapixedungeon.actors.mobs.Statue;
import com.tsh.kurwapixedungeon.actors.mobs.Wraith;
import com.tsh.kurwapixedungeon.actors.mobs.Yog;
import com.tsh.kurwapixedungeon.actors.mobs.npcs.NPC;
import com.tsh.kurwapixedungeon.effects.Speck;
import com.tsh.kurwapixedungeon.items.Item;
import com.tsh.kurwapixedungeon.items.scrolls.ScrollOfTeleportation;
import com.tsh.kurwapixedungeon.sprites.ItemSpriteSheet;
import com.tsh.kurwapixedungeon.utils.GLog;
import com.tsh.utils.Random;

public class RiceBall extends MissileWeapon {
	
	public static final float DURATION = 10f;

	{
		name = "nail";
		image = ItemSpriteSheet.RICEBALL;
		
		DLY = 0.2f;

	}

	public RiceBall() {
		this(1);
	}

	public RiceBall(int number) {
		super();
		quantity = number;
	}
	
	@Override
	public String desc() {
		return "A nail from nail bag. ";
	}
	
	@Override
	public void proc(Char attacker, Char defender, int damage) {
		
		
			
		if (defender != null 
				&& !(defender instanceof NPC)
				&& !(defender instanceof Wraith)
				&& !(defender instanceof Golem)
				&& !(defender instanceof Skeleton)
				&& !(defender instanceof Statue)
				&& !(defender instanceof Yog)
				) {
  
			Buff.affect(defender, Bleeding.class);
			defender.sprite.centerEmitter().start(Speck.factory(Speck.NOTE), 0.3f, 5);
			
			
	    }
		
		super.proc(attacker, defender, damage);
	}

	@Override
	public Item random() {
		quantity = Random.Int(5, 15);
		return this;
	}

	@Override
	public int price() {
		return quantity * 5;
	}

	@Override
	public int min() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int max() {
		// TODO Auto-generated method stub
		return 4;
	}
}
