package com.example.bim.neem.Models;


public class Product {
    private String title, ingredients, url;



    public Product(String title, String ingredients, String url) {
        this.title = title;
        this.ingredients = ingredients;
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

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
}
