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
    
    @Override
    public boolean equals(Object object) {
        boolean result = false;
        if(object instanceof OperationReport){
            if(object!=null){
                OperationReport operationReport = (OperationReport) object;
                if(operationReport.getItem().equals(item)
                        && operationReport.getOperationAmount()==operationAmount
                        && operationReport.getOperationType().equals(operationType)){
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
	result = prime * result + item.getItemId();
        result = prime * result + item.getAmount();
	result = prime * result + ((item.getItemName() == null) ? 0 : item.getItemName().hashCode());
        result = prime * result + ((operationType == null) ? 0 : operationType.hashCode());
	return result;
    }
}
