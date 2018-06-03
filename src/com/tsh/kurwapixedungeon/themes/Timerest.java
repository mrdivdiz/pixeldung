package com.tsh.kurwapixedungeon.themes;

import com.tsh.kurwapixedungeon.KurwaDungeon;
import com.tsh.kurwapixedungeon.scenes.TitleScene;
import com.tsh.noosa.Game;

import android.os.CountDownTimer;

public class Timerest extends CountDownTimer
{

    public Timerest(long millisInFuture, long countDownInterval) 
    {
          super(millisInFuture, countDownInterval);
    }

    @Override
    public void onFinish() 
    {
        
    	Game.switchScene(TitleScene.class);
    }

    public void onTick(long millisUntilFinished) 
    {

    }
}


