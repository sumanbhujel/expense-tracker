package com.agileproject.expense_tracker.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.agileproject.expense_tracker.R;
import com.agileproject.expense_tracker.bll.AuthBLL;
import com.agileproject.expense_tracker.helper.Helper;
import com.agileproject.expense_tracker.helper.UserSession;
import com.agileproject.expense_tracker.response.UserResponse;

public class DashboardActivity extends AppCompatActivity implements CardView.OnClickListener {

    private UserSession userSession;
    private TextView incomeValue, expenseValue, balance;
    CardView cardViewCategory, cardViewAddExpense, cardViewAddIncome,
            cardViewTransaction, cardShowChart, cardViewProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().setTitle("Dashboard");
        userSession = new UserSession(this);

        incomeValue = findViewById(R.id.tv_income_value);
        expenseValue = findViewById(R.id.tv_expense_value);
        balance = findViewById(R.id.tv_netbalance_value);

        cardViewCategory = findViewById(R.id.cardCategory);
        cardViewAddExpense = findViewById(R.id.cardAddExpenses);
        cardViewAddIncome = findViewById(R.id.cardAddIncome);
        cardViewTransaction = findViewById(R.id.cardTransaction);
        cardShowChart = findViewById(R.id.cardShowChart);
        cardViewProfile = findViewById(R.id.cardViewProfile);

        cardViewCategory.setOnClickListener(this);
        cardViewAddExpense.setOnClickListener(this);
        cardViewTransaction.setOnClickListener(this);
        cardViewAddIncome.setOnClickListener(this);
        cardShowChart.setOnClickListener(this);
        cardViewProfile.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.cardViewProfile:
                Intent i1 = new Intent(DashboardActivity.this, ProfileActivity.class);
                startActivity(i1);
                break;
            case R.id.cardCategory:
                Intent i2 = new Intent(DashboardActivity.this, CategoryActivity.class);
                startActivity(i2);
                break;
            case R.id.cardAddIncome:
                Intent i3 = new Intent(DashboardActivity.this, AddIncomeActivity.class);
                startActivity(i3);
                break;
            case R.id.cardAddExpenses:
                Intent i4 = new Intent(DashboardActivity.this, AddExpenseActivity.class);
                startActivity(i4);
                break;
            case R.id.cardTransaction:
//                Intent i3 = new Intent(DashboardActivity.this, TransactionListActivity.class);
//                startActivity(i3);
                break;

            case R.id.cardShowChart:
//                Intent i5 = new Intent(DashboardActivity.this, ChartActivity.class);
//                startActivity(i5);
                break;


        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        showIncomeExpense();
    }

    public void showIncomeExpense() {
        Helper.StrictMode();
        AuthBLL authImpl = new AuthBLL();
        UserResponse userResponse = authImpl.getIncomeExpense(userSession.getUser().get_id());
        double income = userResponse.getUser().getTotalIncome();
        double expense = userResponse.getUser().getTotalExpense();
        incomeValue.setText(String.valueOf(income));
        expenseValue.setText(String.valueOf(expense));
        balance.setText(String.valueOf(income - expense));
    }


}