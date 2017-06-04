package com.xiaoweiyunchuang.orderfood.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.util.UriComponentsBuilder;
import org.thymeleaf.util.ListUtils;

import com.github.pagehelper.PageHelper;
import com.xiaoweiyunchuang.microcloud.common.DateUtil;
import com.xiaoweiyunchuang.microcloud.common.IDGenerate;
import com.xiaoweiyunchuang.orderfood.domain.SysConfig;
import com.xiaoweiyunchuang.orderfood.service.SysConfigService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "基础配制服务", description = "提供RESTful风格API的基础配制的增删改查服务")
@RestController
public class SysConfigController extends AbstractController {

	@Autowired
	SysConfigService service;

	// -------------------Valid PlaceOrderCode------------------- //
	@ApiOperation("校验下单验证码")
	@RequestMapping(value = "/sysConfig/{cfgCode}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, String>> validPlaceOrderCode(@ApiParam("配制Code") @PathVariable String cfgCode) {
		SysConfig sysConfig = new SysConfig();
		sysConfig.setCfgCode("placeOrderCode");
		List<SysConfig> sysConfigList = service.selectSysConfigs(sysConfig);
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("pass", "n");
		if (!ListUtils.isEmpty(sysConfigList)) {
			SysConfig sysConfig1 = sysConfigList.get(0);
			if (sysConfig1.getCfgValue().equals(cfgCode))
				resultMap.put("pass", "y");
		}
		return new ResponseEntity<Map<String, String>>(resultMap, HttpStatus.OK);
	}

	// -------------------Retrieve All------------------- //
	@ApiOperation("查询配制")
	@RequestMapping(value = "/sysConfig/{pageNum}/{pageSize}", method = RequestMethod.GET)
	public ResponseEntity<List<SysConfig>> getSysConfig(@ApiParam("pageNum") @PathVariable Integer pageNum,
			@ApiParam("pageSize") @PathVariable Integer pageSize) {
		if (pageNum != null && pageSize != null) {
			PageHelper.startPage(pageNum, pageSize);
		}

		List<SysConfig> sysConfigList = service.selectSysConfigs(null);
		if (sysConfigList.isEmpty()) {
			return new ResponseEntity<List<SysConfig>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<SysConfig>>(sysConfigList, HttpStatus.OK);
	}

	// -------------------Retrieve by cfgCode------------------- //
	@ApiOperation("通过配制code查询配制")
	@RequestMapping(value = "/sysConfig/{cfgId}/{cfgCode}/{cfgName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SysConfig>> getSysConfig(@ApiParam("配制code") @PathVariable("cfgId") String cfgId,
			@ApiParam("配制Code") @PathVariable String cfgCode, @ApiParam("配制名称") @PathVariable String cfgName) {
		SysConfig sysConfig = new SysConfig();

		// 前台不传值时，PathVariable变量的值为：{变量名}。
		if (StringUtils.isNotEmpty(cfgId) && cfgId.indexOf("{") < 0) {
			sysConfig.setCfgId(cfgId);
		}
		// 前台不传值时，PathVariable变量的值为：{变量名}。
		if (StringUtils.isNotEmpty(cfgCode) && cfgCode.indexOf("{") < 0) {
			sysConfig.setCfgCode(cfgCode);
		}
		// 前台不传值时，PathVariable变量的值为：{变量名}。
		if (StringUtils.isNotEmpty(cfgName) && cfgName.indexOf("{") < 0) {
			sysConfig.setCfgName(cfgName);
		}
		List<SysConfig> sysConfigList = service.selectSysConfigs(sysConfig);
		return new ResponseEntity<List<SysConfig>>(sysConfigList, HttpStatus.OK);
	}

	// -------------------Create a SysConfig------------------- //
	@ApiOperation("新增配制")
	@RequestMapping(value = "/sysConfig", method = RequestMethod.POST)
	public ResponseEntity<Void> createSysConfig(@ApiParam("配制信息") @RequestBody SysConfig sysConfig,
			UriComponentsBuilder ucBuilder) {
		logger.info("Creating SysConfig " + sysConfig.getCfgName());
		if (service.isSysConfigExsit(sysConfig)) {
			logger.info("Create SysConfig by " + sysConfig.getCfgName() + "and" + sysConfig.getCfgCode() + "is exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		Subject subject = SecurityUtils.getSubject();
		sysConfig.setCfgId(IDGenerate.getId());
		sysConfig.setCreateBy(subject.getPrincipal().toString());
		sysConfig.setCreateDate(DateUtil.now());
		sysConfig.setUpdateBy(subject.getPrincipal().toString());
		sysConfig.setUpdateDate(DateUtil.now());
		sysConfig.setDelFlag("N");
		service.insert(sysConfig);

		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}

	// ------------------- Update a SysConfig------------------- //
	@ApiOperation("更新配制")
	@RequestMapping(value = "/sysConfig/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateSysConfig(@ApiParam("配制id") @PathVariable("id") String id,
			@ApiParam("配制信息") @RequestBody SysConfig sysConfig) {
		logger.info("update Order by " + id);

		if (service.selectByPrimaryKey(id) == null) {
			logger.info("update order by " + id + " not found");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

		Subject subject = SecurityUtils.getSubject();
		sysConfig.setCfgId(id);
		sysConfig.setUpdateBy(subject.getPrincipal().toString());
		sysConfig.setUpdateDate(DateUtil.now());

		service.updateByPrimaryKeySelective(sysConfig);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}

	// ------------------- Delete a SysConfig------------------- //
	@ApiOperation("通过ID删除配制")
	@RequestMapping(value = "/sysConfig/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteSysConfig(@ApiParam("配制id") @PathVariable("id") String id) {
		logger.info("Fetching & Deleting SysConfig with id " + id);

		if (service.selectByPrimaryKey(id) == null) {
			logger.info("SysConfig with id " + id + " not found");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

		service.deleteByPrimaryKey(id);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}

}
