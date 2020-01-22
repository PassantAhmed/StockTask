/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esrinea.gssteam.stocktask.dao;

import com.esrinea.gssteam.stocktask.entity.Operation;
import com.esrinea.gssteam.stocktask.util.DatabaseHelper;
import com.esrinea.gssteam.stocktask.dto.FiltrationCriterias;
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
public class OperationDAOImpl implements OperationDAO{
    
   
    @Override
    public List<Operation> getOperations(Session session) {
        // select all operations except the deleted ones 
        String stringQuery = "from Operation where deletedFlag=:deletedFlag";
        Query<Operation> operationQuery = session.createQuery(stringQuery,Operation.class);
        operationQuery.setParameter("deletedFlag", DatabaseHelper.DELETED_FLAG_FALSE);
        List<Operation> operations = operationQuery.getResultList();
        return operations;
    }

    @Override
    public List<Operation> getFilteredOperations(Session session, FiltrationCriterias criterias) {
        // to get the filtered operations   
        // we concat queries according to user's choices
        String filtrationQuery = checkFiltrationCriterias(criterias);
        Query<Operation> operationQuery = session.createQuery(filtrationQuery,Operation.class);
        // set query params according to the concatinations 
        setQueryParams(criterias, operationQuery);
        List<Operation> operations = operationQuery.getResultList();
        return operations;
    }
    
    private String checkFiltrationCriterias(FiltrationCriterias criterias){
        String query = "from Operation where deletedFlag=:deletedFlag ";
        if(criterias.getReportByCategoryId()!=0){
            query = query + "and ( item.id IN ( select id from Item where category.id =:categoyId ) ) ";
        }
        if(criterias.getReportByItemId()!=0){
            query = query + "and ( item.id=:itemId ) ";
        }
        if(criterias.getReportByStartDate()!= null){
            query = query + "and ( createdDate<:startDate or createdDate=:startDate ) ";
        }
        if(criterias.getReportByEndDate()!= null){
            query = query + "and ( lastModifiedDate>:endDate or lastModifiedDate=:endDate ) ";
        }
        query = query + " order by lastModifiedDate desc";
        return query;
    }
    
    private void setQueryParams(FiltrationCriterias criterias, Query<Operation> operationQuery){
        // Adding deleted flag value
        operationQuery.setParameter("deletedFlag", DatabaseHelper.DELETED_FLAG_FALSE);
        if(criterias.getReportByCategoryId()!=0){
            operationQuery.setParameter("categoyId", criterias.getReportByCategoryId());
        }
        if(criterias.getReportByItemId()!=0){
            operationQuery.setParameter("itemId", criterias.getReportByItemId());
        }
        if(criterias.getReportByStartDate()!= null){
            operationQuery.setParameter("startDate", criterias.getReportByStartDate());
        }
        if(criterias.getReportByEndDate()!= null){
            operationQuery.setParameter("endDate", criterias.getReportByEndDate());
        }
    }
    
    @Override
    public int saveOperation(Session session, Operation operation) {
        session.save(operation);
        return operation.getId();
    }

    @Override
    public void deleteOperationsOfCategory(Session session, int categoryId) {
        // update operations of specific category
        String stringQuery ="update Operation set lastModifiedDate=:lastModifiedDate, deletedFlag=:deletedFlag where "
            + "itemId IN ( select item.id from Item where item.categoryId=:categoryId ";
        Query operationQuery = session.createQuery(stringQuery);
        operationQuery.setParameter("lastModifiedDate", new Date(System.currentTimeMillis()));
        operationQuery.setParameter("deletedFlag", DatabaseHelper.DELETED_FLAG_TRUE);
        operationQuery.setParameter("categoryId", categoryId);
        // updating data
        operationQuery.executeUpdate();
    }
    
    @Override
    public void deleteOperationsOfItem(Session session, int itemId) {
        String stringQuery ="update Operation set lastModifiedDate=:lastModifiedDate, deletedFlag=:deletedFlag where item.id=:itemId";
        Query operationQuery = session.createQuery(stringQuery);
        operationQuery.setParameter("lastModifiedDate", new Date(System.currentTimeMillis()));
        operationQuery.setParameter("deletedFlag", DatabaseHelper.DELETED_FLAG_TRUE);
        operationQuery.setParameter("itemId", itemId);
        //updating data
        operationQuery.executeUpdate();
    }
    
}
