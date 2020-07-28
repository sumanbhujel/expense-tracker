package com.agileproject.expense_tracker.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.agileproject.expense_tracker.R;
import com.agileproject.expense_tracker.adapters.CategoriesAdapter;
import com.agileproject.expense_tracker.bll.CategoryBLL;
import com.agileproject.expense_tracker.helper.Helper;
import com.agileproject.expense_tracker.helper.UserSession;
import com.agileproject.expense_tracker.models.Category;

import java.util.ArrayList;
import java.util.List;


public class AllCategoriesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    private static final String ARG_PARAM1 = "param1";
    private List<Category> defaultCategories;
    private List<Category> userCategories;
    private CategoryBLL categoryBLL;
    private UserSession userSession;
    private View divider;
    private TextView myCategories;
    private RecyclerView defaultCategoriesContainer;
    private RecyclerView userCategoriesContainer;

    private SwipeRefreshLayout swipeRefreshLayout;
    int number = 0;

    public AllCategoriesFragment() {
        // Required empty public constructor
    }

    public static AllCategoriesFragment newInstance(String toolbarTitle) {
        AllCategoriesFragment allCategoriesFragment = new AllCategoriesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, toolbarTitle);
        allCategoriesFragment.setArguments(args);
        return allCategoriesFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userSession = new UserSession(getActivity());
        categoryBLL = new CategoryBLL();
        defaultCategories = new ArrayList<>();
        userCategories = new ArrayList<>();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View categoriesView = inflater.inflate(R.layout.fragment_all_categories, container, false);

        divider = categoriesView.findViewById(R.id.divider);
        myCategories = categoriesView.findViewById(R.id.tv_my_categories);
        swipeRefreshLayout = categoriesView.findViewById(R.id.refreshLayout);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                number++;
                swipeRefreshLayout.setRefreshing(false);
                showDefaultCategories();
                showUserCategories();

            }
        });

        defaultCategoriesContainer = categoriesView.findViewById(R.id.def_categories_container);
        defaultCategoriesContainer.setLayoutManager(new LinearLayoutManager(getActivity()));
        userCategoriesContainer = categoriesView.findViewById(R.id.user_categories_container);
        userCategoriesContainer.setLayoutManager(new LinearLayoutManager(getActivity()));

        return categoriesView;
    }

    @Override
    public void onRefresh() {
        showDefaultCategories();
        showUserCategories();
    }

    private void showDefaultCategories() {
        Helper.StrictMode();
        defaultCategories = categoryBLL.getExpenseCategories();
        defaultCategories.addAll(categoryBLL.getIncomeCategories());
        CategoriesAdapter defCategoriesAdapter = new CategoriesAdapter(getActivity(), defaultCategories, "");
        defaultCategoriesContainer.setLayoutManager(new LinearLayoutManager(getActivity()));
        defaultCategoriesContainer.setAdapter(defCategoriesAdapter);
    }

    public void showUserCategories() {
        Helper.StrictMode();
        userCategories = categoryBLL.getUserCategories(userSession.getUser().get_id());
        CategoriesAdapter userCategoriesAdapter = new CategoriesAdapter(getActivity(), userCategories, "user");
        userCategoriesContainer.setLayoutManager(new LinearLayoutManager(getActivity()));
        userCategoriesContainer.setAdapter(userCategoriesAdapter);
    }
}