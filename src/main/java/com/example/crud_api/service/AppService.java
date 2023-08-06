/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.crud_api.service;

import com.example.crud_api.config.JWTConfig;
import com.example.crud_api.dto.UserDto;
import com.example.crud_api.model.UserEntity;
import com.example.crud_api.model.UserLogin;
import com.example.crud_api.pojo.BaseResponse;
import com.example.crud_api.pojo.UserData;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.logging.Logger;

import com.example.crud_api.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.crud_api.config.AppConstant.*;

/**
 *
 * @author iphan
 */
@Service
public class AppService {
    private static final Logger LOG = Logger.getLogger(AppService.class.getName());
    @Autowired
    UserRepo userRepo;

    public BaseResponse createUser(UserData userData) {
        BaseResponse baseResponse = new BaseResponse(true);
        try {
            Optional<UserEntity> checkUserEmail = userRepo.findByEmailAddress(userData.getEmailAddress());
            if (checkUserEmail.isPresent()) {
                baseResponse.setStatus(ERROR_STATUS_CODE);
                baseResponse.setMessage("Email already exists. Choose another email.");
                return baseResponse;
            }
            UserEntity userEntity = new UserEntity(userData);
            userEntity.setPassword(encryptPassword(userData.getPassword()));
            userRepo.save(userEntity);
            baseResponse.setStatus(SUCCESS_STATUS_CODE);
            baseResponse.setMessage(SUCCESS_DATA_INSERTED_STATUS_MESSAGE);
            baseResponse.setResult(userEntity);
        } catch (Exception ex) {
            LOG.warning(ex.getMessage());
        }
        return baseResponse;
    }

    public BaseResponse getSingleUser(String userEmail) {
        BaseResponse baseResponse = new BaseResponse(true);
        try {
            if (userEmail.isEmpty()) {
                baseResponse.setStatus(ERROR_STATUS_CODE);
                baseResponse.setMessage("Email cannot be empty");
                return baseResponse;
            }
            Optional<UserEntity> optUser = userRepo.findByEmailAddress(userEmail);
            if (!optUser.isPresent()) {
                baseResponse.setStatus(ERROR_STATUS_CODE);
                baseResponse.setMessage("User does not exist");
                return baseResponse;
            }
            UserEntity userEntity = optUser.get();
            String firstName = userEntity.getFirstName();
            String lastName = userEntity.getLastName();
            String phoneNumber = userEntity.getPhoneNumber();
            String emailAddress = userEntity.getEmailAddress();
            String dateOfBirth = userEntity.getDateOfBirth();

            HashMap<String, String> responseData = new HashMap<>();
            responseData.put("firstname", firstName);
            responseData.put("lastname", lastName);
            responseData.put("phoneNumber", phoneNumber);
            responseData.put("emailAddress", emailAddress);
            responseData.put("date_of_birth", dateOfBirth);

            baseResponse.setStatus(SUCCESS_STATUS_CODE);
            baseResponse.setMessage("Successful");
            baseResponse.setResult(responseData);

        } catch (Exception ex) {
            LOG.warning(ex.getMessage());
        }
        return baseResponse;
    }

    public BaseResponse updateUser(UserData userData) {
        BaseResponse baseResponse = new BaseResponse(true);

        try {
            Optional<UserEntity> optUser = userRepo.findByEmailAddress(userData.getEmailAddress());
            if (!optUser.isPresent()) {
                baseResponse.setStatus(ERROR_STATUS_CODE);
                baseResponse.setMessage("User does not exist");
                return baseResponse;
            }
            UserEntity getOlduserEntity = optUser.get();
            Long id = getOlduserEntity.getId();
            UserEntity newUserEntity = new UserEntity(userData);
            newUserEntity.setId(id);
            userRepo.save(newUserEntity);
            baseResponse.setStatus(SUCCESS_STATUS_CODE);
            baseResponse.setMessage("Record Updated Successful");
        } catch (Exception ex) {
            LOG.warning(ex.getMessage());
        }
        return baseResponse;
    }

    public BaseResponse getAllUser() {
        BaseResponse baseResponse = new BaseResponse(true);
        try {
            List<UserEntity> allUser = userRepo.findAll();
            List<UserData> userResponse = new ArrayList<>();
            for (UserEntity userEntity : allUser) {
                UserData setUserResponse = new UserData();
                setUserResponse.setEmailAddress(userEntity.getEmailAddress());
                setUserResponse.setFirstName(userEntity.getFirstName());
                setUserResponse.setLastName(userEntity.getLastName());
                setUserResponse.setPhoneNumber(userEntity.getPhoneNumber());
                setUserResponse.setDateOfBirth(userEntity.getDateOfBirth());
                userResponse.add(setUserResponse);
            }
            baseResponse.setStatus(SUCCESS_STATUS_CODE);
            baseResponse.setMessage("Data Available");
            baseResponse.setResult(userResponse);
        } catch (Exception ex) {
            LOG.warning(ex.getMessage());
        }
        return baseResponse;
    }

    public BaseResponse deleteUser(String userEmail) {
        BaseResponse baseResponse = new BaseResponse(true);
        try {
            Optional<UserEntity> getUserEmail = userRepo.findByEmailAddress(userEmail);
            if (!getUserEmail.isPresent()) {
                baseResponse.setStatus(ERROR_STATUS_CODE);
                baseResponse.setMessage("User does not exist");
                return baseResponse;
            }
            UserEntity userEntity = getUserEmail.get();
            userRepo.delete(userEntity);
            baseResponse.setStatus(SUCCESS_STATUS_CODE);
            baseResponse.setMessage("Record Deleted Successful");
        } catch (Exception ex) {
            LOG.warning(ex.getMessage());
        }
        return baseResponse;
    }

    public BaseResponse userLogin(UserLogin userLogin) {
        BaseResponse baseResponse = new BaseResponse(true);
        try {
            String password = encryptPassword(userLogin.getPassword());
            Optional<UserEntity> getUserEmail = userRepo.userLoginAccess(userLogin.getEmailAddress(), password);
            if (!getUserEmail.isPresent()) {
                baseResponse.setStatus(ERROR_STATUS_CODE);
                baseResponse.setMessage("Invalid Username or Password");
                return baseResponse;
            }

            UserEntity userEntity = getUserEmail.get();
            String firstname = userEntity.getFirstName();
            String lastname = userEntity.getLastName();
            String emailAddress = userEntity.getEmailAddress();
            String phoneNumber = userEntity.getPhoneNumber();
            String dateOfBirth = userEntity.getDateOfBirth();

            String token = JWTConfig.generateToken(emailAddress,password);

            UserDto userDto = new UserDto();
            userDto.setFirstname(firstname);
            userDto.setLastname(lastname);
            userDto.setPhoneNumber(phoneNumber);
            userDto.setEmailAddress(emailAddress);
            userDto.setDateOfBirth(dateOfBirth);
            userDto.setToken(token);
//            HashMap<String, String> data = new HashMap<>();
//            data.put("firstname", firstname);
//            data.put("lastname", lastname);
//            data.put("email_address", emailAddress);
//            data.put("phone_number", phoneNumber);
//            data.put("date_of_birth", dateOfBirth);
////            data.put("token", token);
            baseResponse.setStatus(SUCCESS_STATUS_CODE);
            baseResponse.setMessage("Sign In Successful");
            baseResponse.setResult(userDto);
        } catch (Exception ex) {
            LOG.warning(ex.getMessage());
        }
        return baseResponse;
    }

    public String encryptPassword(String userPassword) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(userPassword.getBytes());
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return userPassword;
    }
}
