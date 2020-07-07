package com.agileproject.expense_tracker.bll;

import com.agileproject.expense_tracker.api.AuthAPI;
import com.agileproject.expense_tracker.helper.ApiError;
import com.agileproject.expense_tracker.helper.Retrofit;
import com.agileproject.expense_tracker.models.Error;
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

    public boolean registerUser(User user) {
        boolean isSignUpSuccessful = false;

        Call<UserResponse> signUpCall = authAPI.registerUser(user);

        try {
            Response<UserResponse> signUpResponse = signUpCall.execute();
            if (!signUpResponse.isSuccessful()) {
                apiError = gson.fromJson(signUpResponse.errorBody().string(), ApiError.class);
                authListener.onError(apiError.getError());
//                return isSignUpSuccessful;
            } else if (signUpResponse.body().getUser() != null) {
                isSignUpSuccessful = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return isSignUpSuccessful;
    }


    public interface AuthListener{
        void onError(Error error);
    }

    public void setAuthListener(AuthListener authListener) {
        this.authListener = authListener;
    }


}
