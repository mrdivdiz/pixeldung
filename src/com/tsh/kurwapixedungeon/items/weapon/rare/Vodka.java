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
package com.tsh.kurwapixedungeon.items.weapon.rare;

import java.util.ArrayList;

import com.tsh.kurwapixedungeon.actors.hero.Hero;
import com.tsh.kurwapixedungeon.effects.particles.BloodParticle;
import com.tsh.kurwapixedungeon.effects.particles.FlameParticle;
import com.tsh.kurwapixedungeon.items.weapon.melee.MeleeWeapon;
import com.tsh.kurwapixedungeon.sprites.ItemSpriteSheet;
import com.tsh.kurwapixedungeon.utils.GLog;
import com.tsh.kurwapixedungeon.windows.WndCraft;
import com.tsh.noosa.particles.Emitter;

public class Vodka extends RareWeapon {
	public static String drink = "DRINK";
	{
		name = "vodka bottle";
		image = ItemSpriteSheet.VODKA;
	}
	
	public Vodka() {
		super( 6, 0.5f, 0.9f );
	}
	
	@Override
	public String desc() {
		return "Everday im drinkin'!";
	}
	/*public ArrayList<String> actions( Hero hero ) {
		ArrayList<String> actions = super.actions( hero );
		actions.add( drink );
		return actions;
	}
	public void execute( final Hero hero, String action ){
		if(action.equals(drink)){
			GLog.p("FOR VDV!!!!!11");
			detach(hero.belongings.backpack);
			Emitter emitter = hero.sprite.centerEmitter();
			emitter.start( FlameParticle.FACTORY, 0.8f, 64 );
		}}*/
}
