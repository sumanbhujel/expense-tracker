package com.agileproject.expense_tracker;

import com.agileproject.expense_tracker.bll.CategoryBLL;
import com.agileproject.expense_tracker.models.Category;
import com.agileproject.expense_tracker.response.CategoryResponse;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

public class CategoriesUnitTest {

    private CategoryBLL categoryBLL;

    @Before
    public void setup(){
        categoryBLL = new CategoryBLL();
    }

    //adding new income category test
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

    //adding expense category test
    @Test
    public void testD_emptyExpCatName_shouldNotAddANewCategory() {
        Category newCategory = new Category("", "Expense", "others.png", "5da6c2393cc60c2cb021cdcb");
        CategoryResponse categoryResponse = categoryBLL.addNewCategory(newCategory);
        assertNull(categoryResponse);
    }

    @Test
    public void testE_validExpCatName_shouldAddNewACategory() {
        Category newCategory = new Category("Test Expense", "Expense", "others.png", "5da6c2393cc60c2cb021cdcb");
        CategoryResponse categoryResponse = categoryBLL.addNewCategory(newCategory);
        assertEquals(newCategory.getName(), categoryResponse.getCategory().getName());
    }

    @Test
    public void testF_existingExpCatName_shouldNotAddANewCategory() {
        Category newCategory = new Category("Test Expense", "Expense", "others.png", "5da6c2393cc60c2cb021cdcb");
        CategoryResponse categoryResponse = categoryBLL.addNewCategory(newCategory);
        assertNull(categoryResponse);
    }

    //showing categorylist test
    @Test
    public void testG_defaultExpenseCategoriesSize() {
        List<Category> expCategories = categoryBLL.getExpenseCategories();
        assertEquals(12, expCategories.size());
    }

    @Test
    public void testH_defaultIncomeCategoriesSize() {
        List<Category> incCategories = categoryBLL.getIncomeCategories();
        assertEquals(4, incCategories.size());
    }



}
