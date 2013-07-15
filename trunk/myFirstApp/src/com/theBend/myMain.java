package com.theBend;

import android.*;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class myMain extends Activity {
    /** Called when the activity is first created. */
    public MediaPlayer mp;
    boolean music;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_intro);

        mp = MediaPlayer.create(this, R.raw.intro);
        SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        music = getPrefs.getBoolean("checkbox", true);

        Thread introTimer = new Thread()
        {
        	public void run()
        	{
        		try
        		{
        			int time = 0;
                    if (music)
                    {
                        mp.start();
                    }
        			while (time < 5000)
        			{
        				sleep(100);
        				time += 100;
        			}
        			startActivity(new Intent("com.thebend.STARTSCREEN"));
        		} catch (InterruptedException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
        		finally
        		{
        			finish();
                    mp.stop();
        		}
        	}
        	
        };
        
        introTimer.start();
    }
}