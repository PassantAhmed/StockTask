/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esrinea.gssteam.stocktask.service;

import com.esrinea.gssteam.stocktask.dao.CategoryDAO;
import com.esrinea.gssteam.stocktask.dao.ItemDAO;
import com.esrinea.gssteam.stocktask.dao.OperationDAO;
import com.esrinea.gssteam.stocktask.entity.Category;
import com.esrinea.gssteam.stocktask.entity.Item;
import com.esrinea.gssteam.stocktask.exception.BusinessException;
import com.esrinea.gssteam.stocktask.util.DatabaseHelper;
import com.esrinea.gssteam.stocktask.dto.FiltrationCriterias;
import com.esrinea.gssteam.stocktask.dto.ItemDTO;
import com.esrinea.gssteam.stocktask.dto.OperationDTO;
import com.esrinea.gssteam.stocktask.dto.OperationReport;
import com.esrinea.gssteam.stocktask.entity.Operation;
import com.esrinea.gssteam.stocktask.util.ErrorConstantsHelper;
import com.esrinea.gssteam.stocktask.util.StockObjectMapper;
import com.esrinea.gssteam.stocktask.util.ValidationHelper;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.LockMode;
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
public class OperationServiceImpl implements OperationService, StockObjectMapper{
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @Autowired
    private OperationDAO operationDAO;
    
    @Autowired
    private ItemDAO itemDAO;
    
    @Autowired
    private CategoryDAO categoryDAO;
    
    @Override
    @Transactional
    public List<OperationReport> getAllOperations() {
        // return all operations
        List<Operation> entityOperations = operationDAO.getOperations(sessionFactory.getCurrentSession());
        System.out.println(entityOperations);
        List<OperationReport> operationsReports = null;
        for(Operation operation : entityOperations){
            if(operationsReports==null){
                operationsReports = new ArrayList<OperationReport>();
            }
            operationsReports.add((OperationReport)mapFromEntity(operation));
        }
        return operationsReports;
    }
    
    @Override
    @Transactional
    public List<OperationReport> getOperations(FiltrationCriterias criterias){
        validateCriterias(criterias);
        // after validations get filtered operations
        List<Operation> entityOperations = operationDAO.getFilteredOperations(sessionFactory.getCurrentSession(), criterias);
        List<OperationReport> operationsReports = null;
        for(Operation operation : entityOperations){
            if(operationsReports==null){
                operationsReports = new ArrayList<OperationReport>();
            }
            operationsReports.add((OperationReport)mapFromEntity(operation));
        }
        return operationsReports;
    }

    private void validateCriterias(FiltrationCriterias criterias) {
        // check if start date is checked
        if(criterias.getReportByStartDate()!=null){
            validateCriteriaStartDate(criterias.getReportByStartDate());
        }
        // check if start date is checked
        if(criterias.getReportByEndDate()!=null){
            // check validation
            validateCriteriaEndDate(criterias.getReportByEndDate());
        }
        if(criterias.getReportByCategoryId()!=0){
            validateCriteriaCategoryId(criterias.getReportByCategoryId());
        }
        if(criterias.getReportByItemId()!=0){
            validateCriteriaItemId(criterias.getReportByItemId());
        }
        if(criterias.getReportByItemId()!=0 && criterias.getReportByCategoryId()!=0){
            validateCriteriaItemIdInCategory(criterias.getReportByItemId(), criterias.getReportByCategoryId());
        }   
    }
    
    @Override
    @Transactional
    public int saveOperation(OperationDTO operation) {
        // check operation amount
        if(operation.getOperationAmount()<=0){
            // throw invalid data exception
            throw new BusinessException(ErrorConstantsHelper.INVALID_OPERATION_AMOUNT);     
        }
         // check operation type
        if(!(operation.getOperationType().equals(DatabaseHelper.OPERATION_TYPE_IN) || 
                    operation.getOperationType().equals(DatabaseHelper.OPERATION_TYPE_OUT))){
            // throw invalid data exception
            throw new BusinessException(ErrorConstantsHelper.INVALID_OPERATION_TYPE);
        }
        if(operation.getItemAmount()==0 || operation.getItemAmount()< operation.getOperationAmount()){
            // throw invalid data exception
            throw new BusinessException(ErrorConstantsHelper.INVALID_OPERATION_HIGH_AMOUNT);
        }
        // perform lock on this session to make sure no one will perform any changes except me 
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.lock(operation.getItemId(), LockMode.PESSIMISTIC_WRITE);
        Operation entityOperation =  (Operation) mapToEntity(operation);
        int savedOperationId = operationDAO.saveOperation(currentSession, entityOperation);
        // update the amount of this item
        itemDAO.updateItemAmountOperation(currentSession, entityOperation);
        return savedOperationId;  
    }    
    
    private void validateCriteriaStartDate(Date startDate){
        if(!(ValidationHelper.isValidDate(startDate))){
            // throw invalid data exception
            throw new BusinessException(ErrorConstantsHelper.INVALID_OPERATION_START_DATE);
        }   
    }
    
    private void validateCriteriaEndDate(Date endDate){
        if(!(ValidationHelper.isValidDate(endDate))){
            // throw invalid data exception
            throw new BusinessException(ErrorConstantsHelper.INVALID_OPERATION_END_DATE);
        }
    }
    
    private void validateCriteriaCategoryId(int categoryId){
        try{
            Category category = categoryDAO.getCategory(sessionFactory.getCurrentSession(), categoryId);
            if(category==null){
                // throw data not found exception
                throw new BusinessException(ErrorConstantsHelper.CATEGORY_ID_NOT_FOUND);
            }
        } catch(RuntimeException exception){
            // throw data not found exception
            throw new BusinessException(ErrorConstantsHelper.CATEGORY_ID_NOT_FOUND);
        }
    }
    
    private void validateCriteriaItemId(int itemId){
        try{
            Item item = itemDAO.getItem(sessionFactory.getCurrentSession(), itemId);
            if(item==null){
                // throw data not found exception
                throw new BusinessException(ErrorConstantsHelper.ITEM_ID_NOT_FOUND);
            }
        } catch(RuntimeException exception){
            // throw data not found exception
            throw new BusinessException(ErrorConstantsHelper.ITEM_ID_NOT_FOUND);
        }
    }
    
    private void validateCriteriaItemIdInCategory(int itemId, int categoryId){
        try{
            Item item = itemDAO.checkItemAndCategoryExistance(sessionFactory.getCurrentSession(), categoryId, itemId);
            if(item==null){
                // throw data not found exception
                throw new BusinessException(ErrorConstantsHelper.INVALID_OPERATION_ITEM_CATEGORY);
            }
        } catch(RuntimeException exception){
            // throw data not found exception
            throw new BusinessException(ErrorConstantsHelper.INVALID_OPERATION_ITEM_CATEGORY);
        }
    }

    @Override
    public Object mapFromEntity(Object object) {
        Operation entityOperation = (Operation) object;
        OperationReport operationReport = new OperationReport();
        operationReport.setCreatedDate(entityOperation.getCreatedDate());
        operationReport.setLastModifiedDate(entityOperation.getLastModifiedDate());
        operationReport.setOperationAmount(entityOperation.getOperationAmount());
        operationReport.setOperationType(entityOperation.getOperationType());
        ItemDTO item = getMappedItem(entityOperation.getItem());
        operationReport.setItem(item);
        return operationReport;
    }
    
    private ItemDTO getMappedItem(Item entityItem){
        ItemDTO item = new ItemDTO();
        item.setItemId(entityItem.getId());
        item.setItemName(entityItem.getItemName());
        item.setItemDescription(entityItem.getItemDescription());
        item.setCreatedDate(entityItem.getCreatedDate());
        item.setLastModifiedDate(entityItem.getLastModifiedDate());
        item.setAmount(entityItem.getAmount());
        item.setCategoryId(entityItem.getCategory().getId());
        return item;
    }
    
    @Override
    public Object mapToEntity(Object object) {
        OperationDTO operation = (OperationDTO) object;
        Operation entityOperation = new Operation();
        entityOperation.setCreatedDate(operation.getCreatedDate());
        entityOperation.setLastModifiedDate(operation.getLastModifiedDate());
        entityOperation.setOperationAmount(operation.getOperationAmount());
        entityOperation.setOperationType(operation.getOperationType()); 
        Item item = new Item();
        item.setId(operation.getItemId());
        entityOperation.setDeletedFlag(DatabaseHelper.DELETED_FLAG_FALSE);
        return entityOperation;
    }
    
}
