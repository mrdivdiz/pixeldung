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

import com.tsh.kurwapixedungeon.actors.hero.Hero;
import com.tsh.kurwapixedungeon.effects.Speck;
import com.tsh.kurwapixedungeon.effects.particles.FlameParticle;
import com.tsh.kurwapixedungeon.sprites.ItemSpriteSheet;
import com.tsh.kurwapixedungeon.windows.WndCraft;
import com.tsh.noosa.particles.Emitter;

public class Gayporn extends Trash {

	
	
	{
		
		name = "gay porn";
		image = ItemSpriteSheet.GAYPORN;
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
			WndCraft.craftits();
			detach(hero.belongings.backpack);
			Emitter emitter = hero.sprite.centerEmitter();
			emitter.start( FlameParticle.FACTORY, 0.2f, 8 );	
			
			
		}
		}
	
	
	@Override
	public String desc(){
	
		return
			"????WTF?" +
			".\n\n" +
			"Ahh, old memories, that never ever come true..." +
			"Nobody fucking cares.You only hope that it will help you on your way." +
			"";
	}}
