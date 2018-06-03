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
package com.tsh.kurwapixedungeon.items;

import java.util.ArrayList;
import java.util.Random;

import com.tsh.kurwapixedungeon.Assets;
import com.tsh.kurwapixedungeon.actors.hero.Hero;
import com.tsh.kurwapixedungeon.effects.Speck;
import com.tsh.kurwapixedungeon.effects.particles.ShaftParticle;
import com.tsh.kurwapixedungeon.sprites.CharSprite;
import com.tsh.kurwapixedungeon.sprites.ItemSpriteSheet;
import com.tsh.kurwapixedungeon.sprites.ItemSprite.Glowing;
import com.tsh.kurwapixedungeon.utils.GLog;
import com.tsh.kurwapixedungeon.utils.Utils;
import com.tsh.noosa.audio.Sample;
import com.tsh.utils.Bundle;


public class DewVial extends Item {

	private static final int MAX_VOLUME	= 12000;
	
	
	private static final String AC_DRINK	= "DRINK";
	private static final String AC_SIP	= "SIP";
	private static final String AC_HEAL	= "LVLUP";
	private static final float TIME_TO_DRINK = 1f;
	private static final float TIME_TO_HEAL = 1.5f;
	private static final String TXT_VALUE	= "%+dHP";
	private static final String TXT_STATUS	= "%d/%d";
	private static Random rand = new Random();
	private static final String TXT_AUTO_DRINK	= "The dew vial was emptied to heal your wounds.";
	private static final String TXT_COLLECTED	= "You collected a dewdrop into your dew vial.";
	private static final String TXT_FULL		= "Your dew vial is full!";
	private static final String TXT_EMPTY		= "Your dew vial is empty!";
	
	{
		name = "dew vial";
		image = ItemSpriteSheet.VIAL;
		
		defaultAction = AC_DRINK;
		
		unique = true;
	}
	
	private int volume = 15;
	
	private static final String VOLUME	= "volume";
	
	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle( bundle );
		bundle.put( VOLUME, volume );
	}
	
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		volume	= bundle.getInt( VOLUME );
	}
	
	@Override
	public ArrayList<String> actions( Hero hero ) {
		ArrayList<String> actions = super.actions( hero );
		if (volume > 10) {
			actions.add( AC_DRINK );
			
			if (volume > 30) {
				actions.add( AC_HEAL );
			
				
				if (volume > 3) {
					actions.add( AC_SIP );
				
			}}}
		
		
		return actions;
	}
	
	
	
	private static final double NUM = 20;
	private static final double POW = Math.log10( NUM );
	
	@Override
	public void execute( final Hero hero, String action ) {
		
		if (action.equals( AC_SIP )) {
		
			if (volume > 3) {
				
				int value = (int)Math.ceil( Math.pow( volume, POW ) / NUM * hero.HT );
				int effect = Math.min( hero.HT - hero.HP, value );
				volume = volume - 3;
				hero.HP += effect;
				
				hero.spend( TIME_TO_DRINK );
				
			}}
if (action.equals(AC_HEAL)){
	if(volume > 30){
		int value = (int)Math.ceil( Math.pow( volume, POW ) / NUM * hero.HT );
		int effect = Math.min( hero.HT - hero.HP, value );
				hero.spend( TIME_TO_HEAL );
				hero.spend( TIME_TO_HEAL );
				hero.spend( TIME_TO_HEAL );
				hero.spend( TIME_TO_HEAL );
				volume = volume - 30;
				hero.HP += effect;
				hero.exp = rand.nextInt(16);
			}}
			
		
		
		if (action.equals( AC_DRINK )) {
			
			if (volume > 0) {

				int value = (int)Math.ceil( Math.pow( volume, POW ) / NUM * hero.HT );
				int effect = Math.min( hero.HT - hero.HP, value );
				if (effect > 0) {
					hero.HP += effect;
					hero.sprite.emitter().burst( Speck.factory( Speck.HEALING ), volume > 5 ? 2 : 1 );
					hero.sprite.showStatus( CharSprite.POSITIVE, TXT_VALUE, effect );
				}
				
				volume = volume - 15;
				
				hero.spend( TIME_TO_DRINK );
				hero.busy();
				
				Sample.INSTANCE.play( Assets.SND_DRINK );
				hero.sprite.operate( hero.pos );
				
				updateQuickslot();
				
			} else {
				GLog.w( TXT_EMPTY );
			}
			
		} else {
			
			super.execute( hero, action );
			
		}
	}
	
	@Override
	public boolean isUpgradable() {
		return false;
	}
	
	@Override
	public boolean isIdentified() {
		return true;
	}
	
	public boolean isFull() {
		return volume >= MAX_VOLUME;
	}
	
	public void collectDew( Dewdrop dew ) {
		
		GLog.i( TXT_COLLECTED );
		volume += dew.quantity;
		if (volume >= MAX_VOLUME) {
			volume = MAX_VOLUME;
			GLog.p( TXT_FULL );
		}
		
		updateQuickslot();
	}
public void collectDew( LimeDewdrop dew ) {
		
		GLog.i( TXT_COLLECTED );
		volume = volume + 22;
		if (volume >= MAX_VOLUME) {
			volume = MAX_VOLUME;
			GLog.p( TXT_FULL );
		}
		
		updateQuickslot();
	}
public void collectDew( OrangeDewdrop dew ) {
	
	GLog.i( TXT_COLLECTED );
	volume = volume + 16;
	if (volume >= MAX_VOLUME) {
		volume = MAX_VOLUME;
		GLog.p( TXT_FULL );
	}
	
	updateQuickslot();
}
public void collectDew( RedDewdrop dew ) {
		
		GLog.i( TXT_COLLECTED );
		volume = volume + 5;
		if (volume >= MAX_VOLUME) {
			volume = MAX_VOLUME;
			GLog.p( TXT_FULL );
		}
		
		updateQuickslot();
	}
	
	public void fill() {
		volume = MAX_VOLUME;
		updateQuickslot();
	}
	
	public static void autoDrink( Hero hero ) {
		DewVial vial = hero.belongings.getItem( DewVial.class );
		if (vial != null && vial.volume >= 500) {
			vial.execute( hero );
			vial.volume -= 500;
			hero.sprite.emitter().start( ShaftParticle.FACTORY, 0.2f, 3 );
			
			GLog.w( TXT_AUTO_DRINK );
		}
	}
	
	private static final Glowing WHITE = new Glowing( 0xFFFFCC );
	
	@Override
	public Glowing glowing() {
		return isFull() ? WHITE : null;
	}
	
	@Override
	public String status() {
		return Utils.format( TXT_STATUS, volume, MAX_VOLUME );
	}
	
	@Override
	public String info() {
		return 
			"You can store excess dew in this vessel for drinking it later. " +
			"If the vial is more than 500, in a moment of deadly peril 500 dew drops will be " +
			"consumed automatically.";
	}
	
	@Override
	public String toString() {
		return super.toString() + " (" + status() +  ")" ;
	}
}
