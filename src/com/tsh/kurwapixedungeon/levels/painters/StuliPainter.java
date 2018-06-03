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

import com.tsh.kurwapixedungeon.items.Gold;
import com.tsh.kurwapixedungeon.items.Heap;
import com.tsh.kurwapixedungeon.items.keys.IronKey;
import com.tsh.kurwapixedungeon.items.potions.PotionOfExperience;
import com.tsh.kurwapixedungeon.levels.Level;
import com.tsh.kurwapixedungeon.levels.Room;
import com.tsh.kurwapixedungeon.levels.Terrain;
import com.tsh.utils.Random;

public class StuliPainter extends Painter {

	public static void paint( Level level, Room room ) {

		fill( level, room, Terrain.WALL );
		fill( level, room, 1, Terrain.EMPTY );
		
		set( level, room.center(), Terrain.STATUE );
		
		Heap.Type heapType = Random.Int( 2 ) == 0 ? Heap.Type.CHEST : Heap.Type.HEAP;
		
		int n = Random.IntRange( 2, 3 );
		for (int i=0; i < n; i++) {
			int pos;
			do {
				pos = room.random();
			} while (level.map[pos] != Terrain.EMPTY || level.heaps.get( pos ) != null);
			level.drop( new Gold().random(), pos ).type = (i == 0 && heapType == Heap.Type.CHEST ? Heap.Type.MIMIC : heapType);
		}
		
		if (heapType == Heap.Type.HEAP) {
			for (int i=0; i < 4; i++) {
				int pos;
				do {
					pos = room.random();
				} while (level.map[pos] != Terrain.EMPTY);
				level.drop( new Gold( Random.IntRange( 1, 3 ) ), pos );
				level.drop(new PotionOfExperience(), pos).type = Heap.Type.CHAIRS1;
				level.drop(new PotionOfExperience(), pos).type = Heap.Type.CHAIRS2;
			}
		}
		
		room.entrance().set( Room.Door.Type.BARRICADE );
	}
}
