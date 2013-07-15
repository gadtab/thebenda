package com.theBend;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.View;

/**
 * Created by 011938719 on 11/07/13.
 */
public class MyBenda extends View
{
    Bitmap dude;
    float changingY;
    Typeface font;

    public MyBenda(Context context)
    {
        super(context);
        dude = BitmapFactory.decodeResource(getResources(), R.drawable.dude);
        changingY = 0;
        font = Typeface.createFromAsset(context.getAssets(), "G-Unit.TTF");
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        canvas.drawColor(Color.GRAY);

        Rect middleRect = new Rect();
        middleRect.set(0, 400, canvas.getWidth(), 550);
        Paint ourBlue = new Paint();
        ourBlue.setColor(Color.BLUE);
        canvas.drawRect(middleRect, ourBlue);

        Paint textPaint = new Paint();
        textPaint.setARGB(50, 194, 40, 185);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(50);
        textPaint.setTypeface(font);

        canvas.drawText("Dead man walking", canvas.getWidth()/2, 200, textPaint);

        canvas.drawBitmap(dude, (canvas.getWidth()/2 - dude.getWidth()/2), changingY, null);
        if (changingY < canvas.getHeight())
        {
            changingY += 10;
        }
        else
        {
            changingY = 0;
        }


        invalidate();
    }
}
