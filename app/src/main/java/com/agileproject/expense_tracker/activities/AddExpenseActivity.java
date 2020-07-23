package com.agileproject.expense_tracker.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.agileproject.expense_tracker.R;

public class AddExpenseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        getSupportActionBar().setTitle("Expense Transaction");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}