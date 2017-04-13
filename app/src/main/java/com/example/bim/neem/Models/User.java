package com.example.bim.neem.Models;

/**
 * Created by baydi on 4/13/17.
 */

public class User {
    private static User mInstance = null;
    private String id,email,mobile,gender,age;

    public User(){
        id = "";
        email = "";
        mobile = "";
        gender = "";
        age = "";
    }

    public static User getInstance(){
        if(mInstance == null)
        {
            mInstance = new User();
        }
        return mInstance;
    }

    public void setId(String id){
        this.id = id;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setMobile(String mobile){
        this.mobile = mobile;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    public void setAge(String age){
        this.age = age;
    }

    public String getId(){return id;}

    public String getEmail(){return  email;}

    public String getMobile(){return  mobile;}

    public String getGender(){return  gender;}

    public String getAge(){return  age;}
}
