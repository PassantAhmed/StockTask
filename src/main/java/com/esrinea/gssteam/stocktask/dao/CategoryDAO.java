/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esrinea.gssteam.stocktask.dao;

import com.esrinea.gssteam.stocktask.entity.Category;
import java.util.List;
import org.hibernate.Session;


/**
 *
 * @author passant.swelum
 */
public interface CategoryDAO {
    List<Category> getCategories(Session session);
    Category getCategory(Session session, String categoryName);
    Category getCategory(Session session, int categoryId);
    int saveCategory(Session session, Category category);
    int updateCategory(Session session, Category category);
    void deleteCategory(Session session, int categoryId);
}
