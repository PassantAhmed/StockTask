/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esrinea.gssteam.stocktask.controller;

import com.esrinea.gssteam.stocktask.dto.StockResponse;
import com.esrinea.gssteam.stocktask.dto.ItemDTO;
import com.esrinea.gssteam.stocktask.service.ItemService;
import com.esrinea.gssteam.stocktask.util.ConstantsHelper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author passant.swelum
 */
@RestController
@RequestMapping("/api")
public class ItemRestController {
    
    @Autowired
    private ItemService itemService;
    
    @GetMapping("/items")
    public StockResponse<List<ItemDTO>> getItems(){
        StockResponse<List<ItemDTO>> response = new StockResponse(ConstantsHelper.SUCCESS_CODE_RESPONSE, ConstantsHelper.SUCCESS_MESSAGE
               , itemService.getItems());
        return response;
    }
    
    @GetMapping("/items/name/{itemName}")
    public StockResponse<ItemDTO> getItemByName(@PathVariable String itemName){
        StockResponse<ItemDTO> response = new StockResponse(ConstantsHelper.SUCCESS_CODE_RESPONSE, ConstantsHelper.SUCCESS_MESSAGE
                , itemService.getItemByName(itemName));
        return response;
    }
    
    @GetMapping("/items/id/{itemId}")
    public StockResponse<ItemDTO> getItemById(@PathVariable int itemId){
        StockResponse<ItemDTO> response = new StockResponse(ConstantsHelper.SUCCESS_CODE_RESPONSE, ConstantsHelper.SUCCESS_MESSAGE
                , itemService.getItemById(itemId));
        return response;
    }
    
    @PostMapping("/items")
    public StockResponse<Integer> addItem(@RequestBody ItemDTO item){
        StockResponse<Integer> response = new StockResponse(ConstantsHelper.SUCCESS_CODE_RESPONSE, ConstantsHelper.SUCCESS_MESSAGE
               , itemService.saveItem(item));
        return response;
    }
    
    @RequestMapping(value="/items", method = RequestMethod.PUT)
    public StockResponse<Integer> updateItem(@RequestBody ItemDTO item){
        StockResponse<Integer> response = new StockResponse(ConstantsHelper.SUCCESS_CODE_RESPONSE, ConstantsHelper.SUCCESS_MESSAGE
               , itemService.updateItem(item));
        return response;
    }
    
    @DeleteMapping("/items/{itemId}")
    public void deleteItem(@PathVariable int itemId){
        itemService.deleteItem(itemId); 
    }
}
