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
import com.xiaoweiyunchuang.orderfood.domain.DinnerDesk;
import com.xiaoweiyunchuang.orderfood.service.DinnerDeskService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "桌子服务", description = "提供RESTful风格API的桌子配制的增删改查服务")
@RestController
public class DinnerDeskController extends AbstractController {
	@Autowired
	DinnerDeskService dinnerDeskService;

	// -------------------Retrieve All------------------- //
	@ApiOperation("查询桌子")
	@RequestMapping(value = "/dinnerDesk", method = RequestMethod.GET)
	public ResponseEntity<List<DinnerDesk>> getDinnerDesk() {
		List<DinnerDesk> dinnerDesks = dinnerDeskService.selectDinnerDesks(null);
		if (dinnerDesks.isEmpty()) {
			return new ResponseEntity<List<DinnerDesk>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<DinnerDesk>>(dinnerDesks, HttpStatus.OK);
	}

	// -------------------Retrieve Single------------------- //
	@ApiOperation("通过id查询桌子")
	@RequestMapping(value = "/dinnerDesk/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DinnerDesk> getdinnerDesk(@ApiParam("桌子id") @PathVariable("id") String id) {
		logger.info("Fetching dinnerDesk with id " + id);
		DinnerDesk dinnerDesk = dinnerDeskService.selectByPrimaryKey(id);
		if (dinnerDesk == null) {
			logger.info("dinnerDesk with id " + id + " not found");
			return new ResponseEntity<DinnerDesk>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<DinnerDesk>(dinnerDesk, HttpStatus.OK);
	}

	// -------------------Retrieve Page------------------- //
	@ApiOperation("通过餐厅id查询桌子")
	@RequestMapping(value = "/dinnerDesk/{dinnerRoomId}/{pageNum}/{pageSize}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PageInfo<DinnerDesk>> getdinnerDesk(
			@ApiParam("餐厅id") @PathVariable("dinnerRoomId") String dinnerRoomId,
			@ApiParam("pageNum") @PathVariable Integer pageNum, @ApiParam("pageSize") @PathVariable Integer pageSize) {

		if (pageNum != null && pageSize != null) {
			PageHelper.startPage(pageNum, pageSize);
		}
		DinnerDesk dinnerDesk = new DinnerDesk();
		dinnerDesk.setDinnerRoomId(dinnerRoomId);
		List<DinnerDesk> dinnerDesks = dinnerDeskService.selectByDinnerRoomKey(dinnerRoomId);

		return new ResponseEntity<PageInfo<DinnerDesk>>(new PageInfo<DinnerDesk>(dinnerDesks), HttpStatus.OK);
	}

	// -------------------Create a------------------- //
	@ApiOperation("添加桌子")
	@RequestMapping(value = "/dinnerDesk", method = RequestMethod.POST)
	public ResponseEntity<Void> createDinnerDesk(@ApiParam("桌子信息") @RequestBody DinnerDesk dinnerDesk,
			UriComponentsBuilder ucBuilder) {
		logger.info("Creating dinnerDesk " + dinnerDesk.getDinnerDeskName());

		if (dinnerDeskService.isDinnerDeskExsit(dinnerDesk)) {
			logger.info("A DinnerDesk with name " + dinnerDesk.getDinnerDeskName() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		Subject subject = SecurityUtils.getSubject();
		dinnerDesk.setDinnerDeskId(IDGenerate.getId());
		dinnerDesk.setCreateBy(subject.getPrincipal().toString());
		dinnerDesk.setCreateDate(DateUtil.now());
		dinnerDesk.setUpdateBy(subject.getPrincipal().toString());
		dinnerDesk.setUpdateDate(DateUtil.now());
		dinnerDesk.setDelFlag("N");

		dinnerDeskService.insert(dinnerDesk);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}

	// ------------------- Update a dinnerDesk------------------- //
	@ApiOperation("更新桌子")
	@RequestMapping(value = "/dinnerDesk/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateDinnerDesk(@ApiParam("桌子id") @PathVariable("id") String id,
			@ApiParam("桌子信息") @RequestBody DinnerDesk dinnerDesk) {
		logger.info("Updating dinnerDesk " + id);

		if (dinnerDeskService.selectByPrimaryKey(id) == null) {
			logger.info("dinnerDesk with id " + id + " not found");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

		Subject subject = SecurityUtils.getSubject();
		dinnerDesk.setDinnerDeskId(id);
		dinnerDesk.setUpdateBy(subject.getPrincipal().toString());
		dinnerDesk.setUpdateDate(DateUtil.now());

		dinnerDeskService.updateByPrimaryKeySelective(dinnerDesk);

		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}

	// ------------------- Delete a dinnerDesk------------------- //
	@ApiOperation("通过ID删除桌子")
	@RequestMapping(value = "/dinnerDesk/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteDinnerDesk(@ApiParam("桌子id") @PathVariable("id") String id) {
		logger.info("Fetching & Deleting dinnerDesk with id " + id);

		if (dinnerDeskService.selectByPrimaryKey(id) == null) {
			logger.info("DinnerDesk with id " + id + " not found");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

		dinnerDeskService.deleteByPrimaryKey(id);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}

}
