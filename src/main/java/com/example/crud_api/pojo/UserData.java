/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.crud_api.pojo;

import static com.example.crud_api.config.AppConstant.DATE_OF_BIRTH_REGEX;
import static com.example.crud_api.config.AppConstant.DATE_OF_BIRTH_VALIDATION_MSG;
import static com.example.crud_api.config.AppConstant.NAME_VALIDATION_REGEX;
import static com.example.crud_api.config.AppConstant.PASSWORD_PATTERN;
import static com.example.crud_api.config.AppConstant.PHONENUMBER_VALIDATION_ERROR_MESSAGE;
import static com.example.crud_api.config.AppConstant.PHONENUMBER_VALIDATION_REGEX;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.example.crud_api.model.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

/**
 *
 * @author iphan
 */
@Data
public class UserData {
    @NotEmpty
    @Pattern(regexp = NAME_VALIDATION_REGEX, message="First Name must be at least 3 characters")
    private String firstName;
    
    @NotEmpty
    @Pattern(regexp = NAME_VALIDATION_REGEX, message="Last Name must be at least 3 characters")
    private String lastName;
    
    @NotEmpty 
    @Email(message="Enter a valid email address")
    private String emailAddress;
    
    @NotEmpty
    @Pattern(regexp = PASSWORD_PATTERN , message ="Invalid Password Pattern ")
    private String password;
    
     @NotEmpty
     @Pattern(regexp = PHONENUMBER_VALIDATION_REGEX, message=PHONENUMBER_VALIDATION_ERROR_MESSAGE)
    private String phoneNumber;
    
     @NotEmpty
     @Pattern(regexp = DATE_OF_BIRTH_REGEX, message= DATE_OF_BIRTH_VALIDATION_MSG)
    private String dateOfBirth;

    public UserData() {
    }

    public UserData(UserEntity userEntity) {
        this.firstName = userEntity.getFirstName();
        this.lastName = userEntity.getLastName();
        this.emailAddress = userEntity.getEmailAddress();
        this.phoneNumber = userEntity.getPhoneNumber();
        this.dateOfBirth = userEntity.getDateOfBirth();
    }


}
