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
package com.tsh.kurwapixedungeon.items;

import java.util.ArrayList;

import com.tsh.kurwapixedungeon.Assets;
import com.tsh.kurwapixedungeon.Dungeon;
import com.tsh.kurwapixedungeon.ResultDescriptions;
import com.tsh.kurwapixedungeon.actors.Actor;
import com.tsh.kurwapixedungeon.actors.Char;
import com.tsh.kurwapixedungeon.actors.buffs.Buff;
import com.tsh.kurwapixedungeon.actors.buffs.Paralysis;
import com.tsh.kurwapixedungeon.actors.hero.Hero;
import com.tsh.kurwapixedungeon.effects.CellEmitter;
import com.tsh.kurwapixedungeon.effects.particles.BlastParticle;
import com.tsh.kurwapixedungeon.effects.particles.SmokeParticle;
import com.tsh.kurwapixedungeon.levels.Level;
import com.tsh.kurwapixedungeon.scenes.GameScene;
import com.tsh.kurwapixedungeon.sprites.AdditionalItems;
import com.tsh.kurwapixedungeon.sprites.ItemSpriteSheet;
import com.tsh.kurwapixedungeon.utils.GLog;
import com.tsh.kurwapixedungeon.utils.Utils;
import com.tsh.noosa.audio.Sample;
import com.tsh.utils.Random;

public class ExpJar extends Item {
	public static String AC_DRINK = "USE";
	{
		name = "Little exp jar";
		image = AdditionalItems.EXPJAR;
		defaultAction = AC_DRINK;
		stackable = true;
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
	public ArrayList<String> actions( Hero hero ) {
		ArrayList<String> actions = super.actions( hero );
		actions.add( AC_DRINK );
		return actions;
	}
	
	@Override
	public void execute( final Hero hero, String action ) {
		if (action.equals( AC_DRINK )) {
			int rec = Random.Int(1, 15);
			hero.exp += rec;
			detach( hero.belongings.backpack );
			GLog.p("You received " + rec + "XP!");
		} else {
			super.execute( hero, action );
		}
	}	
	
	@Override
	public int price() {
		return 50 * quantity;
	}
	
	@Override
	public String info() {
		return
			"You wil receive one jar every 500 moves.Pick it on your base.";
	}
}
