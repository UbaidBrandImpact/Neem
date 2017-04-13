package com.example.bim.neem.Models;


public class Story {
    private String id,title, detail;

    public Story(String id, String title, String detail) {
        this.id = id;
        this.title = title;
        this.detail = detail;
    }

    public void setId(String id){
        this.id = id;
    }
    public String getId(){return id;}

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }


}
