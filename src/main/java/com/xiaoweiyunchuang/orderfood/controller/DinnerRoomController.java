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
import com.xiaoweiyunchuang.orderfood.domain.DinnerRoom;
import com.xiaoweiyunchuang.orderfood.service.DinnerRoomService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@Api(value = "餐厅服务", description = "提供RESTful风格API的餐厅的增删改查服务")
@RestController
public class DinnerRoomController extends AbstractController {
	@Autowired
	DinnerRoomService dinnerRoomService;

	// -------------------Retrieve All------------------- //
	@ApiOperation("查询餐厅")
	@RequestMapping(value = "/dinnerRoom", method = RequestMethod.GET)
	public ResponseEntity<List<DinnerRoom>> getDinnerRoom() {
		List<DinnerRoom> dinnerRooms = dinnerRoomService.selectDinnerRooms(null);
		if (dinnerRooms.isEmpty()) {
			return new ResponseEntity<List<DinnerRoom>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<DinnerRoom>>(dinnerRooms, HttpStatus.OK);
	}

	// -------------------Retrieve Single------------------- //
	@ApiOperation("通过id查询餐厅")
	@RequestMapping(value = "/dinnerRoom/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DinnerRoom> getDinnerRoom(@ApiParam("餐厅id") @PathVariable("id") String id) {
		logger.info("Fetching dinnerRoom with id " + id);
		DinnerRoom dinnerRoom = dinnerRoomService.selectByPrimaryKey(id);
		if (dinnerRoom == null) {
			logger.info("dinnerRoom with id " + id + " not found");
			return new ResponseEntity<DinnerRoom>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<DinnerRoom>(dinnerRoom, HttpStatus.OK);
	}

	

	// -------------------Retrieve Page------------------- //
	@ApiOperation("分页查询餐厅")
	@RequestMapping(value = "dinnerRoom/{partnerId}/{pageNum}/{pageSize}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PageInfo<DinnerRoom>> getDinnerRoom(@ApiParam("商户ID") @PathVariable String partnerId,
			@ApiParam("pageNum") @PathVariable Integer pageNum, @ApiParam("pageSize") @PathVariable Integer pageSize) {
		if (pageNum != null && pageSize != null) {
			PageHelper.startPage(pageNum, pageSize);
		}
		DinnerRoom dinnerRoom = new DinnerRoom();
		dinnerRoom.setPartnerId(partnerId);
		List<DinnerRoom> dinnerRooms = dinnerRoomService.selectDinnerRooms(dinnerRoom);
		return new ResponseEntity<PageInfo<DinnerRoom>>(new PageInfo<DinnerRoom>(dinnerRooms), HttpStatus.OK);
	}

	// -------------------Create a------------------- //
	@ApiOperation("添加餐厅")
	@RequestMapping(value = "/dinnerRoom", method = RequestMethod.POST)
	public ResponseEntity<Void> createDinnerRoom(@ApiParam("餐厅信息") @RequestBody DinnerRoom dinnerRoom,
			UriComponentsBuilder ucBuilder) {
		logger.info("Creating dinnerRoom " + dinnerRoom.getDinnerRoomName());

		if (dinnerRoomService.isDinnerRoomExist(dinnerRoom)) {
			logger.info("A DinnerRoom with name " + dinnerRoom.getDinnerRoomName() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		Subject subject = SecurityUtils.getSubject();
		dinnerRoom.setDinnerRoomId(IDGenerate.getId());
		dinnerRoom.setCreateBy(subject.getPrincipal().toString());
		dinnerRoom.setCreateDate(DateUtil.now());
		dinnerRoom.setUpdateBy(subject.getPrincipal().toString());
		dinnerRoom.setUpdateDate(DateUtil.now());
		dinnerRoom.setDelFlag("N");

		dinnerRoomService.insert(dinnerRoom);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}

	// ------------------- Update a dinnerRoom------------------- //
	@ApiOperation("更新餐厅")
	@RequestMapping(value = "/dinnerRoom/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateDinnerRoom(@ApiParam("餐厅id") @PathVariable("id") String id,
			@ApiParam("餐厅信息") @RequestBody DinnerRoom dinnerRoom) {
		logger.info("Updating dinnerRoom " + id);

		if (dinnerRoomService.selectByPrimaryKey(id) == null) {
			logger.info("dinnerRoom with id " + id + " not found");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

		Subject subject = SecurityUtils.getSubject();
		dinnerRoom.setDinnerRoomId(id);
		dinnerRoom.setUpdateBy(subject.getPrincipal().toString());
		dinnerRoom.setUpdateDate(DateUtil.now());

		dinnerRoomService.updateByPrimaryKeySelective(dinnerRoom);

		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}

	// ------------------- Delete a dinnerRoom------------------- //
	@ApiOperation("通过ID删除餐厅")
	@RequestMapping(value = "/dinnerRoom/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteDinnerRoom(@ApiParam("餐厅id") @PathVariable("id") String id) {
		logger.info("Fetching & Deleting dinnerRoom with id " + id);

		if (dinnerRoomService.selectByPrimaryKey(id) == null) {
			logger.info("DinnerRoom with id " + id + " not found");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

		dinnerRoomService.deleteByPrimaryKey(id);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}

}
