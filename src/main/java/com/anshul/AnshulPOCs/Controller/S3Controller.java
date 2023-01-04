package com.anshul.AnshulPOCs.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.anshul.AnshulPOCs.Service.S3Service;

@RestController
@RequestMapping("/api/s3/")
public class S3Controller {
	
	@Autowired
	S3Service s3Service;

	@PostMapping("uploadFileToS3")
	public ResponseEntity<String> uploadFileToS3(@RequestParam("file") MultipartFile file) throws IOException {
	
		return new ResponseEntity<>(s3Service.uploadFileToS3(file),HttpStatus.OK);	 
	}
	
	@GetMapping("downloadFileFromS3/{fileName}")
	public ResponseEntity<byte[]> downloadFileFromS3(@PathVariable String fileName)  {
	
		return new ResponseEntity<>(s3Service.downloadFile(fileName),HttpStatus.OK);	 
	}
	
	@GetMapping("deleteFileFromS3/{fileName}")
	public ResponseEntity<String> deleteFileFromS3(@PathVariable String fileName)  {
	
		return new ResponseEntity<>(s3Service.deleteFile(fileName),HttpStatus.OK);	 
	}
}
