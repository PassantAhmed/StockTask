/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esrinea.gssteam.stocktask.service;

import com.esrinea.gssteam.stocktask.dao.ItemDAO;
import com.esrinea.gssteam.stocktask.dao.OperationDAO;
import com.esrinea.gssteam.stocktask.dto.ItemDTO;
import com.esrinea.gssteam.stocktask.entity.Category;
import com.esrinea.gssteam.stocktask.entity.Item;
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
public class ItemServiceImpl implements ItemService,StockObjectMapper{
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @Autowired
    private ItemDAO itemDAO;
    
    @Autowired
    private OperationDAO operationDAO;
    
    @Override
    @Transactional
    public List<ItemDTO> getItems() {
        List<Item> entityItems = itemDAO.getItems(sessionFactory.getCurrentSession());
        List<ItemDTO> items = null;
        for(Item item : entityItems){
            if(items==null){
                items = new ArrayList<ItemDTO>();
            }
            items.add((ItemDTO)mapFromEntity(item));
        }
        // return all items
        return items;
    }

    @Override
    @Transactional
    public ItemDTO getItemByName(String itemName) {
        ItemDTO item = null;
        try{
            if(!ValidationHelper.isValidName(itemName)){
                throw new BusinessException(ErrorConstantsHelper.INVALID_ITEM_DATA);
            }
            // try to get this item 
            Item entityItem = itemDAO.getItem(sessionFactory.getCurrentSession(), itemName);
            item = (ItemDTO) mapFromEntity(entityItem);
            // throw invalid data exception   
        }catch(RuntimeException exception){
            // throw data not found exception
            throw new BusinessException(ErrorConstantsHelper.ITEM_NAME_NOT_FOUND);
        }
        return item;
    }
    
    @Override
    @Transactional
    public ItemDTO getItemById(int itemId) {
        ItemDTO item = null;
        try{
            if(!ValidationHelper.isValidNumber(String.valueOf(itemId))) {
                // throw invalid data exception
                throw new BusinessException(ErrorConstantsHelper.INVALID_ITEM_DATA);
            }
            Item entityItem = itemDAO.getItem(sessionFactory.getCurrentSession(), itemId);
            item = (ItemDTO) mapFromEntity(entityItem);
        } catch(RuntimeException exception){
            // throw data not found exception
            throw new BusinessException(ErrorConstantsHelper.ITEM_ID_NOT_FOUND);
        }
        return item;
    }

    @Override
    @Transactional
    public int saveItem(ItemDTO item) {
        int id = item.getItemId();
        Item entityItem = null;
        try{
            // check validations
            if(!(ValidationHelper.isValidName(item.getItemName()) 
                    && ValidationHelper.isValidDescription(item.getItemDescription()))){
                throw new BusinessException(ErrorConstantsHelper.INVALID_ITEM_DATA);
            }
            entityItem = itemDAO.getItem(sessionFactory.getCurrentSession(), item.getItemName());
            if(!(entityItem==null)){
                throw new BusinessException(ErrorConstantsHelper.ITEM_FOUND);
            }
        } catch(RuntimeException exception){
            // that means no such an item like this one in db 
            // save item
            entityItem = (Item) mapToEntity(item);
            id = itemDAO.saveItem(sessionFactory.getCurrentSession(), entityItem);
        }
        return id;
    }

    @Override
    @Transactional
    public int updateItem(ItemDTO item) {
        int updatedItemId = 0;
        try{ 
            // check validations
            if(!(ValidationHelper.isValidName(item.getItemName()) 
                    && ValidationHelper.isValidDescription(item.getItemDescription()))){
                throw new BusinessException(ErrorConstantsHelper.INVALID_ITEM_DATA);
            }
            Item entityItem = (Item) mapToEntity(item);
            updatedItemId = itemDAO.updateItem(sessionFactory.getCurrentSession(), entityItem);
        } catch(RuntimeException exception){
            // throw data found exception
            throw new BusinessException(ErrorConstantsHelper.ITEM_FOUND);
        }
        return updatedItemId;
    }

    @Override
    @Transactional
    public void deleteItem(int itemId) {
        Session currentSession = sessionFactory.getCurrentSession();
        // delete operations of this item
        operationDAO.deleteOperationsOfItem(currentSession, itemId);
        // delete item
        itemDAO.deleteItem(currentSession, itemId);
    }

    @Override
    @Transactional
    public void deleteItems(int categoryId) {
        Session currentSession = sessionFactory.getCurrentSession();
        //delete all items operations of this deleted category
        operationDAO.deleteOperationsOfCategory(currentSession, categoryId);
        //delete items of this deleted category
        itemDAO.deleteItemsOfCategory(currentSession, categoryId);
    }

    @Override
    public Object mapFromEntity(Object object) {
        Item entityItem = (Item) object;
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
        ItemDTO item = (ItemDTO) object;
        Item entityItem = new Item();
        entityItem.setItemName(item.getItemName());
        entityItem.setItemDescription(item.getItemDescription());
        entityItem.setCreatedDate(item.getCreatedDate());
        entityItem.setLastModifiedDate(item.getLastModifiedDate());
        entityItem.setAmount(item.getAmount());
        Category category = new Category();
        category.setId(item.getCategoryId());
        entityItem.setCategory(category);
        entityItem.setDeletedFlag(DatabaseHelper.DELETED_FLAG_FALSE);
        return entityItem;
    }
    
}
