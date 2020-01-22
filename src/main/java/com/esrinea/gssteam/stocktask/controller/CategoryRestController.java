/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esrinea.gssteam.stocktask.controller;

import com.esrinea.gssteam.stocktask.dto.CategoryDTO;
import com.esrinea.gssteam.stocktask.dto.StockResponse;
import com.esrinea.gssteam.stocktask.service.CategoryService;
import com.esrinea.gssteam.stocktask.util.ConstantsHelper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author passant.swelum
 */
@RestController
@RequestMapping("/api")
public class CategoryRestController {
    
    @Autowired
    private CategoryService categoryService;
    
    @GetMapping("/categories")
    public StockResponse<List<CategoryDTO>> getCategories(){
        StockResponse<List<CategoryDTO>> response = 
                new StockResponse(ConstantsHelper.SUCCESS_CODE_RESPONSE, ConstantsHelper.SUCCESS_MESSAGE
                , categoryService.getCategories());
        return response;
    }
  
    @GetMapping("/categories/id/{categoryId}")
    public StockResponse<CategoryDTO> getCategoryById(@PathVariable int categoryId){
        StockResponse<CategoryDTO> response = new StockResponse(ConstantsHelper.SUCCESS_CODE_RESPONSE, ConstantsHelper.SUCCESS_MESSAGE
                , categoryService.getCategoryById(categoryId));
        return response;
    }
    
    @GetMapping("/categories/name/{categoryName}")
    public StockResponse<CategoryDTO> getCategoryByName(@PathVariable String categoryName){
        StockResponse<CategoryDTO> response = new StockResponse(ConstantsHelper.SUCCESS_CODE_RESPONSE, ConstantsHelper.SUCCESS_MESSAGE
                , categoryService.getCategoryByName(categoryName));
        return response;
    }
    
    @PostMapping("/categories")
    public StockResponse<Integer> addCategory(@RequestBody CategoryDTO category){
        StockResponse<Integer> response = new StockResponse(ConstantsHelper.SUCCESS_CODE_RESPONSE, ConstantsHelper.SUCCESS_MESSAGE
                , categoryService.saveCategory(category));
        return response;
    }
    
    @RequestMapping(value="/categories", method = RequestMethod.PUT)
    public StockResponse<Integer> updateCategory(@RequestBody CategoryDTO category){
        StockResponse<Integer> response = new StockResponse(ConstantsHelper.SUCCESS_CODE_RESPONSE, ConstantsHelper.SUCCESS_MESSAGE
                , categoryService.updateCategory(category));
        return response;
    }
    
    @DeleteMapping("/categories/{categoryId}")
    public void deleteCategory(@PathVariable int categoryId){
        categoryService.deleteCategory(categoryId); 
    }

}
