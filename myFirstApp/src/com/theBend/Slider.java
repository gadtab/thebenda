package com.theBend;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SlidingDrawer;

/**
 * Created by 011938719 on 15/07/13.
 */
public class Slider extends Activity implements View.OnClickListener, CheckBox.OnCheckedChangeListener, SlidingDrawer.OnDrawerOpenListener
{
    Button b1, b2, b3, b4;
    CheckBox cb;
    SlidingDrawer sd;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sliding);
        initialize();

        sd.setOnDrawerOpenListener(this);
    }

    private void initialize()
    {
        b1 = (Button) findViewById(R.id.bBut1);
        b2 = (Button) findViewById(R.id.bBut2);
        b3 = (Button) findViewById(R.id.bBut3);
        b4 = (Button) findViewById(R.id.bBut4);
        cb = (CheckBox) findViewById(R.id.cbSlidable);
        sd = (SlidingDrawer) findViewById(R.id.slidingDrawer);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        cb.setOnCheckedChangeListener(this);

    }

    @Override
    public void onClick(View view)
    {
        switch(view.getId())
        {
            case R.id.bBut1:
                sd.open();
                break;
            case R.id.bBut2:

                break;
            case R.id.bBut3:
                sd.toggle();
                break;
            case R.id.bBut4:
                sd.close();
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b)
    {
        if (compoundButton.isChecked())
        {
            sd.lock();
        }
        else
        {
            sd.unlock();
        }
    }

    @Override
    public void onDrawerOpened()
    {
        MediaPlayer mp = MediaPlayer.create(this, R.raw.explosion);
        mp.start();
    }
}
