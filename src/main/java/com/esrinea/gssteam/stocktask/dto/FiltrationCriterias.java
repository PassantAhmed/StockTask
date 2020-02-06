/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esrinea.gssteam.stocktask.dto;

import java.sql.Date;

/**
 *
 * @author passant.swelum
 */
public class FiltrationCriterias {
    
    private int reportByCategoryId;
    private int reportByItemId;
    private Date reportByStartDate;
    private Date reportByEndDate;

    public FiltrationCriterias() {
    }

    public FiltrationCriterias(int reportByCategoryId, int reportByItemId, Date reportByStartDate, Date reportByEndDate) {
        this.reportByCategoryId = reportByCategoryId;
        this.reportByItemId = reportByItemId;
        this.reportByStartDate = reportByStartDate;
        this.reportByEndDate = reportByEndDate;
    }
    
    

    public void setReportByCategoryId(int reportByCategoryId) {
        this.reportByCategoryId = reportByCategoryId;
    }

    public int getReportByCategoryId() {
        return reportByCategoryId;
    }

    public void setReportByEndDate(Date reportByEndDate) {
        this.reportByEndDate = reportByEndDate;
    }

    public Date getReportByEndDate() {
        return reportByEndDate;
    }

    public void setReportByItemId(int reportByItemId) {
        this.reportByItemId = reportByItemId;
    }

    public int getReportByItemId() {
        return reportByItemId;
    }

    public void setReportByStartDate(Date reportByStartDate) {
        this.reportByStartDate = reportByStartDate;
    }

    public Date getReportByStartDate() {
        return reportByStartDate;
    }
    
}
