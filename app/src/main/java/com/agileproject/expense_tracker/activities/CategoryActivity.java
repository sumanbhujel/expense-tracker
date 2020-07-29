package com.agileproject.expense_tracker.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.agileproject.expense_tracker.R;
import com.agileproject.expense_tracker.fragments.AllCategoriesFragment;
import com.agileproject.expense_tracker.helper.ConfirmationDialog;

public class CategoryActivity extends AppCompatActivity implements ConfirmationDialog.ConfirmationDialogListener {


    private String categoryId = "";
    private ConfirmationDialog confirmationDialog;

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

        loadFragment(AllCategoriesFragment.newInstance("Categories"));
    }

    private void loadFragment(Fragment activeFragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, activeFragment).commit();

    }

    public void showCatUpdateDialog(String catId) {

    }

    public void confirmCategoryDelete(String catId) {

    }

    @Override
    public void onSure() {

    }

    @Override
    public void onCancel() {

    }
}