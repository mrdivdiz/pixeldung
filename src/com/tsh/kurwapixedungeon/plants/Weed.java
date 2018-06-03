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
package com.tsh.kurwapixedungeon.plants;

import java.io.IOException;

import com.tsh.kurwapixedungeon.Assets;
import com.tsh.kurwapixedungeon.Dungeon;
import com.tsh.kurwapixedungeon.actors.Char;
import com.tsh.kurwapixedungeon.actors.blobs.Blob;
import com.tsh.kurwapixedungeon.actors.blobs.ConfusionGas;
import com.tsh.kurwapixedungeon.items.potions.PotionOfInvisibility;
import com.tsh.kurwapixedungeon.scenes.GameScene;
import com.tsh.kurwapixedungeon.scenes.TitleScene;
import com.tsh.kurwapixedungeon.sprites.ItemSpriteSheet;
import com.tsh.kurwapixedungeon.utils.GLog;
import com.tsh.noosa.Game;
import com.tsh.noosa.audio.Music;

public class Weed extends Plant {

	private static final String TXT_DESC = 
		"NO ONE FUCKING CARES.";
	
	{
		image = 3;
		plantName = "WeEd";
	}
	
	@Override
	public void activate( Char ch ) {
		super.activate( ch );
		
		if (ch != null) {
			
			try {
				Dungeon.saveAll();
			} catch (IOException e) {
				// Do nothing
			}
			Game.switchScene( TitleScene.class );
			Assets.TUNE  = "snd_mlg.mp3";
			Assets.THEME = "snd_mlg.mp3";
			Assets.TILES_SEWERS = "tiles01.png";
			Assets.TILES_PRISON = "tiles11.png";
			Assets.TILES_CAVES = "tiles21.png";
			Assets.TILES_CITY = "tiles31.png";
			Assets.TILES_HALLS = "tiles41.png";
			Assets.ITEMS = "items2.png";
			Assets.SND_GOLD = "snd_weed.mp3";
			Assets.SND_CLICK = "hitmarker.mp3";
			Assets.SND_HIT = "mlgshot.mp3";
			Assets.SND_ITEM = "ohohoh.mp3";
			
			
			
			Game.switchScene(GameScene.class);
			try {
				Dungeon.saveAll();
			} catch (IOException e) {
				// Do nothing
			}
			
			GameScene.add( Blob.seed( pos, 40, ConfusionGas.class ) );
			GLog.p("SMOKE WEED EVERYDAY");
			GLog.p("ACT3V4T3N W33DM0D");
			GLog.p("SMOKE WEED EVERYDAY");
			GLog.p("SMOKE WEED EVERYDAY");
			GLog.p("W33DM0D 4CT1V4T3T");
			
			
		}
	}
	
	@Override
	public String desc() {
		return TXT_DESC;
	}
	
	public static class Seed extends Plant.Seed {
		{
			plantName = "WeEd";
			
			name = "seed of " + plantName;
			image = ItemSpriteSheet.SEED_WEED;
			
			plantClass = Weed.class;
			alchemyClass = PotionOfInvisibility.class;
		}
		
		@Override
		public String desc() {
			return "KIND OF DRUG";
		}
	}
}
