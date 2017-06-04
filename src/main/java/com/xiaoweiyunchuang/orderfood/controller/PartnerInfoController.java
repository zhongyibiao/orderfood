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
import com.xiaoweiyunchuang.orderfood.domain.PartnerInfo;
import com.xiaoweiyunchuang.orderfood.service.PartnerInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "商户服务", description = "提供RESTful风格API的商户的增删改查服务")
@RestController
public class PartnerInfoController extends AbstractController {
	@Autowired
	PartnerInfoService partnerInfoService;

	// -------------------Retrieve All------------------- //
	@ApiOperation("查询商户")
	@RequestMapping(value = "/partnerInfo", method = RequestMethod.GET)
	public ResponseEntity<List<PartnerInfo>> getPartnerInfo() {
		List<PartnerInfo> dinnerRooms = partnerInfoService.selectPartnerInfos(null);
		if (dinnerRooms.isEmpty()) {
			return new ResponseEntity<List<PartnerInfo>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<PartnerInfo>>(dinnerRooms, HttpStatus.OK);
	}

	// -------------------Retrieve Single------------------- //
	@ApiOperation("通过id查询商户")
	@RequestMapping(value = "/partnerInfo/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PartnerInfo> getPartnerInfo(@ApiParam("商户id") @PathVariable("id") String id) {
		logger.info("Fetching PartnerInfo with id " + id);
		PartnerInfo PartnerInfo = partnerInfoService.selectByPrimaryKey(id);
		if (PartnerInfo == null) {
			logger.info("PartnerInfo with id " + id + " not found");
			return new ResponseEntity<PartnerInfo>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<PartnerInfo>(PartnerInfo, HttpStatus.OK);
	}

	// -------------------Retrieve Page------------------- //
	@ApiOperation("分页查询商户")
	@RequestMapping(value = "partnerInfo/{partnerRegion}/{pageNum}/{pageSize}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PageInfo<PartnerInfo>> getPartnerInfo(@ApiParam("地区，地域") @PathVariable String partnerRegion,
			@ApiParam("pageNum") @PathVariable Integer pageNum, @ApiParam("pageSize") @PathVariable Integer pageSize) {
		if (pageNum != null && pageSize != null) {
			PageHelper.startPage(pageNum, pageSize);
		}
		PartnerInfo partnerInfo = new PartnerInfo();
		partnerInfo.setPartnerRegion(partnerRegion);
		List<PartnerInfo> dinnerRooms = partnerInfoService.selectPartnerInfos(partnerInfo);
		return new ResponseEntity<PageInfo<PartnerInfo>>(new PageInfo<PartnerInfo>(dinnerRooms), HttpStatus.OK);
	}

	// -------------------Create a------------------- //
	@ApiOperation("添加商户")
	@RequestMapping(value = "/partnerInfo", method = RequestMethod.POST)
	public ResponseEntity<Void> createPartnerInfo(@ApiParam("商户信息") @RequestBody PartnerInfo partnerInfo,
			UriComponentsBuilder ucBuilder) {
		logger.info("Creating PartnerInfo " + partnerInfo.getPartnerName());

		if (partnerInfoService.isPartnerInfoExist(partnerInfo)) {
			logger.info("A PartnerInfo with name " + partnerInfo.getPartnerName() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		Subject subject = SecurityUtils.getSubject();
		partnerInfo.setPartnerId(IDGenerate.getId());
		partnerInfo.setCreateBy(subject.getPrincipal().toString());
		partnerInfo.setCreateDate(DateUtil.now());
		partnerInfo.setUpdateBy(subject.getPrincipal().toString());
		partnerInfo.setUpdateDate(DateUtil.now());
		partnerInfo.setDelFlag("N");

		partnerInfoService.insert(partnerInfo);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}

	// ------------------- Update a PartnerInfo------------------- //
	@ApiOperation("更新商户")
	@RequestMapping(value = "/partnerInfo/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@ApiParam("商户id") @PathVariable("id") String id,
			@ApiParam("商户信息") @RequestBody PartnerInfo partnerInfo) {
		logger.info("Updating PartnerInfo " + id);

		if (partnerInfoService.selectByPrimaryKey(id) == null) {
			logger.info("PartnerInfo with id " + id + " not found");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

		Subject subject = SecurityUtils.getSubject();
		partnerInfo.setPartnerId(id);
		partnerInfo.setUpdateBy(subject.getPrincipal().toString());
		partnerInfo.setUpdateDate(DateUtil.now());

		partnerInfoService.updateByPrimaryKeySelective(partnerInfo);

		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}

	// ------------------- Delete a PartnerInfo------------------- //
	@ApiOperation("通过ID删除商户")
	@RequestMapping(value = "/partnerInfo/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletePartnerInfo(@ApiParam("商户id") @PathVariable("id") String id) {
		logger.info("Fetching & Deleting PartnerInfo with id " + id);

		if (partnerInfoService.selectByPrimaryKey(id) == null) {
			logger.info("PartnerInfo with id " + id + " not found");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

		partnerInfoService.deleteByPrimaryKey(id);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}

}
