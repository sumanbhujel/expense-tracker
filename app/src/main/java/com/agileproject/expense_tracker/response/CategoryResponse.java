package com.agileproject.expense_tracker.response;

import com.agileproject.expense_tracker.models.Category;

import java.util.List;

public class CategoryResponse {

    private List<Category> categories;
    private Category category;
    private String message;

    public List<Category> getCategories() {

        return categories;
    }

    public Category getCategory() {

        return category;
    }

    public String getMessage() {

        return message;
    }
}
