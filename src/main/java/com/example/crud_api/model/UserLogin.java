package com.example.crud_api.model;

import lombok.Data;

import javax.persistence.Table;

@Data
@Table(name = "java_test")
public class UserLogin {
    private String emailAddress;
    private String password;

    public UserLogin() {
    }
}
