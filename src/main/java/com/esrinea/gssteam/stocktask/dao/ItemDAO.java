/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esrinea.gssteam.stocktask.dao;

import com.esrinea.gssteam.stocktask.entity.Item;
import com.esrinea.gssteam.stocktask.entity.Operation;
import java.util.List;
import org.hibernate.Session;


/**
 *
 * @author passant.swelum
 */
public interface ItemDAO {
    List<Item> getItems(Session session);
    Item getItem(Session session, String itemName);
    Item getItem(Session session, int itemId);
    Item checkItemAndCategoryExistance(Session session, int categoryId, int itemId);
    int saveItem(Session session, Item item);
    int updateItem(Session session, Item item);
    int updateItemAmountOperation(Session session, Operation operation);
    void deleteItem(Session session, int itemId);
    void deleteItemsOfCategory(Session session, int categoryId);
}
