package com.agileproject.expense_tracker.bll;

import com.agileproject.expense_tracker.api.AuthAPI;
import com.agileproject.expense_tracker.helper.ApiError;
import com.agileproject.expense_tracker.helper.Retrofit;
import com.agileproject.expense_tracker.models.Errors;
import com.agileproject.expense_tracker.models.User;
import com.agileproject.expense_tracker.response.UserResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;


public class AuthBLL {

    private AuthAPI authAPI;
    private Gson gson;
    private ApiError apiError;
    private AuthBLL.AuthListener authListener;

    public AuthBLL() {

        authAPI = Retrofit.getInstance().create(AuthAPI.class);
        gson = new GsonBuilder().create();
        apiError = new ApiError();
    }

    //user registration function
    public boolean registerUser(User user) {
        boolean isSignUpSuccessful = false;

        Call<UserResponse> signUpCall = authAPI.registerUser(user);

        try {
            Response<UserResponse> signUpResponse = signUpCall.execute();
            if (!signUpResponse.isSuccessful()) {
                apiError = gson.fromJson(signUpResponse.errorBody().string(), ApiError.class);
                  authListener.onError(apiError.getErrors());
//                return isSignUpSuccessful;
            } else if (signUpResponse.body().getUser() != null) {
                isSignUpSuccessful = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return isSignUpSuccessful;
    }

    //user login function
    public User loginUser(String email, String password) {
        User user = null;
        Call<UserResponse> loginCall = authAPI.loginUser(email, password);

        try {
            Response<UserResponse> loginResponse = loginCall.execute();
            if (!loginResponse.isSuccessful()) {
                apiError = gson.fromJson(loginResponse.errorBody().string(), ApiError.class);
//                authListener.onError(apiError.getErrors());
//                return user;
            } else if (loginResponse.body().getUser() != null) {
                user = loginResponse.body().getUser();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return user;
    }

    public UserResponse getIncomeExpense(String id) {
        UserResponse userResponse = null;
        Call<UserResponse> userResponseCall = authAPI.getIncomeExpense(id);
        try {
            Response<UserResponse> incomeExpenseCall = userResponseCall.execute();
            if (!incomeExpenseCall.isSuccessful()) {
                return userResponse;
            }
            userResponse = incomeExpenseCall.body();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return userResponse;
    }


    public interface AuthListener {
        void onError(Errors errors);
    }

    public void setAuthListener(AuthListener authListener) {
        this.authListener = authListener;
    }


}
