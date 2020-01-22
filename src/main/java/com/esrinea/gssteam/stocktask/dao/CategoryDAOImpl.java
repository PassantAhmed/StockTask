/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esrinea.gssteam.stocktask.dao;

import com.esrinea.gssteam.stocktask.entity.Category;
import com.esrinea.gssteam.stocktask.util.DatabaseHelper;
import java.sql.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author passant.swelum
 */
@Repository
public class CategoryDAOImpl implements CategoryDAO{
    
    @Override
    public List<Category> getCategories(Session session) {
        // select all categories except the deleted ones 
        String stringQuery = "from Category where deletedFlag=:deletedFlag";
        Query<Category> categoriesQuery = session.createQuery(stringQuery,Category.class);
        categoriesQuery.setParameter("deletedFlag", DatabaseHelper.DELETED_FLAG_FALSE);
        List<Category> categories = categoriesQuery.getResultList();
        return categories;
    }
    
    @Override
    public Category getCategory(Session session, String categoryName) {
        Category category = null;
        String stringQuery = "from Category where categoryName=:categoryName and deletedFlag=:deletedFlag";
        Query categoryQuery = session.createQuery(stringQuery);
        categoryQuery.setParameter("categoryName", categoryName);
        categoryQuery.setParameter("deletedFlag", DatabaseHelper.DELETED_FLAG_FALSE);
        Object tempObject = categoryQuery.getSingleResult();
        if(tempObject!=null){
            category = (Category) tempObject; 
        }   
        return category;
    }
    
    @Override
    public Category getCategory(Session session, int categoryId) {
        String stringQuery = "from Category where id=:id and deletedFlag=:deletedFlag";
        Query categoryQuery = session.createQuery(stringQuery);
        categoryQuery.setParameter("id", categoryId);
        categoryQuery.setParameter("deletedFlag", DatabaseHelper.DELETED_FLAG_FALSE);
        Object tempObject = categoryQuery.getSingleResult();
        Category category = null;
        if(tempObject!=null){
            category = (Category) tempObject;        
        }
        return category;
    }
    
    @Override
    public int saveCategory(Session session, Category category){
        category.setCreatedDate(new Date(System.currentTimeMillis()));
        category.setLastModifiedDate(new Date(System.currentTimeMillis()));
        category.setDeletedFlag(DatabaseHelper.DELETED_FLAG_FALSE);
        session.save(category);
        return category.getId();
    }
    
    @Override
    public int updateCategory(Session session, Category category) {
        // Updating last modified date and deleted flag
        category.setLastModifiedDate(new Date(System.currentTimeMillis()));
        category.setDeletedFlag(DatabaseHelper.DELETED_FLAG_FALSE);
        session.update(category);
        return category.getId();
    }

    @Override
    public void deleteCategory(Session session, int categoryId) {
        // Updating last modified date and deleted flag
        String stringQuery = "update Category set lastModifiedDate=:lastModifiedDate, deletedFlag=:deletedFlag where id=:id";
        Query categoryQuery = session.createQuery(stringQuery);
        categoryQuery.setParameter("lastModifiedDate", new Date(System.currentTimeMillis()));
        categoryQuery.setParameter("deletedFlag", DatabaseHelper.DELETED_FLAG_TRUE);
        categoryQuery.setParameter("id", categoryId);
        categoryQuery.executeUpdate();
    }
}
