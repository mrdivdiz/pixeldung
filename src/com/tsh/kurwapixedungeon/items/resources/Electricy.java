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
package com.tsh.kurwapixedungeon.items.resources;

import java.util.ArrayList;

import com.tsh.kurwapixedungeon.Assets;
import com.tsh.kurwapixedungeon.Base;
import com.tsh.kurwapixedungeon.Dungeon;
import com.tsh.kurwapixedungeon.actors.Actor;
import com.tsh.kurwapixedungeon.actors.hero.Hero;
import com.tsh.kurwapixedungeon.actors.mobs.npcs.Bee;
import com.tsh.kurwapixedungeon.effects.Pushing;
import com.tsh.kurwapixedungeon.effects.Splash;
import com.tsh.kurwapixedungeon.items.Item;
import com.tsh.kurwapixedungeon.levels.Level;
import com.tsh.kurwapixedungeon.scenes.GameScene;
import com.tsh.kurwapixedungeon.sprites.AdditionalItems;
import com.tsh.kurwapixedungeon.sprites.ItemSpriteSheet;
import com.tsh.kurwapixedungeon.utils.GLog;
import com.tsh.noosa.audio.Sample;
import com.tsh.noosa.tweeners.AlphaTweener;
import com.tsh.utils.Random;

public class Electricy extends Item {
	public static int curenergy;
	public static int curoxygen;
	public static int curresources;
	public static int curwater;
	public static final String AC_SHATTER	= "DISCRAFT";
	
	{
		name = "Fuel";
		image = AdditionalItems.ELECTRO1;
		defaultAction = AC_THROW;
		stackable = true;
		curenergy = 5;
		curoxygen = 2;
		curresources = 10;
		curwater = 1;
	}
	
	@Override
	public ArrayList<String> actions( Hero hero ) {
		ArrayList<String> actions = super.actions( hero );
		actions.add( AC_SHATTER );
		return actions;
	}
	
	@Override
	public void execute( final Hero hero, String action ) {
		if (action.equals( AC_SHATTER )) {
			if(Base.isUnlocked == true){
			detach( hero.belongings.backpack );
			hero.spend( TIME_TO_THROW );
			Base.energy += curenergy;
			Base.oxygen += curoxygen;
			Base.resources += curresources;
			Base.water += curwater;
			GLog.p("Your resources was send onto your base!");
			}else{GLog.n("Please unlock base first!");}
			
			
		} else {
			super.execute( hero, action );
		}
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
	public int price() {
		return 50 * quantity;
	}
	
	@Override
	public String info() {
		return
			"Basic electronic circuit."
			+ "\n contain: " + curenergy + "x energy, " + curoxygen + "x oxygen, " + curresources + "x resources and" + curwater + "x water points.";
	}
}
