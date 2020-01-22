/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esrinea.gssteam.stocktask.controller;

import com.esrinea.gssteam.stocktask.dto.StockResponse;
import com.esrinea.gssteam.stocktask.exception.BusinessException;
import com.esrinea.gssteam.stocktask.util.ConstantsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author passant.swelum
 */
@ControllerAdvice
public class DataRestExceptionHandler {
    
    @Autowired
    private Environment environment;
   
    @ExceptionHandler
    public ResponseEntity<StockResponse> handleBusinessException(BusinessException exception){
        exception.printStackTrace();
        StockResponse errorResponse = new StockResponse(ConstantsHelper.CLIENT_ERROR_CODE_RESPONSE, 
                environment.getProperty(exception.getMessage()),null);
        return new ResponseEntity<StockResponse>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler
    public ResponseEntity<StockResponse> handleException(Exception exception){
        exception.printStackTrace();
        StockResponse errorResponse = new StockResponse(ConstantsHelper.SERVER_ERROR_CODE_RESPONSE, 
                environment.getProperty(exception.getMessage()),null);
        return new ResponseEntity<StockResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
