package com.example.bim.neem.Video;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.bim.neem.Models.Product;
import com.example.bim.neem.R;
import com.example.bim.neem.adapters.ProductsAdapter;

import java.util.ArrayList;
import java.util.List;

public class VideoDetailActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);
        mToolbar = (Toolbar)findViewById(R.id.appBar);
        videoView = (VideoView) findViewById(R.id.videoView);

        String LINK = "https://youtu.be/3R-0Eyjwcc4";
        MediaController mc = new MediaController(this);
        mc.setAnchorView(videoView);
        mc.setMediaPlayer(videoView);
        Uri video = Uri.parse(LINK);
        videoView.setMediaController(mc);
        videoView.setVideoURI(video);
        videoView.start();


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
}
