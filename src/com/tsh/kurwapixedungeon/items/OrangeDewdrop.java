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
package com.tsh.kurwapixedungeon.items;

import com.tsh.kurwapixedungeon.Assets;
import com.tsh.kurwapixedungeon.Dungeon;
import com.tsh.kurwapixedungeon.actors.hero.Hero;
import com.tsh.kurwapixedungeon.actors.hero.HeroClass;
import com.tsh.kurwapixedungeon.effects.Speck;
import com.tsh.kurwapixedungeon.sprites.CharSprite;
import com.tsh.kurwapixedungeon.sprites.ItemSpriteSheet;
import com.tsh.noosa.audio.Sample;

public class OrangeDewdrop extends Item {

	private static final String TXT_VALUE = "%+dHP";

	{
		name = "ORANGE dewdrop";
		image = ItemSpriteSheet.ORANGEDEWDROP;

		stackable = true;
	}

	@Override
	public boolean doPickUp(Hero hero) {

		DewVial vial = hero.belongings.getItem(DewVial.class);

		if (vial == null || vial.isFull()) {

			int value = 15 + (Dungeon.depth - 1) / 5;
			if (hero.heroClass == HeroClass.HUNTRESS) {
				value++;
			}

			int effect = Math.min(hero.HT - hero.HP, value * quantity);
			if (effect > 0) {
				hero.HP += effect;
				hero.sprite.emitter().burst(Speck.factory(Speck.HEALING), 1);
				hero.sprite.showStatus(CharSprite.POSITIVE, TXT_VALUE, effect);
			}

		} else if (vial != null) {

			vial.collectDew(this);

		}

		Sample.INSTANCE.play(Assets.SND_DEWDROP);
		hero.spendAndNext(TIME_TO_PICK_UP);

		return true;
	}

	@Override
	public String info() {
		return "STOP IT";
	}
}
