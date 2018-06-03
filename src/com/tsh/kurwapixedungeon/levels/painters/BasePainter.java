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
package com.tsh.kurwapixedungeon.levels.painters;

import com.tsh.kurwapixedungeon.actors.hero.Hero;
import com.tsh.kurwapixedungeon.items.ExpJar;
import com.tsh.kurwapixedungeon.items.Generator;
import com.tsh.kurwapixedungeon.items.Item;
import com.tsh.kurwapixedungeon.items.Heap.Type;
import com.tsh.kurwapixedungeon.items.keys.GoldenKey;
import com.tsh.kurwapixedungeon.items.keys.IronKey;
import com.tsh.kurwapixedungeon.levels.Level;
import com.tsh.kurwapixedungeon.levels.Room;
import com.tsh.kurwapixedungeon.levels.Terrain;
import com.tsh.utils.Random;

public class BasePainter extends Painter {

	public static void paint( Level level, Room room ) {

		fill( level, room, Terrain.WALL );
		fill( level, room, 1, Terrain.EMPTY_SP );
		fill( level, room, 2, Terrain.EMPTY );
		
		int cx = (room.left + room.right) / 2;
		int cy = (room.top + room.bottom) / 2;
		int c = cx + cy * Level.WIDTH;
		
		
		level.drop(new ExpJar(), 200);
		
		//room.entrance().set( Room.Door.Type.LOCKED );
		//level.addItemToSpawn( new IronKey() );
	}
	
}
