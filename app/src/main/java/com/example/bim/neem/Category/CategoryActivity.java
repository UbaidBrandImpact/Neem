package com.example.bim.neem.Category;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.bim.neem.Products.ProductsActivity;
import com.example.bim.neem.R;

public class CategoryActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
    }


    public void onBtnClick(View v) {
        switch (v.getId())
        {
            case R.id.oral_btn:
               goToProductsActivity("oral");
                break;

            case R.id.hair_btn:
                goToProductsActivity("hair");
                break;

            case R.id.skin_btn:
                goToProductsActivity("skin");
                break;

            case R.id.personal_btn:
                goToProductsActivity("personal");
                break;

            case R.id.kitchen_btn:
                goToProductsActivity("kitchen");
                break;
        }
    }

    private void goToProductsActivity(String type){
        Intent intent = new Intent(this,ProductsActivity.class);
        intent.putExtra("category_type",type);
        startActivity(intent);
    }
}
