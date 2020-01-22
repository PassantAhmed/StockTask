/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esrinea.gssteam.stocktask.service;

import com.esrinea.gssteam.stocktask.dto.FiltrationCriterias;
import com.esrinea.gssteam.stocktask.dto.OperationDTO;
import com.esrinea.gssteam.stocktask.dto.OperationReport;
import java.util.List;

/**
 *
 * @author passant.swelum
 */
public interface OperationService {
    List<OperationReport> getAllOperations();
    List<OperationReport> getOperations(FiltrationCriterias criterias);
    int saveOperation(OperationDTO operation);
}
