package com.example.bim.neem.Service;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bim.neem.R;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class InvokeVolleyService {

    boolean mIsPost;
    String mMethod;
    Map mRequestParameters;
    IServiceInvokerCallback mCallback;
    Context mCtx;
    String mMode = "";
    String ServiceResponse="";



    public InvokeVolleyService(Context ctx, boolean isPost, String method,
                               HashMap requestParameters, IServiceInvokerCallback callback, String mode) {
        super();
        mCtx = ctx;
        mIsPost = isPost;
        mMethod = method;
        mRequestParameters = requestParameters;
        mCallback = callback;
        mMode = mode;
        callService();
    }

    private void callService() {

        if (isOnline()) {

            CustomProgressDialog.showProgressDailog(mCtx);
            StringRequest postRequest = new StringRequest(Request.Method.POST, getServiceUrl()+mMethod,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            CustomProgressDialog.dismissProgressDailog();
                            Log.e(" Json Response : " , response);
                            ServiceResponse=response;
                            try {
                                mCallback.onRequestCompleted(ServiceResponse, mMode);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            CustomProgressDialog.dismissProgressDailog();
                            error.printStackTrace();
                            Toast.makeText(mCtx,error.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.e(" Json ERROR : " , error.getMessage());

                            ServiceResponse=error.getMessage();
                            try {
                                mCallback.onRequestCompleted(ServiceResponse, mMode);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams() {
                    Log.e(" Json PARAMETERS : " , mRequestParameters.toString());
                    return mRequestParameters;
                }
            };
            postRequest.setRetryPolicy(new DefaultRetryPolicy(
                    50000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            Volley.newRequestQueue(mCtx).add(postRequest);

        } else {
            android.support.v7.app.AlertDialog.Builder myAlertDialog = new android.support.v7.app.AlertDialog.Builder(mCtx);
            myAlertDialog.setTitle("Error");
            myAlertDialog.setMessage("Internet Connection is not available");
            myAlertDialog.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int arg1) {
                            dialog.cancel();
                        }
                    });
            myAlertDialog.show();
        }
    }

    private String getServiceUrl() {
        if (mCtx != null) {
            String serviceUri = mCtx.getString(R.string.service);
            Log.e("", "ServiceUri:" + serviceUri);
            return serviceUri;

        } else {
            Log.e("in ServiceClient.java", "In method getServiceUrl, Context is null");
            return "";
        }
    }
    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) mCtx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    private JSONObject GetCustomErrorJSON(String errorMsg) {
        try {
            JSONObject errorJSON = new JSONObject();
            errorJSON.put("IsSuccess", false);
            errorJSON.put("Error", errorMsg);

            return errorJSON;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}

