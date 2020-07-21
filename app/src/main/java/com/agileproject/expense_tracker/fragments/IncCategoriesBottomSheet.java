package com.agileproject.expense_tracker.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.agileproject.expense_tracker.R;
import com.agileproject.expense_tracker.adapters.BSCategoriesAdapter;
import com.agileproject.expense_tracker.bll.CategoryBLL;
import com.agileproject.expense_tracker.helper.Helper;
import com.agileproject.expense_tracker.helper.UserSession;
import com.agileproject.expense_tracker.models.Category;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class IncCategoriesBottomSheet extends BottomSheetDialogFragment
        implements BSCategoriesAdapter.CategorySelectedListener {

    private RecyclerView allCategoriesContainer;
    private List<Category> allIncCat;
    private CategoryBLL categoryBLL;
    private String creator;
    private IncBottomSheetListener incBottomSheetListener;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View categoriesView = inflater.inflate(R.layout.layout_bottom_sheet, container, false);
        allCategoriesContainer = categoriesView.findViewById(R.id.all_categories_container);
        allCategoriesContainer.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        return categoriesView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        categoryBLL = new CategoryBLL();
        allIncCat = new ArrayList<>();
        UserSession userSession = new UserSession(getActivity());
        creator = userSession.getUser().get_id();
    }

    @Override
    public void onCategorySelected(Category category) {
        incBottomSheetListener.onIncCatSelected(category);
    }

    @Override
    public void onResume() {
        super.onResume();

        BSCategoriesAdapter incCatAdapter = new BSCategoriesAdapter(getActivity(), getAllIncomeCategories());
        incCatAdapter.categorySelectedListener = this;
        allCategoriesContainer.setAdapter(incCatAdapter);
    }

    private List<Category> getAllIncomeCategories() {
        Helper.StrictMode();
        allIncCat = categoryBLL.getIncomeCategories();
        for (Category category : categoryBLL.getUserCategories(creator)) {
            if (category.getType().equals("Income")) {
                allIncCat.add(category);
            }
        }
        return allIncCat;
    }



    public interface IncBottomSheetListener {
        void onIncCatSelected(Category incCategory);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            incBottomSheetListener = (IncBottomSheetListener) context;
        } catch (ClassCastException cce) {
            throw new ClassCastException(context.toString() + " must implement Income BottomSheetListener");
        }
    }
}
