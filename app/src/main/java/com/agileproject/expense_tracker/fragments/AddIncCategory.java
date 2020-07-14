package com.agileproject.expense_tracker.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.agileproject.expense_tracker.R;
import com.agileproject.expense_tracker.bll.CategoryBLL;
import com.agileproject.expense_tracker.helper.EditTextValidation;
import com.agileproject.expense_tracker.helper.Helper;
import com.agileproject.expense_tracker.helper.UserSession;
import com.agileproject.expense_tracker.models.Category;
import com.agileproject.expense_tracker.models.Errors;
import com.agileproject.expense_tracker.response.CategoryResponse;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;


public class AddIncCategory extends Fragment {

    private TextInputLayout etIncCategoryName;
    private CategoryBLL categoryBLL;
    private UserSession userSession;
    private List<Category> categoryList;
    private ImageView imgIncCat;
    private String icon = "others.png";


    public AddIncCategory() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categoryBLL = new CategoryBLL();
        userSession = new UserSession(getActivity());

        categoryList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_add_inc_category, container, false);

        View incCategoryView = inflater.inflate(R.layout.fragment_add_inc_category, container, false);


        imgIncCat = incCategoryView.findViewById(R.id.img_inc_cat);
        etIncCategoryName = incCategoryView.findViewById(R.id.et_inc_cat);
        Button btnAddIncCat = incCategoryView.findViewById(R.id.btn_add_inc_cat);

        RecyclerView incCategoriesContainer = incCategoryView.findViewById(R.id.inc_categories_container);
        incCategoriesContainer.setLayoutManager(new GridLayoutManager(getActivity(), 4));


        btnAddIncCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addIncomeCategory();
            }
        });

        return incCategoryView;
    }

    private void addIncomeCategory() {
        if (!EditTextValidation.isEmpty(etIncCategoryName)) {
            Helper.StrictMode();
            String categoryName = etIncCategoryName.getEditText().getText().toString().trim();
            String creator = userSession.getUser().get_id();
            Category category = new Category(categoryName, "Income", icon, creator);

            CategoryResponse categoryResponse = categoryBLL.addNewCategory(category);
            if (categoryResponse != null) {
                Toast.makeText(getActivity(), categoryResponse.getMessage(), Toast.LENGTH_LONG).show();
                etIncCategoryName.getEditText().setText("");
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        categoryBLL.setCategoryListener(new CategoryBLL.CategoryListener() {
            @Override
            public void onError(Errors error) {
                if (error.getField().equals("name")) {
                    etIncCategoryName.setError(error.getMessage());
                }
            }
        });
    }
}