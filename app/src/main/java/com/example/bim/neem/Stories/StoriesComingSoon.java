package com.example.bim.neem.Stories;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.bim.neem.R;

public class StoriesComingSoon extends AppCompatActivity {

    TextView toolbar_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loyalty_program);
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setText("Stories");
    }
}
