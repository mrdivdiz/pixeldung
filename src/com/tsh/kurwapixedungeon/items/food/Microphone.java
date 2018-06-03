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

import java.io.IOException;

import com.tsh.kurwapixedungeon.Assets;
import com.tsh.kurwapixedungeon.Base;
import com.tsh.kurwapixedungeon.Dungeon;
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
import com.tsh.kurwapixedungeon.scenes.GameScene;
import com.tsh.kurwapixedungeon.scenes.TitleScene;
import com.tsh.kurwapixedungeon.sprites.AdditionalItems;
import com.tsh.kurwapixedungeon.sprites.ItemSpriteSheet;
import com.tsh.kurwapixedungeon.utils.GLog;
import com.tsh.noosa.Game;
import com.tsh.utils.Random;

public class Microphone extends Food {

	{
		name = "Microphone";
		image = AdditionalItems.MIC;
		energy = Hunger.STARV2;
	}
	
	@Override
	public void execute( Hero hero, String action ) {
		
		super.execute( hero, action );
		
		if (action.equals( AC_EAT )) {
			
			
			try {
				Dungeon.saveAll();
				Base.sav();
			} catch (IOException e) {
			}
				Assets.TUNE  = "dick.mp3";
				Assets.THEME = "dick.mp3";
				Game.switchScene( TitleScene.class );
				GLog.i( "Oh shiet!!FUKK11!1" );
				Game.switchScene(GameScene.class);
				
		}
	}
	
	@Override
	public String info() {
		return "Sure, you can eat a microphone.Restore 250 hunger.";
	}
	
	@Override
	public int price() {
		return 15 * quantity;
	}
	
	
}
