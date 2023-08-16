/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.crud_api.controller;


import com.example.crud_api.config.Authorization;
import com.example.crud_api.model.UserLogin;
import com.example.crud_api.pojo.BaseResponse;
import com.example.crud_api.pojo.UserData;
import com.example.crud_api.service.AppService;
import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author iphan
 */
@RestController
@RequestMapping("/crud_api")
@Api(tags = "User Management", description = "Java CRUD API")
public class ApplicationController {
   @Autowired
   AppService appService;
   
   
    @PostMapping("/create-user")
    @ApiOperation(value = "Create user account", notes = "This endpoint is used in creating user account")
    public ResponseEntity createUser(@Valid @RequestBody UserData userdata){
        BaseResponse baseResponse = appService.createUser(userdata);
        HttpStatus status = (baseResponse.getStatus()==200)?HttpStatus.OK:HttpStatus.BAD_REQUEST;
       return new ResponseEntity<>(baseResponse, status); 
    }
    @GetMapping("/get-user")
    public ResponseEntity getUserByEmail(@RequestParam("email") String email,
                                         @RequestHeader(value = "Authorization", required = false) String token){
        BaseResponse validateAuthorization = Authorization.validateTokenAndGetResponse(token);
        if(validateAuthorization != null){
            return  new ResponseEntity<>(validateAuthorization, HttpStatus.UNAUTHORIZED);
        }
        BaseResponse baseResponse = appService.getSingleUser(email);
        HttpStatus status = (baseResponse.getStatus() ==200)?HttpStatus.OK:HttpStatus.BAD_REQUEST;
        return  new ResponseEntity<>(baseResponse,status);
    }
    @GetMapping("/all-user")
    public  ResponseEntity getAllUser(){
        BaseResponse baseResponse =appService.getAllUser();
        HttpStatus status = (baseResponse.getStatus() ==200)?HttpStatus.OK:HttpStatus.BAD_REQUEST;
        return  new ResponseEntity<>(baseResponse,status);
    }
    @PostMapping("/update-user")
    public  ResponseEntity updateUser(@Valid @RequestBody UserData userData){
        BaseResponse baseResponse = appService.updateUser(userData);
        HttpStatus status = (baseResponse.getStatus()==200)?HttpStatus.OK:HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(baseResponse, status);
    }

    @GetMapping("/delete-user")
    public ResponseEntity deleteUser(@RequestParam("email") String email) {
        BaseResponse baseResponse = appService.deleteUser(email);
        HttpStatus status = (baseResponse.getStatus() == 200) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(baseResponse, status);
    }
    @PostMapping("/user_login")
    public ResponseEntity userLogin(@Valid @RequestBody UserLogin userLogin){
        BaseResponse baseResponse = appService.userLogin(userLogin);
        HttpStatus status = (baseResponse.getStatus()==200)?HttpStatus.OK:HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(baseResponse, status);
    }

    //General Exception Handler for Rest Controller 
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse handleValidationExceptions(
            MethodArgumentNotValidException ex) {

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatus(400);
        baseResponse.setMessage("An error occured");
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        baseResponse.setResult(errors);
        return baseResponse;

    }
}
