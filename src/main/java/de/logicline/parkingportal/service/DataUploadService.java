package de.logicline.parkingportal.service;

import org.springframework.web.multipart.MultipartFile;

public interface DataUploadService {
    
    public void generate(MultipartFile file);
   
}
