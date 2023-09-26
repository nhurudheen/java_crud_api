package com.example.crud_api.model;

import com.example.crud_api.pojo.UploadFile;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;
@Data
@Entity
@Table(name = "uploading_file")
public class UploadFileEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    private String name;
    private String age;
    private String occupation;
    private String resume;

    public UploadFileEntity() {
    }
    public UploadFileEntity(UploadFile uploadFile){
        this.name = uploadFile.getName();
        this.age = uploadFile.getAge();
        this.occupation= uploadFile.getOccupation();
    }

}
