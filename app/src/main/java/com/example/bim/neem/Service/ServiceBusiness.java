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

    public static void registration(String email, String password, String gender, String dateOfBirth, String phone_number,Context ctx, IServiceInvokerCallback callback, String mode) throws Exception {
        HashMap<String, String> params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);
        params.put("mobile", phone_number);
        params.put("gender", gender);
        params.put("age", dateOfBirth);
        System.out.println("Registration  Json parameter : " + params);
        new InvokeVolleyService(ctx, true, "signup", params,
                callback, mode);
    }

    public static void login(String email, String password, Context ctx, IServiceInvokerCallback callback, String mode) throws Exception {
        HashMap<String, String> params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);
        System.out.println("login  Json parameter : " + params);
        new InvokeVolleyService(ctx, true, "login", params,
                callback, mode);
    }

    public static void getproductbytype(String type, Context ctx, IServiceInvokerCallback callback, String mode) throws Exception {
        HashMap<String, String> params = new HashMap<>();
        params.put("type", type);
        System.out.println("getproductbytype  Json parameter : " + params);
        new InvokeVolleyService(ctx, true, "getproductbytype", params,
                callback, mode);
    }

    public static void singleproduct(String type, Context ctx, IServiceInvokerCallback callback, String mode) throws Exception {
        HashMap<String, String> params = new HashMap<>();
        params.put("id", type);
        System.out.println("singleproduct  Json parameter : " + params);
        new InvokeVolleyService(ctx, true, "singleproduct", params,
                callback, mode);
    }

}
