package com.theBend;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by 011938719 on 11/07/13.
 */
public class GFXSurface extends Activity implements View.OnTouchListener
{
    MyBendaSurface ourSurfaceView;
    float x, y, sX, sY, fX, fY, dX, dY, aniX, aniY, scaleX, scaleY;
    Bitmap test, plus;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        ourSurfaceView = new MyBendaSurface(this);
        ourSurfaceView.setOnTouchListener(this);
        x = 0;
        y = 0;
        sX = 0;
        sY = 0;
        fX = 0;
        fY = 0;
        dX = dY = aniX = aniY = scaleX = scaleY = 0;
        setContentView(ourSurfaceView);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        ourSurfaceView.pause();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        ourSurfaceView.resume();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent)
    {
        //Setting FPS for the graphics 1000/<refresh rate>
        try
        {
            Thread.sleep(50);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        x = motionEvent.getX();
        y = motionEvent.getY();

        switch(motionEvent.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                sX = motionEvent.getX();
                sY = motionEvent.getY();
                dX = dY = aniX = aniY = scaleX = scaleY = fX = fY= 0;
                break;
            case MotionEvent.ACTION_UP:
                fX = motionEvent.getX();
                fY = motionEvent.getY();
                dX = fX - sX;
                dY = fY - sY;
                scaleX = dX / 30;
                scaleY = dY / 30;
                x = 0;
                y = 0;
                break;
        }

        return true;
    }

    public class MyBendaSurface extends SurfaceView implements Runnable
    {
        SurfaceHolder ourHolder;
        Thread ourThread = null;
        boolean isRunning = false;

        public MyBendaSurface(Context context)
        {
            super(context);
            ourHolder = getHolder();
        }

        public void pause()
        {
            isRunning = false;
            while (true)
            {
                try
                {
                    ourThread.join();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    break;
                }
            }

            ourThread = null;
        }

        public void resume()
        {
            isRunning = true;
            ourThread = new Thread(this);
            ourThread.start();
        }

        @Override
        public void run()
        {
            while(isRunning)
            {
                if (!ourHolder.getSurface().isValid())
                {
                    continue;
                }

                Canvas canvas = ourHolder.lockCanvas();
                canvas.drawRGB(2, 2, 150);

                test = BitmapFactory.decodeResource(getResources(), R.drawable.icon);
                plus = BitmapFactory.decodeResource(getResources(), R.drawable.plus_highlighted);

                if ((x != 0) && (y != 0))
                {
                    canvas.drawBitmap(test, x - test.getWidth()/2, y - test.getHeight()/2, null);
                }

                if ((sX != 0) && (sY != 0))
                {
                    canvas.drawBitmap(plus, sX - plus.getWidth()/2, sY - plus.getHeight()/2, null);
                }

                if ((fX != 0) && (fY != 0))
                {
                    canvas.drawBitmap(test, fX - test.getWidth()/2 - aniX, fY - test.getHeight()/2 - aniY, null);
                    canvas.drawBitmap(plus, fX - plus.getWidth()/2, fY - plus.getHeight()/2, null);
                }

                aniX += scaleX;
                aniY += scaleY;

                ourHolder.unlockCanvasAndPost(canvas);
            }
        }
    }
}
