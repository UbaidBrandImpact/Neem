package com.example.bim.neem.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bim.neem.Models.Product;
import com.example.bim.neem.Models.Story;
import com.example.bim.neem.R;

import java.util.List;

public class CustomPagerAdapter extends PagerAdapter {
    private Context context;
    private List<Story> dataObjectList;
    private LayoutInflater layoutInflater;
    public CustomPagerAdapter(List<Story> dataObjectList,Context context){
        this.context = context;
        this.layoutInflater = (LayoutInflater)this.context.getSystemService(this.context.LAYOUT_INFLATER_SERVICE);
        this.dataObjectList = dataObjectList;
    }
    @Override
    public int getCount() {
        return dataObjectList.size();
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((View)object);
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = this.layoutInflater.inflate(R.layout.story_list_row, container, false);
        ImageView displayImage = (ImageView)view.findViewById(R.id.iv);
        TextView title = (TextView)view.findViewById(R.id.title);
        TextView detail = (TextView)view.findViewById(R.id.tv_details);

        title.setText(dataObjectList.get(position).getTitle());
        detail.setText(dataObjectList.get(position).getDetail());

        container.addView(view);
        return view;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}