package com.example.bim.neem.Products;

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
import android.widget.Toast;

import com.example.bim.neem.Home.MainActivity;
import com.example.bim.neem.LoginReg.FragmentLogin;
import com.example.bim.neem.Models.Product;
import com.example.bim.neem.Models.User;
import com.example.bim.neem.Service.IServiceInvokerCallback;
import com.example.bim.neem.Service.ServiceBusiness;
import com.example.bim.neem.adapters.ProductsAdapter;
import com.example.bim.neem.R;
import com.example.bim.neem.Utils.RecyclerTouchListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProductsActivity extends AppCompatActivity implements IServiceInvokerCallback {

    private Toolbar mToolbar;
    private Context mContext;
    private List<Product> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ProductsAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        mToolbar = (Toolbar)findViewById(R.id.appBar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mContext = this;
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
                Intent i=new Intent(ProductsActivity.this,ProductDetailActivity.class);
                i.putExtra("product_id",movie.getId());
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

        callProductByTypeService(category_type);

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

    private void callProductByTypeService(String type) {
        try {
            ServiceBusiness.getproductbytype(type,mContext, ProductsActivity.this, "getProductByType");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestCompleted(String response, String mode)  {
        //JSONObject result = null;
        try {
            final JSONObject result = new JSONObject(response);
            System.out.println("getProductByType Responce : " + result);
            if(mode.equalsIgnoreCase("getProductByType"))
            {
                if(result.getString("status").equalsIgnoreCase("1")) {
                    JSONObject jsonObject = result.getJSONObject("data");
                    if(jsonObject != null){
                        JSONArray dataArray = jsonObject.getJSONArray("result");
                        movieList.clear();
                        for (int lop = 0 ; lop < dataArray.length();lop++){
                            JSONObject object = dataArray.getJSONObject(lop);
                            Product product = new Product(object.getString("id"),object.getString("title"),object.getString("ingredients"),object.getString("image"));
                            movieList.add(product);
                        }
                        mAdapter.notifyDataSetChanged();
                    }
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
