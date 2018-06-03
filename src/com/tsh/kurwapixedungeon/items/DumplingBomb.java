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
import com.tsh.kurwapixedungeon.Badges;
import com.tsh.kurwapixedungeon.Dungeon;
import com.tsh.kurwapixedungeon.ResultDescriptions;
import com.tsh.kurwapixedungeon.Statistics;
import com.tsh.kurwapixedungeon.actors.Actor;
import com.tsh.kurwapixedungeon.actors.Char;
import com.tsh.kurwapixedungeon.actors.buffs.Bleeding;
import com.tsh.kurwapixedungeon.actors.buffs.Buff;
import com.tsh.kurwapixedungeon.actors.buffs.Paralysis;
import com.tsh.kurwapixedungeon.actors.buffs.Slow;
import com.tsh.kurwapixedungeon.actors.hero.Hero;
import com.tsh.kurwapixedungeon.actors.mobs.Golem;
import com.tsh.kurwapixedungeon.actors.mobs.Mob;
import com.tsh.kurwapixedungeon.actors.mobs.Skeleton;
import com.tsh.kurwapixedungeon.actors.mobs.Statue;
import com.tsh.kurwapixedungeon.actors.mobs.Wraith;
import com.tsh.kurwapixedungeon.actors.mobs.Yog;
import com.tsh.kurwapixedungeon.actors.mobs.npcs.NPC;
import com.tsh.kurwapixedungeon.effects.CellEmitter;
import com.tsh.kurwapixedungeon.effects.Speck;
import com.tsh.kurwapixedungeon.effects.particles.SmokeParticle;
import com.tsh.kurwapixedungeon.items.scrolls.ScrollOfTeleportation;
import com.tsh.kurwapixedungeon.items.weapon.missiles.RiceBall;
import com.tsh.kurwapixedungeon.levels.Level;
import com.tsh.kurwapixedungeon.scenes.GameScene;
import com.tsh.kurwapixedungeon.sprites.CharSprite;
import com.tsh.kurwapixedungeon.sprites.ItemSprite;
import com.tsh.kurwapixedungeon.sprites.ItemSpriteSheet;
import com.tsh.kurwapixedungeon.utils.GLog;
import com.tsh.kurwapixedungeon.utils.Utils;
import com.tsh.noosa.audio.Sample;
import com.tsh.utils.Bundle;
import com.tsh.utils.Random;

public class DumplingBomb extends Item {

	{
		name = "nail bomb";
		image = ItemSpriteSheet.RICEBOMB;
		defaultAction = AC_LIGHTTHROW;
		stackable = true;
	}

	public Fuse fuse;

	// FIXME using a static variable for this is kinda gross, should be a better
	// way
	private static boolean lightingFuse = false;

	private static final String AC_LIGHTTHROW = "Throw";

	@Override
	public ArrayList<String> actions(Hero hero) {
		ArrayList<String> actions = super.actions(hero);
		actions.add(AC_LIGHTTHROW);
		return actions;
	}

	@Override
	public void execute(Hero hero, String action) {
		if (action.equals(AC_LIGHTTHROW)) {
			lightingFuse = true;
			action = AC_THROW;
		} else
			lightingFuse = false;

		super.execute(hero, action);
	}

	@Override
	protected void onThrow(int cell) {
		if (!Level.pit[cell] && lightingFuse) {
			Actor.addDelayed(fuse = new Fuse().ignite(this), 2);
		}
		if (Actor.findChar(cell) != null
				&& !(Actor.findChar(cell) instanceof Hero)) {
			ArrayList<Integer> candidates = new ArrayList<Integer>();
			for (int i : Level.NEIGHBOURS8)
				if (Level.passable[cell + i])
					candidates.add(cell + i);
			int newCell = candidates.isEmpty() ? cell : Random
					.element(candidates);
			Dungeon.level.drop(this, newCell).sprite.drop(cell);
		} else
			super.onThrow(cell);
	}

	@Override
	public boolean doPickUp(Hero hero) {
		if (fuse != null) {
			GLog.w("You calm down the nail bomb.");
			fuse = null;
		}
		return super.doPickUp(hero);
	}

	public void explode(int cell) {
		this.fuse = null;
	    Sample.INSTANCE.play(Assets.SND_BLAST, 2);

	     	for (int n: Level.NEIGHBOURS9) {
			int c = cell + n;
			if (c >= 0 && c < Level.LENGTH()) {
				if (Dungeon.visible[c]) {
					CellEmitter.get(c).burst(SmokeParticle.FACTORY, 4);
				}

				Heap heap = Dungeon.level.heaps.get(c);
				if (heap != null)
					heap.dumpexplode();
				
				Char ch = Actor.findChar(c);
				if (ch != null && !(ch instanceof NPC) && !(ch instanceof Wraith) && !(ch instanceof Golem) && !(ch instanceof Skeleton)  && !(ch instanceof Statue) && !(ch instanceof Yog) && ch != Dungeon.hero) {

					Buff.affect(ch, Bleeding.class);
					Buff.affect(ch, Slow.class, 5);
					Buff.prolong( ch, Paralysis.class, 2 );
					ch.sprite.centerEmitter().start(Speck.factory(Speck.NOTE), 0.3f, 5);
					int dmg = Random.Int( 1 , 8 );
						if (dmg > 0) {
							ch.damage( dmg, this );
						}
								
							

					
				}
			}
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
	public ItemSprite.Glowing glowing() {
		return fuse != null ? new ItemSprite.Glowing(0xFF0000, 0.6f) : null;
	}

	@Override
	public int price() {
		return 20 * quantity;
	}

	@Override
	public String info() {
		return "A weighty bundle of nails that packed into a empty can with some sugar."
				+ (fuse != null ? "\n\nThe nail bomb slowed. It could explode at any moment!"
						: "\n\nShaking the bundle could make it unstable and release it's goodness.");
	}

	private static final String FUSE = "fuse";

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(FUSE, fuse);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		if (bundle.contains(FUSE))
			Actor.add(fuse = ((Fuse) bundle.get(FUSE)).ignite(this));
	}

	public static class Fuse extends Actor {

		private DumplingBomb bomb;

		public Fuse ignite(DumplingBomb bomb) {
			this.bomb = bomb;
			return this;
		}

		@Override
		protected boolean act() {

			// something caused our bomb to explode early, or be defused. Do
			// nothing.
			if (bomb.fuse != this) {
				Actor.remove(this);
				return true;
			}

			// look for our bomb, remove it from its heap, and blow it up.
			for (Heap heap : Dungeon.level.heaps.values()) {
				if (heap.items.contains(bomb)) {
					heap.items.remove(bomb);

					bomb.explode(heap.pos);

					Actor.remove(this);
					return true;
				}
			}

			// can't find our bomb, this should never happen, throw an
			// exception.
			throw new RuntimeException(
					"Something caused an slowed bomb to not be present in a heap on the level!");
		}
	}
	

	
}
