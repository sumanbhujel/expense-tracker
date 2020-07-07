package com.agileproject.expense_tracker.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.agileproject.expense_tracker.MainActivity;
import com.agileproject.expense_tracker.R;
import com.agileproject.expense_tracker.bll.AuthBLL;
import com.agileproject.expense_tracker.helper.EditTextValidation;
import com.agileproject.expense_tracker.helper.Helper;
import com.agileproject.expense_tracker.helper.UserSession;
import com.agileproject.expense_tracker.models.Error;
import com.agileproject.expense_tracker.models.User;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout etEmail, etPassword;
    private AuthBLL authBLL;
    private Button btnLogin;
    private UserSession userSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userSession = new UserSession(this);
        if (userSession.isActive()) {
            navigateDashboard();
        }

        initComponents();
    }

    private void initComponents() {

        authBLL = new AuthBLL();

        etEmail = findViewById(R.id.et_login_email);
        etPassword = findViewById(R.id.et_login_password);
        btnLogin = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
    }

    private void signIn() {
        if (isSignInDetailsValid()) {
            Helper.StrictMode();

            String email = etEmail.getEditText().getText().toString().trim();
            String password = etPassword.getEditText().getText().toString().trim();

            User authUser = authBLL.loginUser(email, password);

            if (authUser != null) {
                userSession.startSession(authUser);
                navigateDashboard();
            }
        }
    }

    private boolean isSignInDetailsValid() {
        if (EditTextValidation.isEmpty(etEmail) | EditTextValidation.isEmpty(etPassword)) {
            return false;
        }
        return true;
    }

    private void navigateDashboard() {
        Intent mainActivity = new Intent(this, MainActivity.class);
        mainActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(mainActivity);

        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();

        authBLL.setAuthListener(new AuthBLL.AuthListener() {
            @Override
            public void onError(Error error) {
                if (error.getField().equals("email")) {
                    etEmail.setError(error.getMessage());
                } else {
                    etPassword.setError(error.getMessage());
                }

            }
        });
    }

    public void showSignUpForm(View view) {
        startActivity(new Intent(this, SignupActivity.class));
    }

}