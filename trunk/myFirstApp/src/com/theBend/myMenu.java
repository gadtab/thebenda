package com.theBend;

import android.R;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * Created by 011938719 on 08/07/13.
 */
public class myMenu extends ListActivity
{
    public String classes[] = {"startApp", "crazyText", "Email", "TestCamera", "data", "GFX", "GFXSurface", "SoundStuff", "Slider", "Tabs"};

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //makes the window to be full screen and no title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setListAdapter(new ArrayAdapter<String>(myMenu.this, R.layout.simple_list_item_1, classes));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu theMenu)
    {
        super.onCreateOptionsMenu(theMenu);
        MenuInflater blowUp = getMenuInflater();
        blowUp.inflate(com.theBend.R.menu.cool_menu, (Menu) theMenu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case com.theBend.R.id.aboutUs:
                Intent i = new Intent("com.thebend.ABOUT");
                startActivity(i);
                break;
            case com.theBend.R.id.preferences:
                Intent p = new Intent("com.thebend.PREFS");
                startActivity(p);
                break;
            case com.theBend.R.id.exit:
                finish();
                break;
        }

        return false;
    }

    @Override
    public void onListItemClick(ListView lv, View v, int position, long id)
    {
        super.onListItemClick(lv, v, position, id);

        try
        {
            Class currentClass = Class.forName("com.theBend." +  classes[position]);
            Intent currentIntent = new Intent(myMenu.this, currentClass);
            startActivity(currentIntent);
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
