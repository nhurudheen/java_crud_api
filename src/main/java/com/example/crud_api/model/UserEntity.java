package com.example.crud_api.model;

import com.example.crud_api.pojo.UserData;
import lombok.Data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "java_test")
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    @Column(unique = true)
    private String emailAddress;
//    @Temporal(javax.persistence.TemporalType.DATE)
    private String dateOfBirth;
    private String password;

    public UserEntity() {
    }
    public UserEntity(UserData userData) {
        this.firstName = userData.getFirstName();
        this.lastName = userData.getLastName();
        this.phoneNumber = userData.getPhoneNumber();
        this.emailAddress = userData.getEmailAddress();
        this.dateOfBirth = userData.getDateOfBirth();
    }



}
