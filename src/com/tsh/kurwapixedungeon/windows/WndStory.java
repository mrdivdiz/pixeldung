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
package com.tsh.kurwapixedungeon.windows;

import com.tsh.input.Touchscreen.Touch;
import com.tsh.kurwapixedungeon.Chrome;
import com.tsh.kurwapixedungeon.Dungeon;
import com.tsh.kurwapixedungeon.scenes.PixelScene;
import com.tsh.kurwapixedungeon.ui.Window;
import com.tsh.noosa.BitmapTextMultiline;
import com.tsh.noosa.Game;
import com.tsh.noosa.TouchArea;
import com.tsh.utils.SparseArray;

public class WndStory extends Window {

	private static final int WIDTH = 120;
	private static final int MARGIN = 6;
	
	private static final float bgR	= 0.77f;
	private static final float bgG	= 0.73f;
	private static final float bgB	= 0.62f;
	
	public static final int ID_SEWERS		= 0;
	public static final int ID_PRISON		= 1;
	public static final int ID_CAVES		= 2;
	public static final int ID_METROPOLIS	= 3;
	public static final int ID_HALLS		= 4;
	public static final int ID_HITLER		= 5;
	
	private static final SparseArray<String> CHAPTERS = new SparseArray<String>();
	
	static {
		CHAPTERS.put( ID_SEWERS, 
		"Many years ago one freak thieved amulet of Vendor, and... Fuk off de world with nuclear war, that because it full of shit. " +
		"Everyone, who was alive, come to live to metro and sewers.Metro got raided in 2035.You come here as last chance, and havee no idea how " +
		"to survive here.You start thinking about your story..." );
		
		CHAPTERS.put( ID_PRISON, 
		"When bombs was dropped on City first, you saw a big white mushroom in the air." +
		"You ran out from your house with your little sister and dad.You saw thousands corpses flood streets." +
		"You're saved your family in a shelter for some time..." );
		
		CHAPTERS.put( ID_CAVES, 
		"'Till next 2 weeks, we searchin' for som food.Hope it will be somewhere.'" +
		"Hah.You was naughty.There is NOTHING on landscape: ash hills and tank corpses. " +
		"Your dad died because of radiation sickness, and sister join stalker's clan, but missed already for two months." +
		"Next year you found this dungeon and go in." );
		
		CHAPTERS.put( ID_METROPOLIS, 
		"When you stepped onto this level, you saw human in dark cloak at the end of hall." +
		"IS THERE SOMEONE ELSE?!"
		+ "You hope that's just  ghost." );
		
		
		CHAPTERS.put( ID_HITLER,
				"Is there a bunker under dungeon?Nobody fucking knows." +
				"You enter next level, and saw many little spiders..Is there one big?" +
				"\n\n" +
				"You'll be careful" );
		
		
		CHAPTERS.put( ID_HALLS,
		"You with Laki get to there.It looks like hell, but it's better than landscape's fallout. " +
		"You enter next level, Laki grab your hand, Spidi are ready for attack someone..." +
		"and now it's called Demon Halls.Sure\n\n" +
		"Very few adventurers have ever descended this far, you think you first till last five centuries..." );
	};
	
	private BitmapTextMultiline tf;
	
	private float delay;
	
	public WndStory( String text ) {
		super( 0, 0, Chrome.get( Chrome.Type.SCROLL ) );
		
		tf = PixelScene.createMultiline( text, 7 );
		tf.maxWidth = WIDTH - MARGIN * 2;
		tf.measure();
		tf.ra = bgR;
		tf.ga = bgG;
		tf.ba = bgB;
		tf.rm = -bgR;
		tf.gm = -bgG;
		tf.bm = -bgB;
		tf.x = MARGIN;
		add( tf );
		
		add( new TouchArea( chrome ) {
			@Override
			protected void onClick( Touch touch ) {
				hide();
			}
		} );
		
		resize( (int)(tf.width() + MARGIN * 2), (int)Math.min( tf.height(), 180 ) );
	}
	
	@Override
	public void update() {
		super.update();
		
		if (delay > 0 && (delay -= Game.elapsed) <= 0) {
			shadow.visible = chrome.visible = tf.visible = true;
		}
	}
	
	public static void showChapter( int id ) {
		
		if (Dungeon.chapters.contains( id )) {
			return;
		}
		
		String text = CHAPTERS.get( id );
		if (text != null) {
			WndStory wnd = new WndStory( text );
			if ((wnd.delay = 0.6f) > 0) {
				wnd.shadow.visible = wnd.chrome.visible = wnd.tf.visible = false;
			}
			
			Game.scene().add( wnd );
			
			Dungeon.chapters.add( id );
		}
	}
}
