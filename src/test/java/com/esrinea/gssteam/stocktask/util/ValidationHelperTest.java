/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esrinea.gssteam.stocktask.util;
 
import java.sql.Date;
import org.junit.Test;
 
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 *
 * @author passant.swelum
 */
@RunWith(JUnit4.class)
public class ValidationHelperTest {
    
    public ValidationHelperTest() {
    }
  
    @Test
    public void testIsValidNumberWithNullText(){
        assertFalse(ValidationHelper.isValidNumber(null));
    }
    
    @Test
    public void testIsValidNumberWithEmptyText(){
        assertFalse(ValidationHelper.isValidNumber(""));
    }
    
    @Test
    public void testIsValidNumberWithOnlyPrefixNumericText(){
        assertFalse(ValidationHelper.isValidNumber("4option"));
    }
    
    @Test
    public void testIsValidNumberWithOnlySuffixNumericText(){
        assertFalse(ValidationHelper.isValidNumber("option4"));
    }
    
    @Test
    public void testIsValidNumberWithInMiddleNumericText(){
        assertFalse(ValidationHelper.isValidNumber("option4option"));
    }
    
    @Test
    public void testIsValidNumberWithOnlyNumericText(){
        assertTrue(ValidationHelper.isValidNumber("4444"));
    }
    
    @Test 
    public void testIsValidNameWithNullText(){
        assertFalse(ValidationHelper.isValidName(null));
    }
    
    @Test
    public void testIsValidNameWithEmptyText(){
        assertFalse(ValidationHelper.isValidName(""));
    }
    
    @Test
    public void testIsValidNameWithNumericText(){
        assertFalse(ValidationHelper.isValidName("category1"));
    }
    
    @Test
    public void testIsValidNameWithSymbolsText(){
        assertFalse(ValidationHelper.isValidName("category_one"));
    }
    
    @Test
    public void testIsValidNameWithLargeLengthText(){
        assertFalse(ValidationHelper.isValidName("Category Category Category Category Category Category Category Category Category Category"));
    }
    
    @Test
    public void testIsValidNameWithAlphabeticsText(){
        assertTrue(ValidationHelper.isValidName("Category One"));
    }
    
    @Test 
    public void testIsValidDescriptionWithNullText(){
        assertFalse(ValidationHelper.isValidDescription(null));
    }
    
    @Test
    public void testIsValidDescriptionWithEmptyText(){
        assertFalse(ValidationHelper.isValidDescription(""));
    }
    
    @Test
    public void testIsValidDescriptionWithNumericText(){
        assertFalse(ValidationHelper.isValidDescription("description1"));
    }
    
    @Test
    public void testIsValidDescriptionWithSymbolsText(){
        assertFalse(ValidationHelper.isValidDescription("description_one"));
    }
    
    @Test
    public void testIsValidDescriptionWithLargeLengthText(){
        assertFalse(ValidationHelper.isValidDescription("Description Description Description Description Description Description Description Description "
                + "Description Description Description Description Description Description Description Description Description Description "
                + "Description Description Description Description Description Description Description Description Description"));
    }
    
    @Test
    public void testIsValidDescriptionWithAlphabeticsText(){
        assertTrue(ValidationHelper.isValidDescription("Description One"));
    } 
    
    @Test 
    public void testIsValidDateWithNullDate(){
        assertFalse(ValidationHelper.isValidDate(null));
    }
    
    @Test
    public void testIsValidDateWithOldDate(){
        assertTrue(ValidationHelper.isValidDate(new Date(1577916000000L)));
    }
    
    @Test
    public void testIsValidDateWithCurrentDate(){
        assertTrue(ValidationHelper.isValidDate(new Date(System.currentTimeMillis())));
    }
}
