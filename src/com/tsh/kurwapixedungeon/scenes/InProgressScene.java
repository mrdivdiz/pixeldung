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
package com.tsh.kurwapixedungeon.scenes;




import com.tsh.kurwapixedungeon.KurwaDungeon;
import com.tsh.kurwapixedungeon.effects.Flare;
import com.tsh.kurwapixedungeon.ui.Archs;
import com.tsh.kurwapixedungeon.ui.ExitButton;
import com.tsh.kurwapixedungeon.ui.Icons;
import com.tsh.noosa.BitmapTextMultiline;
import com.tsh.noosa.Camera;
import com.tsh.noosa.Image;


public class InProgressScene extends PixelScene {

	private static final String TXT = 
		"Challenges!\n" +
		"" + 
		"Some challenges for most advanced users";
	
	
	@Override
	public void create() {
		super.create();
		
		BitmapTextMultiline text = createMultiline( TXT, 8 );
		text.maxWidth = Math.min( Camera.main.width / 5, 120 );
		text.measure();
		add( text );
		
		text.x = align( (Camera.main.width - text.width()) / 2 );
		text.y = align( (Camera.main.height - text.height()) / 2 );
		
		
		


		
		Image wata = Icons.CHALLENGE_ON.get();
		wata.x = align( (Camera.main.width / 2 - wata.width) / 2 );
		wata.y = text.y - wata.height - 8;
		add( wata );
		
		new Flare( 7, 64 ).color( 0x112233, true ).show( wata, 0 ).angularSpeed = +20;
		
		Archs archs = new Archs();
		archs.setSize( Camera.main.width, Camera.main.height );
		addToBack( archs );
		
		ExitButton btnExit = new ExitButton();
		btnExit.setPos( Camera.main.width - btnExit.width(), 0 );
		add( btnExit );
		
		fadeIn();
	}
	
	@Override
	protected void onBackPressed() {
		KurwaDungeon.switchNoFade( TitleScene.class );
	}
}
