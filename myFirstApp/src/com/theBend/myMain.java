package com.theBend;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class myMain extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_intro);
        
        Thread introTimer = new Thread()
        {
        	public void run()
        	{
        		try
        		{
        			int time = 0;
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
        		}
        	}
        	
        };
        
        introTimer.start();
    }
    
    
    
    
}