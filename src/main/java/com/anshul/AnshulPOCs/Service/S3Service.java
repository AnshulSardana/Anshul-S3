package com.anshul.AnshulPOCs.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.anshul.AnshulPOCs.Utility.S3Utility;

@Service
public class S3Service {

	@Autowired
	AmazonS3 amazonS3;

	@Value("${aws.bucketName}")
	private String bucketName;

	public String uploadFileToS3(MultipartFile file) {

		String fileName = System.currentTimeMillis() + file.getOriginalFilename();
		File convFile = null;
		try {
			convFile = S3Utility.convertMultipartToFile(file);
		} catch (IOException e) {

			throw new RuntimeException(e.getMessage());
		}

		PutObjectRequest objectRequest = new PutObjectRequest(bucketName, fileName, convFile);
		PutObjectResult objectResult = amazonS3.putObject(objectRequest);

		return "File Uploaded successfully";
	}

	public byte[] downloadFile(String fileName) {

		S3Object object = amazonS3.getObject(bucketName, fileName);

		S3ObjectInputStream stream = object.getObjectContent();

		try {
			return IOUtils.toByteArray(stream);
		} catch (IOException e) {

			throw new RuntimeException(e.getMessage());
		}
	}

	public String deleteFile(String fileName) {

		amazonS3.deleteObject(bucketName, fileName);

		return "File Deleted Successfully";
	}

	public List<String> getAllFilesInBucket() {

		ListObjectsV2Result list = amazonS3.listObjectsV2(bucketName);
		return list.getObjectSummaries().stream().map(o -> o.getKey()).collect(Collectors.toList());
	}
}
