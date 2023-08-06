/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.crud_api.pojo;

import lombok.Data;

/**
 *
 * @author iphan
 */
@Data
public class BaseResponse {
    private int status;
    private String message;
    private Object result;

    public BaseResponse() {
    }

    public BaseResponse(boolean error){
        this.status = 500;
        this.message= "An error occurred";
    }
}
