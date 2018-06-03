package com.tsh.kurwapixedungeon.sprites;

import com.tsh.kurwapixedungeon.Assets;
import com.tsh.noosa.TextureFilm;

public class PresAnim extends MobSprite{
	public Animation pres;
	
	public PresAnim() {
	
		super();
	
    
	
	TextureFilm frames = new TextureFilm( Assets.PRESENTING, 128, 64 );
	
	pres = new Animation( 4, true );
	pres.frames( frames, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 );
	
	play(pres);
}}

