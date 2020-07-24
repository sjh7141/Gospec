package com.gospec.service;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.gospec.exception.FileDownloadException;
import com.gospec.exception.FileUploadException;
import com.gospec.property.FileUploadProperties;

@Service
public class FileUploadDownloadService {
	private final Path fileLocation;
	
	@Autowired
	public FileUploadDownloadService(FileUploadProperties prop) {
		this.fileLocation = Paths.get(prop.getUploadDir())
				.toAbsolutePath().normalize();
		try {
			Files.createDirectories(this.fileLocation);
		}catch(Exception e) {
			throw new FileUploadException("디렉토리 생성 실패");
		}
	}
	
	public String storeFile(MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		String fileExtension = fileName.substring(fileName.lastIndexOf("."));
		String newFileName = UUID.randomUUID().toString() + fileExtension;
		try {
			Path targetLocation = this.fileLocation.resolve(newFileName);
			File tempfile = new File(targetLocation.toString());
			file.transferTo(tempfile);
			return newFileName;
		}catch(Exception e) {
			throw new FileUploadException("["+fileName+"] 파일 업로드에 실패하였습니다.");
		}
	}
	
	public Resource loadFileasResource(String fileName) {
		try {
			Path filePath = this.fileLocation.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			
			if(resource.exists()) {
				return resource;
			}else {
				throw new FileDownloadException(fileName + " 파일을 찾을 수 없습니다.");
			}
		}catch(MalformedURLException e) {
			throw new FileDownloadException(fileName + " 파일을 찾을 수 없습니다.", e);
		}
	}
}
