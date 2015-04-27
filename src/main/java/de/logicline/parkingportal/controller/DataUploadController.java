package de.logicline.parkingportal.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import de.logicline.parkingportal.model.UserEntity;
import de.logicline.parkingportal.service.DataUploadService;



@Controller
public class DataUploadController {

	@Autowired
	private DataUploadService dataUploadService;
	
	@RequestMapping("/uploadPageJsp")
	public String showUploadPage(Map<String, Object> map) {
		return "dataUpload";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public @ResponseBody String provideUploadInfo() {
		return "You can upload a file by posting to this same URL.";
	}

	
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public @ResponseBody String uploadFileHandler(
			@RequestParam("name") String name,
			@RequestParam("file") MultipartFile file) {
		
		List<UserEntity> userList = new LinkedList<UserEntity>();
		if (!file.isEmpty()) {
			dataUploadService.generate(file);
			
			return "Users successfully added";
		} else {
			return "You failed to upload " + name
					+ " because the file was empty.";
		}
	}

	
	
}
