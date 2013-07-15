package com.theBend;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

/**
 * Created by 011938719 on 15/07/13.
 */
public class TestCamera extends Activity implements View.OnClickListener
{
    Button takePic, setWP;
    ImageView picFrame;
    int actionCode = 100;
    Bitmap bmp;
    TextView log;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);
        takePic = (Button) findViewById(R.id.bGetPic);
        takePic.setOnClickListener(this);
        setWP = (Button) findViewById(R.id.bSetWP);
        setWP.setOnClickListener(this);
        picFrame = (ImageView) findViewById(R.id.ivCameraPic);
        picFrame.setImageBitmap(((BitmapDrawable)getApplicationContext().getWallpaper()).getBitmap());
        log = (TextView) findViewById(R.id.tvSetWPResult);
        log.setText("");
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.bGetPic:
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePictureIntent, actionCode);
                break;
            case R.id.bSetWP:
                try
                {
                    getApplicationContext().setWallpaper(bmp);
                    log.setText("Set the Wallpaper successfully");
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                    log.setText(e.getMessage());
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK)
        {
            Bundle extras = data.getExtras();
            bmp = (Bitmap) extras.get("data");
            picFrame.setImageBitmap(bmp);
        }
    }
}
