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
import com.agileproject.expense_tracker.helper.UserSession;
import com.agileproject.expense_tracker.models.Category;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class ExpCategoriesBottomSheet extends BottomSheetDialogFragment implements BSCategoriesAdapter.CategorySelectedListener {

    private RecyclerView allCategoriesContainer;
    private List<Category> allExpCat;
    private CategoryBLL categoryBLL;
    private String creator;
    private ExpBottomSheetListener expBottomSheetListener;


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
        allExpCat = new ArrayList<>();
        UserSession userSession = new UserSession(getActivity());
        creator = userSession.getUser().get_id();
    }

    public interface ExpBottomSheetListener {
        void onExpCatSelected(Category expCategory);
    }
    @Override
    public void onCategorySelected(Category category) {
        expBottomSheetListener.onExpCatSelected(category);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            expBottomSheetListener = (ExpBottomSheetListener) context;
        } catch (ClassCastException cce) {
            throw new ClassCastException(context.toString() + " must implement Expense BottomSheetListener");
        }
    }
}
