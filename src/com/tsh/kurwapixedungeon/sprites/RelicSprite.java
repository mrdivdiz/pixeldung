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
package com.tsh.kurwapixedungeon.sprites;

import com.tsh.kurwapixedungeon.Assets;
import com.tsh.noosa.Image;

public class RelicSprite {

	public enum  Type {
		Relic1,
		Relic2,
		Relic3,
		Relic4,
		Relic5,
		Relic6
	};
	
	public static Image get( Type type ) {
		Image icon = new Image( Assets.RELIC );
		switch (type) {
		case Relic1:
			icon.frame( icon.texture.uvRect( 0, 0, 127, 127 ) );
			break;
		case Relic2:
			icon.frame( icon.texture.uvRect( 0, 128, 127, 255 ) );
			break;
		case Relic3:
			icon.frame( icon.texture.uvRect( 0, 256, 127, 383 ) );
			break;
		case Relic4:
			icon.frame( icon.texture.uvRect( 0, 384, 127, 511 ) );
			break;
		case Relic5:
			icon.frame( icon.texture.uvRect( 0, 512, 127, 639 ) );
			break;
		case Relic6:
			icon.frame( icon.texture.uvRect( 0, 640, 127, 767 ) );
			break;
		}
		return icon;
	}
}
