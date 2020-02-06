/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esrinea.gssteam.stocktask.service;

import com.esrinea.gssteam.stocktask.dto.FiltrationCriterias;
import com.esrinea.gssteam.stocktask.dto.ItemDTO;
import com.esrinea.gssteam.stocktask.dto.OperationDTO;
import com.esrinea.gssteam.stocktask.dto.OperationReport;
import com.esrinea.gssteam.stocktask.exception.BusinessException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

/**
 *
 * @author passant.swelum
 */

@RunWith(MockitoJUnitRunner.class)
public class OperationServiceImplTest {
    
    OperationServiceImpl operationService;

    public OperationServiceImplTest() {}
    
    @Before
    public void setup(){
        operationService = mock(OperationServiceImpl.class);
    }
    
    @Test
    public void testGetValidItemAndCriteriaOperationsReports(){
        List<OperationReport> exceptedReports = new ArrayList<OperationReport>();
        exceptedReports.add(new OperationReport(3, "in", null, null, new ItemDTO(2, "Item Name", "Item Desc", 30, null, null, 1)));
        exceptedReports.add(new OperationReport(2, "out", null, null, new ItemDTO(2, "Item Name", "Item Desc", 31, null, null, 1)));
        
        FiltrationCriterias filtrationCriterias = new FiltrationCriterias(1, 2, null, null);
        
        List<OperationReport> actualReports = new ArrayList<OperationReport>();
        actualReports.add(new OperationReport(3, "in", null, null, new ItemDTO(2, "Item Name", "Item Desc", 30, null, null, 1)));
        actualReports.add(new OperationReport(2, "out", null, null, new ItemDTO(2, "Item Name", "Item Desc", 31, null, null, 1)));
        
        Mockito.when(operationService.getOperations(filtrationCriterias)).thenReturn(actualReports);
        assertEquals(exceptedReports, actualReports);
    }
    
    @Test
    public void testGetValidCriteriaOperationsReports(){
        List<OperationReport> exceptedReports = new ArrayList<OperationReport>();
        exceptedReports.add(new OperationReport(3, "in", null, null, new ItemDTO(2, "Item Name", "Item Desc", 30, null, null, 1)));
        exceptedReports.add(new OperationReport(2, "out", null, null, new ItemDTO(4, "Item Name", "Item Desc", 23, null, null, 1)));
        
        FiltrationCriterias filtrationCriterias = new FiltrationCriterias(1, 0, null, null);
        
        List<OperationReport> actualReports = new ArrayList<OperationReport>();
        actualReports.add(new OperationReport(3, "in", null, null, new ItemDTO(2, "Item Name", "Item Desc", 30, null, null, 1)));
        actualReports.add(new OperationReport(2, "out", null, null, new ItemDTO(4, "Item Name", "Item Desc", 23, null, null, 1)));
        
        Mockito.when(operationService.getOperations(filtrationCriterias)).thenReturn(actualReports);
        assertEquals(exceptedReports, actualReports);
    }
    
    @Test
    public void testGetValidItemOperationsReports(){
        List<OperationReport> exceptedReports = new ArrayList<OperationReport>();
        exceptedReports.add(new OperationReport(3, "in", null, null, new ItemDTO(2, "Item Name", "Item Desc", 30, null, null, 1)));
        exceptedReports.add(new OperationReport(2, "out", null, null, new ItemDTO(2, "Item Name", "Item Desc", 4, null, null, 5)));
        
        FiltrationCriterias filtrationCriterias = new FiltrationCriterias(0, 2, null, null);
        
        List<OperationReport> actualReports = new ArrayList<OperationReport>();
        actualReports.add(new OperationReport(3, "in", null, null, new ItemDTO(2, "Item Name", "Item Desc", 30, null, null, 1)));
        actualReports.add(new OperationReport(2, "out", null, null, new ItemDTO(2, "Item Name", "Item Desc", 4, null, null, 5)));
        
        Mockito.when(operationService.getOperations(filtrationCriterias)).thenReturn(actualReports);
        assertEquals(exceptedReports, actualReports);
    }
    
    @Test (expected = BusinessException.class)
    public void testGetNotExistingItemAndCriteriaOperationsReports(){
        //List<OperationReport> exceptedReports = new ArrayList<OperationReport>();
        //exceptedReports.add(new OperationReport(3, "in", null, null, new ItemDTO(2, "Item Name", "Item Desc", 30, null, null, 1)));
        //exceptedReports.add(new OperationReport(2, "out", null, null, new ItemDTO(2, "Item Name", "Item Desc", 31, null, null, 1)));
        FiltrationCriterias filtrationCriterias = new FiltrationCriterias(1, 2, null, null);
        BusinessException exception = new BusinessException("Error occurred");
        Mockito.when(operationService.getOperations(filtrationCriterias)).thenThrow(exception);
        throw exception;
    }
    
    @Test (expected = BusinessException.class)
    public void testGetNotExistingCriteriaOperationsReports(){
        List<OperationReport> exceptedReports = new ArrayList<OperationReport>();
        exceptedReports.add(new OperationReport(3, "in", null, null, new ItemDTO(2, "Item Name", "Item Desc", 30, null, null, 1)));
        exceptedReports.add(new OperationReport(2, "out", null, null, new ItemDTO(3, "Item Name", "Item Desc", 43, null, null, 1)));
        
        FiltrationCriterias filtrationCriterias = new FiltrationCriterias(1, 0, null, null);
        BusinessException exception = new BusinessException("Error occurred");
        Mockito.when(operationService.getOperations(filtrationCriterias)).thenThrow(exception);
        throw exception;
    }
    
    @Test (expected = BusinessException.class)
    public void testGetNotExistingItemOperationsReports(){
        List<OperationReport> exceptedReports = new ArrayList<OperationReport>();
        exceptedReports.add(new OperationReport(3, "in", null, null, new ItemDTO(2, "Item Name", "Item Desc", 30, null, null, 1)));
        exceptedReports.add(new OperationReport(2, "out", null, null, new ItemDTO(2, "Item Name", "Item Desc", 3, null, null, 5)));
        
        FiltrationCriterias filtrationCriterias = new FiltrationCriterias(0, 2, null, null);
        BusinessException exception = new BusinessException("Error occurred");
        Mockito.when(operationService.getOperations(filtrationCriterias)).thenThrow(exception);
        throw exception;
    }
    
    @Test
    public void testSaveValidOperation(){
        int returnedId = 58;
        OperationDTO savedOperation = new OperationDTO(3, "in", null, null, 3, 20);
        Mockito.when(operationService.saveOperation(savedOperation)).thenReturn(returnedId);
        assertTrue(returnedId>0);
    }
    
    @Test (expected = BusinessException.class)
    public void testSaveInvalidItemAmountOperation(){
        OperationDTO savedOperation = new OperationDTO(3, "out", null, null, 3, 13);
        OperationDTO actualOperation = new OperationDTO(3, "out", null, null, 3, 2);
        BusinessException exception = new BusinessException("Error occurred");
        Mockito.when(operationService.saveOperation(savedOperation)).thenThrow(exception);
        throw exception;
    }
    
    @Test (expected = BusinessException.class)
    public void testSaveNotExistingItemOperation(){
        OperationDTO savedOperation = new OperationDTO(3, "out", null, null, 3, 13);
        BusinessException exception = new BusinessException("Error occurred");
        Mockito.when(operationService.saveOperation(savedOperation)).thenThrow(exception);
        throw exception;
    }   
    
    @Test (expected = BusinessException.class)
    public void testSaveInvalidTypeOperation(){
        OperationDTO savedOperation = new OperationDTO(3, "add", null, null, 3, 13);
        BusinessException exception = new BusinessException("Error occurred");
        Mockito.when(operationService.saveOperation(savedOperation)).thenThrow(exception);
        throw exception;
    }
    
    @Test (expected = BusinessException.class)
    public void testSaveInvalidAmountOperation(){
        OperationDTO savedOperation = new OperationDTO(-3, "out", null, null, 3, 13);
        BusinessException exception = new BusinessException("Error occurred");
        Mockito.when(operationService.saveOperation(savedOperation)).thenThrow(exception);
        throw exception;
    }    
    
}
