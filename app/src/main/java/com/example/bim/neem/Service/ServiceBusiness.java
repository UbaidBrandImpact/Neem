package com.example.bim.neem.Service;

import android.content.Context;

import java.util.HashMap;

public class ServiceBusiness {


    public static void getPlusuredive(Context ctx, IServiceInvokerCallback callback, String mode) throws Exception {
        HashMap<String, String> params = new HashMap<>();
        params.put("location", "all");
        params.put("index", "1");
        params.put("items", "10");
        System.out.println("Login Json parameter : " + params);
        new InvokeVolleyService(ctx, true, "getPlusuredive", params,
                callback, mode);
    }

}
