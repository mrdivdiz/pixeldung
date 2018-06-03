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

import com.tsh.kurwapixedungeon.Base;
import com.tsh.kurwapixedungeon.actors.hero.Hero;
import com.tsh.kurwapixedungeon.effects.Speck;
import com.tsh.kurwapixedungeon.effects.particles.FlameParticle;
import com.tsh.kurwapixedungeon.scenes.GameScene;
import com.tsh.kurwapixedungeon.sprites.AdditionalItems;
import com.tsh.kurwapixedungeon.sprites.ItemSpriteSheet;
import com.tsh.kurwapixedungeon.utils.GLog;
import com.tsh.kurwapixedungeon.windows.WndCraft;
import com.tsh.kurwapixedungeon.windows.WndEnrcypt1;
import com.tsh.kurwapixedungeon.windows.WndEnrcypt2;
import com.tsh.noosa.particles.Emitter;

public class Encrypt2 extends Item {
	 public static String AC_LOOK = "READ";
	
	{
		
		name = "Avalon and a Unleashed Warrior";
		image = AdditionalItems.RELIC1;
		defaultAction = AC_LOOK;
		stackable = true;
	}
	
	public ArrayList<String> actions( Hero hero ) {
		ArrayList<String> actions = super.actions( hero );
		actions.add( AC_LOOK );
		return actions;
	}
	public void execute( final Hero hero, String action ){
		if(action.equals(AC_LOOK)){
			GameScene.show(new WndEnrcypt2());
			detach( hero.belongings.backpack );
			GLog.n("Scroll burn in flames!");
		}}
	
	@Override
	public String desc(){
	
		return
			"You can't say what is this.";
	}}
