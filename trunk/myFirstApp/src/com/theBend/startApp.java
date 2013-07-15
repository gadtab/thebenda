package com.theBend;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.NotificationManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class startApp extends Activity implements View.OnClickListener
{
    int total = 0;
    TextView tvTotal, tvHello;
    EditText tTextBox;

    @Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

        tvTotal = (TextView) findViewById(R.id.tvTotal);
        tvHello = (TextView) findViewById(R.id.tvHello);
        tvTotal.setText(getString(R.string.total) + total);

        Button bBenda = (Button) findViewById(R.id.bBenda);
		bBenda.setOnClickListener(this);
        Button bAdd = (Button) findViewById(R.id.bAdd);
        bAdd.setOnClickListener(this);
        Button bSub = (Button) findViewById(R.id.bSub);
        bSub.setOnClickListener(this);

        tTextBox = (EditText) findViewById(R.id.etNotify);
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
            case R.id.bBenda:

                finish();
                break;
            case R.id.bAdd:
                total ++;
                tvTotal.setText(getString(R.string.total) + total);
                break;
            case R.id.bSub:
                total --;
                tvTotal.setText(getString(R.string.total) + total);
                break;
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void createNotification(View view) {
        // Prepare intent which is triggered if the
        // notification is selected
        //Called from layout XML...
        Intent intent = new Intent(this, startApp.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);
        Intent intent2 = new Intent(this, GFX.class);
        PendingIntent pIntent2 = PendingIntent.getActivity(this, 0, intent2, 0);
        Intent intent3 = new Intent(this, Tabs.class);
        PendingIntent pIntent3 = PendingIntent.getActivity(this, 0, intent3, 0);

        // Build notification
        // Actions are just fake
        Notification noti = new Notification.Builder(this)
                .setContentTitle("My awesome notification")
                .setContentText(tvHello.getText() + "\n" + tTextBox.getText())
                .setSmallIcon(R.drawable.icon)
                .setContentIntent(pIntent)
                .setSubText("Subtext " + tTextBox.getText())
                .setTicker("Ticker: " + tTextBox.getText())
                .addAction(R.drawable.icon, "Start App", pIntent)
                .addAction(R.drawable.plus, "Graphics", pIntent2)
                .addAction(R.drawable.plus_selected, "Tabs", pIntent3)
                .build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // Hide the notification after its selected
        noti.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, noti);

        finish();

    }
}
