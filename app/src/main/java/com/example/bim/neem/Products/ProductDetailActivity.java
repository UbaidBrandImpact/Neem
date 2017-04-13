package com.example.bim.neem.Products;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bim.neem.Models.Product;
import com.example.bim.neem.Service.IServiceInvokerCallback;
import com.example.bim.neem.Service.ServiceBusiness;
import com.example.bim.neem.adapters.ProductsAdapter;
import com.example.bim.neem.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ProductDetailActivity extends AppCompatActivity implements IServiceInvokerCallback {

    private Toolbar mToolbar;

    Context mContext;
    TextView titleTextView,detailTextView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_detail);
        mToolbar = (Toolbar)findViewById(R.id.appBar);

        mContext = this;
        connectToXML();

        Bundle extras = getIntent().getExtras();
        String product_id = "";

        if (extras != null) {
            product_id = extras.getString("product_id");
        }

        callProductByTypeService(product_id);

    }

    private void connectToXML(){
        titleTextView = (TextView)findViewById(R.id.title);
        detailTextView = (TextView)findViewById(R.id.tv_details);
        imageView = (ImageView)findViewById(R.id.iv);
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
            ServiceBusiness.singleproduct(type,mContext, ProductDetailActivity.this, "singleproduct");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestCompleted(String response, String mode)  {
        //JSONObject result = null;
        try {
            final JSONObject result = new JSONObject(response);
            System.out.println("singleproduct Responce : " + result);
            if(mode.equalsIgnoreCase("singleproduct"))
            {
                if(result.getString("status").equalsIgnoreCase("1")) {
                    JSONArray jsonArray = result.getJSONArray("data");
                    if(jsonArray != null && jsonArray.length() > 0){
                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                        new ProductDetailActivity.DownloadImageTask(imageView)
                                .execute(jsonObject.getString("thumbnail"));
                        titleTextView.setText(jsonObject.getString("title"));
                        detailTextView.setText(jsonObject.getString("ingredients"));
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

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
