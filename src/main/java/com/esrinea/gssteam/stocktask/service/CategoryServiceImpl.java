/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esrinea.gssteam.stocktask.service;

import com.esrinea.gssteam.stocktask.dao.CategoryDAO;
import com.esrinea.gssteam.stocktask.dao.ItemDAO;
import com.esrinea.gssteam.stocktask.dao.OperationDAO;
import com.esrinea.gssteam.stocktask.dto.CategoryDTO;
import com.esrinea.gssteam.stocktask.entity.Category;
import com.esrinea.gssteam.stocktask.exception.BusinessException;
import com.esrinea.gssteam.stocktask.util.DatabaseHelper;
import com.esrinea.gssteam.stocktask.util.ErrorConstantsHelper;
import com.esrinea.gssteam.stocktask.util.StockObjectMapper;
import com.esrinea.gssteam.stocktask.util.ValidationHelper;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author passant.swelum
 */
@Service
public class CategoryServiceImpl implements CategoryService, StockObjectMapper{
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @Autowired
    private CategoryDAO categoryDAO;
    
    @Autowired
    private ItemDAO itemDAO;
    
    @Autowired
    private OperationDAO operationDAO;
    
    @Override
    @Transactional
    public List<CategoryDTO> getCategories() {
        List<Category> entityCategories = categoryDAO.getCategories(sessionFactory.getCurrentSession());
        List<CategoryDTO> categories = null;
        for(Category category : entityCategories){
            if(categories==null){
                categories = new ArrayList<CategoryDTO>();
            }
            categories.add((CategoryDTO)mapFromEntity(category));
        }
        return categories;
    }
    
    @Override
    @Transactional
    public CategoryDTO getCategoryByName(String categoryName) {
        CategoryDTO category = null;
        try{
            if(!ValidationHelper.isValidName(categoryName)) {
                throw new BusinessException(ErrorConstantsHelper.INVALID_CATEGORY_DATA);
            }
            // if exist return category 
            Category entityCategory = categoryDAO.getCategory(sessionFactory.getCurrentSession(), categoryName);
            category = (CategoryDTO) mapFromEntity(entityCategory);
        } catch(RuntimeException exception){
            // throw data not found exception 
            throw new BusinessException(ErrorConstantsHelper.CATEGORY_NAME_NOT_FOUND);
        }
        return category;
    }
    
    @Override
    @Transactional
    public CategoryDTO getCategoryById(int categoryId) {
        CategoryDTO category = null;
        try{
            // check validations
            if(!ValidationHelper.isValidNumber(String.valueOf(categoryId))) {
                throw new BusinessException(ErrorConstantsHelper.INVALID_CATEGORY_DATA);
            }
            // if exist return category 
            Category entityCategory = categoryDAO.getCategory(sessionFactory.getCurrentSession(), categoryId);
            category = (CategoryDTO) mapFromEntity(entityCategory);
        } catch(RuntimeException exception){
            // throw data not found exception 
            throw new BusinessException(ErrorConstantsHelper.CATEGORY_ID_NOT_FOUND);
        }
        return category;
    }
    
    @Override
    @Transactional
    public int saveCategory(CategoryDTO category) {
        int id = category.getId();
        Category entityCategory = null;
        try{
            // check validations
            if(ValidationHelper.isValidName(category.getCategoryName()) 
                && ValidationHelper.isValidDescription(category.getCategoryDescription())){
                // get category if exist before saving category
                entityCategory = categoryDAO.getCategory(sessionFactory.getCurrentSession(), category.getCategoryName());
                if(entityCategory!=null){
                    // throw data found exception
                    throw new BusinessException(ErrorConstantsHelper.CATEGORY_FOUND);
                }
            } else{
                // throw invalid data exception
                throw new BusinessException(ErrorConstantsHelper.INVALID_CATEGORY_DATA);
            }
        }catch(RuntimeException exception){
            // that means no such a category like this one in db so we save category
            entityCategory = (Category) mapToEntity(category);
            id = categoryDAO.saveCategory(sessionFactory.getCurrentSession(), entityCategory);  
        }
        return id;    
    }

    @Override
    @Transactional
    public int updateCategory(CategoryDTO category) {
       int updatedCategoryId = 0;
        try{
            if(ValidationHelper.isValidName(category.getCategoryName()) 
                && ValidationHelper.isValidDescription(category.getCategoryDescription())){
                Category entityCategory = (Category) mapToEntity(category);
                updatedCategoryId = categoryDAO.updateCategory(sessionFactory.getCurrentSession(), entityCategory);    
            } else{
                // throw invalid data exception
                throw new BusinessException(ErrorConstantsHelper.INVALID_CATEGORY_DATA);
            }     
        } catch(RuntimeException exception){
            // throw data found exception
            throw new BusinessException(ErrorConstantsHelper.CATEGORY_FOUND);
        }
        return updatedCategoryId;
    }

    @Override
    @Transactional
    public void deleteCategory(int categoryId) {
        Session currentSession = sessionFactory.getCurrentSession();
        // delete all operations of this category
        operationDAO.deleteOperationsOfCategory(currentSession, categoryId);
        // delete all items of this category
        itemDAO.deleteItemsOfCategory(currentSession, categoryId);
        // delete this category
        categoryDAO.deleteCategory(currentSession, categoryId);  
    }
    
    @Override
    public Object mapToEntity(Object object){
        CategoryDTO category = (CategoryDTO) object;
        Category entityCategory = new Category();
        entityCategory.setCategoryName(category.getCategoryName());
        entityCategory.setCategoryDescription(category.getCategoryDescription());
        entityCategory.setCreatedDate(category.getCreatedDate());
        entityCategory.setLastModifiedDate(category.getLastModifiedDate());
        entityCategory.setDeletedFlag(DatabaseHelper.DELETED_FLAG_FALSE);
        return entityCategory;
    }
    
    @Override 
    public Object mapFromEntity(Object object){
        Category entityCategory = (Category) object;
        CategoryDTO category = new CategoryDTO();
        category.setId(entityCategory.getId());
        category.setCategoryName(entityCategory.getCategoryName());
        category.setCategoryDescription(entityCategory.getCategoryDescription());
        category.setCreatedDate(entityCategory.getCreatedDate());
        category.setLastModifiedDate(entityCategory.getLastModifiedDate());
        return category;
    }
}
