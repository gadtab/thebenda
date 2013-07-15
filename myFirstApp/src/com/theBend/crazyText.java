package com.theBend;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Random;

/**
 * Created by 011938719 on 08/07/13.
 */
public class crazyText extends Activity implements View.OnClickListener
{
    Button chkCmd;
    ToggleButton passTog;
    EditText input;
    TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crazy_text);

        initViews();

        chkCmd.setOnClickListener(this);
        passTog.setOnClickListener(this);
    }

    private void initViews()
    {
        chkCmd = (Button) findViewById(R.id.bResults);
        passTog = (ToggleButton) findViewById(R.id.tbPassword);
        input = (EditText) findViewById(R.id.etCommands);
        display = (TextView) findViewById(R.id.tvResults);
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.bResults:
                String check = input.getText().toString();
                Random rand = new Random();

                display.setText(check);
                if (check.equalsIgnoreCase("left"))
                {
                    display.setGravity(Gravity.LEFT);
                } else if (check.equalsIgnoreCase("right"))
                {
                    display.setGravity(Gravity.RIGHT);
                } else if (check.equalsIgnoreCase("center"))
                {
                    display.setGravity(Gravity.CENTER);
                } else if (check.equalsIgnoreCase("blue"))
                {
                    display.setTextColor(Color.BLUE);
                }else if (check.equalsIgnoreCase("WTF"))
                {
                    display.setText("אבשלום!!!!");
                    display.setTextColor(Color.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
                    display.setTextSize(rand.nextInt(60));
                    switch(rand.nextInt(3))
                    {
                        case 0:
                            display.setGravity(Gravity.CENTER);
                            break;
                        case 1:
                            display.setGravity(Gravity.LEFT);
                            break;
                        case 2:
                            display.setGravity(Gravity.RIGHT);
                            break;
                        default:
                            display.setGravity(Gravity.CENTER);
                            break;
                    }

                } else
                {
                    display.setText("invalid");
                    display.setTextColor(Color.WHITE);
                    display.setGravity(Gravity.CENTER);
                }
                break;
            case R.id.tbPassword:
                if (passTog.isChecked())
                {
                    input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                else
                {
                    input.setInputType(InputType.TYPE_CLASS_TEXT);
                }
                break;
            default:
                break;
        }
    }
}
