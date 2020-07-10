package com.agileproject.expense_tracker.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.agileproject.expense_tracker.R;
import com.agileproject.expense_tracker.bll.AuthBLL;
import com.agileproject.expense_tracker.helper.Helper;
import com.agileproject.expense_tracker.helper.UserSession;
import com.agileproject.expense_tracker.models.User;
import com.agileproject.expense_tracker.response.UserResponse;
import com.google.android.material.textfield.TextInputLayout;

public class ProfileActivity extends AppCompatActivity {


    private TextView incomeValue, expenseValue, balance;
    private TextInputLayout tvFirstName, tvLastName, tvEmailAdd;
    private User currentUser;
    private TextView tvFullname, tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        currentUser = new UserSession(this).getUser();

        tvFullname = findViewById(R.id.tv_fullname);
        tvEmail = findViewById(R.id.tv_profile_email);

        incomeValue = findViewById(R.id.income_value);
        expenseValue = findViewById(R.id.expense_value);
        balance = findViewById(R.id.balance_value);

        tvFirstName = findViewById(R.id.dt_first_name);
        tvLastName = findViewById(R.id.dt_last_name);
        tvEmailAdd = findViewById(R.id.dt_email_id);

        tvFullname.setText(currentUser.getFirstName() + " " + currentUser.getLastName());
        tvEmail.setText(currentUser.getEmail());
        tvFirstName.getEditText().setText(currentUser.getFirstName());
        tvLastName.getEditText().setText(currentUser.getLastName());
        tvEmailAdd.getEditText().setText(currentUser.getEmail());

    }

    @Override
    protected void onResume() {
        super.onResume();
        showIncomeExpense();
    }

    public void showIncomeExpense() {
        Helper.StrictMode();
        AuthBLL authImpl = new AuthBLL();
        UserResponse userResponse = authImpl.getIncomeExpense(currentUser.get_id());
        double income = userResponse.getUser().getTotalIncome();
        double expense = userResponse.getUser().getTotalExpense();
        incomeValue.setText(String.valueOf(income));
        expenseValue.setText(String.valueOf(expense));
        balance.setText(String.valueOf(income - expense));
    }
}