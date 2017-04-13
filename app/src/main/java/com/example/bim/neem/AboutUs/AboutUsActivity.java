package com.example.bim.neem.AboutUs;

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
import android.widget.TextView;

import com.example.bim.neem.Models.Video;
import com.example.bim.neem.R;
import com.example.bim.neem.Service.IServiceInvokerCallback;
import com.example.bim.neem.Service.ServiceBusiness;
import com.example.bim.neem.Utils.DividerItemDecoration;
import com.example.bim.neem.Utils.RecyclerTouchListener;
import com.example.bim.neem.Video.VideoDetailActivity;
import com.example.bim.neem.adapters.VideoAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AboutUsActivity extends AppCompatActivity implements IServiceInvokerCallback {

    TextView tv_details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
        tv_details = (TextView)findViewById(R.id.tv_details);
        try {
            ServiceBusiness.aboutus(this,AboutUsActivity.this,"aboutus");
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        if(mode.equalsIgnoreCase("aboutus"))
        {
            JSONArray data= result.getJSONArray("data");

            for(int i=0;i<data.length();i++)
            {
                JSONObject obj=data.getJSONObject(i);
                String content =obj.getString("content");
                tv_details.setText(content);

            }

          //  {"message":"success","data":[{"id":"1","content":"Darlie Global FZC is a company under Allianz International Holdings Limited -- represented by a\r\n\r\nglobal workforce of over 10,000 visionaries, doers and makers and a strategically diverse\r\n\r\nportfolio of leading international brands that have touched and reached out to countries across\r\n\r\nthe globe. Darlie Global&#39;s range includes Oral Care, Hair Care, Skin Care, Personal Care,\r\n\r\nKitchen &amp; Hygiene and Baby Care products that have been manufactured locally to address the\r\n\r\nmarket gap for locally made health and wellness products. The company&#39;s key reach extends to\r\n\r\nthe Middle East, Asia and Africa (MEAA) region, covering 27 countries. Darlie Global FZC has\r\n\r\nbeen incepted with vision to be the trailblazing wellness company; leveraging our team mates&#39; creativity\r\n\r\nand passion to challenge convention and drive growth, and a mission win through focus, insightful\r\n\r\ninnovation and agility; delivering better solutions to our consumers and customers.\r\n\r\nDarlie Global&#39;s success is guided by its mission to provide 360 solutions for people&#39;s daily care\r\n\r\nneeds-- aiming to be the key expert and provider for your daily health and wellness practices."}],"status":1}
        }
    }
}
