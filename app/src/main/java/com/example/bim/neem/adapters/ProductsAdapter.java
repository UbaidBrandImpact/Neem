package com.example.bim.neem.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bim.neem.Models.Product;
import com.example.bim.neem.R;

import java.io.InputStream;
import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.MyViewHolder> {

    private List<Product> List;
    Context cnx;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, ingredients; ImageView image;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            ingredients = (TextView) view.findViewById(R.id.tv_ingredients);
            image = (ImageView) view.findViewById(R.id.iv);
        }
    }

    public ProductsAdapter(List<Product> list,Context cnx) {
        this.List = list;
        this.cnx = cnx;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Product product = List.get(position);
        holder.title.setText(product.getTitle());
        holder.ingredients.setText(product.getIngredients());
     //   new ProductsAdapter.DownloadImageTask(holder.image).execute(product.getUrl());
        Glide.with(cnx).load(product.getUrl()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return List.size();
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
