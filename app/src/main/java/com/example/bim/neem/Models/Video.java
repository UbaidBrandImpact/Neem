package com.example.bim.neem.Models;


public class Video {
    private String title, duration, url;



    public Video(String title, String duration, String url) {
        this.title = title;
        this.duration = duration;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
