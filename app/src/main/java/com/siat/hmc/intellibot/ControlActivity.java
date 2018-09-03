package com.siat.hmc.intellibot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class ControlActivity extends AppCompatActivity implements View.OnClickListener{

    View lastClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        getSupportActionBar().setTitle(R.string.control);

        Button btn1 = (Button) findViewById(R.id.button1);
        btn1.setOnClickListener(this);
        Button btn2 = (Button) findViewById(R.id.button2);
        btn2.setOnClickListener(this);
        Button btn3 = (Button) findViewById(R.id.button3);
        btn3.setOnClickListener(this);

        lastClick = btn1;
        lastClick.setSelected(true);

    }

    @Override
    public void onClick(View v) {
        if ( lastClick != v ) {
            lastClick.setSelected(false);
            lastClick = v;
            v.setSelected(true);
        }
    }
}
