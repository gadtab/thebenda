package com.theBend;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by 011938719 on 09/07/13.
 */
public class data extends Activity implements View.OnClickListener
{
    Button start;
    Button startFor;
    EditText sendET;
    TextView gotAnswer;
    String bread;
    Bundle basket;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get);
        initialize();
    }

    private void initialize()
    {
        start = (Button) findViewById(R.id.bSA);
        startFor = (Button) findViewById(R.id.bSAFR);
        sendET = (EditText) findViewById(R.id.etSend);
        gotAnswer = (TextView) findViewById(R.id.tvGot);

        start.setOnClickListener(this);
        startFor.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        switch(view.getId())
        {
            case R.id.bSA:
                setBasket();
                Intent a = new Intent(data.this, OpenedClass.class);
                a.putExtras(basket);
                startActivity(a);
                break;
            case R.id.bSAFR:
                Intent i = new Intent(data.this, OpenedClass.class);
                i.putExtras(basket);
                startActivityForResult(i, 0);
                break;
        }
    }

    private void setBasket()
    {
        bread = sendET.getText().toString();
        basket = new Bundle();

        basket.putString("key", bread);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK)
        {
            Bundle basket = data.getExtras();
            String s = basket.getString("answer");
            gotAnswer.setText(s);
        }
    }
}
