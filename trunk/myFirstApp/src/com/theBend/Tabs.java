package com.theBend;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;

/**
 * Created by 011938719 on 15/07/13.
 */
public class Tabs extends Activity implements View.OnClickListener
{
    TabHost th;
    TabHost.TabSpec specs;
    Button newTab, bStart, bStop;
    TextView showResults;
    long start = 0, stop;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs);

        th = (TabHost) findViewById(R.id.tabHost);
        newTab = (Button) findViewById(R.id.bAddTab);
        newTab.setOnClickListener(this);
        bStart = (Button) findViewById(R.id.bStartWatch);
        bStart.setOnClickListener(this);
        bStop = (Button) findViewById(R.id.bStopWatch);
        bStop.setOnClickListener(this);

        showResults = (TextView) findViewById(R.id.tvShowResults);

        th.setup();

        specs = th.newTabSpec("tag1");
        specs.setContent(R.id.tab1);
        specs.setIndicator("StopWatch");
        th.addTab(specs);

        specs = th.newTabSpec("tag2");
        specs.setContent(R.id.tab2);
        specs.setIndicator("Tab 2");
        th.addTab(specs);

        specs = th.newTabSpec("tag3");
        specs.setContent(R.id.tab3);
        specs.setIndicator("Add a tab");
        th.addTab(specs);
    }

    @Override
    public void onClick(View view)
    {
        switch(view.getId())
        {
            case R.id.bAddTab:
                TabHost.TabSpec ourSpec = th.newTabSpec("tag1");
                ourSpec.setContent(new TabHost.TabContentFactory()
                {
                    @Override
                    public View createTabContent(String s)
                    {
                        TextView text = new TextView(Tabs.this);
                        text.setText("You've created a new Tab!");
                        return (text);
                    }
                });

                ourSpec.setIndicator("New");
                th.addTab(ourSpec);
                break;
            case R.id.bStartWatch:
                start = System.currentTimeMillis();
                break;
            case R.id.bStopWatch:
                stop = System.currentTimeMillis();

                if (start != 0)
                {
                    long result = stop - start;
                    int millis = (int) result;
                    int seconds = millis / 1000;
                    int minutes = seconds / 60;
                    millis %= 100;
                    seconds %= 60;

                    String showText = String.format("%d:%02d:%02d", minutes, seconds, millis);
                    showResults.setText(showText);
//                    start = 0;
                }
                break;
        }
    }
}
