package com.example.bim.neem.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.bim.neem.Models.Product;
import com.example.bim.neem.Models.Video;
import com.example.bim.neem.R;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyViewHolder> {

    private List<Video> List;
    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView videoView;TextView title,duration;

        public MyViewHolder(View view) {
            super(view);
            videoView = (ImageView) view.findViewById(R.id.videoView);
            title = (TextView) view.findViewById(R.id.title);
            duration = (TextView) view.findViewById(R.id.duration);
        }
    }

    public VideoAdapter(Context context,List<Video> list) {
        this.List = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.video_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Video video = List.get(position);
        String path=video.getImage();
        holder.title.setText(video.getTitle());
        holder.duration.setText(video.getDuration());
        Glide.with(context).load(path).into(holder.videoView );
      /*  Uri uri= Uri.parse(path);
        holder.videoView.setVideoURI(uri);
        */
    }

    @Override
    public int getItemCount() {
        return List.size();
    }
}
