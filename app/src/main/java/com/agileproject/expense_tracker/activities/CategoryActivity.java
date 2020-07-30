package com.agileproject.expense_tracker.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.agileproject.expense_tracker.R;
import com.agileproject.expense_tracker.bll.CategoryBLL;
import com.agileproject.expense_tracker.fragments.AllCategoriesFragment;
import com.agileproject.expense_tracker.fragments.CategoryUpdateDialog;
import com.agileproject.expense_tracker.helper.ConfirmationDialog;
import com.agileproject.expense_tracker.helper.Helper;

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
        CategoryUpdateDialog dialog = new CategoryUpdateDialog();
        dialog.getCategory(catId);
        dialog.show(getSupportFragmentManager(), "UPDATE CATEGORY");
    }

    public void confirmCategoryDelete(String catId) {
        categoryId = catId;
        confirmationDialog = new ConfirmationDialog("Delete Category?", "Are you sure you want to delete this category?");
        confirmationDialog.show(getSupportFragmentManager(), "DET");
    }

    @Override
    public void onSure() {
        Helper.StrictMode();
        if (new CategoryBLL().deleteUserCategory(categoryId)) {
            Toast.makeText(this, "Category Deleted !", Toast.LENGTH_SHORT).show();
            loadFragment(AllCategoriesFragment.newInstance("Categories"));
        }
    }

    @Override
    public void onCancel() {
        confirmationDialog.dismiss();
    }
}