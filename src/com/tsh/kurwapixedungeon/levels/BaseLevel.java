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
package com.tsh.kurwapixedungeon.levels;

import java.util.Arrays;

import com.tsh.kurwapixedungeon.Assets;
import com.tsh.kurwapixedungeon.items.Amulet;
import com.tsh.kurwapixedungeon.items.ExpJar;
import com.tsh.kurwapixedungeon.levels.painters.Painter;
import com.tsh.noosa.Scene;
import com.tsh.utils.Random;

public class BaseLevel extends Level {

	private static final int SIZE = 32;
	
	{
		color1 = 0x801500;
		color2 = 0xa68521;
	}
	
	private int pedestal;
	
	@Override
	public String tilesTex() {
		return Assets.TILES_BASE;
	}
	
	@Override
	public String waterTex() {
		return Assets.WATER_BASE;
	}
	
	@Override
	protected boolean build() {

		Arrays.fill( map, Terrain.WALL );
		Painter.fill( this, 20, 15, 5, 5, Terrain.WATER );
		Painter.fill( this, 1, 1, SIZE-1, SIZE-1, Terrain.EMPTY_SP );
		Painter.fill( this, SIZE/2, SIZE/2, 5, 5, Terrain.WATER );
		
		pedestal = 96 * 6 + 1;
		
		map[256] = Terrain.WALL_DECO;
		map[99]  = map[195] = map[291] = map[100] = map[101] = map[292] = map[293] = Terrain.WALL;
		map[197] = Terrain.DOOR;
		map[pedestal+1] = map[pedestal] = map[pedestal + 2] = map[pedestal + 97] = map[pedestal + 193] = Terrain.STATUE_SP;
		
		
		feeling = Feeling.WATER;
		
		
		return true;
	}

	@Override
	protected void decorate() {
		for (int i=0; i < LENGTH; i++) {
			if (map[i] == Terrain.EMPTY && Random.Int( 10 ) == 0) { 
				map[i] = Terrain.EMPTY_DECO;
			}
		}
	}

	@Override
	protected void createMobs() {
	}

	@Override
	protected void createItems() {
		drop(new ExpJar(), 200);
	}
	
	@Override
	public int randomRespawnCell() {
		return -1;
	}

	@Override
	public String tileName( int tile ) {
		switch (tile) {
		case Terrain.WATER:
			return "Clean water";
		case Terrain.GRASS:
			return "Embermoss";
		case Terrain.HIGH_GRASS:
			return "Emberfungi";
		case Terrain.STATUE:
		case Terrain.STATUE_SP:
			return "Pipes";
		default:
			return super.tileName( tile );
		}
	}
	
	@Override
	public String tileDesc(int tile) {
		switch (tile) {
		case Terrain.WATER:
			return "Clean and tasty water.";
		case Terrain.STATUE:
		case Terrain.STATUE_SP:
			return "Pipes that goes to somewhere.Looks strange."; 
		default:
			return super.tileDesc( tile );
		}
	}
	
	@Override
	public void addVisuals( Scene scene ) {
		HallsLevel.addVisuals( this, scene );
	}
}
