package com.xiaoweiyunchuang.orderfood.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class FileUploadController extends AbstractController {

	@RequestMapping(value = "/file", method = RequestMethod.GET)
	public String uploadInfo() {
		return "file";
	}

	@RequestMapping(value = "/mutifile", method = RequestMethod.GET)
	public String multipartUploadInfo() {
		return "mutifile";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	@ResponseBody
	public String provideUploadInfo() {
		return "You can upload a file by posting to this same URL.";
	}

	/**
	 * 单文件上传;
	 * 
	 * @param file
	 * @return
	 */
	@RequestMapping("/upload")
	public ResponseEntity<Map<String, String>> handleFileUpload(@RequestParam("file") MultipartFile file) {
		String fileId = "";
		String errMsg = "";
		Map<String, String> resultMap = new HashMap<String, String>();
		if (!file.isEmpty()) {

			try {
				// 获取文件名
				String fileName = file.getOriginalFilename();
				logger.info("上传的文件名为：" + fileName);

				// 获取文件的后缀名
				String suffixName = fileName.substring(fileName.lastIndexOf("."));
				logger.info("上传的后缀名为：" + suffixName);

				// 文件上传路径
				String filePath = "D:/Program Files/upload/files/";

				// 解决中文问题，liunx下中文路径，图片显示问题
				fileName = UUID.randomUUID() + suffixName;
				File dest = new File(filePath + fileName);

				// 检测是否存在目录
				if (!dest.getParentFile().exists()) {
					dest.getParentFile().mkdirs();
				}
				file.transferTo(dest);

				fileId = fileName;

				// TODO 把附件ID、附件名到数据库，用于后面下载和显示，各业务功能自己实现。

				// byte[] bytes = file.getBytes();
				// BufferedOutputStream stream = new BufferedOutputStream(new
				// FileOutputStream(new File(fileName)));
				// stream.write(bytes);
				// stream.close();

				resultMap.put("fileId", fileId);
				return new ResponseEntity<Map<String, String>>(resultMap, HttpStatus.OK);

			} catch (Exception e) {
				errMsg = "You failed to upload " + " => " + e.getMessage();
			}

		} else {
			errMsg = "You failed to upload because the file was empty.";
		}

		if (StringUtils.isNotEmpty(errMsg)) {
			resultMap.put("errMsg", errMsg);
			return new ResponseEntity<Map<String, String>>(resultMap, HttpStatus.BAD_REQUEST);
		}

		resultMap.put("fileId", fileId);
		return new ResponseEntity<Map<String, String>>(resultMap, HttpStatus.OK);
	}

	/**
	 * 多文件上传
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/batch/upload", method = RequestMethod.POST)
	public ResponseEntity<Map<String, String>> handleBatchFileUpload(HttpServletRequest request) {
		List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
		MultipartFile file = null;
		StringBuffer fileIds = new StringBuffer();
		String errMsg = "";

		Map<String, String> resultMap = new HashMap<String, String>();

		// BufferedOutputStream stream = null;
		for (int i = 0; i < files.size(); ++i) {
			file = files.get(i);
			if (!file.isEmpty()) {
				try {
					// byte[] bytes = file.getBytes();
					// stream = new BufferedOutputStream(new
					// FileOutputStream(new File(file.getOriginalFilename())));
					// stream.write(bytes);
					// stream.close();

					// 获取文件名
					String fileName = file.getOriginalFilename();
					logger.info("上传的文件名为：" + fileName);
					// 获取文件的后缀名
					String suffixName = fileName.substring(fileName.lastIndexOf("."));
					logger.info("上传的后缀名为：" + suffixName);
					// 文件上传路径
					String filePath = "D:/Program Files/upload/files/";
					// 解决中文问题，liunx下中文路径，图片显示问题
					fileName = UUID.randomUUID() + suffixName;
					File dest = new File(filePath + fileName);
					// 检测是否存在目录
					if (!dest.getParentFile().exists()) {
						dest.getParentFile().mkdirs();
					}
					file.transferTo(dest);

					// TODO 把附件ID、附件名到数据库，用于后面下载和显示，各业务功能自己实现。
					fileIds.append(fileName);
					fileIds.append(",");

					// byte[] bytes = file.getBytes();
					// BufferedOutputStream stream =
					// new BufferedOutputStream(new FileOutputStream(new
					// File(fileName)));
					// stream.write(bytes);
					// stream.close();

				} catch (Exception e) {
					// stream = null;
					errMsg = "You failed to upload " + " => " + e.getMessage();
					break;

				}

			} else {
				errMsg = "You failed to upload " + i + " because the file was empty.";
			}
		}

		if (StringUtils.isNotEmpty(errMsg)) {
			resultMap.put("errMsg", errMsg);
			return new ResponseEntity<Map<String, String>>(resultMap, HttpStatus.BAD_REQUEST);
		}

		resultMap.put("fileId", fileIds.toString());
		return new ResponseEntity<Map<String, String>>(resultMap, HttpStatus.OK);
	}

}
