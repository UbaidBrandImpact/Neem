package com.example.bim.neem.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.bim.neem.R;
import com.example.bim.neem.Models.DrawerItem;

import java.util.ArrayList;

/**
 * Created by darshanz on 7/6/15.
 */
public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.DrawerViewHolder> {

    public final static int TYPE_HEADER = 0;
    public final static int TYPE_MENU = 1;
    public final static int TYPE_DIVIDER = 2;


    private ArrayList<DrawerItem> drawerMenuList;

    private OnItemSelecteListener mListener;

    public DrawerAdapter(ArrayList<DrawerItem> drawerMenuList) {
        this.drawerMenuList = drawerMenuList;
    }

    @Override
    public DrawerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if(viewType == TYPE_HEADER){

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_drawer_header, parent, false);

        }else{

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_menu_item, parent, false);
        }


        return new DrawerViewHolder(view, viewType);
    }


    @Override
    public void onBindViewHolder(DrawerViewHolder holder, int position) {
        if(position == 0) {

        }
        else if(position==5 || position ==7 || position==9 || position ==11 || position==13)
        {
            holder.title.setVisibility(View.GONE);
            holder.divider.setVisibility(View.VISIBLE);
        }
        else{
            holder.title.setText(drawerMenuList.get(position - 1).getTitle());
            holder.title.setVisibility(View.VISIBLE);
            holder.divider.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {

        return drawerMenuList.size()+1;
    }

    @Override
    public int getItemViewType(int position) {

        if(position == 0){
            return  TYPE_HEADER;
        }
        else if(position == 5 || position == 7 || position==9 || position==11 || position==13)
        {
            return TYPE_DIVIDER;
        }
        else {
            return TYPE_MENU;
        }

    }

    class DrawerViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView headerText;
        View divider;


        public DrawerViewHolder(View itemView, int viewType) {
            super(itemView);


            if(viewType == 0){

            }

                else
             {
                title = (TextView) itemView.findViewById(R.id.title);
                 divider = (View) itemView.findViewById(R.id.divider);


             }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onItemSelected(view, getAdapterPosition());

                }
            });
        }

    }




    public void setOnItemClickLister(OnItemSelecteListener mListener) {
        this.mListener = mListener;
    }

   public interface OnItemSelecteListener{
        public void onItemSelected(View v, int position);
    }

}