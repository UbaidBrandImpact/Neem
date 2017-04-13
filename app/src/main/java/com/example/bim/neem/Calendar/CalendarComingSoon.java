package com.example.bim.neem.Calendar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.bim.neem.R;

public class CalendarComingSoon extends AppCompatActivity {

    TextView toolbar_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loyalty_program);
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setText("Calendar");
    }
}
