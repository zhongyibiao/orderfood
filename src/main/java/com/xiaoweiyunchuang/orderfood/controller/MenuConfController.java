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

import com.github.pagehelper.PageInfo;
import com.xiaoweiyunchuang.microcloud.common.DateUtil;
import com.xiaoweiyunchuang.microcloud.common.IDGenerate;
import com.xiaoweiyunchuang.orderfood.domain.MenuConf;
import com.xiaoweiyunchuang.orderfood.service.MenuConfService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "菜品配制服务", description = "提供RESTful风格API的菜品配制的增删改查服务")
@RestController
public class MenuConfController extends AbstractController {
	@Autowired
	MenuConfService menuConfService;

	// -------------------Retrieve Single------------------- //
	@ApiOperation("通过id查询菜品配制")
	@RequestMapping(value = "/menuConf/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MenuConf> getMenuConf(@ApiParam("菜品配制id") @PathVariable("id") String id) {
		logger.info("Fetching MenuConf with id " + id);
		MenuConf MenuConf = menuConfService.selectByPrimaryKey(id);
		if (MenuConf == null) {
			logger.info("MenuConf with id " + id + " not found");
			return new ResponseEntity<MenuConf>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<MenuConf>(MenuConf, HttpStatus.OK);
	}

	// -------------------Retrieve Page------------------- //
	@ApiOperation("通过 菜品id查询菜品配制")
	@RequestMapping(value = "menuConf/{menuId}/{random}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PageInfo<MenuConf>> getMenuConf(@ApiParam("菜品id") @PathVariable("menuId") String menuId,
			@ApiParam("随机数") @PathVariable("random") long random) {
		MenuConf menuConf = new MenuConf();
		menuConf.setMenuId(menuId);
		List<MenuConf> MenuInfos = menuConfService.selectMenuConfs(menuConf);
		return new ResponseEntity<PageInfo<MenuConf>>(new PageInfo<MenuConf>(MenuInfos), HttpStatus.OK);
	}

	// -------------------Create a------------------- //
	@ApiOperation("添加菜品配制")
	@RequestMapping(value = "/menuConf", method = RequestMethod.POST)
	public ResponseEntity<Void> createMenuConf(@ApiParam("菜品配制信息") @RequestBody MenuConf menuConf,
			UriComponentsBuilder ucBuilder) {
		logger.info("Creating MenuConf " + menuConf.getMenuConfName());

		if (menuConfService.isMenuConfExist(menuConf)) {
			logger.info("A MenuConf with name " + menuConf.getMenuConfName() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		Subject subject = SecurityUtils.getSubject();
		menuConf.setMenuConfId(IDGenerate.getId());
		menuConf.setCreateBy(subject.getPrincipal().toString());
		menuConf.setCreateDate(DateUtil.now());
		menuConf.setUpdateBy(subject.getPrincipal().toString());
		menuConf.setUpdateDate(DateUtil.now());
		menuConf.setDelFlag("N");

		menuConfService.insert(menuConf);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}

	// ------------------- Update a MenuConf------------------- //
	@ApiOperation("更新菜品配制")
	@RequestMapping(value = "/menuConf/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateMenuConf(@ApiParam("菜品配制id") @PathVariable("id") String id,
			@ApiParam("菜品配制信息") @RequestBody MenuConf menuConf) {
		logger.info("Updating MenuConf " + id);

		if (menuConfService.selectByPrimaryKey(id) == null) {
			logger.info("MenuConf with id " + id + " not found");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

		Subject subject = SecurityUtils.getSubject();
		menuConf.setMenuConfId(id);
		menuConf.setUpdateBy(subject.getPrincipal().toString());
		menuConf.setUpdateDate(DateUtil.now());

		menuConfService.updateByPrimaryKeySelective(menuConf);

		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}

	// ------------------- Delete a MenuConf------------------- //
	@ApiOperation("通过ID删除菜品配制")
	@RequestMapping(value = "/menuConf/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteMenuConf(@ApiParam("菜品配制id") @PathVariable("id") String id) {
		logger.info("Fetching & Deleting MenuConf with id " + id);

		if (menuConfService.selectByPrimaryKey(id) == null) {
			logger.info("MenuConf with id " + id + " not found");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

		menuConfService.deleteByPrimaryKey(id);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}

}
