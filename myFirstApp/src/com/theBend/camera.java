package com.theBend;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by 011938719 on 08/07/13.
 */
public class camera extends Activity implements View.OnClickListener
{
    ImageButton ib;
    Button b;
    ImageView iv;
    Intent i;
    final static int cameraData = 8888;
    Bitmap bmp, cameraResult;
    TextView log;
    String logText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo);
        initializeVars();
    }

    private void initializeVars() {
        // TODO Auto-generated method stub
        ib = (ImageButton) findViewById(R.id.ibTakePic);
        b = (Button) findViewById(R.id.bSetWallPaper);
        iv = (ImageView) findViewById(R.id.ivReturnedPicture);
        log = (TextView) findViewById(R.id.tvCameraLog);

        bmp = ((BitmapDrawable)getApplicationContext().getWallpaper()).getBitmap();
        iv.setImageBitmap(bmp);

        InputStream is = getResources().openRawResource(R.drawable.icon);
        ib.setImageBitmap(BitmapFactory.decodeStream(is));

        b.setOnClickListener(this);
        ib.setOnClickListener(this);

        setLog("Finished initialization...");
    }

    private void setLog(String text)
    {
        logText += text + "\n";
        log.setText(logText);
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.bSetWallPaper:
                try
                {
                    getApplicationContext().setWallpaper(bmp);
                    setLog("Got the current wallpaper.");
                }
                catch (IOException e)
                {
                    setLog("Error in getting current wallpaper.");
                    e.printStackTrace();
                }
                break;
            case R.id.ibTakePic:
                i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                setLog("Calling Camera.");
                startActivityForResult(i, cameraData);
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        setLog("Got Result from camera:" + resultCode);;

        if ((requestCode == cameraData) && (resultCode == RESULT_OK))
        {
            cameraResult = (Bitmap) data.getExtras().get("data");
            setLog("Setting new wallpaper");
            iv.setImageBitmap(cameraResult);
        }
        else
        {
            setLog("Error on result");
        }
    }


}
