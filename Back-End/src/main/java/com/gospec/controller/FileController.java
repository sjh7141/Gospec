package com.gospec.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gospec.property.FileUploadResponse;
import com.gospec.security.GoUserDetailsService;
import com.gospec.service.FileUploadDownloadService;

import io.swagger.annotations.ApiOperation;


//http://localhost:8181/swagger-ui.html
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequestMapping("/api/file")
public class FileController {
	
	@Autowired
	private FileUploadDownloadService fileService;

	@ApiOperation(value = "파일업로드, fileDownloadUri정보로 다운로드 가능하다.", response = FileUploadResponse.class)
	@PostMapping(value ="/upload")
	public ResponseEntity<FileUploadResponse> uploadfile(@RequestParam("file") MultipartFile file) {
		String fileName = fileService.storeFile(file);
		
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
								.path("/api/file")
								.path("/download/")
								.path(fileName)
								.toUriString();
		return new ResponseEntity<FileUploadResponse>(new FileUploadResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize()), HttpStatus.OK);
	}
	
	@ApiOperation(value = "파일다운로드, db의 profile_img로 요청", response = FileUploadResponse.class)
	@GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request){
        Resource resource = fileService.loadFileasResource(fileName);

        String contentType = null;
        try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}      
 
        if(contentType == null) {
            contentType = "application/octet-stream";
        }
 
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
