/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.crud_api.config;

/**
 *
 * @author iphan
 */
public class AppConstant {
    
    public static final String NAME_VALIDATION_REGEX = "^[a-zA-Z]{3,}(?: [a-zA-Z]+){0,2}$";
    public static final String PHONENUMBER_VALIDATION_REGEX = "^\\d{11}$";
    public static final String PHONENUMBER_VALIDATION_ERROR_MESSAGE = "Phonenumber must be 11 digit";
    public static final String DATE_OF_BIRTH_REGEX = "^\\d{4}-\\d{2}-\\d{2}$"; 
    public static final String DATE_OF_BIRTH_VALIDATION_MSG = "Date format is YYYY-MM-DD";
    public static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()-[{}]:;'?/*~$^+=<>]).{6,20}$";
    public static final int SUCCESS_STATUS_CODE = 200;
    public static final String SUCCESS_DATA_INSERTED_STATUS_MESSAGE = "Data Inserted Successful";
    public static final String FAIL_DATA_INSERTED_STATUS_MESSAGE = "Data Fail to Insert";
    public static final int ERROR_STATUS_CODE = 400;
}
