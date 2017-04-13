package com.example.bim.neem.LoginReg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.widget.TextView;

import com.example.bim.neem.LoginReg.FragmentLogin;
import com.example.bim.neem.LoginReg.FragmentRegister;
import com.example.bim.neem.R;


public class LoginRegActivity extends FragmentActivity implements View.OnClickListener {

    private FragmentTabHost mTabHost;

    private boolean change_fragment=false;
    int requestCod,resultCod;
    Intent dataIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_reg);
        init();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        change_fragment = true;
        requestCod = requestCode;
        resultCod = resultCode;
        dataIntent = data;


    }

    @Override
    public void onResume() {
        super.onResume();
        if(change_fragment)
        {
            change_fragment=false;
            for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                fragment.onActivityResult(requestCod, resultCod, dataIntent);
            }
        }
    }

    private void init() {

        mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        mTabHost.addTab(mTabHost.newTabSpec("tab1").setIndicator("Login"),FragmentLogin.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("tab2").setIndicator("New User"),FragmentRegister.class, null);
        for(int i=0;i<mTabHost.getTabWidget().getChildCount();i++)
        {
            TextView tv = (TextView) mTabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(getResources().getColor(R.color.colorAccent));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }


}
