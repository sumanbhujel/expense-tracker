package com.agileproject.expense_tracker.api;

import com.agileproject.expense_tracker.models.User;
import com.agileproject.expense_tracker.response.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AuthAPI {

    // register a new user
    @POST("sign-up")
    Call<UserResponse> registerUser(@Body User user);

    // log the user into the app
    @FormUrlEncoded
    @POST("sign-in")
    Call<UserResponse> loginUser(@Field("email") String email, @Field("password") String password);

}

