/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esrinea.gssteam.stocktask.dto;

import java.sql.Date;

/**
 *
 * @author passant.swelum
 */
public class CategoryDTO {

    private int id;
    private String categoryName;
    private String categoryDescription;
    private Date createdDate;
    private Date lastModifiedDate;

    public CategoryDTO() {
    }

    public CategoryDTO(int id, String categoryName, String categoryDescription, Date createdDate, Date lastModifiedDate) {
        this.id = id;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }
    
    public CategoryDTO(String categoryName, String categoryDescription, Date createdDate, Date lastModifiedDate) {
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    @Override
    public boolean equals(Object object) {
        boolean result = false;
        if(object instanceof CategoryDTO){
            if(object!=null){
                CategoryDTO category = (CategoryDTO) object;
                if(category.getCategoryName().equals(categoryName)){
                    result = true;
                }
            }
        }  
        return result; 
    }

    @Override
    public int hashCode() {
        final int prime = 31;
	int result = 1;
	result = prime * result + id;
	result = prime * result + ((categoryName == null) ? 0 : categoryName.hashCode());
	return result;
    }

    
}
