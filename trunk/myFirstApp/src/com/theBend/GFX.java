package com.theBend;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;

/**
 * Created by 011938719 on 11/07/13.
 */
public class GFX extends Activity
{
    MyBenda ourView;
    PowerManager.WakeLock wakeLock;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //Wake Lock
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, "whatever");
        wakeLock.acquire();

        super.onCreate(savedInstanceState);
        ourView = new MyBenda(this);
        setContentView(ourView);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        wakeLock.release();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        wakeLock.acquire();
    }
}
