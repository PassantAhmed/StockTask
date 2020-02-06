/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esrinea.gssteam.stocktask.util;

import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author passant.swelum
 */
public class ValidationHelper {

    private ValidationHelper(){}
    
    //private final static String NAME_REGEX = "[A-Z][a-z]*";
    private final static String NAME_REGEX = "[\\p{Upper}(\\p{Lower}+\\s?)]+";
    private final static String NUMBER_REGEX="[1-9][\\d]?[\\d]?[\\d]?[\\d]?";
    private final static int NAME_MAX_LENGTH_NUMBER = 50;
    private final static int DESCRIPTION_MAX_LENGTH_NUMBER = 250;
    
    private static boolean isAlphabeticText(String name) {
        Pattern pattern = Pattern.compile(NAME_REGEX);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }
    
    private static boolean checkNumberPattern(String number) {
        boolean result = true;
        if(number==null){
            result = false;
        }else{
            Pattern pattern = Pattern.compile(NUMBER_REGEX);
            Matcher matcher = pattern.matcher(number);
            result = matcher.matches();
        }
        return result;
    }
    
    public static boolean isValidName(String name){
        boolean result;
        if(name==null){
            result = false;
        } else if(name.length()>50 || name.length()==0){
            result = false;
        } else{
            result = isAlphabeticText(name);
        }
        return result;
    }
    
    public static boolean isValidDescription(String description){
        boolean result;
        if(description==null){
            result = false;
        } else if(description.length()>250 || description.length()==0){
            result = false;
        } else{
            result = isAlphabeticText(description);
        }
        return result;
    }
    
    public static boolean isValidNumber(String number){
        return checkNumberPattern(number);
    }
    
    public static boolean isValidDate(Date date){
        boolean result = true;
        if(date==null){
            result = false;
        }
        else if(date.after(new Date(System.currentTimeMillis()))){
            result = false;
        } 
        return result;
    }
    
}
