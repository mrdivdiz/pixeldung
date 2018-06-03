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
import com.tsh.kurwapixedungeon.actors.Actor;
import com.tsh.kurwapixedungeon.actors.hero.Hero;
import com.tsh.kurwapixedungeon.actors.mobs.npcs.Bee;
import com.tsh.kurwapixedungeon.effects.Pushing;
import com.tsh.kurwapixedungeon.effects.Splash;
import com.tsh.kurwapixedungeon.levels.Level;
import com.tsh.kurwapixedungeon.scenes.GameScene;
import com.tsh.kurwapixedungeon.sprites.ItemSpriteSheet;
import com.tsh.noosa.audio.Sample;
import com.tsh.noosa.tweeners.AlphaTweener;
import com.tsh.utils.Random;

public class Manapot extends Item {
	
	public static final String AC_SHATTER	= "DRINK";
	
	{
		name = "Mana pot";
		image = ItemSpriteSheet.MANAPOT;
		defaultAction = AC_THROW;
		stackable = true;
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
			Hero.mana = Hero.manat;
			detach( hero.belongings.backpack );
			hero.spendAndNext( TIME_TO_THROW );
			
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
			"This pot restores your mana.";
	}
}
