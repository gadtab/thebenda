package com.theBend;

import android.R;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import android.widget.ListView;


/**
 * Created by 011938719 on 08/07/13.
 */
public class menu extends ListActivity
{
    public String classes[] = {"", "startApp", "crazyText", "Email", "camera"};

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(menu.this, R.layout.simple_list_item_1, classes));
    }

    @Override
    public void onListItemClick(ListView lv, View v, int position, long id)
    {
        super.onListItemClick(lv, v, position, id);

        try
        {
            Class currentClass = Class.forName("com.theBend." +  classes[position]);
            Intent currentIntent = new Intent(menu.this, currentClass);
            startActivity(currentIntent);
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
