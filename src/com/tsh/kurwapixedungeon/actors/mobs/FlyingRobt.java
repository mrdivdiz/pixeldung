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
import com.tsh.kurwapixedungeon.ResultDescriptions;
import com.tsh.kurwapixedungeon.actors.Char;
import com.tsh.kurwapixedungeon.actors.hero.Hero;
import com.tsh.kurwapixedungeon.actors.mobs.npcs.Ghost;
import com.tsh.kurwapixedungeon.effects.particles.SparkParticle;
import com.tsh.kurwapixedungeon.items.Fireball;
import com.tsh.kurwapixedungeon.items.armor.Light;
import com.tsh.kurwapixedungeon.items.food.MysteryMeat;
import com.tsh.kurwapixedungeon.levels.Level;
import com.tsh.kurwapixedungeon.levels.traps.LightningTrap;
import com.tsh.kurwapixedungeon.sprites.CharSprite;
import com.tsh.kurwapixedungeon.sprites.RobtSprite;
import com.tsh.kurwapixedungeon.sprites.ShamanSprite;
import com.tsh.kurwapixedungeon.utils.GLog;
import com.tsh.kurwapixedungeon.utils.Utils;
import com.tsh.noosa.Camera;
import com.tsh.utils.Random;

public class FlyingRobt extends Mob {

	{
		name = "Flying rocket-upgraded robt";
		spriteClass = RobtSprite.class;
		
		HP = HT = 395 + (Mob.generation_robot * 15);
		defenseSkill = 6 + Hero.defenseSkill * 2;
		
		baseSpeed = 4f;
		flying = true;
		maxLvl = 5;
		
		EXP = 55;
		
		loot = new Fireball();
		lootChance = 0.8f;
	}
	
	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 0, Hero.attackSkill );
	}
	
	@Override
	public int attackSkill( Char target ) {
		return 8;
	}
	
	@Override
	protected boolean doAttack( Char enemy ) {

		if (Level.distance( pos, enemy.pos ) <= 1) {
			
			return super.doAttack( enemy );
			
		} else {
			
			boolean visible = Level.fieldOfView[pos] || Level.fieldOfView[enemy.pos]; 
			if (visible) {
				((ShamanSprite)sprite).zap( enemy.pos );
			}
			
			spend( 2f );
			
			if (hit( this, enemy, true )) {
				int dmg = Random.Int( 2, 12 );
				if (Level.water[enemy.pos] && !enemy.flying) {
					dmg *= 1.5f;
				}
				enemy.damage( dmg, LightningTrap.LIGHTNING );
				
				enemy.sprite.centerEmitter().burst( SparkParticle.FACTORY, 3 );
				enemy.sprite.flash();
				
				if (enemy == Dungeon.hero) {
					
					Camera.main.shake( 2, 0.3f );
					
					if (!enemy.isAlive()) {
						Dungeon.fail( Utils.format( ResultDescriptions.MOB, 
							Utils.indefinite( name ), Dungeon.depth ) );
						GLog.n( "Robot's rocket killed you.", name );
					}
				}
			} else {
				enemy.sprite.showStatus( CharSprite.NEUTRAL,  enemy.defenseVerb() );
			}
			
			return !visible;
		}
	}
	
	@Override
	public int dr() {
		return 1;
	}
	
	@Override
	public void die( Object cause ) {
		super.die( cause );
	}
	
	@Override
	public String description() {
		return
			"Very bad if you found me.\n " +
			"Robt - one of most dangerous machines at start.Recommend to run for any newbie."
			+ "This is a flying enraged killing machine with rocket launcher.Anyway, rockets are not that accurate as chainsaws.Version " + Mob.generation_robot + " of generation." ;
	}
}
