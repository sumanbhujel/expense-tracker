package com.agileproject.expense_tracker.api;

import com.agileproject.expense_tracker.models.Category;
import com.agileproject.expense_tracker.response.CategoryResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CategoryAPI {

    //    add a new category
    @POST("categories")
    Call<CategoryResponse> createNewCategory(@Body Category category);
}
