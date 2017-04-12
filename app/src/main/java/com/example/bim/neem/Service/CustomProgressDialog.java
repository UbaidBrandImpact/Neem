package com.example.bim.neem.Service;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import com.example.bim.neem.R;


public class CustomProgressDialog {

    private static ProgressDialog mProgressDialog;

    public static void showProgressDailog(Context ctx) {
        if (mProgressDialog == null || !mProgressDialog.isShowing()) {
            mProgressDialog = ProgressDialog.show( ctx, null, null, false, true );
            mProgressDialog.getWindow().setBackgroundDrawable( new ColorDrawable( Color.TRANSPARENT ) );
            mProgressDialog.setContentView( R.layout.progressdialog );
        }
    }

    public static void dismissProgressDailog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

}
