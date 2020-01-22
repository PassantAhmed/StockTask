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
public class ItemDTO {
    
    private int itemId;
    private String itemName;
    private String itemDescription;
    private int amount;
    private Date createdDate;
    private Date lastModifiedDate;
    private int categoryId;

    public ItemDTO() {
    }

    public ItemDTO(int itemId, String itemName, String itemDescription, int amount, Date createdDate, Date lastModifiedDate, int categoryId) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.amount = amount;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.categoryId = categoryId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }
    
}
