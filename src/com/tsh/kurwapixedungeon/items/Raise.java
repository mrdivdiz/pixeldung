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
import com.tsh.kurwapixedungeon.Statistics;
import com.tsh.kurwapixedungeon.actors.hero.Hero;
import com.tsh.kurwapixedungeon.actors.mobs.Mob;
import com.tsh.kurwapixedungeon.effects.CellEmitter;
import com.tsh.kurwapixedungeon.effects.Speck;
import com.tsh.kurwapixedungeon.items.weapon.missiles.RiceBall;
import com.tsh.kurwapixedungeon.scenes.GameScene;
import com.tsh.kurwapixedungeon.sprites.CharSprite;
import com.tsh.kurwapixedungeon.sprites.ItemSpriteSheet;
import com.tsh.kurwapixedungeon.utils.GLog;
import com.tsh.kurwapixedungeon.utils.Utils;
import com.tsh.noosa.audio.Sample;
import com.tsh.utils.Bundle;
import com.tsh.utils.Random;

public class Raise extends Item {

	public static final String AC_COOK = "One nail";
	public static final String AC_COOKBOMB = "Nail bomb";

	public static final float TIME_TO_COOK = 1;
	public static final float TIME_TO_COOK_BOMB = 4;

	{
		name = "Nail bag";
		image = ItemSpriteSheet.RICESEM;

		stackable = false;

		defaultAction = AC_COOK;
	}

	private int bombcost=12;
	
	@Override
	public ArrayList<String> actions(Hero hero) {
		ArrayList<String> actions = super.actions(hero);
		actions.add(AC_COOK);
		actions.add(AC_COOKBOMB);
		return actions;
	}

	@Override
	public void execute(Hero hero, String action) {

		if (action.equals(AC_COOK)) {

			hero.spend(TIME_TO_COOK);
			hero.busy();

			hero.sprite.operate(hero.pos);
			
			RiceBall riceball = new RiceBall();
			if (riceball.doPickUp(Dungeon.hero)) {
				GLog.i(Hero.TXT_YOU_NOW_HAVE, riceball.name());
				Statistics.ballsCooked++;
			} else {
				Dungeon.level.drop(riceball, Dungeon.hero.pos).sprite.drop();
				Statistics.ballsCooked++;
			}
			
				
			if (Statistics.ballsCooked>100){
				detach(Dungeon.hero.belongings.backpack);
				GLog.n("Your nail bag is wasted!");
				Statistics.ballsCooked = 0;
			} else if (Statistics.ballsCooked>75){
				GLog.n("Your nail bag is wasting...");
			} else if (Statistics.ballsCooked>50){
				GLog.n("Your nail bag are half-emptied...");
			}
				
		} else if (action.equals(AC_COOKBOMB)) {

			hero.spend(TIME_TO_COOK_BOMB);
			hero.busy();

			hero.sprite.operate(hero.pos);
			
			DumplingBomb bomb = new DumplingBomb();
			if (bomb.doPickUp(Dungeon.hero)) {
				GLog.i(Hero.TXT_YOU_NOW_HAVE, bomb.name());
				Statistics.ballsCooked+=bombcost;
			} else {
				Dungeon.level.drop(bomb, Dungeon.hero.pos).sprite.drop();
				Statistics.ballsCooked+=bombcost;
			}
			
				
			if (Statistics.ballsCooked>100){
				detach(Dungeon.hero.belongings.backpack);
				GLog.n("Your nail bag is wasted!");
				Statistics.ballsCooked = 0;
			} else if (Statistics.ballsCooked>75){
				GLog.n("Your nail bag is wasting...");
			} else if (Statistics.ballsCooked>50){
				GLog.n("Your nail bag are half-emptied...");
			}
					

		} else {
			super.execute(hero, action);

		}
	}
	
	@Override
	public boolean doPickUp(Hero hero) {
		if (super.doPickUp(hero)) {

			if (Dungeon.level != null && Dungeon.depth==32) {
				for (Mob mob : Dungeon.level.mobs) {
					mob.beckon(Dungeon.hero.pos);
				}

				
				CellEmitter.center(Dungeon.hero.pos).start(
						Speck.factory(Speck.SCREAM), 0.3f, 3);
				Sample.INSTANCE.play(Assets.SND_CHALLENGE);
			}

			return true;
		} else {
			return false;
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
		return 100 * quantity;
	}

	@Override
	public String info() {
		return "This bag contain nails and cans with sugar to create some bombs.";
	}
}
