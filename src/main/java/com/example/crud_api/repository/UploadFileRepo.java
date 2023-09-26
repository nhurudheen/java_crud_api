package com.example.crud_api.repository;

import com.example.crud_api.model.UploadFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadFileRepo extends JpaRepository<UploadFileEntity, Long> {
}
