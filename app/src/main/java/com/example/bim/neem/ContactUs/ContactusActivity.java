package com.example.bim.neem.ContactUs;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.bim.neem.R;
import com.example.bim.neem.Service.IServiceInvokerCallback;
import com.example.bim.neem.Service.ServiceBusiness;
import com.example.bim.neem.Utils.MLRoundedImageView;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ContactusActivity extends AppCompatActivity implements IServiceInvokerCallback {

    MapView mapView;
    GoogleMap map;

    TextView phone,email,location,fax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);
        phone = (TextView) findViewById(R.id.phone);
        email = (TextView) findViewById(R.id.email);
        location = (TextView)findViewById(R.id.location);
        fax = (TextView)findViewById(R.id.fax);

        try {
            ServiceBusiness.contactus(this,ContactusActivity.this,"contactus");
        } catch (Exception e) {
            e.printStackTrace();
        }
       mapView = (MapView)findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);

        map = mapView.getMap();
        map.getUiSettings().setMyLocationButtonEnabled(false);
        map.setMyLocationEnabled(true);

        try {
            MapsInitializer.initialize(ContactusActivity.this);
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
        if(mode.equalsIgnoreCase("contactus"))
        {

           // {"message":"success","data":"","status":1,"mobile":97143467674,"email":"info@allianz.com","location":"Single Business Tower,Office No 2601,Business Bay,Burj Khalifa Area,Sheikh Zayed Road","fax":97143271723,"lat":25.1882,"lng":55.2583}

            if(result.getString("status").equalsIgnoreCase("1")) {
                phone.setText(result.getString("mobile"));
                email.setText(result.getString("email"));
                location.setText(result.getString("location"));
                fax.setText(result.getString("fax"));
                double lat= Double.parseDouble(result.getString("lat"));
                double lng= Double.parseDouble(result.getString("lng"));


                BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.marker);
                int height = 80;
                int width = 80;
                Bitmap b=bitmapdraw.getBitmap();
                LatLng a=new LatLng(lat,lng);
                final Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
                map.addMarker(new MarkerOptions().position(a).title("Dubai").icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(a, 10);
                map.animateCamera(cameraUpdate);

            }

        }
    }
}
