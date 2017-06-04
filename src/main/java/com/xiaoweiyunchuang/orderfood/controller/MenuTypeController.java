package com.xiaoweiyunchuang.orderfood.controller;

import java.util.List;

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
import org.springframework.web.util.UriComponentsBuilder;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoweiyunchuang.microcloud.common.DateUtil;
import com.xiaoweiyunchuang.microcloud.common.IDGenerate;
import com.xiaoweiyunchuang.orderfood.domain.MenuType;
import com.xiaoweiyunchuang.orderfood.service.MenuTypeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "菜品类型服务", description = "提供RESTful风格API的菜品类型的增删改查服务")
@RestController
public class MenuTypeController extends AbstractController {
	@Autowired
	MenuTypeService menuTypeService;

	// -------------------Retrieve All------------------- //
	@ApiOperation("查询菜品类型")
	@RequestMapping(value = "/menuType", method = RequestMethod.GET)
	public ResponseEntity<List<MenuType>> getMenuTypes() {
		List<MenuType> menuTypes = menuTypeService.selectMenuTypes(null);
		if (menuTypes.isEmpty()) {
			return new ResponseEntity<List<MenuType>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<MenuType>>(menuTypes, HttpStatus.OK);
	}

	// -------------------Retrieve Single------------------- //
	@ApiOperation("通过id查询菜品类型")
	@RequestMapping(value = "/menuType/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MenuType> getMenuType(@ApiParam("菜品类型id") @PathVariable("id") String id) {
		logger.info("Fetching MenuType with id " + id);
		MenuType menuType = menuTypeService.selectByPrimaryKey(id);
		if (menuType == null) {
			logger.info("MenuType with id " + id + " not found");
			return new ResponseEntity<MenuType>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<MenuType>(menuType, HttpStatus.OK);
	}

	// -------------------Retrieve Page------------------- //
	@ApiOperation("分页查询菜品类型")
	@RequestMapping(value = "menuType/{pageNum}/{pageSize}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PageInfo<MenuType>> getMenuTypes(@ApiParam("pageNum") @PathVariable Integer pageNum,
			@ApiParam("pageSize") @PathVariable Integer pageSize, @ApiParam("菜品信息") @RequestBody MenuType menuType) {
		if (pageNum != null && pageSize != null) {
			PageHelper.startPage(pageNum, pageSize);
		}
		List<MenuType> menuTypes = menuTypeService.selectMenuTypes(null);

		return new ResponseEntity<PageInfo<MenuType>>(new PageInfo<MenuType>(menuTypes), HttpStatus.OK);
	}

	// -------------------Create a------------------- //
	@ApiOperation("添加菜品类型")
	@RequestMapping(value = "/menuType", method = RequestMethod.POST)
	public ResponseEntity<Void> createMenuType(@ApiParam("菜品类型信息") @RequestBody MenuType menuType,
			UriComponentsBuilder ucBuilder) {
		logger.info("Creating MenuType " + menuType.getMenuTypeName());

		if (menuTypeService.isMenuTypeExist(menuType)) {
			logger.info("A MenuType with name " + menuType.getMenuTypeName() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		Subject subject = SecurityUtils.getSubject();
		menuType.setMenuTypeId(IDGenerate.getId());
		menuType.setCreateBy(subject.getPrincipal().toString());
		menuType.setCreateDate(DateUtil.now());
		menuType.setUpdateBy(subject.getPrincipal().toString());
		menuType.setUpdateDate(DateUtil.now());
		menuType.setDelFlag("N");

		menuTypeService.insert(menuType);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}

	// ------------------- Update a MenuType------------------- //
	@ApiOperation("更新菜品类型")
	@RequestMapping(value = "/menuType/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateMenuType(@ApiParam("菜品类型id") @PathVariable("id") String id,
			@ApiParam("菜品类型信息") @RequestBody MenuType menuType) {
		logger.info("Updating MenuType " + id);

		if (menuTypeService.selectByPrimaryKey(id) == null) {
			logger.info("MenuType with id " + id + " not found");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

		Subject subject = SecurityUtils.getSubject();
		menuType.setMenuTypeId(id);
		menuType.setUpdateBy(subject.getPrincipal().toString());
		menuType.setUpdateDate(DateUtil.now());

		menuTypeService.updateByPrimaryKeySelective(menuType);

		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}

	// ------------------- Delete a MenuType------------------- //
	@ApiOperation("通过ID删除菜品类型")
	@RequestMapping(value = "/menuType/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteMenuType(@ApiParam("菜品类型id") @PathVariable("id") String id) {
		logger.info("Fetching & Deleting MenuType with id " + id);

		if (menuTypeService.selectByPrimaryKey(id) == null) {
			logger.info("MenuType with id " + id + " not found");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

		menuTypeService.deleteByPrimaryKey(id);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}

}
