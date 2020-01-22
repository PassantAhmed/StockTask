/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esrinea.gssteam.stocktask.controller;

import com.esrinea.gssteam.stocktask.service.OperationService;
import com.esrinea.gssteam.stocktask.dto.FiltrationCriterias;
import com.esrinea.gssteam.stocktask.dto.OperationDTO;
import com.esrinea.gssteam.stocktask.dto.OperationReport;
import com.esrinea.gssteam.stocktask.dto.StockResponse;
import com.esrinea.gssteam.stocktask.util.ConstantsHelper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author passant.swelum
 */
@RestController
@RequestMapping("/api")
public class OperationRestController {
    
    @Autowired
    private OperationService operationService;
    
    @GetMapping("/operations")
    public StockResponse<List<OperationReport>> getAllOperations(){    
        StockResponse<List<OperationReport>> response = new StockResponse(ConstantsHelper.SUCCESS_CODE_RESPONSE, ConstantsHelper.SUCCESS_MESSAGE
               , operationService.getAllOperations());
        return response;
    }
    
    @PostMapping("/operations")
    public StockResponse<Integer> addOperation(@RequestBody OperationDTO operation){
        StockResponse<Integer> response = new StockResponse(ConstantsHelper.SUCCESS_CODE_RESPONSE, ConstantsHelper.SUCCESS_MESSAGE
                , operationService.saveOperation(operation));
        return response;
    }
    
    @PostMapping("/operations/report")
    public StockResponse<List<OperationReport>> getOperations(@RequestBody FiltrationCriterias criterias){
        StockResponse<List<OperationReport>> response = new StockResponse(ConstantsHelper.SUCCESS_CODE_RESPONSE, ConstantsHelper.SUCCESS_MESSAGE
               , operationService.getOperations(criterias));
        return response;
    }
    
}
