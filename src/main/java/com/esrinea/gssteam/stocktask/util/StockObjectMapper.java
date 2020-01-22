/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esrinea.gssteam.stocktask.util;

/**
 *
 * @author passant.swelum
 */
public interface StockObjectMapper {
    Object mapFromEntity(Object object);
    Object mapToEntity(Object object);
}
