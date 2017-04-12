package com.example.bim.neem.Products;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.bim.neem.Models.Product;
import com.example.bim.neem.adapters.ProductsAdapter;
import com.example.bim.neem.R;
import com.example.bim.neem.Utils.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.List;

public class ProductsActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    private List<Product> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ProductsAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        mToolbar = (Toolbar)findViewById(R.id.appBar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new ProductsAdapter(movieList);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
       // recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Product movie = movieList.get(position);
                Toast.makeText(getApplicationContext(), movie.getTitle() + " is selected!", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(ProductsActivity.this,ProductDetailActivity.class);
                startActivity(i);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        prepareMovieData();

    }
    private void prepareMovieData() {
        Product movie = new Product("Mad Max: Fury Road", "Action & Adventure", "2015");
        movieList.add(movie);

        movie = new Product("Inside Out", "Animation, Kids & Family", "2015");
        movieList.add(movie);

        movie = new Product("Star Wars: Episode VII - The Force Awakens", "Action", "2015");
        movieList.add(movie);

        movie = new Product("Shaun the Sheep", "Animation", "2015");
        movieList.add(movie);

        movie = new Product("The Martian", "Science Fiction & Fantasy", "2015");
        movieList.add(movie);

        movie = new Product("Mission: Impossible Rogue Nation", "Action", "2015");
        movieList.add(movie);

        movie = new Product("Up", "Animation", "2009");
        movieList.add(movie);

        movie = new Product("Star Trek", "Science Fiction", "2009");
        movieList.add(movie);

        movie = new Product("The LEGO Product", "Animation", "2014");
        movieList.add(movie);

        movie = new Product("Iron Man", "Action & Adventure", "2008");
        movieList.add(movie);

        movie = new Product("Aliens", "Science Fiction", "1986");
        movieList.add(movie);

        movie = new Product("Chicken Run", "Animation", "2000");
        movieList.add(movie);

        movie = new Product("Back to the Future", "Science Fiction", "1985");
        movieList.add(movie);

        movie = new Product("Raiders of the Lost Ark", "Action & Adventure", "1981");
        movieList.add(movie);

        movie = new Product("Goldfinger", "Action & Adventure", "1965");
        movieList.add(movie);

        movie = new Product("Guardians of the Galaxy", "Science Fiction & Fantasy", "2014");
        movieList.add(movie);

        mAdapter.notifyDataSetChanged();
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
