package com.example.bim.neem.Gallery;

import android.content.Context;
import android.content.DialogInterface;
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

import com.example.bim.neem.Models.Product;
import com.example.bim.neem.Products.ProductDetailActivity;
import com.example.bim.neem.R;
import com.example.bim.neem.Service.IServiceInvokerCallback;
import com.example.bim.neem.Service.ServiceBusiness;
import com.example.bim.neem.Utils.DividerItemDecoration;
import com.example.bim.neem.Utils.RecyclerTouchListener;
import com.example.bim.neem.adapters.GalleryListAdapter;
import com.example.bim.neem.adapters.ProductsAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GalleryActivity extends AppCompatActivity implements IServiceInvokerCallback {

    private Toolbar mToolbar;
    private Context mContext;
    private List<Product> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private GalleryListAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        mToolbar = (Toolbar)findViewById(R.id.appBar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mContext = this;
        mAdapter = new GalleryListAdapter(movieList,this);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Product movie = movieList.get(position);
                Intent i=new Intent(GalleryActivity.this,SelectedCategoryGallery.class);
                i.putExtra("id",movie.getId());
                startActivity(i);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        //prepareMovieData();

        Bundle extras = getIntent().getExtras();
        String category_type = "all";

        if (extras != null) {
            category_type = extras.getString("category_type");
        }

        callGalleryListService();

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

    private void callGalleryListService() {
        try {
            ServiceBusiness.gallerycategoris(mContext, GalleryActivity.this, "gallerycategoris");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestCompleted(String response, String mode)  {
        //JSONObject result = null;
        try {
            final JSONObject result = new JSONObject(response);
            System.out.println("gallerycategoris Responce : " + result);
            if(mode.equalsIgnoreCase("gallerycategoris"))
            {
                if(result.getString("status").equalsIgnoreCase("1")) {

                        JSONArray dataArray = result.getJSONArray("data");
                        movieList.clear();
                        for (int lop = 0 ; lop < dataArray.length();lop++){
                            JSONObject object = dataArray.getJSONObject(lop);
                            Product product = new Product(object.getString("id"),object.getString("title"),object.getString("image"));
                            movieList.add(product);
                        }
                        mAdapter.notifyDataSetChanged();

                }
                else
                {
                    new android.support.v7.app.AlertDialog.Builder(mContext)
                            .setTitle(getString(R.string.error_alert_box))
                            .setMessage(result.getString("message"))
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                    dialog.dismiss();

                                }
                            })

                            .show();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
