package com.agileproject.expense_tracker.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.agileproject.expense_tracker.R;
import com.agileproject.expense_tracker.adapters.AddCategoryAdapter;
import com.agileproject.expense_tracker.fragments.AddIncCategory;
import com.google.android.material.tabs.TabLayout;

public class AddCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        getSupportActionBar().setTitle("Add Category");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ViewPager viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        AddCategoryAdapter addCategoryAdapter = new AddCategoryAdapter(getSupportFragmentManager());
        //addCategoryAdapter.addFragment(new AddExpCategory(), "Expense");
        addCategoryAdapter.addFragment(new AddIncCategory(), "Income");

        viewPager.setAdapter(addCategoryAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}