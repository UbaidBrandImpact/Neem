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
import com.example.bim.neem.CSR.CSRComingSoon;
import com.example.bim.neem.Calendar.CalendarActivity;
import com.example.bim.neem.Calendar.CalendarComingSoon;
import com.example.bim.neem.Category.CategoryActivity;
import com.example.bim.neem.ContactUs.ContactusActivity;
import com.example.bim.neem.Gallery.GalleryActivity;
import com.example.bim.neem.LoyaltyProgram.LoyaltyProgram;
import com.example.bim.neem.Products.ProductsActivity;
import com.example.bim.neem.Profile.ProfileActivity;
import com.example.bim.neem.R;
import com.example.bim.neem.Stories.StoriesActivity;
import com.example.bim.neem.Stories.StoriesComingSoon;
import com.example.bim.neem.Video.VideoActivity;
import com.example.bim.neem.adapters.DrawerAdapter;
import com.example.bim.neem.Models.DrawerItem;
import com.example.bim.neem.adapters.GalleryListAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private ArrayList<DrawerItem> mDrawerItemList;

    private DrawerLayout mDrawerLayout;

    private ActionBarDrawerToggle mDrawerToggle;

    RelativeLayout rl_products,rl_loyalty,rl_videos;
    ImageView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar)findViewById(R.id.appBar);
        rl_products = (RelativeLayout) findViewById(R.id.rl_products);
        rl_loyalty = (RelativeLayout) findViewById(R.id.rl_loyalty);
        rl_videos = (RelativeLayout) findViewById(R.id.rl_videos);
        calendar = (ImageView) findViewById(R.id.calendar);
        calendar.setOnClickListener(this);
        rl_products.setOnClickListener(this);
        rl_loyalty.setOnClickListener(this);
        rl_videos.setOnClickListener(this);

    //    setSupportActionBar(mToolbar);


        mDrawerItemList = new ArrayList<DrawerItem>();
        DrawerItem item = new DrawerItem();

        item.setTitle(getString(R.string.oral_menu));
        mDrawerItemList.add(item);

        DrawerItem item2 = new DrawerItem();
        item2.setTitle(getString(R.string.hair_menu));
        mDrawerItemList.add(item2);

        DrawerItem item3 = new DrawerItem();
        item3.setTitle(getString(R.string.skin_menu));
        mDrawerItemList.add(item3);

        DrawerItem item4 = new DrawerItem();
        item4.setTitle(getString(R.string.personal_menu));
        mDrawerItemList.add(item4);

        DrawerItem item5 = new DrawerItem();
        item5.setTitle(getString(R.string.kitchen_hygene_menu));
        mDrawerItemList.add(item5);

        DrawerItem item6 = new DrawerItem(); //divider
        item6.setTitle("Divider");
        mDrawerItemList.add(item6);

        DrawerItem item7 = new DrawerItem();
        item7.setTitle(getString(R.string.stories_menu));
        mDrawerItemList.add(item7);

        DrawerItem item8 = new DrawerItem();
        item8.setTitle("Divider");
        mDrawerItemList.add(item8);

        DrawerItem item9 = new DrawerItem();
        item9.setTitle(getString(R.string.about_us_menu));
        mDrawerItemList.add(item9);

        DrawerItem item10= new DrawerItem();
        item10.setTitle("Divider");
        mDrawerItemList.add(item10);

        DrawerItem item11 = new DrawerItem();
        item11.setTitle(getString(R.string.gallery_menu));
        mDrawerItemList.add(item11);

        DrawerItem item12= new DrawerItem();
        item12.setTitle("Divider");
        mDrawerItemList.add(item12);

        DrawerItem item13 = new DrawerItem();
        item13.setTitle(getString(R.string.videos_menu));
        mDrawerItemList.add(item13);

        DrawerItem item14= new DrawerItem();
        item14.setTitle("Divider");
        mDrawerItemList.add(item14);

        DrawerItem item15 = new DrawerItem();
        item15.setTitle(getString(R.string.csr_menu));
        mDrawerItemList.add(item15);

        DrawerItem item16= new DrawerItem();
        item16.setTitle("Divider");
        mDrawerItemList.add(item16);

        DrawerItem item17 = new DrawerItem();
        item17.setTitle(getString(R.string.loyalty_menu));
        mDrawerItemList.add(item17);

        DrawerItem item18= new DrawerItem();
        item18.setTitle("Divider");
        mDrawerItemList.add(item18);

        DrawerItem item19 = new DrawerItem();
        item19.setTitle(getString(R.string.contact_menu));
        mDrawerItemList.add(item19);

        DrawerItem item20= new DrawerItem();
        item20.setTitle("Divider");
        mDrawerItemList.add(item20);

        DrawerItem item21 = new DrawerItem();
        item21.setTitle(getString(R.string.settings_menu));
        mDrawerItemList.add(item21);

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

                if(position == 1){
                        goToProductsActivity("oral");
                }
                else if(position == 2){
                    goToProductsActivity("hair");
                }
                else if(position == 3){
                    goToProductsActivity("skin");
                }
                else if(position == 4){
                    goToProductsActivity("personal");
                }
                else if(position == 5){
                    goToProductsActivity("kitchen");
                }
                else if(position==7)
                {
                   // Intent i=new Intent(MainActivity.this,StoriesActivity.class);
                    Intent i=new Intent(MainActivity.this,StoriesComingSoon.class);
                    startActivity(i);
                }
                else if(position==13)
                {
                    Intent i=new Intent(MainActivity.this,VideoActivity.class);
                    startActivity(i);
                }
                else if(position==17)
                {
                    Intent i=new Intent(MainActivity.this,LoyaltyProgram.class);
                    startActivity(i);
                }
                else if(position==15)
                {
                    Intent i=new Intent(MainActivity.this,CSRComingSoon.class);
                    startActivity(i);
                }
                else if(position==9)
                {
                    Intent i=new Intent(MainActivity.this,AboutUsActivity.class);
                    startActivity(i);
                }
                else if(position==11)
                {
                    Intent i=new Intent(MainActivity.this,GalleryActivity.class);
                    startActivity(i);
                }
                else if(position==21)
                {
                    Intent i=new Intent(MainActivity.this,ProfileActivity.class);
                    startActivity(i);
                }
                else if(position==19)
                {
                    Intent i=new Intent(MainActivity.this,ContactusActivity.class);
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

    private void goToProductsActivity(String type){
        Intent intent = new Intent(this,ProductsActivity.class);
        intent.putExtra("category_type",type);
        startActivity(intent);
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
                Intent i=new Intent(this,CategoryActivity.class);
                startActivity(i);
                break;

            case R.id.rl_loyalty:
                Intent intent = new Intent(this, LoyaltyProgram.class);
                startActivity(intent);
                break;

            case R.id.calendar:
                i=new Intent(this,CalendarComingSoon.class);
                startActivity(i);
                break;

            case R.id.rl_videos:
                i=new Intent(this,VideoActivity.class);
                startActivity(i);
                break;
        }
    }
}
