package com.tsh.kurwapixedungeon.themes;

import com.tsh.kurwapixedungeon.Assets;
import com.tsh.kurwapixedungeon.KurwaDungeon;
import com.tsh.kurwapixedungeon.Rankings;
import com.tsh.kurwapixedungeon.actors.hero.HeroClass;
import com.tsh.noosa.audio.Music;
import com.tsh.utils.Bundle;
import com.tsh.utils.Random;

public class Theme {
	//public int older = 0;
	public static boolean olded = false;
	public static boolean mus = true;
	public static boolean bull = false;
	public static boolean bull2 = false;
	public static int scen = 1;
	public static boolean fps = false;
	String THEME1	= "THEME";
	 String THEME2	= "THEME";
	
	
public static void Update(){
		boolean mus = Theme.mus;			
		if (mus == true && Assets.THEME != "snd_mlg.mp3") {
			Assets.THEME = "theme.mp3";
			Music.INSTANCE.mute();
			Music.INSTANCE.play( Assets.THEME, true );
			Music.INSTANCE.volume( 1f );
		}
		if(mus == false && Assets.THEME != "snd_mlg.mp3"){
			Assets.THEME = "theme2.mp3";
			Music.INSTANCE.mute();
			Music.INSTANCE.play( Assets.THEME, true );
			Music.INSTANCE.volume( 0.8f );
		}	

}
	
	
	
	public static void generated() {
		mus = !mus;
	}
	
	public static void generated2() {
		olded = !olded;
	}

public static boolean old(boolean b) {
		return olded;
	}

}