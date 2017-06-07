package com.xiaoweiyunchuang.orderfood.controller;

import java.io.IOException;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileDowloadController extends AbstractController {

	@RequestMapping(value = "/file/{fileId}/{suffixName}", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> downloadFile(@PathVariable("id") String id) throws IOException {
		String filePath = "D:/Apache24/htdocs/images/" + id + ".doc";
		FileSystemResource file = new FileSystemResource(filePath);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getFilename()));
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");

		return ResponseEntity.ok().headers(headers).contentLength(file.contentLength())
				.contentType(MediaType.parseMediaType("application/octet-stream"))
				.body(new InputStreamResource(file.getInputStream()));
	}
}
