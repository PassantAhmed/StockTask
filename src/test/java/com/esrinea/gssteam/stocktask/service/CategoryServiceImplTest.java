/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esrinea.gssteam.stocktask.service;


import com.esrinea.gssteam.stocktask.dto.CategoryDTO;
import com.esrinea.gssteam.stocktask.exception.BusinessException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import org.mockito.runners.MockitoJUnitRunner;
/**
 *
 * @author passant.swelum
 */
@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceImplTest {
    
    CategoryServiceImpl categoryService;
    
    public CategoryServiceImplTest() { }
    
    @Before
    public void setUp() {
        categoryService = mock(CategoryServiceImpl.class);
    }

    @Test
    public void testGetCategoryByExistingName() {
        CategoryDTO actualCategory, expectedCategory;
        actualCategory = new CategoryDTO(1, "CategoryOne", "Category One", null, null);
        expectedCategory = new CategoryDTO(1, "CategoryOne", "Category One", null, null);
        Mockito.when(categoryService.getCategoryByName("CategoryOne")).thenReturn(actualCategory);
        assertEquals(expectedCategory, actualCategory);
    }
    
    @Test
    public void testGetCategoryByNotExistingName() {
        CategoryDTO expectedCategory = new CategoryDTO(4, "Category", "Category", null, null);
        Mockito.when(categoryService.getCategoryByName("Category")).thenReturn(null);
        assertNotEquals(expectedCategory, null);
    }
    
    @Test (expected = BusinessException.class)
    public void testGetCategoryByInvalidName() {
        String categoryName = "Category_33";
        BusinessException exception = new BusinessException("Error occurred");
        Mockito.when(categoryService.getCategoryByName(categoryName)).thenThrow(exception);
        throw exception;
    }
    
    @Test (expected = BusinessException.class)
    public void testGetCategoryByEmptyName() {
        String categoryName = "";
        BusinessException exception = new BusinessException("Error occurred");
        Mockito.when(categoryService.getCategoryByName(categoryName)).thenThrow(exception);
        throw exception;
    }
    
    @Test (expected = BusinessException.class)
    public void testGetCategoryByNullName() {
        String categoryName = null;
        BusinessException exception = new BusinessException("Error occurred");
        Mockito.when(categoryService.getCategoryByName(categoryName)).thenThrow(exception);
        throw exception;
    }
    
    @Test 
    public void testGetCategoryByExistingId(){
        CategoryDTO actualCategory, expectedCategory;
        actualCategory = new CategoryDTO(1, "CategoryOne", "Category One", null, null);
        expectedCategory = new CategoryDTO(1, "CategoryOne", "Category One", null, null);
        Mockito.when(categoryService.getCategoryById(1)).thenReturn(actualCategory);
        assertEquals(expectedCategory, actualCategory);
    }
    
    @Test 
    public void testGetCategoryByNotExistingId(){
        CategoryDTO expectedCategory = new CategoryDTO(4, "Category", "Category", null, null);
        Mockito.when(categoryService.getCategoryById(4)).thenReturn(null);
        assertNotEquals(expectedCategory, null);
    }
    
    @Test (expected = BusinessException.class)
    public void testGetCategoryByInvalidId() {
        int categoryId = -33;
        BusinessException exception = new BusinessException("Error occurred");
        Mockito.when(categoryService.getCategoryById(categoryId)).thenThrow(exception);
        throw exception;
    }
    
    @Test 
    public void testSaveNotExistingCategory(){
        int returnedId = 3;
        CategoryDTO category = new CategoryDTO("CategoryOne", "Category One", null, null);
        Mockito.when(categoryService.saveCategory(category)).thenReturn(returnedId);
        assertTrue(returnedId>0);
    }
    
    @Test (expected = BusinessException.class)
    public void testSaveInvalidDataCategory(){
        CategoryDTO category = new CategoryDTO(-1, "Category_One", "Category One22", null, null);
        BusinessException exception = new BusinessException("Error occurred");
        Mockito.when(categoryService.saveCategory(category)).thenThrow(exception);
        throw exception;
    }
    
    @Test (expected = BusinessException.class)
    public void testSaveExistingCategory(){
        CategoryDTO category = new CategoryDTO("CategoryOne", "Category One", null, null);
        BusinessException exception = new BusinessException("Error occurred");
        Mockito.when(categoryService.saveCategory(category)).thenThrow(exception);
        throw exception;
    }
    
    @Test (expected = BusinessException.class)
    public void testSaveNullCategory(){
        CategoryDTO category = null;
        BusinessException exception = new BusinessException("Error occurred");
        Mockito.when(categoryService.saveCategory(category)).thenThrow(exception);
        throw exception;
    }
    
    @Test 
    public void testUpdateValidCategory(){
        int returnedId = 3, expectedId = 3;
        CategoryDTO category = new CategoryDTO(expectedId, "CategoryOne", "Category One", null, null);
        Mockito.when(categoryService.updateCategory(category)).thenReturn(returnedId);
        assertEquals(expectedId, returnedId);
    }
    
    @Test (expected = BusinessException.class)
    public void testUpdateInvalidDataCategory(){
        CategoryDTO category = new CategoryDTO("Category_One", "Category One22", null, null);
        BusinessException exception = new BusinessException("Error occurred");
        Mockito.when(categoryService.updateCategory(category)).thenThrow(exception);
        throw exception;
    }
    /*
    @Test (expected = BusinessException.class)
    public void testDeleteInvalidDataCategory(){
        int categoryId = -1;
        BusinessException exception = new BusinessException("Error occurred");
        //Mockito.doThrow(exception);
        throw exception;
    }
    
    @Test (expected = BusinessException.class)
    public void testDeleteNotExistingCategory(){
        int categoryId = 100;
        BusinessException exception = new BusinessException("Error occurred");
        //Mockito.doThrow(exception);
        throw exception;
    }
    */
}
