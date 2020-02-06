/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esrinea.gssteam.stocktask.service;

import com.esrinea.gssteam.stocktask.dto.ItemDTO;
import com.esrinea.gssteam.stocktask.exception.BusinessException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
/**
 *
 * @author passant.swelum
 */

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceImplTest {
    
    ItemServiceImpl itemService;
    
    @Before
    public void setup(){
        itemService = mock(ItemServiceImpl.class);
    }
    
    @Test
    public void testGetItemByExistingName() {
        String itemName = "Item One";
        ItemDTO actualItem, expectedItem;
        actualItem = new ItemDTO("Item One", "Item One Desc", 20, null, null, 3);
        expectedItem = new ItemDTO("Item One", "Item One Desc", 20, null, null, 3);
        Mockito.when(itemService.getItemByName(itemName)).thenReturn(actualItem);
        assertEquals(expectedItem, actualItem);
    }
    
    @Test
    public void testGetItemByNotExistingName() {
        String itemName = "Item";
        ItemDTO expectedItem = new ItemDTO("Item", "Item Desc", 20, null, null, 3);
        ItemDTO actualItem = null;
        Mockito.when(itemService.getItemByName(itemName)).thenReturn(null);
        assertNull(actualItem);
    }   
    
    @Test (expected = BusinessException.class)
    public void testGetItemByInvalidName() {
        String itemName = "Item_232";
        BusinessException exception = new BusinessException("Error occurred");
        Mockito.when(itemService.getItemByName(itemName)).thenThrow(exception);
        throw exception;
    }
    
    @Test (expected = BusinessException.class)
    public void testGetItemByEmptyName() {
        String itemName = "";
        BusinessException exception = new BusinessException("Error occurred");
        Mockito.when(itemService.getItemByName(itemName)).thenThrow(exception);
        throw exception;
    } 
    
    @Test (expected = BusinessException.class)
    public void testGetItemByNullName() {
        String itemName = null;
        BusinessException exception = new BusinessException("Error occurred");
        Mockito.when(itemService.getItemByName(itemName)).thenThrow(exception);
        throw exception;
    }     
    
    @Test
    public void testGetItemByExistingId() {
        int itemId = 3;
        ItemDTO actualItem, expectedItem;
        actualItem = new ItemDTO(itemId, "Item One", "Item One Desc", 20, null, null, 3);
        expectedItem = new ItemDTO(itemId, "Item One", "Item One Desc", 20, null, null, 3);
        Mockito.when(itemService.getItemById(itemId)).thenReturn(actualItem);
        assertEquals(expectedItem, actualItem);
    }
    
    @Test
    public void testGetItemByNotExistingId() {
        int itemId = 3;
        ItemDTO expectedItem = new ItemDTO(itemId, "Item One", "Item One Desc", 20, null, null, 3);
        ItemDTO actualItem = null;
        Mockito.when(itemService.getItemById(itemId)).thenReturn(null);
        assertNull(actualItem);
    }   
    
    @Test (expected = BusinessException.class)
    public void testGetItemByInvalidId() {
        int itemId = -232;
        BusinessException exception = new BusinessException("Error occurred");
        Mockito.when(itemService.getItemById(itemId)).thenThrow(exception);
        throw exception;
    }   
    
    @Test 
    public void testSaveNotExistingValidItem(){
        int returnedId = 3;
        ItemDTO item = new ItemDTO("Item Two", "Item Two Desc", 5, null, null, 3);
        Mockito.when(itemService.saveItem(item)).thenReturn(returnedId);
        assertEquals(3, returnedId);
    }
    
    @Test (expected = BusinessException.class)
    public void testSaveNotExistingCategoryItem(){
        ItemDTO item = new ItemDTO("Item Two", "Item Two Desc", 5, null, null, 200);
        BusinessException exception = new BusinessException("Error occurred");
        Mockito.when(itemService.saveItem(item)).thenThrow(exception);
        throw exception;
    }
    
    @Test (expected = BusinessException.class)
    public void testSaveInvalidDataItem(){
        ItemDTO item = new ItemDTO("Item_$$$Two", "Item Two Desc", -5, null, null, 200);
        BusinessException exception = new BusinessException("Error occurred");
        Mockito.when(itemService.saveItem(item)).thenThrow(exception);
        throw exception;
    }
    
    @Test (expected = BusinessException.class)
    public void testSaveExistingCategory(){
        ItemDTO item = new ItemDTO("Item Two", "Item Two Desc", 5, null, null, 2);
        BusinessException exception = new BusinessException("Error occurred");
        Mockito.when(itemService.saveItem(item)).thenThrow(exception);
        throw exception;
    }
    
    @Test (expected = BusinessException.class)
    public void testSaveNullCategory(){
        ItemDTO item = null;
        BusinessException exception = new BusinessException("Error occurred");
        Mockito.when(itemService.saveItem(item)).thenThrow(exception);
        throw exception;
    }
    
    @Test 
    public void testUpdateValidItem(){
        int returnedId = 3, expectedId = 3;
        ItemDTO item = new ItemDTO(expectedId,"Item Two", "Item Two Desc", 5, null, null, 2);
        Mockito.when(itemService.updateItem(item)).thenReturn(returnedId);
        assertEquals(expectedId, returnedId);
    }
    
    @Test (expected = BusinessException.class)
    public void testUpdateInvalidDataItem(){
        ItemDTO item = new ItemDTO(3,"Item22", "Item22_Desc", 5, null, null, 2);
        BusinessException exception = new BusinessException("Error occurred");
        Mockito.when(itemService.updateItem(item)).thenThrow(exception);
        throw exception;
    }
    /*
    @Test (expected = BusinessException.class)
    public void testDeleteInvalidDataItem(){
        int itemId = -1;
        BusinessException exception = new BusinessException("Error occurred");
        throw exception;
    }
    
    @Test (expected = BusinessException.class)
    public void testDeleteNotExistingItem(){
        int itemId = 100;
        BusinessException exception = new BusinessException("Error occurred");
        throw exception;
    }
    */
}
