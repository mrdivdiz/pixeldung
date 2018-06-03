package com.tsh.kurwapixedungeon.scenes;

import com.tsh.kurwapixedungeon.Badges;
import com.tsh.kurwapixedungeon.Chrome;
import com.tsh.kurwapixedungeon.KurwaDungeon;
import com.tsh.kurwapixedungeon.Rankings;
import com.tsh.kurwapixedungeon.ui.Archs;
import com.tsh.kurwapixedungeon.ui.RedButton;
import com.tsh.kurwapixedungeon.ui.ScrollPane;
import com.tsh.kurwapixedungeon.ui.Window;
import com.tsh.noosa.BitmapTextMultiline;
import com.tsh.noosa.Camera;
import com.tsh.noosa.Game;
import com.tsh.noosa.NinePatch;
import com.tsh.noosa.ui.Component;

//TODO: update this class with relevant info as new versions come out.
public class WelcomeScene extends PixelScene {

	
	
	
	public static String Serverconn = " ";
	private static final String TTL_Welcome = "Look here!";

	private static final String TTL_Update = "  Version: 0.8.25REV1";

	private static final String TTL_Future = "Here's some changes and tips.";

	private static final String TXT_Welcome = "	Kurrwa Pixel Dungeon\n\n"
			+  "\n\n\n\n"
			+"This is a version of Pixel Dungeon from Watabou with blackjack & bitches.\n\n"
			+ "Included some of additions from Sprouted and Your versions.\n\n"
			+ "This version was adapted for those who love to grind, level up, and collect loot, like Sprouted.\n\n"
			+ "This version includes DJ S3RL's music.\n\n"
			+"\n\n"

			+"Pixel Dungeon differences:\n\n"
			+"Much larger levels (x16) creating a different game play and strategy experience.\n\n"
			+"Mobs drop monster meat to facilitate longer and more in-depth exploration of the larger levels.\n\n"
			+"Boss fights have been completely reworked to be more intense and challenging requiring completely new tactics.\n\n"
			+"New levels include unique enemies, items and rewards.\n\n"
			+"\n\n"
			+"To unlock themes, get achivemens and search for secrets.\n\n"

			+"Many other tweaks and additions have been included!\n\n";


	private static final String TXT_Update = 
			"Version 0.8.25:"
			+ "\n - A lot of fixes with base;Keep testing it."
			+ "\n - Translation fixes."
			+ "\n"
			+ "\n Version 0.8.24:"
			+ "\n - Fixed skills issues."
			+ ""
			+ "\n\n"
			+ "Version 0.8.23:"
			+ "\n - Now you can be be more bad rhytmed.(Dick figures)"
			+ "\n - Started working on backup or level delete functions."
			+ "\n - Mobs are more aggresive."
			+ "\n - Fixed skills."
			+ "\n - Some text fixes / -_- /"
			+ "\n - Fixed some graphic bugs."
			+ "\n - Base is off for now, debug in progress."
			+ "\n - Added Bite"
			+ "\n - Added Watermelon"
			+ "\n - Added Bite"
			+ "\n - Added Vine"
			+ "\n - Robots!Be careful!"
			+ "\n - Added /dev/null/(101?)"
			+ "\n - Added H20(OH)2 (Very strange, isn`t it?)"
			+ "\n\n"
			+ "Version 0.8.22:"
			+ "\n - A little more weapons like shovel, grenade, etc."
			+ "\n - New rooms with unique mobs!"
			+ "\n - New quests that require clever minds!"
			+ "\n Graphic bug fixes."
			+ "\n\n"
			+ "Version 0.8.21:"
			+ "\n - Added many items for base."
			+ "\n - Added some new mobs."
			+ "\n - Added base."
			+ "\n Base is a place where you can store, repair and improve your weapon and armor, safe grow grass and brew potions, train your skills or sleep for regeneration."
			+ "Base require some resources that can be founded in trash uncraft.Water to regenerate, oxygen to breathe, energy and resources to build, keep and improve your machines, or to create new levels/pets/etc."
			+ "\n - Added mana and mana pot to restore it."
			+ "\n Mana required for wands."
			+"Version 0.8.20:"
			+ "\n - Levels now only x9 larger"
			+ "\n - More mobs spawnings"
			+ "\n - Removed some bugs"
			+ "\n - Potion if strengh now spawn 60% less"
			+ "\n - Added PPSH - shooting weapon."
			+ "\n - Potion of Experience now gives you 75 XP anyway."
			+ "\n - Added nails, nail bombs"
			  +"\n\n Version 0.8.12 put much more new into the game:\n"
			+ "Changelog:"
			+ "\n - Added Light Armor, Cuirass."
			+ "\n - Mobs have MUCH more health (as usually 4x more)"
			+ "\n - Rebalanced item generation"
			+ "\n - New Crafting system, new kind of items - trash - that can be used to make armor, scrolls, potions and special weapons and armor."
			+ "\n - Bosses are healther more than 10x!"
			+ "\n - Unique items!"
			+ "\n - New food: Blin, Cooked hamburger, Semka."
			+ "\n - New levels!"
			+ "\n - Game story has reworked to its final view!"
			+ "\n - Added some Easter eggs."
			+ "In-game guide book."
			//+ "\n - Added new parameters of hero such as Mana and Dewcharge."
			+ "\n - Added new sounds"
			+ "\n - Added WEEDMODE for everyone those who like MLG montages."
			+ "\n - Added new king of dew."
			+ "\n - Some problems with UI has been repaired."
			+ "\n - Added more items to levels."
			+ "\n - Lloyd's Beacon drops from Good with 100% chance."
			+ "\n - New drop from mobs."
			+ "\n - Potion of Mending."
			+ "\n - And a bit more of melee weapons (Sharp knuckles)."
			+ "\n\n"
			+ "\n version 0.8 - additional content debug \n"
			+ "\n till 0.5.7 - 0.7.9 there is no changes thad enabled in the game.\n"
			+ "Version 0.5.6 adds Themes.\n\n"
			+"\n\n"	
			+"Sprouted Pixel Dungeon differences:\n\n"
			+"Much larger levels (x16) creating a different game play and strategy experience.\n\n"
			+"\n\n"
			
			+"New original items include:\n\n"
			+"Beer, Pizza, Hamburger etc.\n\n"
			+"New weapons, mobs and common loot\n\n"
	        +"Themes changes view of UI & music\n\n";

	private static final String TXT_Future = "It seems that your current saves are from a future version of Kurrwa PD!\n\n"
			+ "Either you're messing around with older versions of the app, or something has gone buggy.\n\n"
			+ "Regardless, tread with caution! Your saves may contain things which don't exist in this version, "
			+ "this could cause some very weird errors to occur.";

	private static final String LNK = "https://play.google.com/store/apps/details?id=com.watabou.pixeldungeon";

	@Override
	public void create() {
		super.create();

		final int gameversion = 10;

		BitmapTextMultiline title;
		BitmapTextMultiline text;

		if (gameversion == 10) {

			text = createMultiline(TXT_Welcome, 8);
			title = createMultiline(TTL_Welcome, 16);

		

			text = createMultiline(TXT_Update, 6);
			title = createMultiline(TTL_Update, 9);

		} //else {

			//text = createMultiline(TXT_Future, 8);
			//title = createMultiline(TTL_Future, 16);

		//}

		int w = Camera.main.width;
		int h = Camera.main.height;

		int pw = w - 10;
		int ph = h - 50;

		title.maxWidth = pw;
		title.measure();
		title.hardlight(Window.TITLE_COLOR);

		title.x = align((w - title.width()) / 2);
		title.y = align(8);
		add(title);

		NinePatch panel = Chrome.get(Chrome.Type.WINDOW);
		panel.size(pw, ph);
		panel.x = (w - pw) / 2;
		panel.y = (h - ph) / 2;
		add(panel);

		ScrollPane list = new ScrollPane(new Component());
		add(list);
		list.setRect(panel.x + panel.marginLeft(), panel.y + panel.marginTop(),
				panel.innerWidth(), panel.innerHeight());
		list.scrollTo(0, 0);

		Component content = list.content();
		content.clear();

		text.maxWidth = (int) panel.innerWidth();
		text.measure();

		content.add(text);

		content.setSize(panel.innerWidth(), text.height());

		RedButton okay = new RedButton("Got it!") {
			@Override
			protected void onClick() {

				if (gameversion <= 32) {
					// removes all bags bought badge from pre-0.2.4 saves.
					Badges.disown(Badges.Badge.ALL_BAGS_BOUGHT);
					Badges.saveGlobal();

					// imports new ranking data for pre-0.2.3 saves.
					if (gameversion <= 29) {
						Rankings.INSTANCE.load();
						Rankings.INSTANCE.save();
					}
				}

				KurwaDungeon.version( Game.version);
				Game.switchScene(TitleScene.class);
			}
		};

		/*
		 * okay.setRect(text.x, text.y + text.height() + 5, 55, 18); add(okay);
		 * 
		 * RedButton changes = new RedButton("Changes") {
		 * 
		 * @Override protected void onClick() { parent.add(new WndChanges()); }
		 * };
		 * 
		 * changes.setRect(text.x + 65, text.y + text.height() + 5, 55, 18);
		 * add(changes);
		 */

		okay.setRect((w - pw) / 2, h - 22, pw, 18);
		add(okay);

		Archs archs = new Archs();
		archs.setSize(Camera.main.width, Camera.main.height);
		addToBack(archs);

		fadeIn();
	}
}
