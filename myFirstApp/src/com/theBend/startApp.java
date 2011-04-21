package com.theBend;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class startApp extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		Button bBenda = (Button) findViewById(R.id.bBenda);
		
		bBenda.setOnClickListener(new View.OnClickListener() 
		{			
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
	
}
