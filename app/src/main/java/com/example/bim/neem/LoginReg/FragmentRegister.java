package com.example.bim.neem.LoginReg;

/**
 * Created by mabee on 3/23/2017.
 */

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;


import com.example.bim.neem.R;
import com.example.bim.neem.Service.IServiceInvokerCallback;
import com.example.bim.neem.Service.InvokeVolleyService;
import com.example.bim.neem.Service.ServiceBusiness;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class FragmentRegister extends Fragment implements View.OnClickListener , IServiceInvokerCallback{

    View view;
    private FragmentActivity myContext;

    Button btn_register,dob;

    String dateOfBirth="";
    EditText edt_email,edt_pass,edt_confirm,edt_phone;
    Spinner spinner_gender;

    DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }

    private void hideKeyboard(){
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }



    @Override

    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.register, container, false);
        edt_email = (EditText) view.findViewById(R.id.edt_email);
        edt_pass = (EditText) view.findViewById(R.id.edt_password);
        edt_confirm = (EditText) view.findViewById(R.id.edt_confirm);
        edt_phone = (EditText) view.findViewById(R.id.edt_phone);
        dob = (Button) view.findViewById(R.id.dob);
        dob.setOnClickListener(this);
        btn_register = (Button) view.findViewById(R.id.btn_register);
        btn_register.setOnClickListener(this);

        setGenderSpinner();

        setDateOfBirth();

        return view;
    }


    private void setGenderSpinner() {
        spinner_gender = (Spinner) view.findViewById(R.id.spinner_gender);
        List<String> list = new ArrayList<String>();
        list.add(getString(R.string.choose_gender));
        list.add("Male");
        list.add("Female");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(myContext, R.layout.spinner_item,list);
        spinner_gender.setAdapter(adapter);

    }


    private void setDateOfBirth(){
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        Calendar newCalendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {

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
    public void onViewCreated(View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Menu 1");
    }



    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.dob:
                hideKeyboard();
                datePickerDialog.show();
                break;

            case R.id.btn_register:
                hideKeyboard();
                String gender = spinner_gender.getSelectedItem().toString();
                if(     edt_email.getText().toString().equalsIgnoreCase("")||edt_email.getText().toString().isEmpty()||
                        edt_email.getText().toString()==null ||
                        edt_pass.getText().toString().equalsIgnoreCase("")||edt_pass.getText().toString().isEmpty()||
                        edt_pass.getText().toString()==null ||
                        edt_confirm.getText().toString().equalsIgnoreCase("")||edt_confirm.getText().toString().isEmpty()||
                        edt_confirm.getText().toString()==null ||
                        edt_phone.getText().toString().equalsIgnoreCase("")||edt_phone.getText().toString().isEmpty()||
                        edt_phone.getText().toString()==null ||
                        gender.equalsIgnoreCase("")||gender.isEmpty()||gender==null||
                        dateOfBirth.equalsIgnoreCase("")||dateOfBirth.isEmpty()||dateOfBirth==null)
                {
                    new AlertDialog.Builder(myContext)
                            .setTitle(getString(R.string.error_alert_box))
                            .setMessage(getString(R.string.fields_required))
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                    dialog.dismiss();
                                }
                            })


                            .show();
                }
                else if(!edt_pass.getText().toString().equalsIgnoreCase(edt_confirm.getText().toString())){

                    new AlertDialog.Builder(myContext)
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
                    String email = edt_email.getText().toString();

                    callRegisterService(email,password,gender,dateOfBirth,phone);
                }
                break;

        }
    }

    private void callRegisterService(String email,String password,String gender, String age,String phone) {

        try {
          ServiceBusiness.registration(email,password,gender,age,phone,myContext, FragmentRegister.this, "registration");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestCompleted(String response, String mode)  {
        JSONObject result = null;
        try {
            result = new JSONObject(response);
            System.out.println("Registration Responce : " + result);
            if(mode.equalsIgnoreCase("registration"))
            {
                if(result.getString("status").equalsIgnoreCase("1")) {
                    new AlertDialog.Builder(myContext)
                            .setTitle(getString(R.string.app_name))
                            .setMessage(result.getString("message"))
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                    dialog.dismiss();
                                    //  goto login screen
                                    Intent intent = new Intent(getActivity(),LoginRegActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                }
                            })

                            .show();
                }
                else
                {
                    new AlertDialog.Builder(myContext)
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