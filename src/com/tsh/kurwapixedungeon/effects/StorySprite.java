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
package com.tsh.kurwapixedungeon.effects;

import com.tsh.kurwapixedungeon.Assets;
import com.tsh.noosa.Image;

public class StorySprite {

	public enum  Type {
		PDPRESENTING,
		STOR1,
		STOR2,
		STOR3,
		STOR4,
		STOR5
	};
	
	public static Image get( Type type ) {
		Image icon = new Image( Assets.STORE );
		switch (type) {
		case PDPRESENTING:
			icon.frame( icon.texture.uvRect( 0, 0, 128, 70 ) );
			break;
		case STOR1:
			icon.frame( icon.texture.uvRect( 0, 71, 128, 140 ) );
			break;
		case STOR2:
			icon.frame( icon.texture.uvRect( 0, 141, 128, 210 ) );
			break;
		case STOR3:
			icon.frame( icon.texture.uvRect( 0, 211, 128, 280 ) );
			break;
		case STOR4:
			icon.frame( icon.texture.uvRect( 0, 281, 128, 350 ) );
			break;
		case STOR5:
			icon.frame(icon.texture.uvRect(0, 351, 128, 420));
			break;
		}
		return icon;
	}
}
