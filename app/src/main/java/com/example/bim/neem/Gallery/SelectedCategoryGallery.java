package com.example.bim.neem.Gallery;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.bim.neem.Models.Product;
import com.example.bim.neem.Products.ProductDetailActivity;
import com.example.bim.neem.R;
import com.example.bim.neem.Service.IServiceInvokerCallback;
import com.example.bim.neem.Service.ServiceBusiness;
import com.example.bim.neem.Utils.RecyclerTouchListener;
import com.example.bim.neem.adapters.GalleryListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SelectedCategoryGallery extends AppCompatActivity implements IServiceInvokerCallback {

    private Toolbar mToolbar;
    private Context mContext;
    private List<Product> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private GalleryListAdapter mAdapter;
    String id ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_category);
        mToolbar = (Toolbar)findViewById(R.id.appBar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mContext = this;
        mAdapter = new GalleryListAdapter(movieList,this);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Product movie = movieList.get(position);
                Intent i=new Intent(SelectedCategoryGallery.this,SingleImageActivity.class);
                i.putExtra("path",movie.getUrl());
                startActivity(i);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        //prepareMovieData();

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            id = extras.getString("id");
        }

        callService(id);

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

    private void callService(String id) {
        try {
            ServiceBusiness.gallerycontents(id,mContext, SelectedCategoryGallery.this, "gallerycontents");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestCompleted(String response, String mode)  {
        //JSONObject result = null;
        try {
            final JSONObject result = new JSONObject(response);
            System.out.println("gallerycontents Responce : " + result);
            if(mode.equalsIgnoreCase("gallerycontents"))
            {
                if(result.getString("status").equalsIgnoreCase("1")) {

                        JSONArray dataArray = result.getJSONArray("data");
                        movieList.clear();
                        for (int lop = 0 ; lop < dataArray.length();lop++){
                            JSONObject object = dataArray.getJSONObject(lop);
                            Product product = new Product("","",object.getString("image"));
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
    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

}
