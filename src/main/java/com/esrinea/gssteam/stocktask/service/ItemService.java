/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esrinea.gssteam.stocktask.service;

import com.esrinea.gssteam.stocktask.dto.ItemDTO;
import java.util.List;

/**
 *
 * @author passant.swelum
 */
public interface ItemService {
    List<ItemDTO> getItems();
    ItemDTO getItemByName(String itemName);
    ItemDTO getItemById(int itemId);
    int saveItem(ItemDTO item);
    int updateItem(ItemDTO item);
    void deleteItem(int itemId);
    void deleteItems(int categoryId);
}
