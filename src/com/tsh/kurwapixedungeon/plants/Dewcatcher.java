/*
 * Pixel Dungeon
 * Copyright (C) 2012-2014  Oleg Dolya
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

import com.tsh.kurwapixedungeon.Assets;
import com.tsh.kurwapixedungeon.Dungeon;
import com.tsh.kurwapixedungeon.actors.Char;
import com.tsh.kurwapixedungeon.actors.blobs.Blob;
import com.tsh.kurwapixedungeon.actors.blobs.Fire;
import com.tsh.kurwapixedungeon.actors.blobs.WaterOfTransmutation;
import com.tsh.kurwapixedungeon.actors.blobs.WellWater;
import com.tsh.kurwapixedungeon.actors.buffs.Buff;
import com.tsh.kurwapixedungeon.actors.buffs.Slow;
import com.tsh.kurwapixedungeon.actors.hero.Hero;
import com.tsh.kurwapixedungeon.actors.mobs.Mob;
import com.tsh.kurwapixedungeon.effects.CellEmitter;
import com.tsh.kurwapixedungeon.effects.particles.FlameParticle;
import com.tsh.kurwapixedungeon.items.Dewdrop;
import com.tsh.kurwapixedungeon.items.RedDewdrop;
import com.tsh.kurwapixedungeon.items.potions.PotionOfHealing;
import com.tsh.kurwapixedungeon.items.potions.PotionOfLiquidFlame;
import com.tsh.kurwapixedungeon.items.potions.PotionOfMight;
import com.tsh.kurwapixedungeon.items.potions.PotionOfStrength;
import com.tsh.kurwapixedungeon.levels.Level;
import com.tsh.kurwapixedungeon.levels.Terrain;
import com.tsh.kurwapixedungeon.plants.Plant.Seed;
import com.tsh.kurwapixedungeon.scenes.GameScene;
import com.tsh.kurwapixedungeon.sprites.ItemSpriteSheet;
import com.tsh.noosa.audio.Sample;
import com.tsh.utils.Random;

public class Dewcatcher extends Plant {

	private static final String TXT_DESC = "Grown from sparkling crystal seeds, Dewcatchers camouflage as grass to avoid attention, " +
			                                "but their bulges of collected dew give them away. " +
			                                "Shake them to harvest dew from their leaves. ";
	{
		image = 8;
		plantName = "Dewcatcher";
	}

	@Override
	public void activate(Char ch) {
		explodeDew(pos);
		if (Random.Int(2)==0){super.activate(ch);}	
		    
		
	}

	@Override
	public String desc() {
		return TXT_DESC;
	}

	public static class Seed extends Plant.Seed {
		{
			plantName = "Dewcatcher";

			name = "seed of " + plantName;
			image = ItemSpriteSheet.SEED_DEWCATCHER;

			plantClass = Dewcatcher.class;
			alchemyClass = PotionOfHealing.class;				
		}

		@Override
		public String desc() {
			return TXT_DESC;
		}
		
		
	}
	
public void explodeDew(int cell) {
		
		 for (int n : Level.NEIGHBOURS8) {
			 int c = cell + n;
			 if (c >= 0 && c < Level.LENGTH() && Level.passable[c]) {
				 
				if (Random.Int(10)==1){Dungeon.level.drop(new RedDewdrop(), c).sprite.drop();}		
			    else if (Random.Int(5)==1){Dungeon.level.drop(new Dewdrop(), c).sprite.drop();}
				else if (Random.Int(3)==1){Dungeon.level.drop(new Dewdrop(), c).sprite.drop();}
				else if (Random.Int(7)==1){Dungeon.level.drop(new RedDewdrop(), c).sprite.drop();}
			}
		  }	
		
	}


		
	
}
