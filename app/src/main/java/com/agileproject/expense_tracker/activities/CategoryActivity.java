package com.agileproject.expense_tracker.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.agileproject.expense_tracker.R;

public class CategoryActivity extends AppCompatActivity  {


    private String categoryId = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        getSupportActionBar().setTitle("Categories");
        Button btnAddNewCat = findViewById(R.id.btn_add_new_cat);
        btnAddNewCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i5 = new Intent(CategoryActivity.this, AddCategoryActivity.class);
                startActivity(i5);
            }
        });

        //loadFragment(AllCategoriesFragment.newInstance("Categories"));
    }
}