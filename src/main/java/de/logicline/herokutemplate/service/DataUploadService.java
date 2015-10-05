package de.logicline.herokutemplate.service;

import org.springframework.web.multipart.MultipartFile;

public interface DataUploadService {
    
    public void generate(MultipartFile file);
   
}
