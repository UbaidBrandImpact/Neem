package com.example.bim.neem.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bim.neem.Models.Product;
import com.example.bim.neem.R;

import java.util.List;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.MyViewHolder> {

    private List<Product> List;Context cnx;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, ingredients; ImageView image;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            image = (ImageView) view.findViewById(R.id.iv);
        }
    }

    public StoryAdapter(List<Product> list,Context cnx) {
        this.List = list;
        this.cnx=cnx;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.story_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Product product = List.get(position);
        holder.title.setText(product.getTitle());
        Glide.with(cnx).load(product.getUrl()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return List.size();
    }
}
