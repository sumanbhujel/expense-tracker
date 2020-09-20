package com.agileproject.expense_tracker.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;

import com.agileproject.expense_tracker.R;
import com.agileproject.expense_tracker.bll.CategoryBLL;
import com.agileproject.expense_tracker.helper.Helper;
import com.agileproject.expense_tracker.models.Category;
import com.agileproject.expense_tracker.response.CategoryResponse;
import com.google.android.material.textfield.TextInputLayout;

public class CategoryUpdateDialog extends AppCompatDialogFragment {
    private TextInputLayout etCatName;
    private String categoryId = "";
    private Category category = null;
    private CategoryBLL categoryBLL = new CategoryBLL();

    @Override
    public void onResume() {
        super.onResume();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder updateCat = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_cat_update, null);
        updateCat.setView(view)
                .setCancelable(false)
                .setTitle("Update Category!");

        etCatName = view.findViewById(R.id.et_cat_update);
        if (category != null) {
            etCatName.getEditText().setText(category.getName());
        }

        Button btnUpdateCat = view.findViewById(R.id.btn_update_cat);
        btnUpdateCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCategory();

            }
        });

        return updateCat.create();
    }

    public void getCategory(String catId) {
        categoryId = catId;
        Helper.StrictMode();
        category = categoryBLL.getSingleCategory(catId);
    }

    public void updateCategory() {
        Helper.StrictMode();
        Category newCategory = new Category(etCatName.getEditText().getText().toString().trim(), category.getType(), category.getIcon(), category.getCreator());
        CategoryResponse categoryResponse = categoryBLL.updateUserCategory(categoryId, newCategory);
        if (categoryResponse != null && categoryResponse.getCategory() != null) {
            this.dismiss();
            Toast.makeText(this.getContext(), "Category updated", Toast.LENGTH_SHORT).show();
        }
    }
}
