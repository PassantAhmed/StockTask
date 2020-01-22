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
public class OperationReport {

    private int operationAmount;
    private String operationType;
    private Date createdDate;
    private Date lastModifiedDate;
    private ItemDTO item;

    public OperationReport() {
    }

    public OperationReport(int operationAmount, String operationType, Date createdDate, Date lastModifiedDate, ItemDTO item) {
        this.operationAmount = operationAmount;
        this.operationType = operationType;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.item = item;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setItem(ItemDTO item) {
        this.item = item;
    }

    public ItemDTO getItem() {
        return item;
    }
    
    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setOperationAmount(int operationAmount) {
        this.operationAmount = operationAmount;
    }

    public int getOperationAmount() {
        return operationAmount;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getOperationType() {
        return operationType;
    }
    
}
