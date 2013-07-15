package com.theBend;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by 011938719 on 11/07/13.
 */
public class Prefs extends PreferenceActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefs);
    }
}
