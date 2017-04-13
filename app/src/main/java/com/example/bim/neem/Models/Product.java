package com.example.bim.neem.Models;


public class Product {
    private String id,title, ingredients, url;



    public Product(String id,String title, String ingredients, String url) {
        this.id = id;
        this.title = title;
        this.ingredients = ingredients;
        this.url = url;
    }

    public Product(String id, String title, String image) {
        this.id = id;
        this.title = title;
        this.url = image;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
}
