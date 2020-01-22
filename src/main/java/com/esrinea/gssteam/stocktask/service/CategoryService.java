/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esrinea.gssteam.stocktask.service;

import com.esrinea.gssteam.stocktask.dto.CategoryDTO;
import java.util.List;

/**
 *
 * @author passant.swelum
 */
public interface CategoryService {
    List<CategoryDTO> getCategories();
    CategoryDTO getCategoryByName(String name);
    CategoryDTO getCategoryById(int categoryId);
    int saveCategory(CategoryDTO category);
    int updateCategory(CategoryDTO category);
    void deleteCategory(int categoryId);
}
