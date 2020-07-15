package com.agileproject.expense_tracker.bll;

import com.agileproject.expense_tracker.api.CategoryAPI;
import com.agileproject.expense_tracker.helper.ApiError;
import com.agileproject.expense_tracker.helper.Retrofit;
import com.agileproject.expense_tracker.models.Category;
import com.agileproject.expense_tracker.models.Errors;
import com.agileproject.expense_tracker.response.CategoryResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class CategoryBLL {

    private Gson gson;
    private CategoryAPI categoryAPI;
    private ApiError apiError;
    private CategoryListener categoryListener;

    public CategoryBLL() {
        categoryAPI = Retrofit.getInstance().create(CategoryAPI.class);
        gson = new GsonBuilder().create();
        apiError = new ApiError();
    }

    //adding new category
    public CategoryResponse addNewCategory(Category category) {
        CategoryResponse categoryResponse = null;
        Call<CategoryResponse> addCategoryCall = categoryAPI.createNewCategory(category);
        try {
            Response<CategoryResponse> addCategoryResponse = addCategoryCall.execute();
            if (!addCategoryResponse.isSuccessful()) {
                apiError = gson.fromJson(addCategoryResponse.errorBody().string(), ApiError.class);
                categoryListener.onError(apiError.getErrors());
                //return  categoryResponse;
            } else if (addCategoryResponse.body().getCategory() != null) {
                categoryResponse = addCategoryResponse.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return categoryResponse;
    }



    public interface CategoryListener {
        void onError(Errors error);
    }

    public void setCategoryListener(CategoryListener categoryListener) {
        this.categoryListener = categoryListener;
    }
}
