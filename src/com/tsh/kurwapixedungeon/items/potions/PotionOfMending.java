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
package com.tsh.kurwapixedungeon.items.potions;

import com.tsh.kurwapixedungeon.Dungeon;
import com.tsh.kurwapixedungeon.actors.buffs.Bleeding;
import com.tsh.kurwapixedungeon.actors.buffs.Buff;
import com.tsh.kurwapixedungeon.actors.buffs.Cripple;
import com.tsh.kurwapixedungeon.actors.buffs.Poison;
import com.tsh.kurwapixedungeon.actors.buffs.Weakness;
import com.tsh.kurwapixedungeon.actors.hero.Hero;
import com.tsh.kurwapixedungeon.effects.Speck;
import com.tsh.kurwapixedungeon.utils.GLog;

public class PotionOfMending extends Potion {

	{
		name = "Potion of Mending";


	}

	@Override
	public void apply(Hero hero) {
		setKnown();
		heal(Dungeon.hero);
		GLog.p("Your wounds heal some.");
	}

	public static void heal(Hero hero) {

		hero.HP = hero.HP+Math.min((hero.HT/4), hero.HT-hero.HP);
		Buff.detach(hero, Poison.class);
		Buff.detach(hero, Cripple.class);
		Buff.detach(hero, Weakness.class);
		Buff.detach(hero, Bleeding.class);

		hero.sprite.emitter().start(Speck.factory(Speck.HEALING), 0.4f, 4);
	}

	@Override
	public String desc() {
		return "An elixir that will mend some wounds and cure ailments.";
	}

	@Override
	public int price() {
		return isKnown() ? 20 * quantity : super.price();
	}
}
