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

    public static void getvideos(Context ctx, IServiceInvokerCallback callback, String mode) throws Exception {
        HashMap<String, String> params = new HashMap<>();
        System.out.println("getavideos  Json parameter : " + params);
        new InvokeVolleyService(ctx, true, "getvideos", params,
                callback, mode);
    }

    public static void contactus(Context ctx, IServiceInvokerCallback callback, String mode) throws Exception {
        HashMap<String, String> params = new HashMap<>();
        System.out.println("contactus  Json parameter : " + params);
        new InvokeVolleyService(ctx, true, "contactus", params,
                callback, mode);
    }
    public static void getsingleproduct(String type, Context ctx, IServiceInvokerCallback callback, String mode) throws Exception {
        HashMap<String, String> params = new HashMap<>();
        params.put("id", type);
        System.out.println("getsingleproduct  Json parameter : " + params);
        new InvokeVolleyService(ctx, true, "getsingleproduct", params,
                callback, mode);
    }

    public static void gallerycontents(String id, Context ctx, IServiceInvokerCallback callback, String mode) throws Exception {
        HashMap<String, String> params = new HashMap<>();
        params.put("id", id);
        System.out.println("gallerycontents  Json parameter : " + params);
        new InvokeVolleyService(ctx, true, "gallerycontents", params,
                callback, mode);
    }
    public static void aboutus(Context ctx, IServiceInvokerCallback callback, String mode) throws Exception {
        HashMap<String, String> params = new HashMap<>();
        System.out.println("aboutus  Json parameter : " + params);
        new InvokeVolleyService(ctx, true, "about", params,
                callback, mode);
    }

    public static void gallerycategoris(Context ctx, IServiceInvokerCallback callback, String mode) throws Exception {
        HashMap<String, String> params = new HashMap<>();
        System.out.println("gallerycategoris  Json parameter : " + params);
        new InvokeVolleyService(ctx, true, "gallerycategoris", params,
                callback, mode);
    }

    public static void settings(String id, String password,  String dateOfBirth, String phone_number,Context ctx, IServiceInvokerCallback callback, String mode) throws Exception {
        HashMap<String, String> params = new HashMap<>();
        params.put("id", id);
        params.put("password", password);
        params.put("dob", dateOfBirth);
        params.put("mobile", phone_number);
        System.out.println("settings  Json parameter : " + params);
        new InvokeVolleyService(ctx, true, "settings", params,
                callback, mode);
    }

}
