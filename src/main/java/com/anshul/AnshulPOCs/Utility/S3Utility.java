package com.anshul.AnshulPOCs.Utility;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class S3Utility {

	
	public static File convertMultipartToFile(MultipartFile file) throws IOException {

		File convertedFile = new File(file.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(convertedFile);
		fos.write(file.getBytes());
		fos.close();

		return convertedFile;
	}

}
