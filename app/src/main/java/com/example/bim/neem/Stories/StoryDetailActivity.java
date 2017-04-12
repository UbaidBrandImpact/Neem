package com.example.bim.neem.Stories;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.bim.neem.Models.Product;
import com.example.bim.neem.R;
import com.example.bim.neem.adapters.ProductsAdapter;

import java.util.ArrayList;
import java.util.List;

public class StoryDetailActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    private List<Product> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ProductsAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_detail);
        mToolbar = (Toolbar)findViewById(R.id.appBar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }
}
