package com.example.bim.neem.Stories;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.example.bim.neem.Models.Story;
import com.example.bim.neem.adapters.CustomPagerAdapter;
import com.example.bim.neem.R;
import java.util.ArrayList;
import java.util.List;

public class StoriesActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    private List<Story> stories = new ArrayList<>();
    private ViewPager viewpager;
    private CustomPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stories);
        mToolbar = (Toolbar)findViewById(R.id.appBar);
        prepareMovieData();
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        mAdapter = new CustomPagerAdapter(stories,this);
        viewpager.setAdapter(mAdapter);
    }
    private void prepareMovieData() {
        Story movie = new Story("Mad Max: Fury Road", "Action & Adventure", "2015");
        stories.add(movie);
        movie = new Story("Mad Max:  Road", "Action & Adventure", "2015");
        stories.add(movie);
        movie = new Story("Mad : Fury Road", "Action & Adventure", "2015");
        stories.add(movie);
        movie = new Story(" : Fury Road", "Action & Adventure", "2015");
        stories.add(movie);
    }
//    private void prepareMovieData() {
//        Product movie = new Product("Mad Max: Fury Road", "Action & Adventure", "2015");
//        movieList.add(movie);
//
//        movie = new Product("Inside Out", "Animation, Kids & Family", "2015");
//        movieList.add(movie);
//
//        movie = new Product("Star Wars: Episode VII - The Force Awakens", "Action", "2015");
//        movieList.add(movie);
//
//        movie = new Product("Shaun the Sheep", "Animation", "2015");
//        movieList.add(movie);
//
//        movie = new Product("The Martian", "Science Fiction & Fantasy", "2015");
//        movieList.add(movie);
//
//        movie = new Product("Mission: Impossible Rogue Nation", "Action", "2015");
//        movieList.add(movie);
//
//        movie = new Product("Up", "Animation", "2009");
//        movieList.add(movie);
//
//        movie = new Product("Star Trek", "Science Fiction", "2009");
//        movieList.add(movie);
//
//        movie = new Product("The LEGO Product", "Animation", "2014");
//        movieList.add(movie);
//
//        movie = new Product("Iron Man", "Action & Adventure", "2008");
//        movieList.add(movie);
//
//        movie = new Product("Aliens", "Science Fiction", "1986");
//        movieList.add(movie);
//
//        movie = new Product("Chicken Run", "Animation", "2000");
//        movieList.add(movie);
//
//        movie = new Product("Back to the Future", "Science Fiction", "1985");
//        movieList.add(movie);
//
//        movie = new Product("Raiders of the Lost Ark", "Action & Adventure", "1981");
//        movieList.add(movie);
//
//        movie = new Product("Goldfinger", "Action & Adventure", "1965");
//        movieList.add(movie);
//
//        movie = new Product("Guardians of the Galaxy", "Science Fiction & Fantasy", "2014");
//        movieList.add(movie);
//
//        mAdapter.notifyDataSetChanged();
//    }

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
