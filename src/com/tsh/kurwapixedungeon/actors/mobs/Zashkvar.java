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
package com.tsh.kurwapixedungeon.actors.mobs;

import com.tsh.kurwapixedungeon.Dungeon;
import com.tsh.kurwapixedungeon.actors.Actor;
import com.tsh.kurwapixedungeon.actors.Char;
import com.tsh.kurwapixedungeon.actors.mobs.npcs.Ghost;
import com.tsh.kurwapixedungeon.effects.particles.ShadowParticle;
import com.tsh.kurwapixedungeon.items.Gold;
import com.tsh.kurwapixedungeon.items.potions.PotionOfHealing;
import com.tsh.kurwapixedungeon.items.weapon.melee.Shovel;
import com.tsh.kurwapixedungeon.levels.Level;
import com.tsh.kurwapixedungeon.scenes.GameScene;
import com.tsh.kurwapixedungeon.sprites.GnollSprite;
import com.tsh.kurwapixedungeon.sprites.PetuhSprite;
import com.tsh.noosa.tweeners.AlphaTweener;
import com.tsh.utils.Random;

public class Zashkvar extends Mob {
	
	{
		name = "Roaster";
		spriteClass = PetuhSprite.class;
		
		HP = HT = 145;
		defenseSkill = 2;
		
		EXP = 8;
		maxLvl = 8;
		
		loot = new Shovel();
		lootChance = 0.6f;
	}
	
	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 5, 12 );
	}
	
	@Override
	public int attackSkill( Char target ) {
		return 9;
	}
	
	@Override
	public int dr() {
		return 2;
	}
	public static Zashkvar spawnAt( int pos ) {
		if (Level.passable[pos] && Actor.findChar( pos ) == null) {
			
			Zashkvar w = new Zashkvar();
			w.pos = pos;
			w.state = w.HUNTING;
			GameScene.add( w, 1F );
			
			w.sprite.alpha( 0 );
			w.sprite.parent.add( new AlphaTweener( w.sprite, 1, 0.5f ) );
			
			w.sprite.emitter().burst( ShadowParticle.CURSE, 5 );
			
			return w;
		} else {
			return null;
		}
	}
	public static void spawnAround( int pos ) {
		for (int n : Level.NEIGHBOURS4) {
			int cell = pos + n;
			if (Level.passable[cell] && Actor.findChar( cell ) == null) {
				spawnAt( cell );
			}
		}
	}
	@Override
	public void die( Object cause ) {
		Ghost.Quest.processSewersKill( pos );
		super.die( cause );
	}
	
	@Override
	public String description() {
		return
			"There are two chairs: on the one are sharped peaks, on the other one are erected dicks. Which one would you sit on and which one would you seat your mother? " +
			"\n Be _fucking_ careful, meh";
	}
}
