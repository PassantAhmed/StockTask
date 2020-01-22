/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esrinea.gssteam.stocktask.dao;

import com.esrinea.gssteam.stocktask.entity.Item;
import com.esrinea.gssteam.stocktask.entity.Operation;
import com.esrinea.gssteam.stocktask.util.DatabaseHelper;
import java.sql.Date;
import java.util.List;
import javax.persistence.LockModeType;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author passant.swelum
 */
@Repository
public class ItemDAOImpl implements ItemDAO{
    
    @Override
    public List<Item> getItems(Session session) {
        // select all items except the deleted ones 
        String stringQuery = "from Item where deletedFlag=:deletedFlag";
        Query<Item> itemQuery = session.createQuery(stringQuery,Item.class);
        itemQuery.setParameter("deletedFlag", DatabaseHelper.DELETED_FLAG_FALSE);
        List<Item> items = itemQuery.getResultList();
        return items;
    }
    
    @Override
    public Item getItem(Session session, String itemName) {
        Item item = null;
        String stringQuery ="from Item where itemName=:itemName";
        Query itemQuery = session.createQuery(stringQuery);
        itemQuery.setParameter("itemName", itemName);
        Object tempObject = itemQuery.getSingleResult();
        if(tempObject!=null){
            item = (Item) tempObject;
        }
        return item;
    }

    @Override
    public Item getItem(Session session, int itemId) {
        String stringQuery = "from Item where id=:id and deletedFlag=:deletedFlag";
        Query itemQuery = session.createQuery(stringQuery);
        itemQuery.setParameter("id", itemId);
        itemQuery.setParameter("deletedFlag", DatabaseHelper.DELETED_FLAG_FALSE);
        Item item = null;
        Object tempObject = itemQuery.getSingleResult();
        if(tempObject!=null){
            item = (Item) tempObject;
        }
        return item;
    }
    
    @Override
    public int saveItem(Session session, Item item) {
        // Adding created and last modified dates & the deleted flag value
        item.setCreatedDate(new Date(System.currentTimeMillis()));
        item.setLastModifiedDate(new Date(System.currentTimeMillis()));
        item.setDeletedFlag(DatabaseHelper.DELETED_FLAG_FALSE);
        session.save(item);
        return item.getId();
    }
    
    @Override
    public int updateItem(Session session, Item item) {
        // Updating last modified date and deleted flag
        item.setLastModifiedDate(new Date(System.currentTimeMillis()));
        item.setDeletedFlag(DatabaseHelper.DELETED_FLAG_FALSE);
        session.update(item);
        return item.getId();
    }

    @Override
    public void deleteItem(Session session, int itemId) {
        Item item = session.get(Item.class, itemId);
        // Updating last modified date and deleted flag
        item.setDeletedFlag(DatabaseHelper.DELETED_FLAG_TRUE);
        item.setLastModifiedDate(new Date(System.currentTimeMillis()));
        session.update(item);	
    }

    @Override
    public void deleteItemsOfCategory(Session session, int categoryId) {
        // Updating last modified date and deleted flag for those items of this category
        String stringQuery ="update Item set deletedFlag=:deletedFlag, lastModifiedDate=:lastModifiedDate where category.id=:id";
        Query itemQuery = session.createQuery(stringQuery);
        itemQuery.setParameter("deletedFlag", DatabaseHelper.DELETED_FLAG_TRUE);
        itemQuery.setParameter("lastModifiedDate", new Date(System.currentTimeMillis()));
        itemQuery.setParameter("id", categoryId);
        itemQuery.executeUpdate();
    }
    
    @Override
    public int updateItemAmountOperation(Session session, Operation operation) {
        Item item = operation.getItem();
        // check operation type 
        if(operation.getOperationType().equals(DatabaseHelper.OPERATION_TYPE_IN)){
            item.setAmount(item.getAmount()+operation.getOperationAmount());
        } 
        else{
            item.setAmount(item.getAmount()-operation.getOperationAmount());
        }
        // Updating last modified date and amount and deleted flag for those items
        String stringQuery = "update Item set deletedFlag=:deletedFlag, amount=:amount, lastModifiedDate=:lastModifiedDate where id=:id";
        Query<Item> itemQuery = session.createQuery(stringQuery);
        // lock this item until we finish the operation
        itemQuery.setLockMode(LockModeType.PESSIMISTIC_WRITE);
        itemQuery.setParameter("deletedFlag", DatabaseHelper.DELETED_FLAG_FALSE);
        itemQuery.setParameter("amount", item.getAmount());
        itemQuery.setParameter("lastModifiedDate", new Date(System.currentTimeMillis()));
        itemQuery.setParameter("id", item.getId());
        itemQuery.executeUpdate();
        return operation.getId();
    }
    
    @Override
    public Item checkItemAndCategoryExistance(Session session, int categoryId, int itemId){
        String query = "from Item where deletedFlag=:deletedFlag and id=:itemId and category.id=:categoryId";
        Query<Item> itemQuery = session.createQuery(query,Item.class);
        itemQuery.setParameter("deletedFlag", DatabaseHelper.DELETED_FLAG_FALSE);
        itemQuery.setParameter("itemId", itemId);
        itemQuery.setParameter("categoryId", categoryId);
        return itemQuery.getSingleResult();
    }
    
}
