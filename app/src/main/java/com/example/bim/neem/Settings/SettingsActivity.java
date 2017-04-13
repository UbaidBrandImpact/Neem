package com.example.bim.neem.Settings;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TabHost;

import com.example.bim.neem.LoginReg.FragmentRegister;
import com.example.bim.neem.Models.User;
import com.example.bim.neem.R;
import com.example.bim.neem.Service.IServiceInvokerCallback;
import com.example.bim.neem.Service.ServiceBusiness;
import com.example.bim.neem.Utils.MLRoundedImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener,IServiceInvokerCallback {


    Button save,dob;

    String dateOfBirth="";
    EditText edt_pass,edt_confirm,edt_phone;
    Spinner spinner_gender;

    DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        edt_pass = (EditText) findViewById(R.id.edt_password);
        edt_confirm = (EditText) findViewById(R.id.edt_confirm);
        edt_phone = (EditText) findViewById(R.id.edt_phone);
        dob = (Button) findViewById(R.id.dob);
        dob.setOnClickListener(this);
        save = (Button) findViewById(R.id.btn_save);
        save.setOnClickListener(this);


        setDateOfBirth();
    }


    private void setDateOfBirth(){
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        Calendar newCalendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                dob.setText(dateFormatter.format(newDate.getTime()));
                dateOfBirth = dateFormatter.format(newDate.getTime());
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());

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
    private void hideKeyboard(){
        // Check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.dob:
                hideKeyboard();
                datePickerDialog.show();
                break;

            case R.id.btn_save:
                hideKeyboard();
                if(
                        edt_pass.getText().toString().equalsIgnoreCase("")||edt_pass.getText().toString().isEmpty()||
                        edt_pass.getText().toString()==null ||
                        edt_confirm.getText().toString().equalsIgnoreCase("")||edt_confirm.getText().toString().isEmpty()||
                        edt_confirm.getText().toString()==null ||
                        edt_phone.getText().toString().equalsIgnoreCase("")||edt_phone.getText().toString().isEmpty()||
                        edt_phone.getText().toString()==null ||
                        dateOfBirth.equalsIgnoreCase("")||dateOfBirth.isEmpty()||dateOfBirth==null)
                {
                    new AlertDialog.Builder(this)
                            .setTitle(getString(R.string.error_alert_box))
                            .setMessage(getString(R.string.fields_required))
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                  dialog.dismiss();
                                }
                            })

                            .show();
                }
                else if(!edt_pass.getText().toString().equalsIgnoreCase(edt_confirm.getText().toString())){

                    new AlertDialog.Builder(this)
                            .setTitle(getString(R.string.error_alert_box))
                            .setMessage(getString(R.string.password_didnt_match))
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                    dialog.dismiss();
                                }
                            })


                            .show();
                }
                else{
                    String phone = edt_phone.getText().toString();
                    String password = edt_pass.getText().toString();

                    callUpdateService(User.getInstance().getId(),password,dateOfBirth,phone);
                }
                break;

        }
    }
    private void callUpdateService(String id,String password, String age,String phone) {

        try {
            ServiceBusiness.settings(id,password,age,phone,this, SettingsActivity.this, "settings");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestCompleted(String response, String mode)  {
        JSONObject result = null;
        try {
            result = new JSONObject(response);
            System.out.println("Settings Responce : " + result);
            if(mode.equalsIgnoreCase("settings"))
            {
                if(result.getString("status").equalsIgnoreCase("1")) {
                    new AlertDialog.Builder(this)
                            .setTitle(getString(R.string.app_name))
                            .setMessage(getString(R.string.success_msg_settings))
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    finish();

                                }
                            })

                            .show();
                }
                else
                {
                    new AlertDialog.Builder(this)
                            .setTitle(getString(R.string.error_alert_box))
                            .setMessage(result.getString("message"))
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                    dialog.dismiss();

                                }
                            })

                            .show();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
