package com.example.bim.neem.Home;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bim.neem.AboutUs.AboutUsActivity;
import com.example.bim.neem.Calendar.CalendarActivity;
import com.example.bim.neem.Products.ProductsActivity;
import com.example.bim.neem.Profile.ProfileActivity;
import com.example.bim.neem.R;
import com.example.bim.neem.Stories.StoriesActivity;
import com.example.bim.neem.Video.VideoActivity;
import com.example.bim.neem.adapters.DrawerAdapter;
import com.example.bim.neem.Models.DrawerItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private ArrayList<DrawerItem> mDrawerItemList;

    private DrawerLayout mDrawerLayout;

    private ActionBarDrawerToggle mDrawerToggle;

    RelativeLayout rl_products,rl_videos;
    ImageView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar)findViewById(R.id.appBar);
        rl_products = (RelativeLayout) findViewById(R.id.rl_products);
        rl_videos = (RelativeLayout) findViewById(R.id.rl_videos);
        calendar = (ImageView) findViewById(R.id.calendar);
        calendar.setOnClickListener(this);
        rl_products.setOnClickListener(this);
        rl_videos.setOnClickListener(this);

    //    setSupportActionBar(mToolbar);


        mDrawerItemList = new ArrayList<DrawerItem>();
        DrawerItem item = new DrawerItem();

        item.setTitle("Oral");
        mDrawerItemList.add(item);

        DrawerItem item2 = new DrawerItem();
        item2.setTitle("Hair");
        mDrawerItemList.add(item2);

        DrawerItem item3 = new DrawerItem();
        item3.setTitle("Skin");
        mDrawerItemList.add(item3);

        DrawerItem item4 = new DrawerItem();
        item4.setTitle("Personal");
        mDrawerItemList.add(item4);

        DrawerItem item5 = new DrawerItem(); //divider
        item5.setTitle("Divider");
        mDrawerItemList.add(item5);

        DrawerItem item6 = new DrawerItem();
        item6.setTitle("Stories");
        mDrawerItemList.add(item6);

        DrawerItem item7 = new DrawerItem(); //divider
        item7.setTitle("Divider");
        mDrawerItemList.add(item7);

        DrawerItem item8 = new DrawerItem();
        item8.setTitle("Videos");
        mDrawerItemList.add(item8);

        DrawerItem item9 = new DrawerItem(); //divider
        item9.setTitle("Divider");
        mDrawerItemList.add(item9);

        DrawerItem item10= new DrawerItem();
        item10.setTitle("About Us");
        mDrawerItemList.add(item10);

        DrawerItem item11 = new DrawerItem(); //divider
        item11.setTitle("Divider");
        mDrawerItemList.add(item11);

        DrawerItem item12= new DrawerItem();
        item12.setTitle("My Profile");
        mDrawerItemList.add(item12);

        DrawerItem item13 = new DrawerItem(); //divider
        item13.setTitle("Divider");
        mDrawerItemList.add(item13);

        mRecyclerView = (RecyclerView) findViewById(R.id.drawerRecyclerView);

        DrawerAdapter adapter = new DrawerAdapter(mDrawerItemList);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                //TODO Add some action here

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //TODO Add some action here
            }
        };



        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        adapter.setOnItemClickLister(new DrawerAdapter.OnItemSelecteListener() {
            @Override
            public void onItemSelected(View v, int position) {


                if(position==6)
                {
                    Intent i=new Intent(MainActivity.this,StoriesActivity.class);
                    startActivity(i);
                }
                if(position==8)
                {
                    Intent i=new Intent(MainActivity.this,VideoActivity.class);
                    startActivity(i);
                }
                if(position==10)
                {
                    Intent i=new Intent(MainActivity.this,AboutUsActivity.class);
                    startActivity(i);
                }
                if(position==12)
                {
                    Intent i=new Intent(MainActivity.this,ProfileActivity.class);
                    startActivity(i);
                }

            }
        });



        try {
            Glide.with(this).load(R.drawable.products).into((ImageView) findViewById(R.id.products));
            Glide.with(this).load(R.drawable.videos).into((ImageView) findViewById(R.id.videos));
            Glide.with(this).load(R.drawable.loyalty_program).into((ImageView) findViewById(R.id.loyaltyprogram));
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.rl_products:
                Intent i=new Intent(this,ProductsActivity.class);
                startActivity(i);

                break;

            case R.id.calendar:
                i=new Intent(this,CalendarActivity.class);
                startActivity(i);
                break;

            case R.id.rl_videos:
                i=new Intent(this,VideoActivity.class);
                startActivity(i);
                break;
        }
    }
}
