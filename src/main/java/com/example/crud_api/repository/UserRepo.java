package com.example.crud_api.repository;

import com.example.crud_api.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmailAddress(String emailAddress);
    @Query(value = "SELECT u FROM UserEntity u WHERE u.emailAddress = :userEmailAddress AND u.password= :userPassword")
    Optional<UserEntity> userLoginAccess(@Param("userEmailAddress") String userEmailAddress, @Param("userPassword") String userPassword);

    @Override
    List<UserEntity> findAll();
}
