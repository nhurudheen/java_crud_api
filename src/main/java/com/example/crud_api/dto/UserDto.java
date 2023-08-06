package com.example.crud_api.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {
    private String firstname;
    private String lastname;
    private String emailAddress;
    private String phoneNumber;
    private String dateOfBirth;
    private String token;

    public UserDto(){

    }
    public UserDto(String firstname, String lastname, String emailAddress, String phoneNumber, String dateOfBirth, String token) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.token = token;
    }
}
