package com.agileproject.expense_tracker;

import com.agileproject.expense_tracker.bll.CategoryBLL;
import com.agileproject.expense_tracker.models.Category;
import com.agileproject.expense_tracker.response.CategoryResponse;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

public class CategoriesUnitTest {

    private CategoryBLL categoryBLL;

    @Before
    public void setup(){
        categoryBLL = new CategoryBLL();
    }

    @Test
    public void testA_emptyIncCatName_shouldNotAddANewCategory() {
        Category newCategory = new Category("", "Income", "others.png", "5da6c2393cc60c2cb021cdcb");
        CategoryResponse categoryResponse = categoryBLL.addNewCategory(newCategory);
        assertNull(categoryResponse);
    }

    @Test
    public void testB_validIncCatName_shouldAddNewACategory() {
        Category newCategory = new Category("Test Income", "Income", "others.png", "5da6c2393cc60c2cb021cdcb");
        CategoryResponse categoryResponse = categoryBLL.addNewCategory(newCategory);
        assertEquals(newCategory.getName(), categoryResponse.getCategory().getName());
    }

    @Test
    public void testC_existingIncCatName_shouldNotAddANewCategory() {
        Category newCategory = new Category("Test Income", "Income", "others.png", "5da6c2393cc60c2cb021cdcb");
        CategoryResponse categoryResponse = categoryBLL.addNewCategory(newCategory);
        assertNull(categoryResponse);
    }
}
