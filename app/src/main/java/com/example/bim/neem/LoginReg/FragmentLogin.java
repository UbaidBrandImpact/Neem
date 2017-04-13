package com.example.bim.neem.LoginReg;

/**
 * Created by mabee on 3/23/2017.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.example.bim.neem.Home.MainActivity;
import com.example.bim.neem.Models.User;
import com.example.bim.neem.R;
import com.example.bim.neem.Service.IServiceInvokerCallback;
import com.example.bim.neem.Service.ServiceBusiness;

import org.json.JSONException;
import org.json.JSONObject;


public class FragmentLogin extends Fragment implements  View.OnClickListener , IServiceInvokerCallback {

    View view;
    private FragmentActivity myContext;
    EditText edt_email,edt_pass;
    Button btn_login;



    TextView info;
    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }



    @Override

    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {

            view = inflater.inflate(R.layout.login, container, false);
            edt_email = (EditText) view.findViewById(R.id.edt_email);
            edt_pass = (EditText) view.findViewById(R.id.edt_password);
            btn_login = (Button) view.findViewById(R.id.btn_signin);
            btn_login.setOnClickListener(this);

        edt_email.setText("ubaid@brandimpact.ae");
        edt_pass.setText("qwertyuiop");

        return view;
    }


    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Menu 1");
    }


    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {


    }

    private void hideKeyboard(){
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_signin:
                hideKeyboard();
               if(  edt_email.getText().toString().equalsIgnoreCase("")||edt_email.getText().toString().isEmpty()||
                        edt_email.getText().toString()==null ||
                        edt_pass.getText().toString().equalsIgnoreCase("")||edt_pass.getText().toString().isEmpty()||
                        edt_pass.getText().toString()==null )
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
                else {
                    callLoginService(edt_email.getText().toString(),edt_pass.getText().toString());
                }

                break;





        }
    }

    private void callLoginService(String email,String password) {


        try {
            ServiceBusiness.login(email,password,myContext, FragmentLogin.this, "login");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestCompleted(String response, String mode)  {
        //JSONObject result = null;
        try {
            final JSONObject result = new JSONObject(response);
            System.out.println("login Responce : " + result);
            if(mode.equalsIgnoreCase("login"))
            {
                if(result.getString("status").equalsIgnoreCase("1")) {

                    User.getInstance().setId(result.getJSONObject("data").getString("id"));
                    User.getInstance().setEmail(result.getJSONObject("data").getString("email"));
                    User.getInstance().setMobile(result.getJSONObject("data").getString("mobile"));
                    User.getInstance().setGender(result.getJSONObject("data").getString("gender"));
                    User.getInstance().setAge(result.getJSONObject("data").getString("age"));




                    Intent i = new Intent(myContext, MainActivity.class);
                    myContext.startActivity(i);
                   /* new android.support.v7.app.AlertDialog.Builder(myContext)
                            .setTitle(getString(R.string.app_name))
                            .setMessage(result.getString("message"))
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                    try {

                                        User.getInstance().setId(result.getJSONObject("data").getString("id"));
                                        User.getInstance().setEmail(result.getJSONObject("data").getString("email"));
                                        User.getInstance().setMobile(result.getJSONObject("data").getString("mobile"));
                                        User.getInstance().setGender(result.getJSONObject("data").getString("gender"));
                                        User.getInstance().setAge(result.getJSONObject("data").getString("age"));

                                        dialog.dismiss();
                                        //  goto login screen


                                        Intent i = new Intent(myContext, MainActivity.class);
                                        myContext.startActivity(i);
                                    }catch (Exception ex){
                                        ex.printStackTrace();
                                    }
                                }
                            })

                            .show();*/
                }
                else
                {
                    new android.support.v7.app.AlertDialog.Builder(myContext)
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