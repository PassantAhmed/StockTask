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
public class OperationDTO {

    private int operationAmount;
    private String operationType;
    private Date createdDate;
    private Date lastModifiedDate;
    private int itemId;
    private int itemAmount;

    public OperationDTO() {
    }

    public OperationDTO(int operationAmount, String operationType, Date createdDate, Date lastModifiedDate, int itemId, int itemAmount) {
        this.operationAmount = operationAmount;
        this.operationType = operationType;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.itemId = itemId;
        this.itemAmount = itemAmount;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(int itemAmount) {
        this.itemAmount = itemAmount;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public int getOperationAmount() {
        return operationAmount;
    }

    public void setOperationAmount(int operationAmount) {
        this.operationAmount = operationAmount;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }
    
}
