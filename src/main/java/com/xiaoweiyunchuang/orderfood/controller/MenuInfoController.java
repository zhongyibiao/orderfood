package com.xiaoweiyunchuang.orderfood.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoweiyunchuang.microcloud.common.DateUtil;
import com.xiaoweiyunchuang.microcloud.common.IDGenerate;
import com.xiaoweiyunchuang.orderfood.domain.MenuInfo;
import com.xiaoweiyunchuang.orderfood.service.MenuConfService;
import com.xiaoweiyunchuang.orderfood.service.MenuInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "菜品服务", description = "提供RESTful风格API的菜品的增删改查服务")
@RestController
public class MenuInfoController extends AbstractController {
	@Autowired
	MenuInfoService menuInfoService;
	@Autowired
	MenuConfService menuConfService;

	// -------------------Retrieve All------------------- //
	@ApiOperation("查询菜品")
	@RequestMapping(value = "/menuInfo", method = RequestMethod.GET)
	public ResponseEntity<List<MenuInfo>> getMenuInfo() {
		List<MenuInfo> menuInfos = menuInfoService.selectMenuInfos(null);
		if (menuInfos.isEmpty()) {
			return new ResponseEntity<List<MenuInfo>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<MenuInfo>>(menuInfos, HttpStatus.OK);
	}

	// -------------------Retrieve Single------------------- //
	@ApiOperation("通过id查询菜品")
	@RequestMapping(value = "/menuInfo/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MenuInfo> getMenuInfo(@ApiParam("菜品id") @PathVariable("id") String id) {
		logger.info("Fetching MenuInfo with id " + id);
		MenuInfo menuInfo = menuInfoService.selectByPrimaryKey(id);
		if (menuInfo == null) {
			logger.info("MenuInfo with id " + id + " not found");
			return new ResponseEntity<MenuInfo>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<MenuInfo>(menuInfo, HttpStatus.OK);
	}

	// -------------------Retrieve Page------------------- //
	@ApiOperation("通过菜品类型id查询菜品")
	@RequestMapping(value = "menuInfo/{menuTypeId}/{pageNum}/{pageSize}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PageInfo<MenuInfo>> getMenuInfo(
			@ApiParam("菜品类型id") @PathVariable("menuTypeId") String menuTypeId,
			@ApiParam("pageNum") @PathVariable Integer pageNum, @ApiParam("pageSize") @PathVariable Integer pageSize) {
		if (pageNum != null && pageSize != null) {
			PageHelper.startPage(pageNum, pageSize);
		}
		MenuInfo menuInfo = new MenuInfo();
		menuInfo.setMenuTypeId(menuTypeId);
		List<MenuInfo> menuInfos = menuInfoService.selectMenuInfos(menuInfo);

		return new ResponseEntity<PageInfo<MenuInfo>>(new PageInfo<MenuInfo>(menuInfos), HttpStatus.OK);
	}

	// -------------------Retrieve Page------------------- //
	// @ApiOperation("分页查询菜品")
	// @RequestMapping(value = "menuInfo/{pageNum}/{pageSize}", method =
	// RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	// public ResponseEntity<PageInfo<MenuInfo>>
	// getMenuInfo(@ApiParam("pageNum") @PathVariable Integer pageNum,
	// @ApiParam("pageSize") @PathVariable Integer pageSize,
	// @ApiParam("菜品类型") @RequestParam(value = "menuTypeId", required = false)
	// String menuTypeId,
	// @ApiParam("菜品名称") @RequestParam(value = "menuName", required = false)
	// String menuName) {

	@RequestMapping(value = "menuInfo/{menuTypeId}/{menuName}/{pageNum}/{pageSize}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PageInfo<MenuInfo>> getMenuInfo(
			@ApiParam("菜品类型") @PathVariable(value = "menuTypeId", required = false) String menuTypeId,
			@ApiParam("菜品名称") @PathVariable(value = "menuName", required = false) String menuName,
			@ApiParam("pageNum") @PathVariable Integer pageNum, @ApiParam("pageSize") @PathVariable Integer pageSize) {
		if (pageNum != null && pageSize != null) {
			PageHelper.startPage(pageNum, pageSize);
		}

		MenuInfo menuInfo = new MenuInfo();
		// 前台不传值时，PathVariable变量的值为：{变量名}。
		if (StringUtils.isNotEmpty(menuName) && menuName.indexOf("{") < 0) {
			menuInfo.setMenuName(menuName);
		}
		if (StringUtils.isNotEmpty(menuTypeId) && menuName.indexOf("{") < 0) {
			menuInfo.setMenuTypeId(menuTypeId);
		}

		List<MenuInfo> MenuInfos = menuInfoService.selectMenuInfos(menuInfo);

		return new ResponseEntity<PageInfo<MenuInfo>>(new PageInfo<MenuInfo>(MenuInfos), HttpStatus.OK);
	}

	// -------------------Create a------------------- //
	@ApiOperation("添加菜品")
	@RequestMapping(value = "/menuInfo", method = RequestMethod.POST)
	// public ResponseEntity<Void> createMenuInfo(@ApiParam("菜品信息") @RequestBody
	// MenuInfo menuInfo,
	// @RequestParam("file") MultipartFile file, UriComponentsBuilder ucBuilder)
	// {

	public ResponseEntity<Void> createMenuInfo(@ApiParam("菜品信息") @RequestBody MenuInfo menuInfo,
			UriComponentsBuilder ucBuilder) {

		logger.info("Creating MenuInfo " + menuInfo.getMenuName());

		if (menuInfoService.isMenuInfoExist(menuInfo)) {
			logger.info("A MenuInfo with name " + menuInfo.getMenuName() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		/*
		 * if (!file.isEmpty()) { String fileName = ""; try { fileName =
		 * handleFileUpload(file); } catch (IllegalStateException | IOException
		 * e) { logger.info("You failed to upload " + " => " +
		 * e.getMessage(),e); } menuInfo.setMenuImg(fileName); }
		 * 
		 */

		Subject subject = SecurityUtils.getSubject();
		menuInfo.setMenuId(IDGenerate.getId());
		menuInfo.setCreateBy(subject.getPrincipal().toString());
		menuInfo.setCreateDate(DateUtil.now());
		menuInfo.setUpdateBy(subject.getPrincipal().toString());
		menuInfo.setUpdateDate(DateUtil.now());
		menuInfo.setDelFlag("N");

		menuInfoService.insert(menuInfo);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}

	// ------------------- Update a MenuInfo------------------- //
	@ApiOperation("更新菜品")
	@RequestMapping(value = "/menuInfo/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateMenuInfo(@ApiParam("菜品id") @PathVariable("id") String id,
			@ApiParam("菜品信息") @RequestBody MenuInfo menuInfo) {
		logger.info("Updating MenuInfo " + id);

		if (menuInfoService.selectByPrimaryKey(id) == null) {
			logger.info("MenuInfo with id " + id + " not found");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

		Subject subject = SecurityUtils.getSubject();
		menuInfo.setMenuId(id);
		menuInfo.setUpdateBy(subject.getPrincipal().toString());
		menuInfo.setUpdateDate(DateUtil.now());

		menuInfoService.updateByPrimaryKeySelective(menuInfo);

		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}

	// ------------------- Delete a MenuInfo------------------- //
	@ApiOperation("通过ID删除菜品")
	@RequestMapping(value = "/menuInfo/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteMenuInfo(@ApiParam("菜品id") @PathVariable("id") String id) {
		logger.info("Fetching & Deleting MenuInfo with id " + id);

		if (menuInfoService.selectByPrimaryKey(id) == null) {
			logger.info("MenuInfo with id " + id + " not found");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

		menuInfoService.deleteByPrimaryKey(id);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}

	// 上传附件
	private String handleFileUpload(MultipartFile file) throws IllegalStateException, IOException {
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

		// byte[] bytes = file.getBytes();
		// BufferedOutputStream stream = new BufferedOutputStream(new
		// FileOutputStream(new File(fileName)));
		// stream.write(bytes);
		// stream.close();

		logger.info("You successfully uploaded.");

		return fileName;
	}

}
