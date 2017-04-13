package com.example.bim.neem.Video;

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

import com.example.bim.neem.Models.Video;
import com.example.bim.neem.R;
import com.example.bim.neem.Service.IServiceInvokerCallback;
import com.example.bim.neem.Service.ServiceBusiness;
import com.example.bim.neem.Utils.DividerItemDecoration;
import com.example.bim.neem.Utils.RecyclerTouchListener;
import com.example.bim.neem.adapters.VideoAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class VideoActivity extends AppCompatActivity implements IServiceInvokerCallback {

    private Toolbar mToolbar;

    private List<Video> videoArrayList = new ArrayList<>();
    private RecyclerView recyclerView;
    private VideoAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        mToolbar = (Toolbar)findViewById(R.id.appBar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new VideoAdapter(VideoActivity.this,videoArrayList);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Video movie = videoArrayList.get(position);
                Intent i=new Intent(VideoActivity.this,VideoDetailActivity.class);
                i.putExtra("url",movie.getUrl());
                startActivity(i);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        CallVideoService();

    }

    private void CallVideoService() {
        try {
            ServiceBusiness.getvideos(this,VideoActivity.this,"getvideos");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void prepareVideoData(JSONArray videos) {

        for(int i=0;i<videos.length();i++)
        {
            try {
                JSONObject video = videos.getJSONObject(i);
                Video v=new Video( video.getInt("id"),video.getString("title"),video.getString("duration"),video.getString("image"),
                        video.getString("url"));
                videoArrayList.add(v);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        mAdapter.notifyDataSetChanged();
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

    @Override
    public void onRequestCompleted(String response, String mode) throws JSONException {
        final JSONObject result = new JSONObject(response);
        System.out.println("login Responce : " + result);
        if(mode.equalsIgnoreCase("getvideos"))
        {
            if(result.getString("status").equalsIgnoreCase("1")) {

                /*Json Response :: {"message":"success","data":
                    {"result":[{"id":"1","title":"Video 1","url":"https:\/\/vimeo.com\/212603149",
                            "duration":"5:13","description":"Some description ",
                            "image":"http:\/\/bimtesting.net\/posfront\/assets\/allianz\/images\/video1.png"},
                        {"id":"2","title":"Second video ","url":"https:\/\/vimeo.com\/211387965","duration":"5:36",
                                "description":"Some description about the video 2",
                                "image":"http:\/\/bimtesting.net\/posfront\/assets\/allianz\/images\/video2.png"}]},"status":1}

                */

                JSONArray videos= result.getJSONArray("data");
                prepareVideoData(videos);
            }

            }
    }
}
