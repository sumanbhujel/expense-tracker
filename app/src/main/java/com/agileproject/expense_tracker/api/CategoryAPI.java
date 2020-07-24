package com.agileproject.expense_tracker.api;

import com.agileproject.expense_tracker.models.Category;
import com.agileproject.expense_tracker.response.CategoryResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CategoryAPI {

    //add a new category
    @POST("categories")
    Call<CategoryResponse> createNewCategory(@Body Category category);

    //    get all the default income categories
    @GET("categories/income")
    Call<CategoryResponse> fetchIncomeCategories();

    //    get all the user's categories
    @GET("categories/users/{userId}")
    Call<CategoryResponse> fetchUserCategories(@Path("userId") String userId);

    //    get all the default expense categories
    @GET("categories/expense")
    Call<CategoryResponse> fetchExpenseCategories();
}
