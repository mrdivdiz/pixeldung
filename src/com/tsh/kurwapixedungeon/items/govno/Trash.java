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
package com.tsh.kurwapixedungeon.items.govno;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.tsh.kurwapixedungeon.Assets;
import com.tsh.kurwapixedungeon.Badges;
import com.tsh.kurwapixedungeon.Dungeon;
import com.tsh.kurwapixedungeon.actors.Actor;
import com.tsh.kurwapixedungeon.actors.Char;
import com.tsh.kurwapixedungeon.actors.buffs.SnipersMark;
import com.tsh.kurwapixedungeon.actors.hero.Hero;
import com.tsh.kurwapixedungeon.effects.Degradation;
import com.tsh.kurwapixedungeon.effects.Speck;
import com.tsh.kurwapixedungeon.items.Item;
import com.tsh.kurwapixedungeon.items.armor.Armor;
import com.tsh.kurwapixedungeon.items.bags.Bag;
import com.tsh.kurwapixedungeon.items.rings.Ring;
import com.tsh.kurwapixedungeon.items.wands.Wand;
import com.tsh.kurwapixedungeon.items.weapon.Weapon;
import com.tsh.kurwapixedungeon.items.weapon.missiles.MissileWeapon;
import com.tsh.kurwapixedungeon.mechanics.Ballistica;
import com.tsh.kurwapixedungeon.scenes.CellSelector;
import com.tsh.kurwapixedungeon.scenes.GameScene;
import com.tsh.kurwapixedungeon.sprites.CharSprite;
import com.tsh.kurwapixedungeon.sprites.ItemSprite;
import com.tsh.kurwapixedungeon.sprites.MissileSprite;
import com.tsh.kurwapixedungeon.ui.QuickSlot;
import com.tsh.kurwapixedungeon.utils.GLog;
import com.tsh.kurwapixedungeon.utils.Utils;
import com.tsh.noosa.audio.Sample;
import com.tsh.utils.Bundlable;
import com.tsh.utils.Bundle;
import com.tsh.utils.Callback;
import com.tsh.utils.PointF;

public class Trash extends Item {
	
	String AC_LOOK = "CRAFT";
	public Hero hero;
	
	
}
