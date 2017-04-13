package com.example.bim.neem.LoginReg;

/**
 * Created by mabee on 3/23/2017.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.example.bim.neem.Home.MainActivity;
import com.example.bim.neem.R;


public class FragmentLogin extends Fragment implements  View.OnClickListener {

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
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_signin:
         /*       if(  edt_email.getText().toString().equalsIgnoreCase("")||edt_email.getText().toString().isEmpty()||
                        edt_email.getText().toString()==null ||
                        edt_pass.getText().toString().equalsIgnoreCase("")||edt_pass.getText().toString().isEmpty()||
                        edt_pass.getText().toString()==null )
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
                else {
                    callLoginService(edt_email.getText().toString(),edt_pass.getText().toString());
                }
                */

                Intent i=new Intent(myContext,MainActivity.class);
                myContext.startActivity(i);
                break;





        }
    }

    private void callLoginService(String email,String password) {


        try {
      //      ServiceBusiness.login(email,password,myContext, FragmentLogin.this, "login");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}