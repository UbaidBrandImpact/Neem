package com.example.bim.neem.LoginReg;

/**
 * Created by mabee on 3/23/2017.
 */

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;


import com.example.bim.neem.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragmentRegister extends Fragment implements  View.OnClickListener {

    View view;
    private FragmentActivity myContext;

    Button btn_register;
    LinearLayout content_main;
    ImageView iv_logo;
    Button getBtn_register;
    ImageView btn_close;
    TextView tv_forgotpass;
    String from="";
    EditText edt_email,edt_pass,edt_phone;
    Spinner spinner_age,spinner_nationality,spinner_gender;

    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }


    @Override

    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.register, container, false);
        edt_email = (EditText) view.findViewById(R.id.edt_email);
        edt_pass = (EditText) view.findViewById(R.id.edt_password);

        callGetCountryService();
        setGenderSpinner();




        return view;
    }

    private void callGetCountryService() {
        try {
         //   ServiceBusiness.getcountry(myContext, FragmentRegister.this, "getcountry");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private void setGenderSpinner() {
        spinner_gender = (Spinner) view.findViewById(R.id.spinner_gender);
        List<String> list = new ArrayList<String>();
        list.add("Male");
        list.add("Female");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(myContext, R.layout.spinner_item,list);
        spinner_gender.setAdapter(adapter);

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
            case R.id.btn_register:

                String age = spinner_age.getSelectedItem().toString();
                String gender = spinner_gender.getSelectedItem().toString();
                String nationality = spinner_nationality.getSelectedItem().toString();

                if(age.equalsIgnoreCase("")||age.isEmpty()||age==null ||
                        gender.equalsIgnoreCase("")||gender.isEmpty()||gender==null ||
                        nationality.equalsIgnoreCase("")||nationality.isEmpty()||nationality==null ||
                        edt_email.getText().toString().equalsIgnoreCase("")||edt_email.getText().toString().isEmpty()||
                        edt_email.getText().toString()==null ||
                        edt_pass.getText().toString().equalsIgnoreCase("")||edt_pass.getText().toString().isEmpty()||
                        edt_pass.getText().toString()==null ||
                        edt_phone.getText().toString().equalsIgnoreCase("")||edt_phone.getText().toString().isEmpty()||
                        edt_phone.getText().toString()==null )
                {
                    new AlertDialog.Builder(myContext)
                            .setTitle("Error")
                            .setMessage("All fields are required!")
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                    dialog.dismiss();
                                }
                            })


                            .show();
                }
                else{
                    String phone = edt_phone.toString();
                    String password = edt_pass.getText().toString();
                    String email = edt_email.getText().toString();

                    callRegisterService(email,password,phone,age,gender,nationality);}
                break;


        }
    }

    private void callRegisterService(String email,String password,String phone,String age,String gender,String nationality) {

        try {
          //  ServiceBusiness.registeration(email,password,phone,age,gender,nationality,myContext, FragmentRegister.this, "registeration");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}