package com.example.bim.neem.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bim.neem.Models.Product;
import com.example.bim.neem.R;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.MyViewHolder> {

    private List<Product> List;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, ingredients; ImageView image;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            ingredients = (TextView) view.findViewById(R.id.tv_ingredients);
            image = (ImageView) view.findViewById(R.id.iv);
        }
    }


    public ProductsAdapter(List<Product> list) {
        this.List = list;
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
       // holder.image.setText(product.getUrl());
        holder.ingredients.setText(product.getIngredients());
    }

    @Override
    public int getItemCount() {
        return List.size();
    }
}
