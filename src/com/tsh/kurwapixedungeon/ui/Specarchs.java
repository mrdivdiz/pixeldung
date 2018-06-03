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
package com.tsh.kurwapixedungeon.ui;

import com.tsh.kurwapixedungeon.Assets;
import com.tsh.noosa.Game;
import com.tsh.noosa.SkinnedBlock;
import com.tsh.noosa.ui.Component;

public class Specarchs extends Component {

	private static final float SCROLL_SPEED	= 20f;
	
	private SkinnedBlock arcsBg;
	private SkinnedBlock arcsFg;
	
	private static float offsB = 0;
	private static float offsF = 0;
	
	public boolean reversed = false;
	
	@Override
	protected void createChildren() {
		arcsBg = new SkinnedBlock( 1, 1, Assets.ARCS_SPC );
		arcsBg.autoAdjust = true;
		arcsBg.offsetTo( 0,  offsB );
		add( arcsBg );
	}
	
	@Override
	protected void layout() {
		arcsBg.size( width, height );
		arcsBg.offset( arcsBg.texture.width / 4 - (width % arcsBg.texture.width) / 2, 0 );
	}
	
	@Override
	public void update() {
		
		super.update();
		
		float shift = Game.elapsed * SCROLL_SPEED;
		if (reversed) {
			shift = -shift;
		}
		
		arcsBg.offset( 0, shift );
		
		offsB = arcsBg.offsetY();
	}
}
