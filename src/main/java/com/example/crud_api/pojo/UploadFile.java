package com.example.crud_api.pojo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;

@Data
public class UploadFile {
    @NotEmpty(message = "Name cannot be empty ID cannot be empty")
    private String name;
    private String age;
    private String occupation;
    private MultipartFile resume;

    public UploadFile() {
    }
}
