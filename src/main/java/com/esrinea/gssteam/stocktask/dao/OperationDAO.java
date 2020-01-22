/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esrinea.gssteam.stocktask.dao;

import com.esrinea.gssteam.stocktask.entity.Operation;
import com.esrinea.gssteam.stocktask.dto.FiltrationCriterias;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author passant.swelum
 */
public interface OperationDAO {
    List<Operation> getOperations(Session session);
    List<Operation> getFilteredOperations(Session session, FiltrationCriterias criterias);
    int saveOperation(Session session, Operation operation);
    void deleteOperationsOfCategory(Session session, int categoryId);
    void deleteOperationsOfItem(Session session, int itemId);
}
